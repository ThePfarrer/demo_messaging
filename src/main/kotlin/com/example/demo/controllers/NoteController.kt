package com.example.demo.controllers

import com.example.demo.models.Note
import com.example.demo.repository.NoteRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/notes")
class NoteController(var noteRepository: NoteRepository) {

//    init {
//        noteRepository.deleteAll()
//        noteRepository.save(Note("fas1", "First Note", "This is a sample note"))
//    }

    @GetMapping
    fun getNotes() = noteRepository.findAll()


    @PostMapping
    fun createNote(@RequestBody note: Note) {
        noteRepository.save(note)
    }

    @GetMapping("/{id}")
    fun getNote(@PathVariable id: String) = noteRepository.findById(id)

    @PutMapping("/{id}")
    fun updateNote(@PathVariable id: String, @RequestBody note: Note): ResponseEntity<Note> {
        return if (noteRepository.existsById(id)) {
            ResponseEntity(noteRepository.save(note), HttpStatus.OK)
        } else {
            ResponseEntity(noteRepository.save(note), HttpStatus.CREATED)

        }

    }

    @DeleteMapping("/{id}")
    fun deleteNote(@PathVariable id: String) = noteRepository.deleteById(id)


}