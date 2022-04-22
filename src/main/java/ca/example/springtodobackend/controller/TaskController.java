package ca.example.springtodobackend.controller;

import ca.example.springtodobackend.model.Task;
import ca.example.springtodobackend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    private String validateTask(Task task) {
        try {
            if (task == null) return "Task can't be null.";
            String title = task.getTitle();
            if (title == null) return "Task tittle can't be null.";
            title = title.trim();
            if (title.isEmpty()) return "Task tittle can't be empty.";

            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam(required = false) String titleText) {
        try {
            List<Task> tasks = new ArrayList<>();

            if (titleText == null) {
                tasks.addAll(taskRepository.findAll());
            } else {
                tasks.addAll(taskRepository.findByTitleContaining(titleText));
            }

            if (tasks.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") long id) {
        try {
            Task task = taskRepository.findById(id);

            if (task != null) {
                return new ResponseEntity<>(task, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Notice:
     * Return an array containing all tasks with each response.
     * In order to keep front-end data up to date.
     */
    @PostMapping("/tasks")
    public ResponseEntity<List<Task>> createTask(@RequestBody Task task) {
        try {
            String validationResult = validateTask(task);
            if (validationResult != null) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

            taskRepository.insert(new Task(task.getTitle().trim(), task.getDescription()));

            List<Task> tasks = taskRepository.findAll();
            if (tasks != null) {
                return new ResponseEntity<>(tasks, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Notice:
     * Return an array containing all tasks with each response.
     * In order to keep front-end data up to date.
     */
    @PutMapping("/tasks/{id}")
    public ResponseEntity<List<Task>> updateTask(@PathVariable("id") long id, @RequestBody Task updatedTask) {
        try {
            String validationResult = validateTask(updatedTask);
            if (validationResult != null) return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

            Task task = taskRepository.findById(id);

            if (task != null) {
                task.setTitle(updatedTask.getTitle());
                task.setDescription(updatedTask.getDescription());
                taskRepository.update(task);

                List<Task> tasks = taskRepository.findAll();
                if (tasks != null) {
                    return new ResponseEntity<>(tasks, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Notice:
     * Return an array containing all tasks with each response.
     * In order to keep front-end data up to date.
     */
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<List<Task>> deleteTask(@PathVariable("id") long id) {
        try {
            taskRepository.deleteById(id);

            List<Task> tasks = taskRepository.findAll();
            if (tasks != null) {
                return new ResponseEntity<>(tasks, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tasks")
    public ResponseEntity<HttpStatus> deleteAllTasks() {
        try {
            taskRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
