package Project.TuHe.DTOs;

import jakarta.validation.constraints.NotEmpty;

public class StudentDTO {

    private Long id;
    @NotEmpty
    private String studentName;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
