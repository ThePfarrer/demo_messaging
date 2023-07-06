package com.example.demo.repository

import com.example.demo.models.Note
import org.springframework.data.repository.CrudRepository

interface NoteRepository : CrudRepository<Note, String> {
}