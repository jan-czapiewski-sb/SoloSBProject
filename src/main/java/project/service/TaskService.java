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
import java.util.*;
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

    public List<TaskResponse> getAllDoneTasks() {
        return taskRepository.findAll()
                .stream()
                .filter(task -> task.isDone() == true)
                .map(taskMapper::map)
                .collect(Collectors.toList());
    }

    public List<TaskResponse> getAllUndoneTasks() {
        return taskRepository.findAll().stream()
                .filter(task -> task.isDone() == false)
                .sorted(Comparator.comparing(Task::getExecutionDay))
                .map(taskMapper::map)
                .collect(Collectors.toList());
    }

    public List<TaskResponse> getAllTasksForTodayFilteredByPriority() {
        return taskRepository.findAll().stream()
                .sorted(Comparator.comparing(Task::getPriorityOfTask).reversed())
                .map(taskMapper::map)
                .filter(taskResponse -> taskResponse.getExecutionDay().equals(LocalDate.now()))
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


    public void moveTaskToNextDay(long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        LocalDate localDate = task.getExecutionDay().plusDays(1);
        task.setExecutionDay(localDate);
        taskRepository.save(task);
    }

    private void checkIfAnyOfFieldsIsntNull(TaskRequest taskRequest, Task task) {
        if (taskRequest.getPriorityOfTask() != null) {
            PriorityOfTask[] values = PriorityOfTask.values();
            for (PriorityOfTask value : values) {
                if (value.name().equals(taskRequest.getPriorityOfTask().name())) {
                    task.setPriorityOfTask(value);
                }
            }
        }
        if (!taskRequest.getTitleOfTask().isEmpty()) {
            task.setTitleOfTask(taskRequest.getTitleOfTask());
        }
        if (!taskRequest.getDescription().isEmpty()) {
            task.setDescription(taskRequest.getDescription());
        }
        if (taskRequest.getExecutionDay() != null) {
            task.setExecutionDay(taskRequest.getExecutionDay());
        }
    }

    public List<TaskResponse> getSummaryOfTasksForToday() {
        return taskRepository.findAll().stream()
                .filter(task -> task.getExecutionDay().equals(LocalDate.now()))
                .map(taskMapper::map)
                .collect(Collectors.toList());
    }

    public void markAsDone(long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setDone(!task.isDone());
        taskRepository.save(task);
    }
}
