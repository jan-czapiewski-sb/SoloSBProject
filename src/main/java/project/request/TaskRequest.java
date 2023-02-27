package project.request;

import lombok.Getter;
import lombok.Setter;
import project.model.PriorityOfTask;

import java.time.LocalDate;

@Getter
@Setter
public class TaskRequest {
    private String titleOfTask;
    private String description;
    private PriorityOfTask priorityOfTask;
    private LocalDate executionDay;
    private boolean done;


}
