<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<style>
    th, td {
        text-align: center;
        padding: 8px;
    }

    tr:nth-child(even) {
        background-color: #f2f2f2
    }
</style>

<div id="content">

    <c:if test="${not empty friendships}">
        <table style="width:36%; border: 1px solid black; border-collapse: inherit;">
            <tr>
                <th>name</th>
                <th>email</th>
                <th>action</th>
            </tr>

            <c:forEach items="${friendships}" var="f">
                <c:if test="${f.status eq 'requested'}">
                    <td>${f.nameFrom}</td>
                    <td>${f.from}</td>
                    <td>
                        <form method="post" action="/services/FriendsPageController/acceptRequest">
                            <input type="hidden" name="_method" value="put" />
                            <input type="hidden" name="from" value="${f.from}">
                            <a onclick="submitForm(this)">accept</a>
                        </form>
                        <form method="post" action="/services/FriendsPageController/declineRequest">
                            <input type="hidden" name="_method" value="put" />
                            <input type="hidden" name="from" value="${f.from}">
                            <a onclick="submitForm(this)">decline</a>
                        </form>
                </c:if>
            </c:forEach>
        </table>
        <script>
            function submitForm(link){
                var l = $(link);
                l.parent().submit();
            }
        </script>
    </c:if>

    <form method="get" action="/services/FriendsPageController">
        <p>Search</p>
        <input name="filter" value="${filter ne null ? filter : ''}" type="text" size="50">
        <button>Search</button>
    </form>

    <c:if test="${not empty users}">
        <table style="width:36%; border: 1px solid black">
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