package next.controller.api;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApiQnaListController extends AbstractController {
    private final QuestionDao questionDao = QuestionDao.getInstance();
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return jsonView().setModel("questionList", questionDao.findAll());
    }
}
