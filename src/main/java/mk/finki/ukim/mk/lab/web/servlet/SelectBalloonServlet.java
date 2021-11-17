package mk.finki.ukim.mk.lab.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

@WebServlet(name = "selectballonservlet", urlPatterns = "/selectBalloon")
public class SelectBalloonServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;

    SelectBalloonServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        Object balloonColorObj = req.getSession().getAttribute("balloonColor");

        if (balloonColorObj == null) {
            resp.sendRedirect("/");
            return;
        }

        String balloonColor = balloonColorObj.toString();
        context.setVariable("balloonColor", balloonColor);
        springTemplateEngine.process("selectBalloonSize.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sizeValue = req.getParameter("size");
        req.getSession().setAttribute("balloonSize", sizeValue);
        resp.sendRedirect("/deliveryInfo");
    }

}
