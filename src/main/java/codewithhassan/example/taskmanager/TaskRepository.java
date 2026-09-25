package codewithhassan.example.taskmanager;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
