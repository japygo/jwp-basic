package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateQnaFormController extends AbstractController {
    private final QuestionDao questionDao = new QuestionDao();

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
            throw new IllegalStateException("다른 사용자의 글은 수정할 수 없습니다.");
        }

        return jspView("/qna/update.jsp").setModel("question", question);
    }
}
