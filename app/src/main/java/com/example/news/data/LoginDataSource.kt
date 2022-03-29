package com.example.news.data

import com.example.news.data.model.LoggedInUser






/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        val user = LoggedInUser(username, "")
        return Result.Success(user)
    }

    fun logout() {
        // TODO: revoke authentication
    }
}