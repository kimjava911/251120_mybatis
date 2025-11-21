<%@ page import="kr.java.mybatis.model.dto.PostDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글 수정</title>
</head>
<body>
    <nav>
        <a href="<%= request.getContextPath()%>/">메인</a>
    </nav>
    <h1>글 수정</h1>
    <%
        PostDTO p = (PostDTO) request.getAttribute("post");
    %>
    <form action="<%= request.getContextPath()%>/post/<%= p.postId() %>/edit"
          method="post">
        <input name="title" placeholder="제목" value="<%= p.title() %>"><br>
        <textarea name="content" placeholder="내용"><%= p.content() %></textarea><br>
        <input type="hidden" name="postId" value="<%= p.postId() %>">
        <input type="hidden" name="memberId" value="<%= p.memberId() %>">
        <button type="submit">수정</button>
    </form>
</body>
</html>
