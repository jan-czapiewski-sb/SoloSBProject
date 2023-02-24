package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.model.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
}
