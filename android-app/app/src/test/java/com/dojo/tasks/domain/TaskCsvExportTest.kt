package com.dojo.tasks.domain

import com.dojo.tasks.data.Task
import org.junit.Assert.assertTrue
import org.junit.Test

class TaskCsvExportTest {

    @Test
    fun `csv starts with header and contains the task title`() {
        val csv = TaskCsvExport.toCsv(listOf(Task(1, "Buy milk")))
        assertTrue(csv.startsWith("title,notes,due,done"))
        assertTrue(csv.contains("Buy milk"))
    }
}
