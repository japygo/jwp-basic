package next.controller.user;

import core.mvc.Controller;
import core.mvc.JspView;
import core.mvc.ModelAndView;
import next.dao.UserDao;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();
        User user = userDao.findByUserId(userId);
        if (user == null) {
            JspView jspView = new JspView("redirect:/user/login.jsp");
            modelAndView.setView(jspView);
            return modelAndView;
        }

        if (user.login(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            JspView jspView = new JspView("redirect:/");
            modelAndView.setView(jspView);
        } else {
            JspView jspView = new JspView("redirect:/user/login_failed.html");
            modelAndView.setView(jspView);
        }
        return modelAndView;
    }
}
