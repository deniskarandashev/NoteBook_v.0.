package com.example.NoteBook.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Denis Karandashev
 * @project NoteBook_v.0.
 * @date 05.06.2020
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cousers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer noteId;
    private User author;
    private User coauthor;
    private User coreader;
}
