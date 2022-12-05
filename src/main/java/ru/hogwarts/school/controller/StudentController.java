package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(studentService.getStudentInfo(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/get_faculty")
    public ResponseEntity<Faculty> getStudentFaculty(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(studentService.getFacultyByStudentId(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("")
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("")
    public ResponseEntity<Student> editStudentInfo(@RequestBody Student student) {
        Student processedStudent = studentService.editStudentInfo(student);
        if (processedStudent == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(processedStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> removeStudent(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(studentService.removeStudent(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public Collection<Student> getStudentsByAge(@RequestParam(required = false) Integer age,
                                                @RequestParam(required = false) Integer minAge,
                                                @RequestParam(required = false) Integer maxAge) {
        if (age != null) {
            return studentService.getStudents(age);
        } else if (minAge != null || maxAge != null) {
            return studentService.getStudents(minAge, maxAge);
        } else {
            return studentService.getStudents();
        }
    }
}
