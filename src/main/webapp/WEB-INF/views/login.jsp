<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
    <h1>로그인</h1>
    <% if (request.getAttribute("msg") != null) { %>
    <p>
        <%= request.getAttribute("msg") %>
    </p>
    <% } %>
    <form method="post">
        <input name="username" placeholder="유저네임 입력"><br>
        <input name="password" type="password" placeholder="비밀번호 입력"><br>
        <button>로그인</button>
    </form>
</body>
</html>
