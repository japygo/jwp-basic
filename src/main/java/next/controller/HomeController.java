package next.controller;

import core.mvc.Controller;
import core.mvc.JspView;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        QuestionDao questionDao = new QuestionDao();
        modelAndView.setModel("questions", questionDao.findAll());

        JspView jspView = new JspView("/home.jsp");
        modelAndView.setView(jspView);

        return modelAndView;
    }
}
