package com.revature.todoapp.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "to_dos")
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "to_do_id")
    private Integer id;

    @Column(name = "to_do_text", nullable = false)
    private String toDoText;

    @Column(name = "is_complete")
    private boolean isComplete;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private User createdBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToDoText() {
        return toDoText;
    }

    public void setToDoText(String toDoText) {
        this.toDoText = toDoText;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ToDo toDo)) return false;
        return isComplete() == toDo.isComplete() && Objects.equals(getId(), toDo.getId()) && Objects.equals(getToDoText(), toDo.getToDoText()) && getCategory() == toDo.getCategory() && Objects.equals(getCreatedBy(), toDo.getCreatedBy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getToDoText(), isComplete(), getCategory(), getCreatedBy());
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", toDoText='" + toDoText + '\'' +
                ", isComplete=" + isComplete +
                ", category=" + category +
                ", createdBy=" + createdBy +
                '}';
    }
}


