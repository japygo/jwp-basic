package next.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserFormController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "/user/form.jsp";
    }
}
