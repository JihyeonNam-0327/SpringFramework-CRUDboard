<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href='<c:url value="/resources/bootstrap/css/bootstrap.min.css" />' rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<title>게시글 보기</title>
<script>
	var re_curPage = 1;

    $(document).ready(function(){
    	
        //listReply(); // **댓글 목록 불러오기
        listReply2(); // ** json 리턴방식
        
        // ** 댓글 쓰기 버튼 클릭 이벤트 (ajax로 처리)
        $("#btnReply").click(function(){
        	//댓글 내용과 댓글 글쓴이 변수로 받은 뒤, 내용이 없다면 return 처리
            var replytext=$("#replytext").val();
            if(replytext == ""){
            	alert("댓글 내용을 입력하세요");
                return;
            }
            var replyer=$("#replyer").val();
            if(replyer == ""){
            	alert("이름을 입력하세요");
                return;
            }
            //댓글 내용과 댓글 글쓴이 내용 초기화
            $("#replytext").val("");
            $("#replyer").val("");
            var bno="${dto.id}"
            var param="replytext="+replytext+"&id="+bno+"&replyer="+replyer;
            $.ajax({
                type: "post",
                url: "${path}/app/board/reply/insert.do",
                data: param,
                success: function(){
                    alert("댓글이 등록되었습니다.");
                    listReply2();
                }
            });
        });
    	        
        //게시글 삭제
        $("#btnDelete").click(function(){
            if(confirm("삭제하시겠습니까?")){
                document.form1.action = "${path}/app/board/gongji_delete.do";
                document.form1.submit();
            }
        });
        
        //게시글 수정
        $("#btnUpdete").click(function(){
            //var title = document.form1.title.value; ==> name속성으로 처리할 경우
            //var content = document.form1.content.value;
            //아래는 id속성으로 처리한 경우
            var title = $("#title").val();
            var content = $("#content").val();
            if(title == ""){
                alert("제목을 입력하세요");
                document.form1.title.focus();
                return;
            }
            if(content == ""){
                alert("내용을 입력하세요");
                document.form1.content.focus();
                return;
            }
            
            document.form1.action="${path}/app/board/gongji_update.do"
            // 폼에 입력한 데이터를 서버로 전송
            document.form1.submit();
        });
        
        //목록으로 돌아가기
        $("#btnBack").click(function(){
        	location.href = "${path}/app/board/gongji_list.do?curPage="+${curPage};
        });

    });
    
// Controller방식
// **댓글 목록1
function listReply(){
    $.ajax({
        type: "get",
        url: "${path}/app/board/reply/list.do?id=${dto.id}",
        success: function(result){
        // responseText가 result에 저장됨.
            $("#listReply").html(result);
        }
    });
}
// RestController방식 (Json)
// **댓글 목록2 (json)
function listReply2(param){
	
	if(param != null){
		re_curPage = param;
	}
	
    $.ajax({
        type: "get",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8", //==> 생략가능(RestController이기때문에 가능)
        url: "${path}/app/board/reply/listJson.do?id=${dto.id}&re_curPage="+re_curPage,
        success: function(result){
            console.log(result);
            var output = "<table class='table'>";
            for(var i in result.data){
                output += "<tr>";
                output += "<td id='detailReply_"+result.data[i].rno+"'><input type=text class='form-control' style='border:none;' id='replyer_"+result.data[i].rno+"' value='"+result.data[i].replyer+" ("+result.data[i].date+")' readonly /><br>";
                output += "<textarea class='form-control' id='replyValue_"+result.data[i].rno+"' rows='5' readonly>"+result.data[i].replytext+" </textarea>";
                output += "<button class='btn btn-default pull-right' type='button' id='btnReplyDelete' onclick='replyDelete("+result.data[i].rno+")'>삭제</button>";
                output += "<button class='btn btn-default pull-right' type='button' id='btnReplyUpdate' onclick='replyUpdate("+result.data[i].rno+")'>수정</button></td>";
                output += "</tr>";
            }
            output += "</table>";
            
            $("#listReply").html(output);
			
            calReplyPage(result.page);
        }
    });
}  

