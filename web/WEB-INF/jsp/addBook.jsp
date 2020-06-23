<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<c:url var="insertBook_url" value="/app/insertBook"/>
<c:url var="create_author_url" value="/app/insertAuthor"/>

<fmt:bundle basename="i18n">
    <fmt:message key="register.firstname" var="first_name"/>
    <fmt:message key="register.lastname" var="last_name"/>
    <fmt:message key="register.middlename" var="middle_name"/>
    <fmt:message key="register.book.name" var="book_name"/>
    <fmt:message key="register.book.description" var="description"/>
    <fmt:message key="register.book.genre" var="book_genre"/>
    <fmt:message key="register.book.author" var="book_author"/>
    <fmt:message key="register.book.year" var="book_year"/>
    <fmt:message key="register.book.legend.about.book" var="legend_about_book"/>
    <fmt:message key="register.book.legend.about.author" var="legend_about_author"/>
    <fmt:message key="register.book.pholder.description" var="ph_about_book"/>
    <fmt:message key="register.book.pholder.isbn" var="ph_isbn"/>
    <fmt:message key="register.book.pholder.description" var="ph_descrip_book"/>
    <fmt:message key="register.book.pholder.year" var="ph_year_book"/>
    <fmt:message key="register.book.pholder.name" var="ph_name_book"/>
    <fmt:message key="register.book.button.save" var="button_save"/>
    <fmt:message key="register.error.data" var="error_data"/>
    <fmt:message key="register.book.legend.about.author" var="author_info"/>
    <fmt:message key="register.book.amount" var="amount"/>
    <fmt:message key="submit" var = "submit"/>
    <fmt:message key="add.author" var = "add_author"/>
    <fmt:message key="new.author" var = "new_author"/>

    <fmt:message key="regis.author.last" var = "surn"/>
    <fmt:message key="regis.author.middle" var = "fath_name"/>
    <fmt:message key="regis.author.name" var = "name"/>
</fmt:bundle>

<style>
    <jsp:directive.include file="/WEB-INF/style.css"/>
</style>

<%@ page contentType="text/html;charset=UTF-8"%>
<jsp:directive.include file="/WEB-INF/jsp/navbar.jsp"/>
<div id="content">
    <form action="${insertBook_url}" method="POST">
        <fieldset>
            <legend>${legend_about_book}</legend>
            <div>
                <label>${book_name}
                    <input type="text" name="book_name" placeholder="${ph_name_book}">
                </label>
            </div>
            <div>
                <c:if test="${not empty book_name_error}">
                    <p>${error_data}</p>
                </c:if>
            </div>
            <div>
                <label>ISBN
                    <input type="text" name="isbn" placeholder="${ph_isbn}"></label>
                <c:if test="${not empty isbn_error}">
                    <p>${error_data}</p>
                </c:if>
            </div>
            <div>
                <label>${book_year}
                    <input type="text" name="year" placeholder="${ph_year_book}"></label>
                <c:if test="${not empty year_error}">
                    <p>${error_data}</p>
                </c:if>
            </div>
            <div>
                <label>${book_genre}
                    <select name="genre_name">
                        <c:forEach items="${genres}" var="genres">
                            <option value="${genres.id}">${genres.name}</option>
                        </c:forEach>
                    </select>
                </label>
            </div>
            <div>
                <label>${description}
                    <textarea placeholder="${ph_descrip_book}" name="description" rows="4"></textarea></label>
                <c:if test="${not empty description_error}">
                    <p>${error_data}</p>
                </c:if>
            </div>
            <div>
                <label>${amount}
                    <input type="text" name="amount" placeholder="10"></label>
                <c:if test="${not empty amount_error}">

                    <p>${error_data}</p>
                </c:if>
            </div>
            <div>
                <label>${book_author}
                    <select name="author_id">
                        <c:forEach items="${authors}" var="author">
                            <option value="${author.id}">${author.lastName} ${author.firstName} ${author.middleName}</option>
                        </c:forEach>
                        <option value="0" selected>${add_author}</option>
                    </select>
                </label>
            </div>
            <details>
                <summary>${new_author}</summary>
                <legend>${author_info}</legend>
                <div>
                    <label>${first_name}
                        <input type="text" name="first_name" placeholder="${name}"></label>
                    <c:if test="${not empty first_name_error}">
                        <p>${error_data}</p>
                    </c:if>
                </div>
                <div>
                    <label>${last_name}
                        <input type="text" name="last_name" placeholder="${surn}"></label>
                    <c:if test="${not empty last_name_error}">
                        <p>${error_data}</p>
                    </c:if>
                </div>
                <div>
                    <label>${middle_name}
                        <input type="text" name="middle_name" placeholder="${fath_name}"></label>
                    <c:if test="${not empty middle_name_error}">

                        <p>${error_data}</p>
                    </c:if>
                </div>
            </details>
            <div><button type="submit" value="${submit}">${button_save}</button></div>
        </fieldset>
    </form>
    <hr>
</div>
</body>
