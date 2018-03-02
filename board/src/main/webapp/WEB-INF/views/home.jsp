<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
	<table>
        <thead>
            <tr>
                <th>아이디</th>
                <th>글 제목</th>
                <th>작성시간</th>
                <th>내용</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${memberList}" var="member">
                <tr>
                	<td>${member.id}</td>
                    <td>${member.title}</td>
                    <td>${member.date}</td>
                    <td>${member.content}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
