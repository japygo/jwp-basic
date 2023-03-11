package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateAnswerController extends AbstractController {
    private final AnswerDao answerDao = AnswerDao.getInstance();
    private final QuestionDao questionDao = QuestionDao.getInstance();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String writer = null;
        HttpSession session = request.getSession();
        Object value = session.getAttribute("user");
        if (value != null) {
            User user = (User) value;
            writer = user.getName();
        }
        if (writer == null) {
            return jspView("redirect:/user/loginForm");
        }

        long questionId = Long.parseLong(request.getParameter("questionId"));

        Answer answer = new Answer(
                writer,
                request.getParameter("contents"),
                questionId
        );

        answerDao.insert(answer);

        Question question = questionDao.findByQuestionId(questionId);
        question.setCountOfAnswer(question.getCountOfAnswer() + 1);
        questionDao.update(question);

        return jspView("/qna/show?questionId=" + questionId);
    }
}
