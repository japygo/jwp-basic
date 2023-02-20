package next.controller.user;

import core.mvc.Controller;
import core.mvc.JspView;
import core.mvc.ModelAndView;
import next.dao.UserDao;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserFormController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("userId");
        HttpSession session = request.getSession();
        Object value = session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        if (value != null) {
            User user = (User) value;
            if (!user.getUserId().equals(userId)) {
                JspView jspView = new JspView("redirect:/user/list");
                modelAndView.setView(jspView);
                return modelAndView;
            }
        }

        UserDao userDao = new UserDao();
        JspView jspView = new JspView("/user/update.jsp");
        modelAndView.setView(jspView);
        modelAndView.setModel("user", userDao.findByUserId(userId));
        return modelAndView;
    }
}
