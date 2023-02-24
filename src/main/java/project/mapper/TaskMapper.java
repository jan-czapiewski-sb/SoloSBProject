package project.mapper;

import org.springframework.stereotype.Component;
import project.model.PriorityOfTask;
import project.model.Task;
import project.request.TaskRequest;
import project.response.TaskResponse;

@Component
public class TaskMapper {
    public Task map(TaskRequest taskRequest){
        return Task.builder()
                .description(taskRequest.getDescription())
                .titleOfTask(taskRequest.getTitleOfTask())
                .priorityOfTask(taskRequest.getPriorityOfTask())
                .executionDay(taskRequest.getExecutionDay())
                .build();
    }
    public TaskResponse map(Task task){
        return TaskResponse.builder()
                .id(task.getId())
                .description(task.getDescription())
                .titleOfTask(task.getTitleOfTask())
//                .priorityOfTask(task.getPriorityOfTask().name().toUpperCase())
                .executionDay(task.getExecutionDay())
                .build();
    }

    private PriorityOfTask checkIfPriorityMatches(String priority){
        PriorityOfTask[] typesOfPriorities = PriorityOfTask.values();
        for (PriorityOfTask types: typesOfPriorities) {
            if(types.name().equals(priority)) {
                return types;
            }
        }
        return null;
    }

}
