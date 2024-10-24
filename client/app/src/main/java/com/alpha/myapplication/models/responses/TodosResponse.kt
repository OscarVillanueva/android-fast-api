package com.alpha.myapplication.models.responses

data class TodosResponse(
    val id: Int,
    val todo: String,
    val is_completed: Boolean,
    val belong_to: Int
)
