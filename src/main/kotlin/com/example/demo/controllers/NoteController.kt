package com.example.demo.controllers

import com.example.demo.dto.NoteDTO
import com.example.demo.event.CurrencyConversionEvent
import com.example.demo.models.Note
import com.example.demo.repository.NoteRepository
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/notes")
class NoteController(var noteRepository: NoteRepository) {

//    init {
//        noteRepository.deleteAll()
//        noteRepository.save(Note("fas1", "First Note", "This is a sample note"))
//    }

    val log = LoggerFactory.getLogger(CurrencyConversionEvent::class.java)

    @GetMapping
//    fun getNotes() = noteRepository.findAll()
    fun getNotes() = noteRepository.findAll()


    @PostMapping
    fun createNote(@RequestBody note: Note) {
        noteRepository.save(note)
        log.info("$note created at ${Date()}")
    }
//    @PostMapping
//    fun createNote(@RequestBody note: NoteDTO) {
//        NoteDTO(noteRepository.save(Note(title = note.title, body = note.body)))
//        log.info("$note created at ${Date()}")
//    }

    @GetMapping("/{id}")
    fun getNote(@PathVariable id: String) = noteRepository.findById(id)

    @PutMapping("/{id}")
    fun updateNote(@PathVariable id: String, @RequestBody note: NoteDTO): ResponseEntity<NoteDTO> {
        return if (noteRepository.existsById(id)) {
            ResponseEntity(
                NoteDTO(
                    noteRepository.save(
                        Note(
                            id = note.id,
                            title = note.title,
                            body = note.body,
                            created = note.created,
                            modified = Date()
                        )
                    )
                ), HttpStatus.OK
            )
        } else {
            ResponseEntity(NoteDTO(noteRepository.save(Note(title = note.title, body = note.body))), HttpStatus.CREATED)

        }

    }

    @DeleteMapping("/{id}")
    fun deleteNote(@PathVariable id: String) = noteRepository.deleteById(id)


}