package com.jodelapp.data.models

data class User(val id: String, val name: String, val username: String, val email: String) {

    companion object {
        val NONE = User("", "", "", "")
    }
}