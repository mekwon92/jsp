<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common/head.jsp" />
</head>
<body>
	<div class='wrap'>
		<jsp:include page="../common/header.jsp" />
		<main class="container">
			 <div class="my-3 col-md-9 mx-auto">
                <label for="title" class="form-label mt-3"><i class="fa-solid fa-t text-primary"></i><b> Title</b></label>
                <input type="text" class="form-control" id="title" placeholder="title" name="title" value="${post.title}" disabled>

                <label for="content" class="form-label mt-3"><i class="fa-solid fa-align-left text-primary"></i><b> Content</b></label>
                <textarea class="form-control" id="content" name="content" disabled rows="20">${post.content}</textarea>

                <label for="writer" class="form-label mt-3"><i class="fa-solid fa-user text-primary"></i></i><b> Writer</b></label>
                <input type="text" class="form-control" id="writer" placeholder="writer" name="writer" value="${post.writer}" disabled>

                <label for="regdate" class="form-label mt-3"><i class="fa-solid fa-calendar-days text-primary"></i></i><b> Register Date</b></label>
                <input type="text" class="form-control" id="regdate" placeholder="regdate" name="regdate" value="${post.regdate}" disabled>

                <label for="updatedate" class="form-label mt-3"><i class="fa-solid fa-wrench text-primary"></i></i><b> Updated Date</b></label>
                <input type="text" class="form-control" id="updatedate" placeholder="updatedate" name="updatedate" value="${post.updatedate}" disabled>
                <hr>
                <div class="text-center my-5">
                    <a href="list" class="btn btn-primary">목록</a>
                </div>
            </div>
		</main>
		<jsp:include page="../common/footer.jsp" />
	</div>
</body>
</html>