package models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import play.db.ebean.Model;
import views.formdata.TodoForm;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
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
