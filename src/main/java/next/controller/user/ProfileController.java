package next.controller.user;

import core.mvc.Controller;
import next.dao.UserDao;
import next.model.User;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        request.setAttribute("user", userDao.findByUserId(userId));
        return "/user/profile.jsp";
    }
}
