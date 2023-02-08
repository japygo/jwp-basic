package next.controller;

import core.db.DataBase;
import core.mvc.Controller;
import next.model.User;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("userId");
        User user = DataBase.findUserById(userId);
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

        DataBase.addUser(new User(userId, password, name, email));
        return "redirect:/user/list";
    }
}
