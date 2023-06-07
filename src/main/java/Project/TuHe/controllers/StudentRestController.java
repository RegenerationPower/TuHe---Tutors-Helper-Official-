package Project.TuHe.controllers;

import Project.TuHe.DTOs.EventDTO;
import Project.TuHe.DTOs.StudentDTO;
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

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<StudentEntity>> getAllStudents(@PathVariable("userId") Long userId) {
        List<StudentDTO> studentsDTO = studentService.getAllStudents(userId);
        List<StudentEntity> studentsEntities = studentsDTO.stream()
                .filter(Objects::nonNull)
                .map(studentEntity -> modelMapper.map(studentEntity, StudentEntity.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(studentsEntities);
    }

    @PostMapping("/users/{userId}")
    public ResponseEntity<StudentEntity> createStudent(@PathVariable("userId") Long userId, @RequestBody @Valid StudentDTO studentDTO) {
        StudentDTO createdStudentDTO = studentService.saveStudent(userId, studentDTO);
        StudentEntity createdStudentEntity = modelMapper.map(createdStudentDTO, StudentEntity.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudentEntity);
    }
}
