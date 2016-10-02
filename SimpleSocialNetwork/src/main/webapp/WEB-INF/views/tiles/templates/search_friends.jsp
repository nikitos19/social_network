<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<div id="content">
    <form method="get" action="/services/SearchFriendsController">
        <p>Search</p>
        <input name="filter" value="${filter ne null ? filter : ''}" type="text" size="50">
        <button>Search</button>
    </form>

    <!--
    <c:forEach items="${usersResult}" var="u">
        <tr>
            <td>${u.name}</td>
            <td>${u.email}</td>
         </tr>
    </c:forEach>
    -->
</div>