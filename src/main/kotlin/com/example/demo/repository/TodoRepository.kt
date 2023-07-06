package com.example.demo.repository

import com.example.demo.models.Todo
import org.springframework.data.repository.CrudRepository

interface TodoRepository: CrudRepository<Todo, String> {
}