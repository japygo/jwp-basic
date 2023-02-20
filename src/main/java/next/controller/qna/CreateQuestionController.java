package next.controller.qna;

import core.mvc.Controller;
import core.mvc.JspView;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateQuestionController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Question question = new Question(
                request.getParameter("writer"),
                request.getParameter("title"),
                request.getParameter("contents")
        );

        QuestionDao questionDao = new QuestionDao();
        questionDao.insert(question);

        ModelAndView modelAndView = new ModelAndView();
        JspView jspView = new JspView("/");
        modelAndView.setView(jspView);

        return modelAndView;
    }
}
