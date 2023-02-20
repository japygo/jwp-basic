package next.controller.user;

import core.mvc.Controller;
import core.mvc.JspView;
import core.mvc.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        session.invalidate();
        ModelAndView modelAndView = new ModelAndView();
        JspView jspView = new JspView("redirect:/");
        modelAndView.setView(jspView);
        return modelAndView;
    }
}
