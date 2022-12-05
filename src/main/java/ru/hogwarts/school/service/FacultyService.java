package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.Set;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        faculty.setId(0L);
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyInfo(long id) {
        return facultyRepository.findById(id).orElseThrow();
    }

    public Faculty editFacultyInfo(Faculty faculty) {
        if (facultyRepository.existsById(faculty.getId())) {
            return facultyRepository.save(faculty);
        }
        return null;
    }

    public Faculty removeFaculty(long id) {
        Faculty deletedFaculty = facultyRepository.findById(id).orElseThrow();
        facultyRepository.deleteById(id);
        return deletedFaculty;
    }

    public Collection<Faculty> getFaculties() {
        return facultyRepository.findAll();
    }

    public Collection<Faculty> getFaculties(String name, String color) {
        if (name == null) {
            return facultyRepository.findByColorIgnoreCase(color);
        } else if (color == null) {
            return facultyRepository.findByNameIgnoreCase(name);
        } else {
            return facultyRepository.findByNameIgnoreCaseAndColorIgnoreCase(name, color);
        }
    }

    public Set<Student> getStudents(Long id) {
        return facultyRepository.findById(id).orElseThrow().getStudents();
    }
}
