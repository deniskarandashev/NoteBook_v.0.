package com.example.NoteBook.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.*;

/**
 * @author Denis Karandashev
 * @project NoteBook
 * @date 22.05.2020
 */

@Entity
@XmlRootElement
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String text;
    private String title;
    private Date date;
    private Date lastEditDate;
    private boolean isVisible;
    private boolean isRestored;
    private int previousId;
    private int noteGroup;
    private int redaction;

//    private Cousers cousers;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String authorName;

    private String coauthor;
    private Long coauthorId;

    public Note() {
    }

    public Note(String text, String title) {
        this.text = text;
        this.title = title;
        this.date = new GregorianCalendar().getTime();
        this.lastEditDate = this.date;
        this.isVisible = true;
        this.isRestored = false;
        this.previousId = 0;
    }

    public User getAuthor() {
        return author;
    }

//    @XmlElement(name = "user_id")
    public void setAuthor(User author) {
        this.author = author;
    }

    @XmlElement(name = "text")
    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Integer getId() {
        return id;
    }

    @XmlAttribute(name = "id")
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    @XmlElement(name = "title")
    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    @XmlElement(name = "date")
    public void setDate(Date date) {
        this.date = date;
    }

    public Date getLastEditDate() {
        return lastEditDate;
    }

    @XmlElement(name = "last_edit_date")
    public void setLastEditDate(Date lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    public boolean isVisible() {
        return isVisible;
    }

    @XmlElement(name = "is_visible")
    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public int getPreviousId() {
        return previousId;
    }

    @XmlElement(name = "previous_id")
    public void setPreviousId(int previousId) {
        this.previousId = previousId;
    }

    public int getNoteGroup() {
        return noteGroup;
    }

    @XmlElement(name = "note_group")
    public void setNoteGroup(int noteGroup) {
        this.noteGroup = noteGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return noteGroup == note.noteGroup;
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteGroup);
    }

    public int getRedaction() {
        return redaction;
    }

    @XmlElement(name = "redaction")
    public void setRedaction(int redaction) {
        this.redaction = redaction;
    }

    public String getCoauthor() {
        return coauthor;
    }

    @XmlElement(name = "coauthor")
    public void setCoauthor(String coauthor) {
        this.coauthor = coauthor;
    }

    public Long getCoauthorId() {
        return coauthorId;
    }

//    @XmlElement(name = "coauthor_id")
    public void setCoauthorId(Long coauthorId) {
        this.coauthorId = coauthorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    @XmlElement(name = "author")
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public boolean isRestored() {
        return isRestored;
    }

    public void setRestored(boolean restored) {
        isRestored = restored;
    }
}

