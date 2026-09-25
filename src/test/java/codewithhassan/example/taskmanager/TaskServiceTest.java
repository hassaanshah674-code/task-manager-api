package codewithhassan.example.taskmanager;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskServiceTest {
    TaskRepository taskRepository = Mockito.mock(TaskRepository.class);
    TaskService taskService = new TaskService(taskRepository);

    @Test
    void getTaskByIDReturnTask(){
        Task task = new Task();
        task.setId(1);
        task.setTitle("Automated Testing");
        task.setDescription("Learning");
        task.setCompleted(false);
        Mockito.when(taskRepository.findById(1))
                .thenReturn(Optional.of(task));
        Task result = taskService.getTaskByID(1);
        assertEquals(1, result.getId());
    }
    @Test
    void getTaskByIDThrowsExceptionWhenMissing() {
        Mockito.when(taskRepository.findById(999))
                .thenReturn(Optional.empty());
        assertThrows(
                TaskNotFoundException.class,
                () -> taskService.getTaskByID(999)
        );
    }
}
