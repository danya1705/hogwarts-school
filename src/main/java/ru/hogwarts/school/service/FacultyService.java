package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        faculty.setId(null);
        return facultyRepository.save(faculty);
    }

    public Optional<Faculty> getFacultyInfo(long id) {
        return facultyRepository.findById(id);
    }

    public Faculty editFacultyInfo(Faculty faculty) {
        if (facultyRepository.existsById(faculty.getId())) {
            return facultyRepository.save(faculty);
        }
        return null;
    }

    public Optional<Faculty> removeFaculty(long id) {
        Optional<Faculty> deletedFaculty = facultyRepository.findById(id);
        facultyRepository.deleteById(id);
        return deletedFaculty;
    }

    public Collection<Faculty> getFaculties() {
        return facultyRepository.findAll();
    }

    public Set<Faculty> getFaculties(String nameOrColor) {
        Set<Faculty> set = new HashSet<>(facultyRepository.findByNameIgnoreCase(nameOrColor));
        set.addAll(facultyRepository.findByColorIgnoreCase(nameOrColor));
        return set;
    }
}
