<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
        <div class="container">
		<div class="input-form-backgroud row">
			<div class="input-form col-md-12 mx-auto">
				<form action="${root}/user/regist" method="post">
					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="id">아이디</label> <input type="text"
								class="form-control" id="id" name="id" placeholder="" value=""
								required>
						</div>
						<div class="col-md-6 mb-3">
							<label for="pw">비밀번호</label> <input type="password"
								class="form-control" id="pass" name="pass" placeholder="비밀번호 입력" />
						</div>
					</div>
					<div class="mb-3">
						<label for="name">이름</label> <input type="text"
							class="form-control" id="name" name="name" placeholder="이름"
							required>
					</div>
					<div class="mb-3">
						<label for="email">이메일</label> <input type="email"
							class="form-control" id="email" name="email" placeholder="you@example.com"
							required>
						<div class="invalid-feedback">이메일을 입력해주세요.</div>
					</div>
					<div class="mb-3">
						<label for="address">주소</label> <input type="text"
							class="form-control" id="addr1" name="addr1" placeholder="서울특별시 강남구"
							required>
						<div class="invalid-feedback">주소를 입력해주세요.</div>
					</div>
					<div class="mb-3">
						<label for="address2">상세주소</label> <input type="text"
							class="form-control" id="addr2" name="addr2" placeholder="상세주소를 입력해주세요.">
					</div>
					<button class="btn btn-primary btn-lg btn-block" type="submit">가입하기</button>
				</form>
			</div>
		</div>
	</div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
  
  name: "MemberRegister",
  components: {},
        computed: {
            ...mapState(memberStore, ["userInfo"]),
        },
};
</script>

<style></style>
