package project.servicetests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import project.mapper.TaskMapper;
import project.model.PriorityOfTask;
import project.model.Task;
import project.repository.TaskRepository;
import project.request.TaskRequest;
import project.response.TaskResponse;
import project.service.TaskService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private TaskMapper taskMapper;
    @InjectMocks
    private TaskService taskService;

    @Test
    void saveTask_correct() {
        //given
        TaskRequest taskRequest = new TaskRequest();
        Task task = new Task();
        when(taskMapper.map(taskRequest)).thenReturn(task);
        //when
        taskService.save(taskRequest);
        //then
        verify(taskRepository).save(task);
    }

    @Test
    void editTask_correct() {
        //given
        TaskRequest taskRequest = new TaskRequest();
        Task task = new Task();
        long id = 1;
        when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        //when
        taskService.editFields(taskRequest, id);
        //then
        verify(taskRepository).save(task);
    }

    @Test
    void checkIfAnyOfFieldsIsNotNull_correct() {
        //given
        TaskRequest taskRequest = new TaskRequest();
        Task task = new Task();
        long id = 1;
        taskRequest.setTitleOfTask("a");
        taskRequest.setDescription("a");
        taskRequest.setExecutionDay(LocalDate.now());
        taskRequest.setPriorityOfTask("LOW");
        task.setId(id);
        task.setTitleOfTask(taskRequest.getTitleOfTask());
        task.setPriorityOfTask(PriorityOfTask.valueOf(taskRequest.getPriorityOfTask()));
        task.setDescription(taskRequest.getDescription());
        when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        //when
        taskService.editFields(taskRequest,id);
        //then
        verify(taskRepository).save(task);
    }

    @Test
    void getAllTasks_correct() {
        //given
        List<Task> tasks = List.of(new Task());
        Task task = new Task();
        TaskResponse taskResponse = new TaskResponse();
        when(taskRepository.findAll()).thenReturn(tasks);
        //when
        taskService.getAllTasks();
        //then
        verify(taskRepository).findAll();
    }
    @Test
    void getAllTasksFilteredByPriority_correct(){
        //given
        List<Task> tasks = List.of(new Task());
        when(taskRepository.findAll()).thenReturn(tasks);
        //when
        taskService.getAllTasksFilteredByPriority();
        //then
        verify(taskRepository).findAll();
    }
    @Test
    void moveTaskToAnotherDay(){
        //given
        long id = 1L;
        Task task = new Task();
        task.setExecutionDay(LocalDate.now());
        when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        //when
        taskService.moveTaskToAnotherDay(id);
        //then
        verify(taskRepository).save(task);
    }
    @Test
    void getSummaryOfTasksForToday_correct(){
        //given
        Task task = new Task();
        task.setExecutionDay(LocalDate.now());
        List<Task> tasks = List.of(task);
        when(taskRepository.findAll()).thenReturn(tasks);
        //when
        taskService.getSummaryOfTasksForToday();
        //then
        verify(taskRepository).findAll();
    }

}
