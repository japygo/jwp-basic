package next.controller.qna;

import core.mvc.Controller;
import core.mvc.JspView;
import core.mvc.ModelAndView;
import next.dao.AnswerDao;
import next.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QnaShowController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Object value = session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        if (value == null) {
            JspView jspView = new JspView("redirect:/user/loginForm");
            modelAndView.setView(jspView);
            return modelAndView;
        }
        request.setAttribute("user", value);

        long questionId = Long.parseLong(request.getParameter("questionId"));

        QuestionDao questionDao = new QuestionDao();
        modelAndView.setModel("question", questionDao.findByQuestionId(questionId));

        AnswerDao answerDao = new AnswerDao();
        modelAndView.setModel("answers", answerDao.findAll(questionId));

        JspView jspView = new JspView("/qna/show.jsp");
        modelAndView.setView(jspView);

        return modelAndView;
    }
}
