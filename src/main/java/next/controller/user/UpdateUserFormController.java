package next.controller.user;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.UserDao;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserFormController extends AbstractController {
    private final UserDao userDao = UserDao.getInstance();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("userId");
        HttpSession session = request.getSession();
        Object value = session.getAttribute("user");
        if (value != null) {
            User user = (User) value;
            if (!user.getUserId().equals(userId)) {
                return jspView("redirect:/user/list");
            }
        }

        return jspView("/user/update.jsp").setModel("user", userDao.findByUserId(userId));
    }
}
