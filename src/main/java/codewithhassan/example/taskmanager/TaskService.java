package codewithhassan.example.taskmanager;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository= taskRepository;
    }
    public List<Task> getTasks(){
        return taskRepository.findAll();
    }
    public void addTask(TaskRequest taskRequest){
    Task task = new Task();
    task.setTitle(taskRequest.getTitle());
    task.setDescription(taskRequest.getDescription());
    task.setCompleted(taskRequest.isCompleted());

    taskRepository.save(task);

    }
    public Task getTaskByID(int id){
        return taskRepository.findById(id).
                orElseThrow(()-> new TaskNotFoundException ("Task with id "+id+ " not found "));
    }
    public Task updateTask(int id,TaskRequest taskRequest ){
        Task existingTask = taskRepository.findById(id).
                orElseThrow(()-> new TaskNotFoundException ("Task with id "+id+ " not found "));

            existingTask.setTitle(taskRequest.getTitle());
            existingTask.setDescription(taskRequest.getDescription());
            existingTask.setCompleted(taskRequest.isCompleted());
             return taskRepository.save(existingTask);
    }
    public void deleteTaskById(int id){
        Task existingTask = taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException("Task with id "+id+ " not found "));
        taskRepository.delete(existingTask);
    }
}
