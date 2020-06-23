package my.library.action.get;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.entity.Order;
import my.library.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static my.library.action.Constants.REFERER;

public class DeleteOrderAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Integer id_order = Integer.parseInt(req.getParameter("id_order"));
        OrderService orderService = new OrderService();
        orderService.deleteOrder(id_order);
        return new ActionResult(req.getHeader(REFERER), true);
    }
}
