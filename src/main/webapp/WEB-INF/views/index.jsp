<%@ page import="kr.java.mybatis.model.dto.PostDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시판</title>
</head>
<body>
    <nav>
        <% if (session.getAttribute("username") == null) { %>
        <a href="<%= request.getContextPath() %>/signup">회원가입</a>
        <a href="<%= request.getContextPath() %>/login">로그인</a>
        <% } else { %>
        <p> <%= session.getAttribute("username") %> </p>
        <a href="<%= request.getContextPath() %>/logout">로그아웃</a>
        <a href="<%= request.getContextPath() %>/post">글 작성</a>
        <% } %>
    </nav>
    <h1>게시판에 오신 걸 환영합니다</h1>
    <p>무엇을 적으시겠어요?</p>
    <% List<PostDTO> posts = (List<PostDTO>) request.getAttribute("posts");
        for (PostDTO p : posts) {
    %>
        <p>
            <span><%= p.postId() %></span>
            <span><%= p.title() %></span>
            <span><%= p.content() %></span>
            <span><%= p.memberId() %></span>
            <span><%= p.createdAt() %></span>
        </p>
    <% } %>
</body>
</html>
