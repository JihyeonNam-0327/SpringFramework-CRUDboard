<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href='<c:url value="/resources/bootstrap/css/bootstrap.min.css" />' rel="stylesheet">
<title>게시글 목록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
    $(document).ready(function(){
        $("#btnWrite").click(function(){
            // 페이지 주소 변경(이동)
            location.href = "${path}/app/board/gongji_write.do";
        });
    });
</script>
<!-- OnsenUI 적용(css 2, js) -->
<link rel="stylesheet" href="https://unpkg.com/onsenui/css/onsenui.css">
<link rel="stylesheet" href="https://unpkg.com/onsenui/css/onsen-css-components.min.css">
<script src="https://unpkg.com/onsenui/js/onsenui.min.js"></script>
</head>
<body>

<div style="width:device-width; height:100%; overflow:scroll; -webkit-overflow-scrolling:touch;">
<ons-toolbar fixed-style>
     <div class="center">폴리텍 익명 게시판</div>
     
     <div class="right">
     	 <ons-toolbar-button id="btnWrite" >글쓰기</ons-toolbar-button>
     </div>
</ons-toolbar>
<br><br><br><br>
<table class="table table-bordered table-hover">
<thead>
    <tr>
        <th class="text-center" width=50><span class='text-success'>번호</span></th>
        <th class="text-center" width=300><span class='text-success'>제목</span></th>
        <th class="text-center" width=110><span class='text-success'>작성일</span></th>
    </tr>
</thead>
<tbody>
    <c:forEach var="row" items="${list}">
    <tr>
        <td class="text-center" >${row.id}</td>
        <td class="text-left"><a class="page-link" href="${path}/app/board/gongji_view.do?id=${row.id}">${row.title}
        	<!-- ** 댓글이 있으면 게시글 이름 옆에 출력하기 -->
            <c:if test="${row.recnt > 0}">
            <span style="color: red;">(${row.recnt})
            </span>
            </c:if>
        </a></td>
        <td class="text-center" >${row.date}</td>
    </tr>    
    </c:forEach>
</tbody>
<tfoot></tfoot>
</table>
<div class="text-center">
<ul class="pagination">
	<!-- 시작페이지 -->
	<c:if test="${page.curPage>1 }">
	<li class="page-item">
		<a class="page-link" href="${path}/app/board/gongji_list.do?curPage=1"> &lt;&lt; </a>
	</li>
	</c:if>
	
	<!-- 이전 페이지 (&lt;) = (<) -->
	<c:if test="${page.curBlock > 1 }">
	<li class="page-item">
		<a class="page-link" href="${path}/app/board/gongji_list.do?curPage=${page.prevPage}"> &lt; </a>
	</li>
	</c:if>
	
	<!-- 현재 페이지 출력 -->
	<c:forEach var="pageNum" begin="${page.blockStart}" end="${page.blockEnd}">
		<c:choose>
		    <c:when test="${pageNum==page.curPage }">
		    <li class="page-item">
		    	<span class="page-link" style="color:green;"><strong>${pageNum}</strong></span>
		    </li>
		    </c:when>
		    <c:otherwise>
		    <li class="page-item">
				<a class="page-link" href="${path}/app/board/gongji_list.do?curPage=${pageNum}">${pageNum} </a>&nbsp;
			</li>
		    </c:otherwise>
		</c:choose>
	</c:forEach>
	 
	<!-- 다음 (&gt;) = (>) -->
	<c:if test="${page.curBlock <= page.totBlock}">
	<li class="page-item">
		<a class="page-link" href="${path}/app/board/gongji_list.do?curPage=${page.nextPage}"> &gt; </a>
	</li>
	</c:if>
	 
	<!-- 마지막 페이지 -->
	<c:if test="${page.curPage < page.totPage}">
	<li class="page-item">
		<a class="page-link" href="${path}/app/board/gongji_list.do?curPage=${page.totPage}"> &gt;&gt; </a>
	</li>
	</c:if>
</ul>
</div>

</div>

<!-- <button class="btn btn-default pull-right" type="button" id="btnWrite">글쓰기</button> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</body>
</html>