package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(facultyService.getFacultyInfo(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/get_students")
    public ResponseEntity<Set<Student>> getStudents(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(facultyService.getStudents(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("")
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @PutMapping("")
    public ResponseEntity<Faculty> editFacultyInfo(@RequestBody Faculty faculty) {
        Faculty processedFaculty = facultyService.editFacultyInfo(faculty);
        if (processedFaculty == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(processedFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> removeFaculty(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(facultyService.removeFaculty(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public Collection<Faculty> getFacultiesByNameOrColor(@RequestParam(required = false) String name, @RequestParam(required = false) String color) {
        if (color == null && name == null) {
            return facultyService.getFaculties();
        } else {
            return facultyService.getFaculties(name, color);
        }
    }
}
