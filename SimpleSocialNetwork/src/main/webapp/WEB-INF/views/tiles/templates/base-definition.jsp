<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>

<html>
<head>

    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>Social network</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style>
        .footer {
            border: solid 1px silver;
            position: fixed;
            bottom: 0;
            width: 100%;
            left: 0;
            text-align: center;
        }

        #content1 {
            margin-left: 30px; /* Отступ слева */
            margin-top: 70px;
            padding: 0px; /* Поля вокруг текста */
            background: #fff; /* Цвет фона правой колонки */
        }

    </style>
</head>
<body style="height:1500px">

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/services/HomePageController">Social network</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/services/HomePageController">Home</a></li>
            <li class="${content == 'search_friends' ? 'active' : ""}"><a href="/services/SearchFriendsController">Search friends</a></li>
            <li><a href="/services/FriendsPageController">Friends</a></li>
            <c:if test="${user.role=='admin'}">
                <li><a href="/services/UsersPageController">Users</a></li>
            </c:if>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="navbar-brand">${user.email}</li>
            <li><a href="/services/EntryPageController">Exit</a></li>
        </ul>
    </div>
</nav>

<div id="content1">
    <tiles:insertAttribute name="content"/>
</div>

<div class="footer">
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
    &copy; Nikitos
</div>

</body>
</html>