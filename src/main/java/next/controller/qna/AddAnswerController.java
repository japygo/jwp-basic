package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAnswerController extends AbstractController {
    private final AnswerDao answerDao = new AnswerDao();
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long questionId = Long.parseLong(request.getParameter("questionId"));
        Answer answer = new Answer(
                request.getParameter("writer"),
                request.getParameter("contents"),
                questionId
        );

        Answer savedAnswer = answerDao.insert(answer);

        questionDao.addCountOfAnswer(questionId);

        return jsonView().setModel("answer", savedAnswer);
    }
}
