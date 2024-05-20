package com.example.module15hw171.service;

import com.example.module15hw171.entity.Note;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Slf4j
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteDBService noteDBService;
    @Override
    public List<Note> listAll() {
        return noteDBService.findAll();
    }
    @Override
    public Note add(Note note) {
        return noteDBService.save(note);
    }

    @Override
    public void deleteById(UUID id) {
        noteDBService.deleteById(id);
    }

    @Override
    public void update(Note note) {
        if (Objects.isNull(note.getId())) {
            throw new NoSuchElementException();
        }
        getById(note.getId());
        noteDBService.save(note);
    }

    @Override
    public Note getById(UUID id) {
        Optional<Note> optionalNote = noteDBService.getById(id);
        if (optionalNote.isPresent()) {
            return optionalNote.get();
        } else {
            throw new NoSuchElementException();
        }
    }

}
