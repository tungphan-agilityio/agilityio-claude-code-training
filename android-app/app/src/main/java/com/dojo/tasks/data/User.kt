package com.dojo.tasks.data

/** A signed-up user. [email] is always normalized (trimmed, lowercase). Holds no password material. */
data class User(val email: String)
