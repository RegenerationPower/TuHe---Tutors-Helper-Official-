package Project.TuHe.controllers;

import Project.TuHe.DTOs.EventDTO;
import Project.TuHe.entities.EventEntity;
import Project.TuHe.entities.StudentEntity;
import Project.TuHe.services.StudentService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {

    private final StudentService studentService;
    private final ModelMapper modelMapper;

    public StudentRestController(StudentService studentService, ModelMapper modelMapper) {
        this.studentService = studentService;
        this.modelMapper = modelMapper;
    }

/*    @GetMapping("/{userId}")
    public ResponseEntity<List<StudentEntity>> getAllStudents(@PathVariable("userId") Long userId) {
        List<StudentEntity> students = studentService.getAllStudents(userId);
        List<StudentEntity> studentsList = students.stream()
                .filter(Objects::nonNull)
                .map(studentEntity -> modelMapper.map(studentEntity, StudentEntity.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(studentsList);
    }*/

    @PostMapping("")
    public ResponseEntity<StudentEntity> createStudent(@RequestBody @Valid StudentEntity studentEntity) {
        StudentEntity student = studentService.saveStudent(studentEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }
}
