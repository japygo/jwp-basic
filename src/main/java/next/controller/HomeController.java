package next.controller;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController extends AbstractController {
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return jspView("/home.jsp").setModel("questions", questionDao.findAll());
    }
}
