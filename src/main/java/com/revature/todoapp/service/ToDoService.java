package com.revature.todoapp.service;

import com.revature.todoapp.entity.ToDo;
import com.revature.todoapp.entity.User;
import com.revature.todoapp.exception.AccessDeniedException;
import com.revature.todoapp.repository.ToDoRepository;
import com.revature.todoapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ToDoService {

    private final ToDoRepository toDoRepository;
    private final UserRepository userRepository;


    public ToDoService(ToDoRepository toDoRepository, UserRepository userRepository) {
        this.toDoRepository = toDoRepository;
        this.userRepository = userRepository;
    }

    public ToDo addToDo(ToDo newToDo, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        newToDo.setCreatedBy(user);
        return toDoRepository.save(newToDo);
    }

    public List<ToDo> getAllToDosByUserId(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return toDoRepository.findAllByCreatedBy(user);
    }


    public ToDo getToDoById(Integer toDoId) {
        return toDoRepository.findById(toDoId)
                .orElseThrow(() -> new EntityNotFoundException("To-Do not found with id: " + toDoId));
    }

    public void deleteToDoById(Integer toDoId) {
        if (toDoRepository.existsById(toDoId)) {
            toDoRepository.deleteById(toDoId);
        } else {
            throw new EntityNotFoundException("To-Do not found with id: " + toDoId);
        }
    }

    public ToDo updateToDoById(Integer toDoId, ToDo modifiedToDo) {
        User user = modifiedToDo.getCreatedBy();
        userRepository.findById(user.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        ToDo foundToDo = toDoRepository.findById(toDoId)
                .orElseThrow(() -> new EntityNotFoundException("To-Do not found with id: " + toDoId));
        if (foundToDo.getCreatedBy().equals(user)) {
            foundToDo.setToDoText(modifiedToDo.getToDoText());
            foundToDo.setComplete(modifiedToDo.isComplete());
        } else {
            throw new AccessDeniedException("Access Denied");
        }
        return toDoRepository.save(foundToDo);

    }


}
