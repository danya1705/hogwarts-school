package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        student.setId(null);
        return studentRepository.save(student);
    }

    public Optional<Student> getStudentInfo(long id) {
        return studentRepository.findById(id);
    }

    public Student editStudentInfo(Student student) {
        if (studentRepository.existsById(student.getId())) {
            return studentRepository.save(student);
        }
        return null;
    }

    public Optional<Student> removeStudent(long id) {
        Optional<Student> deletedStudent = studentRepository.findById(id);
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
        return studentRepository.findByAgeBetween(minAge,maxAge);
    }

}
