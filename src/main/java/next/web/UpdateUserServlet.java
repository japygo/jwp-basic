package next.web;

import core.db.DataBase;
import next.model.User;
import org.springframework.util.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/update")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user", DataBase.findUserById(req.getParameter("userId")));
        RequestDispatcher rd = req.getRequestDispatcher("/user/update.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        User user = DataBase.findUserById(userId);
        String password = req.getParameter("password");
        if (!StringUtils.hasText(password)) {
            password = user.getPassword();
        }
        String name = req.getParameter("name");
        if (!StringUtils.hasText(name)) {
            name = user.getName();
        }
        String email = req.getParameter("email");
        if (!StringUtils.hasText(email)) {
            email = user.getEmail();
        }

        DataBase.addUser(new User(userId, password, name, email));
        resp.sendRedirect("/user/list");
    }
}
