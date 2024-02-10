package com.revature.todoapp.repository;

import com.revature.todoapp.entity.ToDo;
import com.revature.todoapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Integer> {
    // view To-Do's associated with the users account
    List<ToDo> findAllByUser(User createdBy);

}
