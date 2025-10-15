package taskmanagement.com.TaskManagement.Controller;

import org.springframework.web.bind.annotation.*;
import taskmanagement.com.TaskManagement.Model.Task;
import taskmanagement.com.TaskManagement.Model.TaskStatus;
import taskmanagement.com.TaskManagement.Service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping
    public List<Task> getAllTask() {
        return taskService.getAllTask();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable int id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/status")
    public List<Task> getTasksByStatus(@RequestParam TaskStatus status) {
        return taskService.getTaskByStatus(status);
    }

    @GetMapping("/overdue")
    public List<Task> getOverdueTasks() {
        return taskService.getOverdueTasks();
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable int id, @RequestBody Task updatedTask) {
        return taskService.updateTask(id, updatedTask);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
    }

    @GetMapping("/sort")
    public List<Task> getTasksSortedByDueDate() {
        return taskService.getSortedByDueDate();
    }

    @GetMapping("/deleted")
    public List<Task> getDeletedTasks() {
        return taskService.getDeletedTask();
    }
}
