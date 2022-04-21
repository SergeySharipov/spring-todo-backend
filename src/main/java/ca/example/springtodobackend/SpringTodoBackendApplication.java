package ca.example.springtodobackend;

import ca.example.springtodobackend.model.Task;
import ca.example.springtodobackend.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringTodoBackendApplication implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskRepository taskRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringTodoBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("start");
    }

    @GetMapping("/tasks")
    public String getTasks() {
        logger.info("getTasks");
        return taskRepository.findAll().toString();
    }

    @GetMapping("/task")
    public String getTask(@RequestParam(value = "id") String idStr) {
        logger.info("getTask id=" + idStr);
        try {
            long id;
            if (idStr != null) {
                id = Long.parseLong(idStr);
            } else {
                return "Error: id can't be empty.";
            }

            Task task = taskRepository.findById(id);
            if (task != null) {
                return task.toString();
            } else {
                return "Task not found";
            }
        } catch (NumberFormatException e) {
            return "Error: id is not long.";
        }
    }

}