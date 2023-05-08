package Project.TuHe.services.implementation;

import Project.TuHe.DTOs.EventDTO;
import Project.TuHe.entities.EventEntity;
import Project.TuHe.entities.StudentEntity;
import Project.TuHe.entities.UserEntity;
import Project.TuHe.repositories.StudentRepository;
import Project.TuHe.repositories.UserRepository;
import Project.TuHe.services.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public StudentServiceImpl(UserRepository userRepository,
                              StudentRepository studentRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

/*    public List<StudentEntity> getAllStudents(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User with id " + userId + " not found"));
        List<StudentEntity> students = studentRepository.findByUser(user);
        return students.stream().map(student -> modelMapper.map(student, StudentEntity.class)).collect(Collectors.toList());
    }*/

    public StudentEntity saveStudent(StudentEntity studentEntity) {
        StudentEntity student = modelMapper.map(studentEntity, StudentEntity.class);
        student = studentRepository.save(student);
        return modelMapper.map(student, StudentEntity.class);
    }
}
