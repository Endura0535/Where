<template>
  <div class="intro intro-carousel swiper position-relative">

<div class="swiper-wrapper">
  
  <!-- <div class="swiper-slide carousel-itemR-a intro-item bg-image"
    
  :style="{ backgroundImage: 'url(' + require('@/assets/img/Gwanghwamun.jpg') +')'}">
    <div class="overlay overlay-a"></div>
    <div class="intro-content display-table">
      <div class="table-cell">
        <div class="container">
          <div class="row">
            <div class="col-lg-8">
              <div class="intro-body">
                <p class="intro-title-top">
                  Jong-ro, Seoul
                </p>
                <h1 class="intro-title mb-4 ">
                  <span class="color-b">03045 </span> <br> Gwanghwamun
                </h1>
                
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div> -->
  <div class="swiper-slide carousel-item-a intro-item bg-image"
    :style="{ backgroundImage: 'url(' + require('@/assets/img/jamsil-tower.jpg') +')'}">
    <div class="overlay overlay-a"></div>
    <div class="intro-content display-table">
      <div class="table-cell">
        <div class="container">
          <div class="row">
            <div class="col-lg-8">
              <div class="intro-body">
                <p class="intro-title-top">
                  Song-pa, Seoul <br> 05556
                </p>
                <h1 class="intro-title mb-4">
                  <span class="color-b">300 </span> <br> Olympic-ro
                </h1>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="swiper-slide carousel-item-a intro-item bg-image"
  :style="{ backgroundImage: 'url(' + require('@/assets/img/Suwon-Hwaseong.jpg') +')'}">
    <div class="overlay overlay-a"></div>
    <div class="intro-content display-table">
      <div class="table-cell">
        <div class="container">
          <div class="row">
            <div class="col-lg-8">
              <div class="intro-body">
                <p class="intro-title-top">
                  Suwon, Gyeonggi <br> 16255
                </p>
                <h1 class="intro-title mb-4">
                  <span class="color-b">21 </span> <br> Changnyong-daero
                </h1>
                <p class="intro-subtitle intro-price">
                  <a href="#"><span class="price-a">views more</span></a>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="swiper-pagination"></div>

</div>
</template>

<script>
export default {
  name: "HomeView",
  props: {
    msg: String,
  },
  
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(72, 190, 233, 0.3) 30%);
}
</style>
