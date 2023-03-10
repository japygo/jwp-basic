package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import next.model.Result;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class DeleteQuestionController extends AbstractController {
    private final QuestionDao questionDao = new QuestionDao();
    private final AnswerDao answerDao = new AnswerDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Object value = session.getAttribute("user");
        if (value == null) {
            return jspView("redirect:/user/loginForm");
        }

        User user = (User) value;
        long questionId = Long.parseLong(request.getParameter("questionId"));
        Question question = questionDao.findByQuestionId(questionId);
        if (!user.getName().equals(question.getWriter())) {
            throw new IllegalStateException("다른 사용자의 글은 삭제할 수 없습니다.");
        }

        List<Answer> answerList = answerDao.findAll(questionId);
        if (!answerList.isEmpty()) {
            for (Answer answer : answerList) {
                if (!user.getName().equals(answer.getWriter())) {
                    throw new IllegalStateException("다른 사용자의 답글이 있어서 삭제할 수 없습니다.");
                }
            }
        }

        questionDao.delete(questionId);

        return jspView("redirect:/").setModel("result", Result.ok());
    }
}
