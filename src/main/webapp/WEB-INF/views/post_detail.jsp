<%@ page import="kr.java.mybatis.model.dto.PostDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글 자세히보기</title>
</head>
<body>
    <nav>
        <a href="<%= request.getContextPath()%>/">메인</a>
    </nav>
    <h1>글 자세히 보기</h1>
    <% PostDTO p = (PostDTO) request.getAttribute("post"); %>
    <div>
        <p><%= p.postId() %></p>
        <p><%= p.title() %></p>
        <p><%= p.content() %></p>
        <p><%= p.memberId() %></p>
        <p><%= p.createdAt() %></p>
    </div>
</body>
</html>