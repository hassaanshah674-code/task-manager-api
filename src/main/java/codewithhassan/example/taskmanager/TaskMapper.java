package codewithhassan.example.taskmanager;

import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskResponse toResponse(Task task){
        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setCompleted(task.isCompleted());

        return response;
    }
}
