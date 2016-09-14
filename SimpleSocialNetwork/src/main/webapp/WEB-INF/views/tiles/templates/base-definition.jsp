<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!doctype html>

<html>
<head>

    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>Site</title>
    <!--<frameset cols="200,300">
        <frame src="index1.html" name = "Foto" scroll="none">
        <frame src="index2.html" name = "Foto" scroll="none">
    </frameset>-->
    <style type="text/css">
        body {
            font: 10pt Arial, Helvetica, sans-serif; /* Шрифт на веб-странице */
            background-image: url("header-bg.jpg"); /* Цвет фона */
        }

        h2 {
            font-size: 1.5em; /* Размер шрифта */
            color: white; /* Цвет текста */
            margin-top: 0; /* Отступ сверху */
        }

        #container {
            width: 1000px; /* Ширина слоя */
            margin: 0 auto; /* Выравнивание по центру */
            background: #87CEFA; /* Цвет фона левой колонки */
        }

        #header {
            font-size: 2.2em; /* Размер текста */
            text-align: center; /* Выравнивание по центру */
            padding: 5px; /* Отступы вокруг текста */
            background: #87CEFA; /* Цвет фона шапки */
            color: white; /* Цвет текста */
        }

        #sidebar {
            margin-top: 10px;
            width: 210px; /* Ширина слоя */
            padding: 0 10px; /* Отступы вокруг текста */
            float: left; /* Обтекание по правому краю */
            background: #F0F8FF;
            font-size: 2.0em;
            color: white;
        }

        #content {
            margin-left: 330px; /* Отступ слева */
            padding: 10px; /* Поля вокруг текста */
            background: #87CEFA; /* Цвет фона правой колонки */
        }

        #footer {
            background: #87CEFA; /* Цвет фона подвала */
            color: #fff; /* Цвет текста */
            padding: 5px; /* Отступы вокруг текста */
            clear: left; /* Отменяем действие float */
        }
    </style>
</head>
<body>
<div id="container">
    <div id="header">My site</div>
    <hr/>
    <div id="sidebar">
        <p><a href="">My page</a></p>
        <p><a href="">My friends</a></p>
        <p><a href="">Search friends</a></p>
        <p><a href="">Chat</a></p>
        <p><a href="">Exit</a></p>
    </div>

    <tiles:insertAttribute name="content"/>

</div>
<div id="footer">&copy;Nikitos</div>


</body>
</html>