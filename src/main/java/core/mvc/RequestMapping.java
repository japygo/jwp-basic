package core.mvc;

import next.controller.*;
import next.controller.api.ApiDeleteQuestionController;
import next.controller.api.ApiQnaListController;
import next.controller.qna.*;
import next.controller.user.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private static final Map<String, Controller> controllers = new HashMap<>();

    public void initMapping() {
        controllers.put("/user/create", new CreateUserController());
        controllers.put("/", new HomeController());
        controllers.put("/user/list", new ListUserController());
        controllers.put("/user/login", new LoginController());
        controllers.put("/user/logout", new LogoutController());
        controllers.put("/user/update", new UpdateUserController());
        controllers.put("/user/form", new UserFormController());
        controllers.put("/user/updateUserForm", new UpdateUserFormController());
        controllers.put("/user/loginForm", new ForwardController("/user/login.jsp"));
        controllers.put("/user/profile", new ProfileController());
        controllers.put("/qna/form", new QnaFormController());
        controllers.put("/qna/show", new QnaShowController());
        controllers.put("/question/create", new CreateQuestionController());
        controllers.put("/answer/create", new CreateAnswerController());
        controllers.put("/api/qna/addAnswer", new AddAnswerController());
        controllers.put("/api/qna/deleteAnswer", new DeleteAnswerController());
        controllers.put("/api/qna/list", new ApiQnaListController());
        controllers.put("/question/updateForm", new UpdateQnaFormController());
        controllers.put("/question/update", new UpdateQnaController());
        controllers.put("/question/delete", new DeleteQuestionController());
        controllers.put("/api/question/delete", new ApiDeleteQuestionController());
    }

    public Controller getController(String url) {
        return controllers.get(url);
    }
}
