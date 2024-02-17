package ru.ardyc.model

data class Repository(
    val name: String,
    val description: String,
    val starsCount: Int,
    val issuesCount: Int,
    val watchersCount: Int,
    val language: String,
    val forksCount: Int,
)
