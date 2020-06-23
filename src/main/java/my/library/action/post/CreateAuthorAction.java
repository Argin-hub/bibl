package my.library.action.post;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.entity.Author;
import my.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static my.library.action.Constants.*;

public class CreateAuthorAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
      String fir_name = req.getParameter(FIRST_NAME);
      String last_name = req.getParameter(LAST_NAME);
      String mid_name = req.getParameter(MIDDLE_NAME);
        BookService bookService = new BookService();
        Author author = new Author();
        author.setFirstName(fir_name);
        author.setLastName(last_name);
        author.setMiddleName(mid_name);
        bookService.createAuthor(author);

        return new ActionResult(req.getHeader(REFERER), true);
    }
}
