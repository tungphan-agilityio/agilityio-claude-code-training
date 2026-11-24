package com.dojo.tasks.ui

import com.dojo.tasks.data.InMemoryUserRepository
import com.dojo.tasks.data.User
import com.dojo.tasks.domain.EmailError
import com.dojo.tasks.domain.PasswordError
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class AuthViewModelTest {

    private val repo = InMemoryUserRepository()
    private val vm = AuthViewModel(repo)

    @Test
    fun `starts signed out on the log in screen`() {
        assertNull(vm.currentUser.value)
        assertEquals(AuthScreen.Login, vm.screen.value)
    }

    @Test
    fun `back from sign up returns to log in, and back on log in is not handled`() {
        vm.openSignUp()
        assertEquals(AuthScreen.SignUp, vm.screen.value)
        assertTrue(vm.back())
        assertEquals(AuthScreen.Login, vm.screen.value)
        assertFalse(vm.back())
    }

    @Test
    fun `open log in from sign up shows log in`() {
        vm.openSignUp()
        vm.openLogin()
        assertEquals(AuthScreen.Login, vm.screen.value)
    }

    @Test
    fun `valid sign up signs the user in`() {
        vm.openSignUp()
        assertTrue(vm.signUp("Nova@Dojo.dev", "password1"))
        assertEquals(User("nova@dojo.dev"), vm.currentUser.value)
    }

    @Test
    fun `sign up with blank email shows required and stays signed out`() {
        vm.openSignUp()
        assertFalse(vm.signUp("", "password1"))
        assertEquals(EmailError.REQUIRED, vm.errors.value.email)
        assertNull(vm.currentUser.value)
    }

    @Test
    fun `sign up with malformed email shows invalid and stays signed out`() {
        vm.openSignUp()
        assertFalse(vm.signUp("nova@dojo", "password1"))
        assertEquals(EmailError.INVALID, vm.errors.value.email)
        assertNull(vm.currentUser.value)
    }

    @Test
    fun `sign up with short password shows too short and stays signed out`() {
        vm.openSignUp()
        assertFalse(vm.signUp("nova@dojo.dev", "1234567"))
        assertEquals(PasswordError.TOO_SHORT, vm.errors.value.password)
        assertNull(vm.currentUser.value)
    }

    @Test
    fun `sign up with an already registered email shows taken and stays signed out`() {
        repo.register("nova@dojo.dev", "password1")
        vm.openSignUp()
        assertFalse(vm.signUp(" NOVA@dojo.dev ", "password2"))
        assertEquals(EmailError.TAKEN, vm.errors.value.email)
        assertNull(vm.currentUser.value)
    }

    @Test
    fun `invalid sign up does not create an account`() {
        vm.openSignUp()
        vm.signUp("nova@dojo.dev", "short")
        assertNull(repo.authenticate("nova@dojo.dev", "short"))
    }

    @Test
    fun `log in with correct details signs the user in`() {
        repo.register("nova@dojo.dev", "password1")
        assertTrue(vm.logIn("nova@dojo.dev", "password1"))
        assertEquals(User("nova@dojo.dev"), vm.currentUser.value)
    }

    @Test
    fun `log in with a wrong password shows bad credentials and stays signed out`() {
        repo.register("nova@dojo.dev", "password1")
        assertFalse(vm.logIn("nova@dojo.dev", "password2"))
        assertTrue(vm.errors.value.badCredentials)
        assertNull(vm.currentUser.value)
    }

    @Test
    fun `log in with an unknown email shows bad credentials`() {
        assertFalse(vm.logIn("ghost@dojo.dev", "password1"))
        assertTrue(vm.errors.value.badCredentials)
    }

    @Test
    fun `log in with blank fields shows bad credentials`() {
        assertFalse(vm.logIn("", ""))
        assertTrue(vm.errors.value.badCredentials)
    }

    @Test
    fun `log out signs the user out and returns to log in`() {
        vm.openSignUp()
        vm.signUp("nova@dojo.dev", "password1")
        vm.logOut()
        assertNull(vm.currentUser.value)
        assertEquals(AuthScreen.Login, vm.screen.value)
    }

    @Test
    fun `switching screens clears errors`() {
        vm.logIn("ghost@dojo.dev", "password1")
        vm.openSignUp()
        assertEquals(AuthErrors(), vm.errors.value)
    }
}
