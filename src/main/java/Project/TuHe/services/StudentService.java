package Project.TuHe.services;

import Project.TuHe.DTOs.EventDTO;
import Project.TuHe.entities.StudentEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
/*    List<StudentEntity> getAllStudents(Long userId);*/
    StudentEntity saveStudent(StudentEntity studentEntity);
}
