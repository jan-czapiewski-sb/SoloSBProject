package project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.mapper.TaskMapper;
import project.model.PriorityOfTask;
import project.model.Task;
import project.repository.TaskRepository;
import project.request.TaskRequest;
import project.response.TaskResponse;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;

    public void save(TaskRequest taskRequest) {
        Task task = taskMapper.map(taskRequest);
        taskRepository.save(task);
    }

    public TaskResponse editFields(TaskRequest taskRequest, long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        checkIfAnyOfFieldsIsntNull(taskRequest, task);
        taskRepository.save(task);
        return taskMapper.map(task);
    }

    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapper::map)
                .collect(Collectors.toList());
    }

    public List<TaskResponse> getAllTasksFilteredByPriority() {
        return taskRepository.findAll().stream()
                .sorted(Comparator.comparing(Task::getPriorityOfTask).reversed())
                .map(taskMapper::map)
                .collect(Collectors.toList());
    }



//    public TaskResponse updateTaskByFields(Long id, Map<Object, Object> fields) {
//        Task task = taskRepository.findById(id).orElseThrow();
//        fields.forEach((key, value) -> {
//            Field field = ReflectionUtils.findField(Task.class, (String) key);
//            field.setAccessible(true);
//            if (field.getName().equals("priorityOfTask")) {
//                ReflectionUtils.setField(field, task, PriorityOfTask.valueOf(String.valueOf(value)));
//            } else {
//                ReflectionUtils.setField(field, task, value);
//            }
//        });
//        TaskResponse taskResponse = taskMapper.map(task);
//        taskRepository.save(task);
//        return taskResponse;
//    }


    public void moveTaskToAnotherDay(long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        LocalDate localDate = task.getExecutionDay().plusDays(1);
        task.setExecutionDay(localDate);
        taskRepository.save(task);
    }

    private void checkIfAnyOfFieldsIsntNull(TaskRequest taskRequest, Task task) {
        if (taskRequest.getPriorityOfTask() != null) {
            PriorityOfTask[] values = PriorityOfTask.values();
            PriorityOfTask priority = Arrays.stream(values)
                    .filter(priorityOfTask -> priorityOfTask.name().equals(taskRequest.getPriorityOfTask()))
                    .findFirst()
                    .orElseThrow();
            task.setPriorityOfTask(task.getPriorityOfTask());
        } else if (taskRequest.getTitleOfTask() != null) {
            task.setTitleOfTask(taskRequest.getTitleOfTask());
        } else if (taskRequest.getDescription() != null) {
            task.setDescription(taskRequest.getDescription());
        } else if (taskRequest.getExecutionDay() != null) {
            task.setExecutionDay(taskRequest.getExecutionDay());
        }
    }

    public List<TaskResponse> getSummaryOfTasksForToday() {
        return taskRepository.findAll().stream()
                .filter(task -> task.getExecutionDay().equals(LocalDate.now()))
                .map(taskMapper::map)
                .collect(Collectors.toList());
    }
}
