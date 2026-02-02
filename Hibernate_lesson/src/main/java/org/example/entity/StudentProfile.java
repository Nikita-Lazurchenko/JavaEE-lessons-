package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "public", name = "student_profile")
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "progress")
    private Short progress;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public void setStudent(Student student) {
        this.student = student;
        student.setStudentProfile(this);
    }

}
