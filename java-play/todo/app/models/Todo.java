package models;

import play.db.ebean.Model;
import views.formdata.TodoForm;

import javax.persistence.*;

@Entity
public class Todo extends Model {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String note;

    @Column(nullable = false)
    private Integer ownerType;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long groupId;

    @Column(nullable = false)
    private Long createdAt;

    @Column(nullable = false)
    private Long updatedAt;

    @ManyToOne
    @JoinColumn(name = "todo_state_id")
    private TodoState todoState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(Integer ownerType) {
        this.ownerType = ownerType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public TodoState getTodoState() {
        return todoState;
    }

    public void setTodoState(TodoState todoState) {
        this.todoState = todoState;
    }

    public static Finder<String, Todo> find = new Finder<>(String.class, Todo.class);

    public static Todo makeInstance(TodoForm form) {
        Todo todo = new Todo();
        todo.setTitle(form.title);
        todo.setNote(form.note);
        todo.setOwnerType(0);
        todo.setTodoState(TodoState.find.byId(form.todoStateId));
        todo.setGroupId(0L);
        todo.setCreatedAt(System.currentTimeMillis());
        todo.setUpdatedAt(System.currentTimeMillis());
        return todo;
    }
}
