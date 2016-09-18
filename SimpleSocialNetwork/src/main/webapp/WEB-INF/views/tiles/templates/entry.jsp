<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!doctype html>

<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>Entry</title>
</head>
<body>
    <br>
    <h2>Entry for page</h2>
    <form action="Registration" method="post"></form>
    User:<input type="text" name="user" size="10">
    <br>
    Password:<input type="password" name="password" size="10">
    <br>
    <p>
        <table>
            <tr>
                <th>
                    <small>
                        <input type="submit" name="login" value="Entry for page">
                    </small>
                </th>
                <th>
                    <small>
                        <input type="submit" name="registration" value="Registration">
                    </small>
                </th>
            </tr>
        </table>
    </p>
</body>
</html>