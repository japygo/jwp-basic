package next.controller.qna;

import core.mvc.Controller;
import core.mvc.JspView;
import core.mvc.ModelAndView;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateAnswerController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String writer = null;
        HttpSession session = request.getSession();
        Object value = session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        if (value != null) {
            User user = (User) value;
            writer = user.getName();
        }
        if (writer == null) {
            JspView jspView = new JspView("redirect:/user/loginForm");
            modelAndView.setView(jspView);
            return modelAndView;
        }

        long questionId = Long.parseLong(request.getParameter("questionId"));

        Answer answer = new Answer(
                writer,
                request.getParameter("contents"),
                questionId
        );

        AnswerDao answerDao = new AnswerDao();
        answerDao.insert(answer);

        QuestionDao questionDao = new QuestionDao();
        Question question = questionDao.findByQuestionId(questionId);
        question.setCountOfAnswer(question.getCountOfAnswer() + 1);
        questionDao.update(question);

        JspView jspView = new JspView("/qna/show?questionId=" + questionId);
        modelAndView.setView(jspView);
        return modelAndView;
    }
}
