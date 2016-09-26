<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!doctype html>

<html>
<head>

    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>Social network</title>

    <link rel="stylesheet"
          href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

    <link rel="stylesheet"
          href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">

    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>

    <script type="text/javascript"
            src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>

    <script
            src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

    <style>

        #custom-bootstrap-menu.navbar-default .navbar-brand {
            color: rgba(119, 119, 119, 1);
        }
        #custom-bootstrap-menu.navbar-default {
            font-size: 14px;
            background-color: rgba(248, 248, 248, 1);
            border-width: 1px;
            border-radius: 4px;
        }
        #custom-bootstrap-menu.navbar-default .navbar-nav>li>a {
            color: rgba(119, 119, 119, 1);
            background-color: rgba(248, 248, 248, 0);
        }
        #custom-bootstrap-menu.navbar-default .navbar-nav>li>a:hover,
        #custom-bootstrap-menu.navbar-default .navbar-nav>li>a:focus {
            color: rgba(51, 51, 51, 1);
            background-color: rgba(248, 248, 248, 0);
        }
        #custom-bootstrap-menu.navbar-default .navbar-nav>.active>a,
        #custom-bootstrap-menu.navbar-default .navbar-nav>.active>a:hover,
        #custom-bootstrap-menu.navbar-default .navbar-nav>.active>a:focus {
            color: rgba(85, 85, 85, 1);
            background-color: rgba(231, 231, 231, 1);
        }
        #custom-bootstrap-menu.navbar-default .navbar-toggle {
            border-color: #ddd;
        }
        #custom-bootstrap-menu.navbar-default .navbar-toggle:hover,
        #custom-bootstrap-menu.navbar-default .navbar-toggle:focus {
            background-color: #ddd;
        }
        #custom-bootstrap-menu.navbar-default .navbar-toggle .icon-bar {
            background-color: #888;
        }
        #custom-bootstrap-menu.navbar-default .navbar-toggle:hover .icon-bar,
        #custom-bootstrap-menu.navbar-default .navbar-toggle:focus .icon-bar {
            background-color: #888;
        }
        .footer {
            border: solid 1px silver;
            position: fixed;
            bottom: 0;
            width: 100%;
            left: 0;
            text-align: center;
        }
        #ul1 {
            list-style-type: none;
            margin: 0;
            padding: 0;
            width: 15%;
            background-color: #f1f1f1;
            height: 83%; /* Full height */
            position: absolute; /* Make it stick, even on scroll */
            overflow: auto; /* Enable scrolling if the sidenav has too much content */
        }
        li a {
            display: block;
            color: #000;
            padding: 8px 16px;
            text-decoration: none;
        }

        li a.active {
            background-color: #DCDCDC;
            color: white;
        }

        li a:hover:not(.active) {
            background-color: #DCDCDC;
            color: white;
        }
        #content {
            margin-left: 120px; /* Отступ слева */
            padding: 0px; /* Поля вокруг текста */
            background: #fff; /* Цвет фона правой колонки */
        }

    </style>
</head>
<body>

<div id="custom-bootstrap-menu" class="navbar navbar-default " role="navigation">
    <div class="container-fluid">
        <div class="navbar-header"><a class="navbar-brand" href="#">Social network</a>
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-menubuilder"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse navbar-menubuilder">
            <ul class="nav navbar-nav navbar-left">
                <li><a href="">Home</a>
                </li>
                <li><a href="">Search friends</a>
                </li>
                <li><a href="/services/EntryPageController">Exit</a>
                </li>
            </ul>
        </div>
    </div>
</div>

<div id="ul1">
    <li><a class="active" href="">My page</a></li>
    <li><a href="">Friends</a></li>
    <li><a href="">Chat</a></li>
</div>

<div id="content">
    <tiles:insertAttribute name="content"/>
</div>

<div class="footer">
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>

    &copy; Nikitos
</div>

</body>
</html>