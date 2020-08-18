package com.example.NoteBook.controller;

import com.example.NoteBook.domain.Cousers;
import com.example.NoteBook.domain.Note;
import com.example.NoteBook.domain.User;
import com.example.NoteBook.repos.CousersRepo;
import com.example.NoteBook.repos.NotesRepo;
import com.example.NoteBook.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author Denis Karandashev
 * @project NoteBook
 * @date 21.05.2020
 */

@Controller
public class MainController {
    @Autowired
    private NotesRepo notesRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CousersRepo cousersRepo;



    @GetMapping("/")
    public String noteBook(Map<String, Object> model) {
        return "noteBook";
    }

    @GetMapping("/login")
    public String index(Model model) {
        return "login";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Note> notes = notesRepo.findAll();
        List<User> users = userRepo.findAll();
        model.put("notes", notes);
        model.put("users", users);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String title,
            Model model
    ) {
        Note note = new Note(text, title);
        note.setAuthor(user);
        note.setRedaction(1);
        note.setAuthorName(note.getAuthor().getUsername());
        note.setCoauthor(user.getCoauthor());
        List<User> users = userRepo.findAll();
        for (User user1 : users) {
            if (user1.getUsername().equals(note.getCoauthor())) {
                note.setCoauthorId(user1.getId());
            }
        }
        notesRepo.save(note);
        note.setNoteGroup(note.getId());
        notesRepo.save(note);
//        Cousers cousers = new Cousers(user);
//        cousersRepo.save(cousers);

        Iterable<Note> notes = notesRepo.findAll();
        model.addAttribute("notes", notes);
        return "main";
    }

//    @PostMapping("filter")
//    public String filter(@RequestParam String filter, Map<String, Object> model) {
//        Iterable<Note> notes;
//
//        if (filter != null && !filter.isEmpty()) {
//            notes = notesRepo.findByTitle(filter);
//        } else {
//            notes = notesRepo.findAll();
//        }
//
//        model.put("notes", notes);
//
//        return "main";
//    }

    @Transactional
    @GetMapping("/delete/{note}")
    public String deleteNote(@RequestParam Note note) {
        Integer id = note.getId();
        String title = note.getTitle();
        Integer noteGroupId = note.getNoteGroup();
        if (note.getPreviousId() == 0) {
            notesRepo.deleteNoteByNoteGroup(noteGroupId);
//            cousersRepo.deleteAllByNoteId(id);
        } else {
            notesRepo.deleteNoteByNoteGroup(noteGroupId);
//            cousersRepo.deleteAllByNoteId(id);
        }
        return "redirect:/main";
    }

    @GetMapping("/user-notes/{user}")
    public String userNotes(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model,
            @RequestParam(required = false) Note note
    ) {
        Set<Note> notes = user.getNotes();
        List<User> users = userRepo.findAll();

        model.addAttribute("users", users);
        model.addAttribute("notes", notes);
        model.addAttribute("note", note);
        model.addAttribute("isCurrentUser", currentUser.equals(user));

        return "userNotes";
    }

    //    список редакций заметки. странное название, т.к. с другими возникали ошибки
    @GetMapping("/ed-not/{user}")
    public String edNot(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model,
            @RequestParam(required = false) Note note
    ) {
        List<Note> notes = notesRepo.findAllByNoteGroup(note.getNoteGroup());

        model.addAttribute("notes", notes);
        model.addAttribute("note", note);
        model.addAttribute("isCurrentUser", currentUser.equals(user));
        return "edNot";
    }

    @Transactional
    @PostMapping("/user-notes/{user}")
    public String updateNote(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            @RequestParam Note note,
            @RequestParam(required = false) String coauthor,
            @RequestParam("text") String text,
            @RequestParam("title") String title,
            Model model) {

        Note updatedNote = new Note(text, title);
        Date currentTime = new GregorianCalendar().getTime();

        if (note.getPreviousId() == 0) {
            updatedNote.setNoteGroup(note.getId());
            note.setNoteGroup(note.getId());
        } else {
            updatedNote.setNoteGroup(note.getNoteGroup());
        }

        //указываем автора старой редакции, если его по какой-то причине нет
        if (note.getAuthor() == null) {
            note.setAuthor(user);
        }

        //указываем автора новой редакции
        updatedNote.setAuthor(note.getAuthor());

        updatedNote.setAuthorName(note.getAuthor().getUsername());

        //указываем соавтора
        if (user.getCoauthor().equals("0") || user.getCoauthor().equals("") || user.getCoauthor().equals(" ")) {
            updatedNote.setCoauthor("0");
        } else if (user.getCoauthor().equals(note.getCoauthor())) {
            updatedNote.setCoauthor(user.getCoauthor());
            setCoId(user, model);
            updatedNote.setCoauthorId(note.getCoauthorId());
        } else if (note.getCoauthor().equals(user.getUsername())) {
            updatedNote.setCoauthor(note.getCoauthor());
            setCoId(user, model);
            updatedNote.setCoauthorId(note.getCoauthorId());
        }

        updatedNote.setRedaction(note.getRedaction() + 1);
        updatedNote.setDate(note.getDate());
        updatedNote.setLastEditDate(currentTime);
        updatedNote.setPreviousId(note.getId());
        notesRepo.save(updatedNote);

        note.setVisible(false);
        notesRepo.save(note);
        return "redirect:/main";
    }

    public void setCoId(User user, Model model) {
        Iterable<Note> coauthors = notesRepo.findAllByCoauthor(user.getCoauthor());
        List<User> users = userRepo.findAll();
        for (Note n : coauthors) {
            for (User u : users) {
                if (n.getCoauthor().equals(u.getUsername())) {
                    n.setCoauthorId(u.getId());
                    model.addAttribute("users", users);
                }
            }
        }
    }
}
