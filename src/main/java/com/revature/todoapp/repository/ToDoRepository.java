package com.revature.todoapp.repository;

import com.revature.todoapp.entity.Category;
import com.revature.todoapp.entity.ToDo;
import com.revature.todoapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Integer> {

    List<ToDo> findAllByCreatedBy(User createdBy);
    List<ToDo> findAllByCreatedByAndIsComplete(User createdBy, boolean isComplete);
    List<ToDo> findAllByCreatedByAndCategory(User createdBy, Category category);


}
