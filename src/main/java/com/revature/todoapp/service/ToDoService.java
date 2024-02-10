package com.revature.todoapp.service;

import ch.qos.logback.classic.Logger;
import com.revature.todoapp.entity.ToDo;
import com.revature.todoapp.entity.User;
import com.revature.todoapp.repository.ToDoRepository;
import com.revature.todoapp.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ToDoService {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(ToDoService.class);

    private final ToDoRepository toDoRepository;
    private final UserRepository userRepository;


    public ToDoService(ToDoRepository toDoRepository, UserRepository userRepository) {
        this.toDoRepository = toDoRepository;
        this.userRepository = userRepository;
    }

    public ToDo addToDo(ToDo newToDo) {
        return toDoRepository.save(newToDo);
    }

    public List<ToDo> getAllToDosByUser(User createdBy) {
        User user = userRepository.findById(createdBy.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return toDoRepository.findAllByUser(user);
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
            //todo: create custom exception
            throw new AccessDeniedException("User mismatch: cannot update ToDo item created by another user.");
        }
        return toDoRepository.save(foundToDo);

    }

//    public Integer deleteToDoById(Integer toDoId) {
//        boolean exists = toDoRepository.existsById(toDoId);
//        if (exists) {
//            toDoRepository.deleteById(toDoId);
//            return 1;
//        }
//        return null;
//    }



}
