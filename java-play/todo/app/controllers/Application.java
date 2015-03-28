package controllers;

import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.formdata.Login;
import views.html.login;

import static play.data.Form.form;

public class Application extends Controller {
    @Security.Authenticated(Secured.class)
    public static Result index() {
        return redirect(routes.Todo.index());
    }

    public static Result login() {
        return ok(login.render(form(Login.class).fill(new Login())));
    }

    public static Result logout() {
        session().clear();
        flash("success", "You have been logged out.");
        return redirect(routes.Application.login());
    }

    public static Result authenticate() {
        Form<Login> form = form(Login.class).bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(login.render(form));
        }
        session().clear();
        session("username", form.get().username);
        session("user_id", User.find.where().eq("username", form.get().username).findUnique().getId().toString());
        return redirect(routes.Todo.index());
    }
}
