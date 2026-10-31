package com.dojo.tasks.ui

import com.dojo.tasks.data.InMemoryTaskRepository
import com.dojo.tasks.data.Task
import com.dojo.tasks.domain.TitleError
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate

class TasksViewModelTest {

    private val repo = InMemoryTaskRepository(listOf(Task(1, "Existing", dueDate = LocalDate.of(2026, 11, 5))))
    private val vm = TasksViewModel(repo)

    @Test
    fun `adding a task saves it and opens its detail`() {
        vm.openNewTask()
        assertTrue(vm.saveForm("Write tests", "", "2026-11-10"))
        val added = repo.tasks.value.single { it.title == "Write tests" }
        assertEquals(LocalDate.of(2026, 11, 10), added.dueDate)
        assertEquals(Screen.Detail(added.id), vm.screen.value)
    }

    @Test
    fun `empty title shows an error and saves nothing`() {
        vm.openNewTask()
        assertFalse(vm.saveForm("", "notes", ""))
        assertEquals(TitleError.REQUIRED, vm.formErrors.value.title)
        assertEquals(1, repo.tasks.value.size)
    }

    @Test
    fun `bad date shows an error`() {
        vm.openNewTask()
        assertFalse(vm.saveForm("Title", "", "tomorrow"))
        assertTrue(vm.formErrors.value.badDate)
    }

    @Test
    fun `toggle done flips the flag`() {
        vm.toggleDone(1)
        assertTrue(repo.get(1)!!.done)
    }

    @Test
    fun `delete removes the task and returns to the list`() {
        vm.openDetail(1)
        vm.delete(1)
        assertTrue(repo.tasks.value.isEmpty())
        assertEquals(Screen.List, vm.screen.value)
    }

    @Test
    fun `back from edit form returns to detail, then to list`() {
        vm.openEdit(1)
        assertTrue(vm.back())
        assertEquals(Screen.Detail(1), vm.screen.value)
        assertTrue(vm.back())
        assertEquals(Screen.List, vm.screen.value)
        assertFalse(vm.back())
    }
}
