package com.example.notepad.controller;

import com.example.notepad.domain.Note;
import com.example.notepad.domain.Views;
import com.example.notepad.repo.NoteRepo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("note")
public class NoteController {
    private final NoteRepo noteRepo;

    @Autowired
    public NoteController(NoteRepo noteRepo) {
        this.noteRepo = noteRepo;
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Note> getList(){
        return noteRepo.findAll();
    }

    @GetMapping("{id}")
    public Note getNote(@PathVariable("id") Note note){
        return note;
    }

    @PostMapping
    public Note create(@RequestBody Note note){
        note.setCreationDate(LocalDateTime.now());
        return noteRepo.save(note);
    }

    @PutMapping("{id}")
    public Note update(
            @PathVariable("id") Note noteFromDb,
            @RequestBody Note note
    ){
        BeanUtils.copyProperties(note, noteFromDb, "id");
        return noteRepo.save(noteFromDb);
    }

    @DeleteMapping("{id}")
    public void delete (@PathVariable("id") Note note){
        noteRepo.delete(note);
    }

}
