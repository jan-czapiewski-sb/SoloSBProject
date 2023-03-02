package project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.mapper.TaskMapper;
import project.model.Task;
import project.repository.TaskRepository;
import project.request.TaskRequest;
import project.response.TaskResponse;
import project.service.TaskService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {
    private final TaskService taskService;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @GetMapping("/api/task-done/{id}")
    public void markAsDone(@PathVariable long id) {
        taskService.markAsDone(id);
    }
    @GetMapping("/api/task")
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    @PostMapping("/api/task")
    public void addTask(@RequestBody Task taskRequest) {
        System.out.println(taskRequest);
        taskRepository.save(taskRequest);
    }

    @DeleteMapping("/api/task/{id}")
    public void deleteTask(@PathVariable long id) {
        taskRepository.delete(taskRepository.findById(id).orElseThrow());
    }

    //    @PutMapping("/api/task/{id}")
//    public TaskResponse editTask(@RequestBody TaskRequest taskRequest, @PathVariable long id) {
//        return taskService.editFields(taskRequest, id);
//    }
    @PutMapping("/api/task/{id}")
    public TaskResponse updateModel(@RequestBody TaskRequest taskRequest, @PathVariable long id) {
        return taskService.editFields(taskRequest, id);
//        return taskRepository.findById(id).map(toDoModel -> {
//            toDoModel.setExecutionDay(newToDoModel.getExecutionDay());
//            toDoModel.setDescription(newToDoModel.getDescription());
//            toDoModel.setTitleOfTask(newToDoModel.getTitleOfTask());
//            toDoModel.setPriorityOfTask(newToDoModel.getPriorityOfTask());
//            return taskRepository.save(toDoModel);
//        }).orElseThrow();
    }

    @GetMapping("/api/task/{id}")
    public TaskResponse getSingleTask(@PathVariable long id) {
        return taskRepository.findById(id).stream().map(taskMapper::map).findFirst().orElseThrow();
    }

    @GetMapping("/api/task-undone")
    public List<TaskResponse> getAllUndoneTasks() {
        return taskService.getAllUndoneTasks();
    }

    @GetMapping("/api/task-done")
    public List<TaskResponse> getAllDoneTasks() {
        return taskService.getAllDoneTasks();
    }

    @GetMapping("/api/task/alltodaytasks-bypriority")
    public List<TaskResponse> getAllTasksForTodayFilteredByPriority() {
        return taskService.getAllTasksForTodayFilteredByPriority();
    }

    @GetMapping("/api/task-move/{id}")
    public ResponseEntity<String> moveTaskToNextDay(@PathVariable long id) {
        taskService.moveTaskToNextDay(id);
        return new ResponseEntity<>("Moved to another day", HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/task-summary")
    public List<TaskResponse> getSummaryOfTasksForToday() {
        return taskService.getSummaryOfTasksForToday();
    }


//    @PatchMapping(path = "api/task/{id}")
//    public TaskResponse editSingleField(@PathVariable Long id, @RequestBody Map<Object, Object> fields) {
//        return taskService.updateTaskByFields(id, fields);
//    }

}
