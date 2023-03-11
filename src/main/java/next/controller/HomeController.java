package next.controller;

import core.annotaion.Controller;
import core.annotaion.RequestMapping;
import core.mvc.ModelAndView;
import core.nmvc.AbstractNewController;
import next.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController extends AbstractNewController {
    private final QuestionDao questionDao = QuestionDao.getInstance();

    @RequestMapping("/")
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return jspView("/home.jsp").setModel("questions", questionDao.findAll());
    }
}
