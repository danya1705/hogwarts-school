package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.Set;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Set<Faculty> findByNameIgnoreCase(String name);

    Set<Faculty> findByColorIgnoreCase(String color);
}
