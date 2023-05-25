<template>
    <div class="col-md-4" style="width=30%;">
    <div class="card-box-d" >
        <div class="card-img-d">
            <div v-if="imgPath ===''"> 
                <img src="assets/img/noimg.gif" alt="" class="img-d img-fluid" style="width:500px; height:250px">       
            </div>
            <div v-else>
                <img :src=imgPath alt="" class="img-d img-fluid">
            </div>
        </div>
        <div class=" card-overlay card-overlay-hover">
            <div class="card-header-d">
                <div class="card-title-d align-self-center">
                    <h3 class="title-d">
                        <a href="#" class="link-two"> {{title}} </a>
                    </h3>
                </div>
            </div>
            <div class="card-body-d">
                <div class="info-agents color-a">
                    <p>
                        <strong>주소 : </strong> {{address1}} {{address2}}
                    </p>
                    <p>
                        <strong>추천수 : </strong> {{count}}
                    </p>
                </div>
            </div>
            <div class="card-footer-d">
                <div class="socials-footer d-flex justify-content-center">
                    <ul class="list-inline">
                        <li class="list-inline-item"><a href="#" class="link-one">
                                <i class="bi bi-arrow-up-right-circle-fill"
                                aria-hidden="true"></i>
                        </a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>  
</div>
</template>

<script>
export default {
  name: "HotPlaceItem",
  props: {
    title: String,
    imgPath: String,
    address1: String,
    address2: String,
    count: Number,
  },
};
</script>

<style scoped>
/* @import "https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css";
      @import "https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css";
        @import "https://use.fontawesome.com/releases/v6.1.0/js/all.js";
    @import "https://fonts.googleapis.com/css?family=Montserrat:400,700";
    @import "https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic";
    @import "https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"; */
</style>
