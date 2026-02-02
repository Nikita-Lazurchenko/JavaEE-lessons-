package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@ToString(exclude = {"course","studentProfile"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(schema = "public", name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="course_id")
    private Course course;

    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    private StudentProfile studentProfile;
}
