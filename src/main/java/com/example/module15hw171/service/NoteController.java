package com.example.module15hw171.service;

import ch.qos.logback.core.model.Model;
import com.example.module15hw171.entity.Note;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/notes")
public class NoteController {

    private final NoteServiceImpl noteService;

    @PostConstruct
    public void init() {
        System.out.println("Start");
    }

    @GetMapping("/list")
    public ModelAndView getAllNotes() {
        ModelAndView result = new ModelAndView("notes");
        result.addObject("notes", noteService.listAll());
        return result;
    }

    @PostMapping("/create")
    public String createNote(@RequestParam("title") String title, @RequestParam("content") String content) {
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        noteService.add(note);
        return "redirect:/notes/list";
    }

    @PostMapping("/save")
    public String saveNote(@RequestParam("id") UUID id, @RequestParam("title") String title, @RequestParam("content") String content) {
        Note note = new Note();
        note.setId(id);
        note.setTitle(title);
        note.setContent(content);
        noteService.update(note);
        return "redirect:/notes/list";
    }

    @GetMapping("/edit")
    public ModelAndView getNoteForEdit(@RequestParam("id") UUID id){
        ModelAndView result = new ModelAndView("edit_note");
        result.addObject("note",noteService.getById(id));
        return result;

    }

    @PostMapping("/update")
    public String editNote(@RequestParam("id") UUID id, @RequestParam("title") String title, @RequestParam("content") String content){
        Note note = new Note();
        note.setId(id);
        note.setTitle(title);
        note.setContent(content);
        noteService.update(note);
        return "redirect:/notes/list";
   }

    @PostMapping("/delete")
    public String deleteNote(@RequestParam("id") UUID id) {
        noteService.deleteById(id);
        return "redirect:/notes/list";
    }

}
