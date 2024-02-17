package ru.ardyc.model.dto

data class GenerationStrategy(
    val backgroundColor: String = "blue",
    val isStrokeEnabled: Boolean = false,
    val opacity: Double = 0.2,
    val textColor: String = "black"
)