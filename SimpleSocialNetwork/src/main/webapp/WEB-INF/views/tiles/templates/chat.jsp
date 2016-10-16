<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="content">
    <h2>Chat with ${recipient}</h2>
    <c:forEach items="${messages}" var="m">
        <div style="color: ${m.sender eq recipient ? 'black' : 'blue'}">
            <c:if test="${lastAuthor eq null or lastAuthor ne m.sender}">
                <br>${m.sender}<br>
                <c:set var="lastAuthor" value="${m.sender}" scope="page"></c:set>
            </c:if>
            <fmt:formatDate type="both" value="${m.sendTime}"/>
                ${m.message}
        </div>
    </c:forEach>

    <form id="form-send" action="/services/ChatPageController/sendMessage" method="post">
        <textarea name="message" rows="4" cols="50"></textarea>
        <button id="submitSend" onclick="sendSubmit();">Send</button>
        <input type="hidden" name="recipient" value="${recipient}">
    </form>

    <script>
        function sendSubmit() {
            $("#form-send").submit();
        }
    </script>
</div>