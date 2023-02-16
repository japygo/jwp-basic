package next.controller.user;

import core.mvc.Controller;
import next.dao.UserDao;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserFormController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("userId");
        HttpSession session = request.getSession();
        Object value = session.getAttribute("user");
        if (value != null) {
            User user = (User) value;
            if (!user.getUserId().equals(userId)) {
                return "redirect:/user/list";
            }
        }

        UserDao userDao = new UserDao();
        request.setAttribute("user", userDao.findByUserId(userId));
        return "/user/update.jsp";
    }
}
