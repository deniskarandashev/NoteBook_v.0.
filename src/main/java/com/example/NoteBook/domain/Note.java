package com.example.NoteBook.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Denis Karandashev
 * @project NoteBook
 * @date 22.05.2020
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;
    private String authorName;
    private String coauthor;
    private Long coauthorId;

    public Note(String text, String title) {
        this.text = text;
        this.title = title;
        this.date = new GregorianCalendar().getTime();
        this.lastEditDate = this.date;
        this.isVisible = true;
        this.isRestored = false;
        this.previousId = 0;
    }

    @XmlElement(name = "text")
    public void setText(String text) {
        this.text = text;
    }

    @XmlAttribute(name = "id")
    public void setId(Integer id) {
        this.id = id;
    }

    @XmlElement(name = "title")
    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(name = "date")
    public void setDate(Date date) {
        this.date = date;
    }

    @XmlElement(name = "last_edit_date")
    public void setLastEditDate(Date lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    @XmlElement(name = "is_visible")
    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    @XmlElement(name = "previous_id")
    public void setPreviousId(int previousId) {
        this.previousId = previousId;
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

    @XmlElement(name = "redaction")
    public void setRedaction(int redaction) {
        this.redaction = redaction;
    }

    @XmlElement(name = "coauthor")
    public void setCoauthor(String coauthor) {
        this.coauthor = coauthor;
    }

    @XmlElement(name = "author")
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}

