package com.revature.todoapp.controller;

import com.revature.todoapp.entity.ToDo;
import com.revature.todoapp.service.ToDoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ToDoController {

    private final ToDoService toDoService;


    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @PostMapping
    public ResponseEntity<ToDo> addToDo(@RequestBody ToDo toDo) {
        return ResponseEntity.ok(toDoService.addToDo(toDo));
    }

    @GetMapping("todos/{toDoId}")
    public ResponseEntity<ToDo> getToDoById(@PathVariable Integer toDoId) {
        return ResponseEntity.ok(toDoService.getToDoById(toDoId));
    }

    @DeleteMapping("todos/{toDoId}")
    public ResponseEntity<?> deleteToDoById(@PathVariable Integer toDoId) {
        toDoService.deleteToDoById(toDoId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("todos/{toDoId}")
    public ResponseEntity<ToDo> updateToDoById(@PathVariable Integer toDoId, @RequestBody ToDo toDo) {
        return ResponseEntity.ok(toDoService.updateToDoById(toDoId, toDo));
    }

    @GetMapping("/users/{userId}/todos")
    public ResponseEntity<List<ToDo>> getAllToDosByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(toDoService.getAllToDosByUserId(userId));
    }

}
