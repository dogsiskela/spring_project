package mk.finki.ukim.mk.lab.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.service.OrderService;

@WebServlet(name = "confirmationinfoservlet", urlPatterns = "/confirmationInfo")
public class ConfirmationInfoServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final OrderService orderService;

    ConfirmationInfoServlet(SpringTemplateEngine springTemplateEngine, OrderService orderService) {
        this.springTemplateEngine = springTemplateEngine;
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        Object balloonColorObj = req.getSession().getAttribute("balloonColor");
        Object balloonSizeObj = req.getSession().getAttribute("balloonSize");
        Object clientNameObj = req.getSession().getAttribute("clientName");
        Object clientAddressObj = req.getSession().getAttribute("clientAddress");

        if (balloonColorObj == null || balloonSizeObj == null || clientNameObj == null || clientAddressObj == null) {
            resp.sendRedirect("/");
            return;
        }

        String ipAddress = req.getRemoteAddr();
        String browser = req.getHeader("User-Agent");
        String balloonColor = balloonColorObj.toString();
        String balloonSize = balloonSizeObj.toString();
        String clientName = clientNameObj.toString();
        String clientAddress = clientAddressObj.toString();

        Order newOrder = new Order(balloonColor, balloonSize, clientName, clientAddress);
        orderService.saveOrder(newOrder);
        orderService.getOrders();

        context.setVariable("balloonColor", balloonColor);
        context.setVariable("balloonSize", balloonSize);
        context.setVariable("clientName", clientName);
        context.setVariable("clientAddress", clientAddress);
        context.setVariable("ipAddress", ipAddress);
        context.setVariable("browser", browser);

        springTemplateEngine.process("confirmationInfo.html", context, resp.getWriter());

    }
}
