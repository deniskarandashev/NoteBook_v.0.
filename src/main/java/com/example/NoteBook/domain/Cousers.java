package com.example.NoteBook.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Denis Karandashev
 * @project NoteBook_v.0.
 * @date 05.06.2020
 */

@Entity
public class Cousers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer noteId;
    private User author;
    private User coauthor;
    private User coreader;

    public Cousers() {
    }

    public Cousers(User author) {
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getCoauthor() {
        return coauthor;
    }

    public void setCoauthor(User coauthor) {
        this.coauthor = coauthor;
    }

    public User getCoreader() {
        return coreader;
    }

    public void setCoreader(User coreader) {
        this.coreader = coreader;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

}
