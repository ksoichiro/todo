package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.Login;
import views.html.index;
import views.html.login;

import static play.data.Form.form;

public class Application extends Controller {
    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result login() {
        return ok(
                login.render(form(Login.class).fill(new Login()))
        );
    }

    public static Result authenticate() {
        Form<Login> form = form(Login.class).bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(login.render(form));
        }
        return redirect(routes.Application.index());
    }
}
