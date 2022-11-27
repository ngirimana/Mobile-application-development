package com.example.lab3.model

data class Todo(val todoId: String, val title:String,val description: String, val isCompleted:Boolean)
{
    override fun toString(): String {
        return "Todo(todoId='$todoId', title='$title', description='$description', isCompleted=$isCompleted)"
    }



}