package ru.netology.nmedia

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likedByMe: Boolean = false,
    var likesSum: Long,
    var sharedSum: Long,
    var viewSum: Long
)
