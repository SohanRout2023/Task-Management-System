package taskmanagement.com.TaskManagement.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taskmanagement.com.TaskManagement.Model.Task;
import taskmanagement.com.TaskManagement.Model.TaskStatus;
import taskmanagement.com.TaskManagement.Repository.TaskRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    // creating a task and saving it to repo
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // Non-deleted task
    public List<Task> getAllTask() {
        return taskRepository.findAll().stream().filter(t -> !t.isDeleted()).toList();
    }

    // By Id
    public Task getTaskById(int id) {
        return taskRepository.findById(id).filter(t -> !t.isDeleted())
                .orElse(null);
    }

    // by status
    public List<Task> getTaskByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status).stream()
                .filter(t -> !t.isDeleted())
                .toList();
    }

    // due tasks
    public List<Task> getOverdueTasks() {
        return taskRepository.findByDueDateBeforeAndStatus(LocalDate.now(), TaskStatus.PENDING).stream()
                .filter(t -> !t.isDeleted())
                .toList();
    }

    public Task updateTask(int id, Task updatedTask) {
        Task task = getTaskById(id);
        if (task != null) {
            task.setName(updatedTask.getName());
            task.setDescription(updatedTask.getDescription());
            task.setStatus(updatedTask.getStatus());
            task.setDueDate(updatedTask.getDueDate());
            return taskRepository.save(task);
        }
        return null;
    }

    // delete a task by its id // so firstly we'll delete the task according to specific id and atlast we'll
    // retrive the the task that are deleted
    public void deleteTask(int id) {
        Task task = getTaskById(id);
        if (task != null) {
            task.setDeleted(true);
            taskRepository.save(task);
        }

    }

    // Retrive all deleted task
    public List<Task> getDeletedTask() {
        return taskRepository.findByDeletedTrue();
    }

    public List<Task> getSortedByDueDate() {
        return taskRepository.findAll().stream()
                .filter(t -> !t.isDeleted())
                .sorted(Comparator.comparing(Task::getDueDate))
                .toList();
    }
}
