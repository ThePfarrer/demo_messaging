package com.example.demo.dto

import com.example.demo.models.Note
import java.util.*

data class NoteDTO(var title: String, var body: String, var location: String = "") {
    var id: String = ""
    var created: Date = Date()
    var modified: Date = Date()

    constructor(note: Note) : this( note.title, note.body) {
        id = note.id
        title = note.title
        body = note.body
        created = note.created
        modified = note.modified
    }
}
