<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<body>

	<%@ include file="/WEB-INF/views/common/SignIn.jsp"%>
	<%@ include file="/WEB-INF/views/common/nav.jsp"%>

	<div class="row justify-content-center" style="margin-top: 200px">
		<div class="col-lg-8 col-md-10 col-sm-12">
			<form method="POST" action="${root}/article/write">
				<div class="mb-3">
					<label for="subject" class="form-label">제목 : </label> 
					<input
						type="text" class="form-control" id="subject" name="subject"
						placeholder="제목..." />
				</div>
				<div class="mb-3">
					<label for="content" class="form-label">내용 : </label>
					<textarea class="form-control" id="content" name="content" rows="7"></textarea>
				</div>
				<div class="col-auto text-center">
					<button type="submit" id="btn-register"
						class="btn btn-outline-primary mb-3">글작성</button>
					<button type="reset" class="btn btn-outline-danger mb-3">초기화</button>
				</div>
			</form>
		</div>
	</div>
	<script>
		document.querySelector("#btn-register").addEventListener("click",
				function() {
					if (!document.querySelector("#subject").value) {
						alert("제목 입력!!");
						return;
					} else if (!document.querySelector("#content").value) {
						alert("내용 입력!!");
						return;
					} else {
						let form = document.querySelector("#form-register");
						form.setAttribute("action", "${root}/article");
						form.submit();
					}
				});
	</script>

	<%@ include file="/WEB-INF/views/common/footer.jsp"%>

</body>

</html>