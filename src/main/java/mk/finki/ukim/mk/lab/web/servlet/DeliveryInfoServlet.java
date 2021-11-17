package mk.finki.ukim.mk.lab.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

@WebServlet(name = "deliveryinfoservlet", urlPatterns = "/deliveryInfo")
public class DeliveryInfoServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;

    DeliveryInfoServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        Object balloonColorObj = req.getSession().getAttribute("balloonColor");
        Object balloonSizeObj = req.getSession().getAttribute("balloonSize");

        if (balloonColorObj == null || balloonSizeObj == null) {
            resp.sendRedirect("/");
            return;
        }

        String balloonColor = balloonColorObj.toString();
        String balloonSize = balloonSizeObj.toString();

        context.setVariable("balloonColor", balloonColor);
        context.setVariable("balloonSize", balloonSize);

        springTemplateEngine.process("deliveryInfo.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientName = req.getParameter("clientName");
        String clientAddress = req.getParameter("clientAddress");
        req.getSession().setAttribute("clientName", clientName);
        req.getSession().setAttribute("clientAddress", clientAddress);

        resp.sendRedirect("/confirmationInfo");
    }

}
