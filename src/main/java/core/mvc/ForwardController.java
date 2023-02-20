package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardController extends AbstractController {
    private final String url;

    public ForwardController(String url) {
        this.url = url;
        if (url == null) {
            throw new NullPointerException("url is null");
        }
    }

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return jspView(url);
    }
}
