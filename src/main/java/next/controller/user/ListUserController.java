package next.controller.user;

import core.mvc.Controller;
import next.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Object value = session.getAttribute("user");
        if (value == null) {
            return "redirect:/user/loginForm";
        }

        UserDao userDao = new UserDao();
        request.setAttribute("users", userDao.findAll());
        return "/user/list.jsp";
    }
}
