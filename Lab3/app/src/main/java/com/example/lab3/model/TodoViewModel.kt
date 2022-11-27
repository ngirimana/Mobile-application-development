package com.example.lab3.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class TodoViewModel: ViewModel()
{
    var todoList= mutableStateListOf<Todo>()

    fun add(newTodo: Todo)
    {
        todoList.add(newTodo )
    }

    fun deleteTodo(anyTodo: Todo)
    {
        todoList.remove(anyTodo)
    }
}