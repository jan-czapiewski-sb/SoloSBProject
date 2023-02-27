package project.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "task")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titleOfTask;
    private String description;
    @Enumerated(EnumType.STRING)
    private PriorityOfTask priorityOfTask;
    private LocalDate executionDay;
    private boolean done;
}
