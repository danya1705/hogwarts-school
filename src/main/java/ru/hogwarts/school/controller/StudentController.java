package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        if (studentService.getStudentInfo(id).isPresent()) {
            return ResponseEntity.ok(studentService.getStudentInfo(id).get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}/faculty")
    public ResponseEntity<Faculty> getStudentFaculty(@PathVariable Long id) {
        if (studentService.getStudentInfo(id).isPresent()) {
            return ResponseEntity.ok(studentService.getStudentInfo(id).get().getFaculty());
        } else {
            return ResponseEntity.notFound().build();
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
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(processedStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> removeStudent(@PathVariable Long id) {
        if (studentService.removeStudent(id).isPresent()) {
            return ResponseEntity.ok(studentService.removeStudent(id).get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    public Collection<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping(value = "", params = {"age"})
    public Collection<Student> getStudentsByAge(@RequestParam(value = "age") Integer age) {
        return studentService.getStudents(age);
    }

    @GetMapping(value = "", params = {"minAge", "maxAge"})
    public Collection<Student> getStudentsByAgeBetween(@RequestParam(value = "minAge") Integer minAge, @RequestParam(value = "maxAge") Integer maxAge) {
        return studentService.getStudents(minAge, maxAge);
    }
}
