package my.library.action.post;

import my.library.action.manager.Action;
import my.library.action.manager.ActionResult;
import my.library.entity.Author;
import my.library.entity.Book;
import my.library.entity.BookInfo;
import my.library.entity.Genre;
import my.library.service.BookService;
import my.library.util.SqlDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static my.library.action.Constants.*;
import static my.library.action.Constants.AUTHOR_ID;
import static my.library.util.SqlDate.stringToDate;

public class AddBookAction implements Action {
    private boolean wrong = false;

    @Override
    public ActionResult execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


        BookService bookService = new BookService();
        Properties properties = new Properties();

        Author author = new Author();
        Genre genre = new Genre();
        Book book = new Book();
        BookInfo bookInfo = new BookInfo();

        properties.load(AddBookAction.class.getClassLoader().getResourceAsStream(VALIDATION_PROPERTIES));

        request.setAttribute(ATT_GENRES, bookService.getAllGenre());

        int authorId = Integer.parseInt(request.getParameter(AUTHOR_ID));

        boolean createNewAuthor = true;
        if (authorId != 0)
            createNewAuthor = false;

        if (createNewAuthor) {
            String firstName = request.getParameter(FIRST_NAME);
            String lastName = request.getParameter(LAST_NAME);
            String middleName = request.getParameter(MIDDLE_NAME);

            checkParamValid(FIRST_NAME, firstName, properties.getProperty(NAME_VALID), request);
            checkParamValid(LAST_NAME, lastName, properties.getProperty(NAME_VALID), request);
            checkParamValid(MIDDLE_NAME, middleName, properties.getProperty(NAME_VALID), request);

            if (wrong) {
                wrong = false;
                return new ActionResult(ADD_BOOK);
            }

            author.setFirstName(firstName);
            author.setLastName(lastName);
            author.setMiddleName(middleName);

            bookService.addAuthor(author);
        }
        else {
            author = bookService.findAuthorById(authorId);
        }

        String isbn = request.getParameter(ISBN);
        String description = request.getParameter(DESCRIPTION);
        String name = request.getParameter(BOOK_NAME);
        String year = request.getParameter(YEAR);
        String genreName = request.getParameter(GENRE_NAME);
        String amount = request.getParameter(AMOUNT);

        genre.setId(Integer.parseInt(genreName));

        book.setAuthor(author);
        book.setGenre(genre);
        book.setIsbn(isbn);
        book.setDate(stringToDate(year));
        book.setDescription(description);
        book.setName(name);
        bookInfo.setAmount(Integer.parseInt(amount));
        bookInfo.setBook(book);

        checkParamValid(ISBN, isbn, properties.getProperty(ISBN_VALID), request);
        checkParamValid(DESCRIPTION, isbn, properties.getProperty(DESCRIPTION_VALID), request);
        checkParamValid(BOOK_NAME, name, properties.getProperty(BOOK_NAME_VALID), request);
        checkParamValid(YEAR, year, properties.getProperty(DATE_VALID), request);
        checkParamValid(AMOUNT, amount, properties.getProperty(BOOK_AMOUNT_VALID), request);

        if (wrong) {
            wrong = false;
            return new ActionResult(ADD_BOOK);
        } else {
            try {
                bookService.addBook(bookInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ActionResult(BOOKS, true);
    }

    private void checkParamValid(String paramName, String paramValue, String validator, HttpServletRequest request) {
        Pattern pattern = Pattern.compile(validator);
        Matcher matcher = pattern.matcher(paramValue);
        if (!matcher.matches()) {
            request.setAttribute(paramName + ERROR, TRUE);
            wrong = true;
        }
    }
}
