package com.example.demo.controllers

import com.example.demo.models.Todo
import com.example.demo.repository.TodoRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todos")
class TodoController(val todoRepository: TodoRepository) {

    @GetMapping
    fun getTodos() = todoRepository.findAll()


    @PostMapping
    fun createTodo(@RequestBody todo: Todo) {
        todoRepository.save(todo)
    }

    @GetMapping("/{id}")
    fun getTodo(@PathVariable id: String) = todoRepository.findById(id)

    @PutMapping("/{id}")
    fun updateTodo(@PathVariable id: String, @RequestBody todo: Todo): ResponseEntity<Todo> {
        return if (todoRepository.existsById(id)) {
            ResponseEntity(todoRepository.save(todo), HttpStatus.OK)
        } else {
            ResponseEntity(todoRepository.save(todo), HttpStatus.CREATED)

        }

    }

    @DeleteMapping("/{id}")
    fun deleteTodo(@PathVariable id: String) = todoRepository.deleteById(id)
}