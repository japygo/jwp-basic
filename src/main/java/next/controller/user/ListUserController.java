package next.controller.user;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListUserController extends AbstractController {
    private final UserDao userDao = UserDao.getInstance();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Object value = session.getAttribute("user");
        if (value == null) {
            return jspView("redirect:/user/loginForm");
        }

        return jspView("/user/list.jsp").setModel("users", userDao.findAll());
    }
}
