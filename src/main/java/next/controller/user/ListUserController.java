package next.controller.user;

import core.mvc.Controller;
import core.mvc.JspView;
import core.mvc.ModelAndView;
import next.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListUserController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Object value = session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        if (value == null) {
            JspView jspView = new JspView("redirect:/user/loginForm");
            modelAndView.setView(jspView);
            return modelAndView;
        }

        UserDao userDao = new UserDao();
        JspView jspView = new JspView("/user/list.jsp");
        modelAndView.setView(jspView);
        modelAndView.setModel("users", userDao.findAll());

        return modelAndView;
    }
}
