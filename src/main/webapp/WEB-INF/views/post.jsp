<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글 작성</title>
</head>
<body>
    <nav>
        <a href="<%= request.getContextPath()%>/">메인</a>
    </nav>
    <h1>글 작성</h1>
    <form action="<%= request.getContextPath()%>/post" method="post">
        <input name="title" placeholder="제목"><br>
        <textarea name="content" placeholder="내용"></textarea><br>
        <button type="submit">작성</button>
    </form>
</body>
</html>
