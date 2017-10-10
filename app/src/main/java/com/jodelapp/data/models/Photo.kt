package com.jodelapp.data.models

data class Photo(val albumId: String, val id: String, val title: String,
                 val url: String, val thumbnailUrl: String) {

    companion object {
        val EMPTY = Photo("", "", "", "", "")
    }
}

