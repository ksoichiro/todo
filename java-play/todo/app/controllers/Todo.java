package controllers;

import play.data.Form;
import play.mvc.Result;

import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

public class Todo {
    public static Result index() {
        return ok("It works!");
    }
    public static Result create() {
        Form<models.Todo> form = Form.form(models.Todo.class).bindFromRequest();
        if (form.hasErrors()) {
            return ok("");
        }
        return redirect("/todos");
    }

    public static Result update(Long id) {
        return ok("");
    }

    public static Result delete(Long id) {
        return redirect("/todos");
    }
}
