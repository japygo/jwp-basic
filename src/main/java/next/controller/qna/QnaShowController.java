package next.controller.qna;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class QnaShowController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Object value = session.getAttribute("user");
        if (value == null) {
            return "redirect:/user/loginForm";
        }
        request.setAttribute("user", value);

        long questionId = Long.parseLong(request.getParameter("questionId"));

        QuestionDao questionDao = new QuestionDao();
        request.setAttribute("question", questionDao.findByQuestionId(questionId));

        AnswerDao answerDao = new AnswerDao();
        List<Answer> answerList = answerDao.findAll(questionId);
        request.setAttribute("answers", answerList);
        request.setAttribute("answersCount", answerList.size());

        return "/qna/show.jsp";
    }
}
