package codewithhassan.example.taskmanager;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.ArrayList;
import java.util.List;

    @RestController
    @RequestMapping("/api/tasks")
    public  class TaskController {
        private final TaskService taskService;
        private final TaskMapper taskMapper;
        public TaskController(TaskService taskService, TaskMapper taskMapper){
            this.taskService=taskService;
            this.taskMapper = taskMapper;
        }
        @Operation (summary = "Get all tasks")
        @GetMapping
        public ResponseEntity<List<TaskResponse>> getTasks() {
            List<Task> tasks = taskService.getTasks();
           List<TaskResponse> responses = new ArrayList<>();
           for(Task task : tasks){
               TaskResponse response = taskMapper.toResponse(task);
               responses.add(response);
           }
            return ResponseEntity.status(HttpStatus.OK).body(responses);
        }
        @Operation (summary = "Add task ")
        @PostMapping
        public ResponseEntity<TaskResponse> addTask(@Valid @RequestBody TaskRequest taskRequest){
            taskService.addTask(taskRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build() ;
        }
        @Operation(summary = "Get a task by ID")
        @GetMapping("/{id}")
        public ResponseEntity<TaskResponse> getByID(@PathVariable Integer id){
            Task task = taskService.getTaskByID(id);
            TaskResponse response = taskMapper.toResponse(task);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        @Operation(summary = "Update task")
        @PutMapping("/{id}")
        public ResponseEntity<TaskResponse> updateTask(@PathVariable int id , @Valid @RequestBody TaskRequest taskRequest){
            Task task = taskService.updateTask(id,taskRequest);
            TaskResponse response = taskMapper.toResponse(task);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        @Operation (summary = "Delete task by id")
        @DeleteMapping ("/{id}")
        public ResponseEntity<Void> deleteByID(@PathVariable int id){
             taskService.deleteTaskById(id);
             return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }


    }
