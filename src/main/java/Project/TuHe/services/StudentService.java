package Project.TuHe.services;

import Project.TuHe.DTOs.StudentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    List<StudentDTO> getAllStudents(Long userId);
    StudentDTO saveStudent(Long userId, StudentDTO studentDTO);
}
