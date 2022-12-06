package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
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
        if (facultyService.getFacultyInfo(id).isPresent()) {
            return ResponseEntity.ok(facultyService.getFacultyInfo(id).get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<Set<Student>> getStudents(@PathVariable Long id) {
        if (facultyService.getFacultyInfo(id).isPresent()) {
            return ResponseEntity.ok(facultyService.getFacultyInfo(id).get().getStudents());
        } else {
            return ResponseEntity.notFound().build();
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
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(processedFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> removeFaculty(@PathVariable Long id) {
        if (facultyService.removeFaculty(id).isPresent()) {
            return ResponseEntity.ok(facultyService.removeFaculty(id).get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    public Collection<Faculty> getFaculties() {
        return facultyService.getFaculties();
    }

    @GetMapping(value = "", params = {"color"})
    public Collection<Faculty> getFacultiesByColor(@RequestParam(value = "color") String Color) {
        return facultyService.getFaculties(Color);
    }

    @GetMapping(value = "", params = {"name_or_color"})
    public Collection<Faculty> getFacultiesByNameOrColor(@RequestParam(value = "name_or_color") String nameOrColor) {
            return facultyService.getFaculties(nameOrColor);
    }
}
