package next.controller.user;

import core.mvc.Controller;
import next.dao.UserDao;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();
        User user = userDao.findByUserId(userId);
        if (user == null) {
            return "redirect:/user/login.jsp";
        }

        if (user.login(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return "redirect:/";
        } else {
            return "redirect:/user/login_failed.html";
        }
    }
}
