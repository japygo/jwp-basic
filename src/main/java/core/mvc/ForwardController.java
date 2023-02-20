package core.mvc;

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
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        JspView jspView = new JspView(url);
        modelAndView.setView(jspView);
        return modelAndView;
    }
}
