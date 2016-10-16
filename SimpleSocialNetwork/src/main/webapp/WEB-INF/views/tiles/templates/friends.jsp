<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<div id="content">
    <form method="get" action="/services/SearchFriendsController">
        <p>Search</p>
        <input name="filter" value="${filter ne null ? filter : ''}" type="text" size="50">
        <button>Search</button>
    </form>

    <c:if test="${not empty users}">
        <table style="width:100%; border: 1px solid black">
            <tr>
                <th>name</th>
                <th>email</th>
                <th>message</th>
            </tr>

            <c:forEach items="${users}" var="u">
                <tr>
                    <td>${u.name}</td>
                    <td>${u.email}</td>
                    <td><a href="/services/ChatPageController/chatWith?recipient=${u.email}">go to the chat</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>