package taskmanagement.com.TaskManagement.Repository;

import ch.qos.logback.core.status.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taskmanagement.com.TaskManagement.Model.Task;
import taskmanagement.com.TaskManagement.Model.TaskStatus;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {

    List<Task> findByStatus(TaskStatus status);
    List<Task> findByDueDateBeforeAndStatus(LocalDate date , TaskStatus status);
    List<Task> findByDeletedTrue();
}
