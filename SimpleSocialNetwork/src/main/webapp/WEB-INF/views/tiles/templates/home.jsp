<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
    img {
        border: 1px solid #ddd;
        border-radius: 4px;
        padding: 5px;
        max-width: 35%;
        height: 400px;
    }
</style>

<div id="content">
    <h2>Name: ${user.name}</h2>
    <h2>Email: ${user.email}</h2>

    <br/><img src="/services/HomePageController/getPhoto"/>

    <form action="/services/HomePageController/setAvatar" enctype="multipart/form-data" method="POST">
        <c:if test="${error ne null}">
            Error:${error}
        </c:if>
        <input type="file" name="photo">
        <input type="submit" value="Set avatar"/>
    </form>

</div>