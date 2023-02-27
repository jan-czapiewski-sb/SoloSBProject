package project.response;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaskResponse {
    private Long id;
    private String titleOfTask;
    private String description;
    private String priorityOfTask;
    private LocalDate executionDay;
    private String done;
}
