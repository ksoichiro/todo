package views.formdata;

import play.data.validation.Constraints;

public class TodoForm {
    @Constraints.Required
    @Constraints.MinLength(1)
    @Constraints.MaxLength(1024)
    public String title;

    @Constraints.MinLength(0)
    @Constraints.MaxLength(1024)
    public String note;

    public String todoStateId;
}
