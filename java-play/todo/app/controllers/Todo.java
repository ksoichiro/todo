package controllers;

import play.data.Form;
import play.mvc.Result;
import views.formdata.TodoForm;
import views.html.todo;

import static play.data.Form.form;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

public class Todo {
    public static Result index() {
        return ok(todo.render(form(TodoForm.class).fill(new TodoForm())));
    }
    public static Result create() {
        Form<models.Todo> form = form(models.Todo.class).bindFromRequest();
        if (form.hasErrors()) {
//            return badRequest(form);
        }
        return redirect(routes.Todo.index());
    }

    public static Result update(Long id) {
        return ok("");
    }

    public static Result delete(Long id) {
        return redirect(routes.Todo.index());
    }
}
