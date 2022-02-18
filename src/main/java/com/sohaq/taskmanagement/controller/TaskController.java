package com.sohaq.taskmanagement.controller;

import com.sohaq.taskmanagement.model.Task;
import com.sohaq.taskmanagement.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<Task> create(@RequestBody @Validated Task task) {
        task = taskRepository.save(task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Task> update(@RequestBody @Validated Task task) {
        task = taskRepository.save(task);
        return new ResponseEntity<>(task, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/update/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Integer id) {
        taskRepository.existsById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Integer id) {
        Task task = taskRepository.findById(id).orElseThrow(RuntimeException::new);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/add-assignee/{id}/{assignee}")
    public ResponseEntity<Task> addAssignee(@PathVariable Integer id, @PathVariable String assignee) {
        Task task = taskRepository.findById(id).orElseThrow(RuntimeException::new);
        task.setAssignee(assignee);
        task = taskRepository.save(task);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/remove-assignee/{id}")
    public ResponseEntity<Task> removeAssignee(@PathVariable Integer id) {
        Task task = taskRepository.findById(id).orElseThrow(RuntimeException::new);
        task.setAssignee(null);
        task = taskRepository.save(task);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }
}
