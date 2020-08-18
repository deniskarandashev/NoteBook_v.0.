package com.example.NoteBook.repos;

import com.example.NoteBook.domain.Cousers;
import com.example.NoteBook.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author Denis Karandashev
 * @project NoteBook_v.0.
 * @date 05.06.2020
 */
public interface CousersRepo extends CrudRepository<Cousers, Integer> {
    Optional<Cousers> findById(Integer id);
    Optional<Cousers> findAllByAuthor(User user);
    Iterable<Cousers> findAll();
    Optional<Cousers> findByNoteId(Integer id);
    Optional<Cousers> deleteAllByNoteId(Integer id);
    Optional<Cousers> deleteAllByAuthor(User user);


}
