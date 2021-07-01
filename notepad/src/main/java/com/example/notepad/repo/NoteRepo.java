package com.example.notepad.repo;

import com.example.notepad.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepo extends JpaRepository<Note, Long> {
}
