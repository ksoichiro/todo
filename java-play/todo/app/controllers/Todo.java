package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.formdata.TodoForm;
import views.html.todo;

import java.util.List;

import static play.data.Form.form;

@Security.Authenticated(Secured.class)
public class Todo extends Controller {
    public static Result index() {
        return ok(todo.render(form(TodoForm.class).fill(new TodoForm()), allTodos()));
    }

    public static Result create() {
        Form<TodoForm> form = form(TodoForm.class).bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(todo.render(form, allTodos()));
        }
        models.Todo todo = models.Todo.makeInstance(form.get());
        todo.setUserId(Long.parseLong(session().get("user_id")));
        todo.save();
        return redirect(routes.Todo.index());
    }

    public static Result update(Long id) {
        return ok("");
    }

    public static Result delete(Long id) {
        models.Todo.find.byId(id.toString()).delete();
        return redirect(routes.Todo.index());
    }

    private static List<models.Todo> allTodos() {
        return models.Todo.find.where().eq("user_id", session().get("user_id")).findList();
    }
}
