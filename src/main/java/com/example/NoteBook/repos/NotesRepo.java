package com.example.NoteBook.repos;

import com.example.NoteBook.domain.Note;
import com.example.NoteBook.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Denis Karandashev
 * @project NoteBook
 * @date 22.05.2020
 */

public interface NotesRepo extends CrudRepository<Note, Integer> {
    List<Note> deleteNoteByNoteGroup(int ng);
    List<Note> findAllByNoteGroup(int ng);
    List<Note> findAllByAuthor(User user);
    List<Note> findAllByCoauthor(String coauthor);
 }
