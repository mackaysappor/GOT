package com.example.got.models

data class Books (
    val url: String,
    val name: String,
    val isbn: String,
    val authors: ArrayList<String>,
    val numberOfPages: String,
    val publisher: String,
    val country: String,
    val mediaType: String,
    val released: String,
    val characters: ArrayList<String>,
    val povCharacters: ArrayList<String>

)