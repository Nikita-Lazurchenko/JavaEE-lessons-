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
@Table(schema = "public", name = "trainer_course")
public class TrainerCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    public void setCourse(Course course) {
        this.course = course;
        course.getTrainerCourses().add(this);
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
        trainer.getTrainerCourses().add(this);
    }
}
