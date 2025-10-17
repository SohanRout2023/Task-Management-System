package taskmanagement.com.TaskManagement.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import taskmanagement.com.TaskManagement.Model.Task;
import taskmanagement.com.TaskManagement.Model.TaskStatus;
import taskmanagement.com.TaskManagement.Repository.TaskRepository;
import taskmanagement.com.TaskManagement.Service.TaskService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



public class TaskServiceTest {

     @Mock
     private TaskRepository taskRepository;


    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTask(){
        Task task = new Task();
        task.setName("Test Sohan");
        task.setDescription("Sohan Task");
        task.setDueDate(LocalDate.now().plusDays(3));
        task.setStatus(TaskStatus.PENDING);
        when(taskRepository.save(task)).thenReturn(task);

        Task savedTask = taskService.createTask(task);
        verify(taskRepository, times(1)).save(task);
        assertEquals("Test Task", savedTask.getName());
        assertEquals(TaskStatus.PENDING, savedTask.getStatus());
    }

    @Test
    void testGetAllTasks() {
        Task t1 = new Task();
        t1.setName("Task 1");

        Task t2 = new Task();
        t2.setName("Task 2");

        when(taskRepository.findAll()).thenReturn(Arrays.asList(t1, t2));

        List<Task> tasks = taskService.getAllTask();

        assertEquals(2, tasks.size());
        verify(taskRepository, times(1)).findAll();
    }
    @Test
    void testGetById(){
        Task task = new Task();
        task.setId(1);
        task.setName("Task-1 Sohan");

        when(taskRepository.findById(1)).thenReturn(Optional.of(task));
        Task res = taskService.getTaskById(1);

        assertNotNull(res);
        assertEquals("Task-1 Sohan",res.getName());
    }

}
