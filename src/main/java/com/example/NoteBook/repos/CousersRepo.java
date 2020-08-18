package com.example.NoteBook.repos;

import com.example.NoteBook.domain.Cousers;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Denis Karandashev
 * @project NoteBook_v.0.
 * @date 05.06.2020
 */
public interface CousersRepo extends CrudRepository<Cousers, Integer> {
    Optional<Cousers> findById(Integer id);
    Iterable<Cousers> findAll();
}
