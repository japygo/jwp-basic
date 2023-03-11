package next.controller.user;

import core.annotaion.Controller;
import core.annotaion.RequestMapping;
import core.annotaion.RequestMethod;
import core.mvc.ModelAndView;
import core.nmvc.AbstractNewController;
import next.dao.UserDao;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController extends AbstractNewController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserDao userDao = UserDao.getInstance();

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = new User(
                request.getParameter("userId"),
                request.getParameter("password"),
                request.getParameter("name"),
                request.getParameter("email")
        );

        userDao.insert(user);

        return jspView("/user/list");
    }

    @RequestMapping("/user/list")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Object value = session.getAttribute("user");
        if (value == null) {
            return jspView("redirect:/user/loginForm");
        }

        return jspView("/user/list.jsp").setModel("users", userDao.findAll());
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        User user = userDao.findByUserId(userId);
        if (user == null) {
            return jspView("redirect:/user/login.jsp");
        }

        if (user.login(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return jspView("redirect:/");
        } else {
            return jspView("redirect:/user/login_failed.html");
        }
    }

    @RequestMapping("/user/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        session.invalidate();
        return jspView("redirect:/");
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

    @RequestMapping("/user/form")
    public ModelAndView form(HttpServletRequest req, HttpServletResponse resp) {
        return jspView("/user/form.jsp");
    }

    @RequestMapping("/user/updateUserForm")
    public ModelAndView updateUserForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

    @RequestMapping("/user/loginForm")
    public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return jspView("/user/login.jsp");
    }

    @RequestMapping("/user/profile")
    public ModelAndView profile(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