function calReplyPage(page){
	
	var pageOutput = "";
	
    var curPage = parseInt(page.curPage);
	// 시작 페이지
	if(curPage > 1){
		pageOutput += "<li class='page-item'>"
		pageOutput += "<a class='page-link' onclick='listReply2(1)' >시작 </a>";
		pageOutput += "</li>";
	}
	
    var curBlock = parseInt(page.curBlock);
    var prevPage = parseInt(page.prevPage);
	// 이전 블록
	if(curBlock > 1){
		pageOutput += "<li class='page-item'>";
    	pageOutput += "<a class='page-link' onclick='listReply2("+prevPage+")' >이전 </a>";
    	pageOutput += "</li>";
	}
	
	var blockStart = parseInt(page.blockStart);
	var blockEnd = parseInt(page.blockEnd);
	//현재 페이지
	pageOutput += "<li class='page-item'>";
	
	for(var i = blockStart; i <= blockEnd; i++){
		pageOutput += "<a class='page-link' onclick='listReply2("+i+")'>"+i+"</a>&nbsp;";
	}
	
	pageOutput += "</li>";
	
	var curBlock = parseInt(page.curBlock);
	var totBlock = parseInt(page.totBlock);
	var nextPage = parseInt(page.nextPage);
	// 다음 블록
	if(curBlock < totBlock){
		pageOutput += "<li class='page-item'>";
    	pageOutput += "<a class='page-link' onclick='listReply2("+nextPage+")'>다음 </a>";
    	pageOutput += "</li>";
	}       	
	
	var totPage = parseInt(page.totPage);
	// 마지막 페이지
	if(curPage < totPage){
		pageOutput += "<li class='page-item'>";
    	pageOutput += "<a class='page-link' onclick='listReply2("+totPage+")'>끝 </a>";
    	pageOutput += "</li>";
	}
	
	$("#pageReply").html(pageOutput);
}


//댓글 삭제
function replyDelete(rno){
	 $.ajax({
         type: "get",
         //contentType: "application/json", //==> 생략가능(RestController이기때문에 가능)
         url: "${path}/app/board/reply/delete/"+rno,
         success: function(result){
        	 alert("댓글이 삭제되었습니다.");
        	 listReply2();
         }
	 });
}

//댓글 수정 준비
function replyUpdate(rno){
	var replyer = $("#replyer_"+rno).val();
	var replytext = $("#replyValue_"+rno).val();
	var output = "<input type=text class='form-control' style='border:none;' id='replyer_"+rno+"' value='"+replyer+"' readonly /><br>";
	output += "<textarea class='form-control' id='replyValue_"+rno+"' rows='5' >"+replytext+"</textarea>";
	output += "<input type='button' class='btn btn-default pull-right' onclick='replyCancle("+rno+")' value='취소'/>";
	output += "<input type='button' class='btn btn-default pull-right' onclick='replyUpdate2("+rno+")' value='수정'/>";
	$("#detailReply_"+rno).html(output);
}

//댓글 수정 처리 함수
function replyUpdate2(rno){
   var replytext = $('#replyValue_'+rno).val();//textarea에 입력된 값 가져오기
   if (replytext != "") {
      $.ajax({
            url : '${path}/app/board/reply/update',
            type : 'post',
            data : {'replytext' : replytext, 'rno' : rno},
            success : function(data){
            	alert("댓글이 수정되었습니다.");
               listReply2(); //댓글 수정후 목록 출력 
            }
        });
   }
}

//댓글 수정 취소
function replyCancle(rno){
	listReply2()
}
</script>
<!-- OnsenUI 적용(css 2, js) -->
<link rel="stylesheet" href="https://unpkg.com/onsenui/css/onsenui.css">
<link rel="stylesheet" href="https://unpkg.com/onsenui/css/onsen-css-components.min.css">
<script src="https://unpkg.com/onsenui/js/onsenui.min.js"></script>
</head>
<body>

	<ons-toolbar>
	      <div class="left">
	        <ons-back-button id="btnBack">Njh Board</ons-back-button>
	      </div>
	      <div class="center">게시글 보기</div>
	</ons-toolbar>
 
<br><br><br><br>

<!-- <h2 align=center>게시글 보기</h2> -->
<br>
<form name="form1" method="post">
    <table class="table">
        <tr>
	        <td>작성일자</td>
	        <td>${dto.date}</td>
        </tr>
    <tr>
    <td>제목</td>
    <td><input class="form-control" name="title" id="title" size="80" value="${dto.title}" placeholder="제목을 입력해주세요"></td>
    </tr>
    <tr>
    <td>내용</td>
    <td><textarea class="form-control" name="content" id="content" rows="15" cols="80" placeholder="내용을 입력해주세요">${dto.content}</textarea></td>
    </tr>
    <tr>
    <td colspan=2>
        <!-- 게시물번호를 hidden으로 처리 -->
        <input type="hidden" name="id" value="${dto.id}">
        <button class="btn btn-default pull-right" type="button" id="btnUpdete">수정</button>
        <button class="btn btn-default pull-right" type="button" id="btnDelete">삭제</button>
    </td>
    </tr>
    </table>
</form>
	
    <!-- 댓글 목록 출력할 위치 -->
    <div id="listReply"></div>
    
    <!-- 댓글 페이지 출력할 위치 -->
    <div class="text-center" >
    <ul class='pagination' id="pageReply"></ul>	
    </div>
	
	<!-- 댓글 작성 폼 출력 위치 -->
	<input class="form-control" name="replyer" id="replyer" size="80" placeholder="이름을 입력해주세요">
    <textarea class="form-control" rows="5" cols="80" id="replytext" placeholder="댓글을 작성해주세요"></textarea>
    <br>
    <button class="btn btn-default pull-right" type="button" id="btnReply">댓글 작성</button>
    <br><br>
</body>
</html>