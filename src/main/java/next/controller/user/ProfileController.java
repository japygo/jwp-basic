package next.controller.user;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.UserDao;
import next.model.User;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileController extends AbstractController {
    private final UserDao userDao = new UserDao();

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

        return jspView("/user/profile.jsp").setModel("user", userDao.findByUserId(userId));
    }
}
