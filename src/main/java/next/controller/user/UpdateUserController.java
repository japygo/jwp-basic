package next.controller.user;

import core.mvc.Controller;
import core.mvc.JspView;
import core.mvc.ModelAndView;
import next.dao.UserDao;
import next.model.User;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("userId");
        UserDao userDao = new UserDao();
        User user = userDao.findByUserId(userId);
        String password = request.getParameter("password");
        if (!StringUtils.hasText(password)) {
            password = user.getPassword();
        }
        String name = request.getParameter("name");
        if (!StringUtils.hasText(name)) {
            name = user.getName();
        }
        String email = request.getParameter("email");
        if (!StringUtils.hasText(email)) {
            email = user.getEmail();
        }

        userDao.update(new User(userId, password, name, email));

        ModelAndView modelAndView = new ModelAndView();
        JspView jspView = new JspView("redirect:/user/list");
        modelAndView.setView(jspView);

        return modelAndView;
    }
}
