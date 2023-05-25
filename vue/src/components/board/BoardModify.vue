<template>
  <div>
    <section class="intro-single">
      <div class="container">
        <div class="row">
          <div class="col-md-12 col-lg-8 w">
            <div class="title-single-box">
              <h1 class="title-single">글 수정</h1>
            </div>
          </div>
          <div class="col-md-12 col-lg-4">
            <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
              <ol class="breadcrumb">
                <li class="breadcrumb-item">
                  <!-- <router-link :to="{ name: 'boardDetail', params: { articleNo: articleNo } }">{{ subject }}</router-link> -->
                  <router-link :to="{name: 'home'}">Home</router-link>
                </li>
                <li class="breadcrumb-item">
                  <a href="#">커뮤니티</a>
                </li>
                <li class="breadcrumb-item">
                  <router-link :to="{name: 'boardList'}">QnA 게시판</router-link>
                </li>
              </ol>
            </nav>
          </div>
        </div>
        <board-input-item type="modify"></board-input-item>
      </div>
    </section>
  </div>

</template>

<script>
import BoardInputItem from "@/components/board/item/BoardInputItem.vue";

export default {
  name: "BoardModify",
  components: {
    BoardInputItem,
  },
};
</script>

<style></style>
