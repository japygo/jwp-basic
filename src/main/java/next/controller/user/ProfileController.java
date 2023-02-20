package next.controller.user;

import core.mvc.Controller;
import core.mvc.JspView;
import core.mvc.ModelAndView;
import next.dao.UserDao;
import next.model.User;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("userId");
        if (!StringUtils.hasText(userId)) {
            HttpSession session = request.getSession();
            Object value = session.getAttribute("user");
            if (value != null) {
                User user = (User) value;
                userId = user.getUserId();
            }
        }

        UserDao userDao = new UserDao();
        ModelAndView modelAndView = new ModelAndView();
        JspView jspView = new JspView("/user/profile.jsp");
        modelAndView.setView(jspView);
        modelAndView.setModel("user", userDao.findByUserId(userId));

        return modelAndView;
    }
}
