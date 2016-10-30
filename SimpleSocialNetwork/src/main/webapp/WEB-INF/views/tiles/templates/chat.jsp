<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
    .div1{
        text-align: left;
        width: 400px;
        height: 400px;
        overflow-y: scroll;
    }
</style>

<div id="content" align="center">
    <h2>Chat with ${recipient}</h2>

    <div class="div1" id="mydiv"">
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
    </div>


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

    <script>
        $(document).ready(function(){
            $("#mydiv").scrollTop($("#mydiv")[0].scrollHeight);
        });
    </script>
</div>