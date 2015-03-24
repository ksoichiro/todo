package controllers;

import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.formdata.TodoForm;
import views.html.todo;

import static play.data.Form.form;

@Security.Authenticated(Secured.class)
public class Todo extends Controller {
    public static Result index() {
        return ok(todo.render(form(TodoForm.class).fill(new TodoForm())));
    }

    public static Result create() {
        Form<TodoForm> form = form(TodoForm.class).bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(todo.render(form));
        }
        models.Todo todo = models.Todo.makeInstance(form.get());
        todo.setUserId(User.find.where().eq("username", session().get("username")).findUnique().id);
        todo.save();
        return redirect(routes.Todo.index());
    }

    public static Result update(Long id) {
        return ok("");
    }

    public static Result delete(Long id) {
        return redirect(routes.Todo.index());
    }
}
