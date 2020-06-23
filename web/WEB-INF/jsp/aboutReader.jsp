<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="i18n">

    <fmt:message key="register.birthday" var = "birthday"/>
    <fmt:message key="register.firstname" var = "firstname"/>
    <fmt:message key="register.lastname" var = "lastname"/>
    <fmt:message key="register.middlename" var = "middlename"/>
    <fmt:message key="register.phone" var = "phone"/>
    <fmt:message key="register.password" var = "password"/>
    <fmt:message key="register.email" var = "email"/>
    <fmt:message key="register.confirm.password" var = "confirm_password"/>
    <fmt:message key="register.pholder.firstname" var = "pholder_firstname"/>
    <fmt:message key="register.pholder.lastname" var = "pholder_lastname"/>
    <fmt:message key="register.pholder.middlename" var = "pholder_middlename"/>
    <fmt:message key="register.pholder.phone" var = "pholder_phone"/>
    <fmt:message key="register.pholder.email" var = "pholder_email"/>
    <fmt:message key="register.pholder.birthday" var = "pholder_birthday"/>
    <fmt:message key="aboutReader.deleteUser" var = "deleteUser"/>
    <fmt:message key="aboutReader.role" var = "role"/>
    <fmt:message key="readers.date" var = "readers_date"/>
    <fmt:message key="submit" var = "submit"/>
</fmt:bundle>


<style>
    <jsp:directive.include file="/WEB-INF/style.css"/>
</style>

<jsp:directive.include file="/WEB-INF/jsp/navbar.jsp"/>
<div id="content" align="center">
    <div>
        <p>${firstname}: ${user_info.person.firstName}</p>
        <p>${lastname}: ${user_info.person.lastName} </p>
        <p>${middlename}: ${user_info.person.middleName}</p>
        <p>${phone}: ${user_info.person.phone}</p>
        <p>${birthday}: ${user_info.person.birthday}</p>
        <p>${email}: ${user_info.email}</p>
        <p>${readers_date}: ${user_info.registerDate} </p>
        <p>${role}: ${user_info.userRole.name}</p>
    </div>
    <div>
        <form action="deleteProfile" method="POST">
            <input type="hidden" name="delete_id" value="${user_info.id}">
            <button id="submit" name="submit" value="${submit}">${deleteUser}</button>
        </form>
    </div>
</div>
</BODY>

