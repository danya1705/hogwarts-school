package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        student.setId(0L);
        return studentRepository.save(student);
    }

    public Student getStudentInfo(long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    public Student editStudentInfo(Student student) {
        if (studentRepository.existsById(student.getId())) {
            return studentRepository.save(student);
        }
        return null;
    }

    public Student removeStudent(long id) {
        Student deletedStudent = studentRepository.findById(id).orElseThrow();
        studentRepository.deleteById(id);
        return deletedStudent;
    }

    public Collection<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Collection<Student> getStudents(int age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> getStudents(Integer minAge, Integer maxAge) {

        if (minAge == null) minAge = Integer.MIN_VALUE;
        if (maxAge == null) maxAge = Integer.MAX_VALUE;

        return studentRepository.findByAgeBetween(minAge,maxAge);
    }

    public Faculty getFacultyByStudentId(Long id) {
        return studentRepository.findById(id).orElseThrow().getFaculty();
    }
}
