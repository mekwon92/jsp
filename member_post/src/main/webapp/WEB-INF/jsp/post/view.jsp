<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/head.jsp" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.30.1/moment-with-locales.min.js" integrity="sha512-4F1cxYdMiAW98oomSLaygEwmCnIP38pb4Kx70yQYqRwLVCs3DbRumfBq82T08g/4LJ/smbFGFpmeFlQgoDccgg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>
<body>
	<div class='wrap'>
		<jsp:include page="../common/header.jsp" />
		<main class="container">
			 <div class="clearfix py-4">
				<h2 class="float-start">Post View</h2>
			 </div>
			 <div class="my-3 col-md-9 mx-auto">
                <label for="title" class="form-label mt-3"><i class="fa-solid fa-t text-primary"></i><b> Title</b></label>
                <input type="text" class="form-control" id="title" placeholder="title" name="title" value="${post.title}" disabled>

                <label for="content" class="form-label mt-3"><i class="fa-solid fa-align-left text-primary"></i><b> Content</b></label>
                <textarea class="form-control" id="content" name="content" disabled rows="20">${post.content}</textarea>

                <label for="writer" class="form-label mt-3"><i class="fa-solid fa-user text-primary"></i><b> Writer</b></label>
                <input type="text" class="form-control" id="writer" placeholder="writer" name="writer" value="${post.writer}" disabled>

                <label for="regdate" class="form-label mt-3"><i class="fa-solid fa-calendar-days text-primary"></i><b> Register Date</b></label>
                <input type="text" class="form-control" id="regdate" placeholder="regdate" name="regdate" value="${post.regdate}" disabled>

                <label for="updatedate" class="form-label mt-3"><i class="fa-solid fa-wrench text-primary"></i><b> Updated Date</b></label>
                <input type="text" class="form-control" id="updatedate" placeholder="updatedate" name="updatedate" value="${post.updatedate}" disabled>
                <label class="form-label mt-3"><i class="fa-solid fa-paperclip text-primary"></i><b> Attach</b><br></label><br>
                <!-- readonly는 서버로 보내짐 disabled와 차이가 있음 -->
				<%-- ${post} --%>
				<ul class="list-group attach-result">
				<c:if test="${empty post.attachs}">
					<li class="list-group-item">첨부파일이 없습니다.</li>
				</c:if>
				<c:forEach items="${post.attachs}" var="a">
					<li class="list-group-item"><a href="${cp}download?uuid=${a.uuid}&origin=${a.origin}&path=${a.path}">${a.origin}</a></li>			
				</c:forEach>
				</ul>
				<div class="clearfix mt-3 mb-2">
                <label class="form-label float-start"><i class="fa-brands fa-replyd text-primary"></i><b> Reply</b></label>
				<button type="button" class="btn btn-primary float-end btn-sm" id="btnWriteReply">write reply</button>
				</div>
				<ul class="list-group small replies">
                 
				</ul>
                
                <div class="text-center my-5">
                    <c:if test="${post.writer == member.id}">
                    <a href="modify?pno=${post.pno}&${cri.qs2}" class="btn btn-warning">수정</a>
                    <a href="remove?pno=${post.pno}&${cri.qs2}" class="btn btn-danger" onclick="return confirm('삭제하시겠습니까?')">삭제</a>
                    </c:if>
                    <a href="list?${cri.qs2}" class="btn btn-primary">목록</a>
                </div>
            </div>
		</main>
		<script src="${cp}js/reply.js"></script>
		<script>
			moment.locale('ko');
			const pno = '${post.pno}'
			// replyService.write({content:"abcd"});
			replyService.list(pno, function(data) {
				// console.log(data);
				let str = "";
				for(let i in data) {
					str += makeLi(data[i]);
				}
				$(".replies").append(str);
			});

			
			function makeLi(reply) {
                return `<li class="list-group-item" data-rno="\${reply.rno}">
                    <p class="text-black fw-bold mt-3 text-truncate">\${reply.content}</p>
                    <div class="clearfix text-secondary">
                        <span class="float-start">\${reply.writer}</span>
                        <span class="float-end small">\${moment(reply.regdate,'yyyy/MM/DD-HH:mm:ss').fromNow()}</span>
                        <a class="float-end small text-danger mx-2">삭제</a>
                    </div>
                </li>`;
            }
			
			$("#btnWriteReply").click(function() {
				$("#replyModal").modal("show");
			})

			$(function() {
				/* $("#replyModal").modal("show") */
				$("#btnReplyWriteSubmit").click(function() {
					const writer = $("#replyWriter").val();
					const content = $("#replyContent").val();
					const reply = {pno, writer, content};
					replyService.write(reply, function(data){						
						$("#replyModal").modal("hide");
						$("#replyWriter").val("");
						$("#replyContent").val("");
						
						/* location.reload(); */
					});
				});
			})

		</script>
		<jsp:include page="../common/footer.jsp" />
	</div>
		
	<!-- The Modal -->
	<div class="modal fade" id="replyModal">
		<div class="modal-dialog">
			<div class="modal-content">
	
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Reply</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
		
				<!-- Modal body -->
				<div class="modal-body">
					<label for="replyContent" class="mb-2">Content</label>
					<input type="text" class="form-control mb-3" id="replyContent">
					
					<label for="replyWriter" class="mb-2">Writer</label>
					<input type="text" class="form-control mb-3" id="replyWriter" value="${member.id}">			
				</div>
	
				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-bs-dismiss="modal" id="btnReplyWriteSubmit">Write</button>
					<button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>