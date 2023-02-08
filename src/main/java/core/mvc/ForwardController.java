package core.mvc;

import core.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardController implements Controller {
    private final String url;

    public ForwardController(String url) {
        this.url = url;
        if (url == null) {
            throw new NullPointerException("url is null");
        }
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return url;
    }
}
