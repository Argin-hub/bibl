package my.library.action.get;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.entity.User;
import my.library.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static my.library.action.Constants.*;

/**
 * Определяет бизнес-логику для страницы AboutReader
 */
public class PageAboutReaderAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        UserService userService = new UserService();
        User user = null;
        String id = req.getParameter(READER_ID);
        try {
            user = userService.findUserById(Integer.parseInt(id));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute(ATT_USER_INFO, user);
        return new ActionResult(ABOUT_READER);
    }
}

