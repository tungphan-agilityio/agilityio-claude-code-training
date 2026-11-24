package com.dojo.tasks.ui

import androidx.lifecycle.ViewModel
import com.dojo.tasks.data.User
import com.dojo.tasks.data.UserRepository
import com.dojo.tasks.domain.AuthRules
import com.dojo.tasks.domain.EmailError
import com.dojo.tasks.domain.PasswordError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/** Which signed-out screen is showing. Kept in the ViewModel so navigation is unit-testable. */
sealed interface AuthScreen {
    data object Login : AuthScreen
    data object SignUp : AuthScreen
}

/** Errors for the log in / sign up form. Null or false means "no error". */
data class AuthErrors(
    val email: EmailError? = null,
    val password: PasswordError? = null,
    /** Log in only: the email or password was wrong. Deliberately does not say which. */
    val badCredentials: Boolean = false,
) {
    val hasErrors get() = email != null || password != null || badCredentials
}

/** Holds who is signed in (null when nobody is) and drives the log in / sign up screens. */
class AuthViewModel(private val users: UserRepository) : ViewModel() {

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    private val _screen = MutableStateFlow<AuthScreen>(AuthScreen.Login)
    val screen: StateFlow<AuthScreen> = _screen.asStateFlow()

    private val _errors = MutableStateFlow(AuthErrors())
    val errors: StateFlow<AuthErrors> = _errors.asStateFlow()

    fun openSignUp() = navigate(AuthScreen.SignUp)

    fun openLogin() = navigate(AuthScreen.Login)

    /** Handles system back. Returns false on log in, so the activity can close as usual. */
    fun back(): Boolean {
        if (_screen.value == AuthScreen.Login) return false
        navigate(AuthScreen.Login)
        return true
    }

    /** Validates, creates the account and signs the user in. Returns false when the input is rejected. */
    fun signUp(email: String, password: String): Boolean {
        val errors = AuthErrors(email = AuthRules.validateEmail(email), password = AuthRules.validatePassword(password))
        _errors.value = errors
        if (errors.hasErrors) return false

        val user = users.register(email, password)
        if (user == null) {
            _errors.value = AuthErrors(email = EmailError.TAKEN)
            return false
        }
        _currentUser.value = user
        return true
    }

    /** Signs the user in when the details match an account. Returns false otherwise. */
    fun logIn(email: String, password: String): Boolean {
        val user = users.authenticate(email, password)
        _errors.value = AuthErrors(badCredentials = user == null)
        _currentUser.value = user
        return user != null
    }

    fun logOut() {
        _currentUser.value = null
        navigate(AuthScreen.Login)
    }

    private fun navigate(target: AuthScreen) {
        _errors.value = AuthErrors()
        _screen.value = target
    }
}
