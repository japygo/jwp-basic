package next.controller.qna;

import core.mvc.Controller;
import next.dao.UserDao;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QnaFormController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Object value = session.getAttribute("user");
        if (value == null) {
            return "redirect:/user/loginForm";
        }

        request.setAttribute("user", value);
        return "/qna/form.jsp";
    }
}
