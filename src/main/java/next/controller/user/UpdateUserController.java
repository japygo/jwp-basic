package next.controller.user;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.UserDao;
import next.model.User;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserController extends AbstractController {
    private final UserDao userDao = new UserDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("userId");
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

        return jspView("redirect:/user/list");
    }
}
