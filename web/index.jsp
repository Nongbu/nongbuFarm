<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="shortcut icon" href="resources/images/star.png" type="favicon/ico" /> -->

<title>농부의 텃밭</title>

<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/owl.carousel.css">
<link rel="stylesheet" href="resources/css/owl.theme.css">
<link rel="stylesheet" href="resources/css/animate.css">
<link rel="stylesheet" href="resources/css/flexslider.css">
<link rel="stylesheet" href="resources/css/pricing.css">
<link rel="stylesheet" href="resources/css/main.css">


<script src="resources/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript"
   src="resources/js/jquery.flexslider.min.js"></script>
<script type="text/javascript">
            $(window).load(function() {
                $('.flexslider').flexslider({
                 animation: "slide",
                 controlsContainer: ".flexslider-container"
                });
            });
        </script>



</head>
<body data-spy="scroll" data-target="#template-navbar">

   <!--== 헤더 ==-->
   <%@ include file="./views/common/header.jsp"%>

   <!--== 이벤트 ==-->
   <section id="header-slider" class="owl-carousel">
      <div class="item">
         <div class="container">
            <div class="header-content">
               <h1 class="header-title">이번주 신상품</h1>
               <p class="header-sub-title">확인하러가기</p>
            </div>
            <!-- /.event-content -->
         </div>
      </div>
      <div class="item">
         <div class="container">
            <div class="header-content">
               <h1 class="header-title">회원가입시 드리는 혜택</h1>
               <p class="header-sub-title">혜택보러 바로가기</p>
            </div>
            <!-- /.event-content -->
         </div>
      </div>
      <div class="item">
         <div class="container">
            <div class="header-content text-right pull-right">
               <h1 class="header-title">이번주 이벤트</h1>
               <p class="header-sub-title">이벤트 확인하러 가기</p>
            </div>
            <!-- /.event-content -->
         </div>
      </div>
      </div>
   </section>

   <!--== 상품목록 ==-->
   <div id="w">
      <div class="container">
         <div class="row">
            <div class="col-md-10 col-md-offset-1">
               <ul id="menu-pricing" class="menu-price">

                  <h2 class="pricing-title">Affordable Pricing</h2>
                  <li class="item dinner"><a href="#">
                        <img src="resources/images/food1.jpg" class="img-responsive"
                        alt="Food">
                        <div class="menu-desc text-center">


                           <span>
                              <h3>방울토마토</h3> 달고 맛있는 방울토마토

                           </span>
                        </div>
                  </a>

                     <h2 class="white">5000원</h2></li>

                  <li class="item breakfast"><a href="#"> <img
                        src="resources/images/food2.jpg" class="img-responsive"
                        alt="Food">
                        <div class="menu-desc">
                           <span>
                              <h3>양상추</h3> 신선한 양상추
                           </span>
                        </div>
                  </a>

                     <h2 class="white">2000원</h2></li>
                  <li class="item desert"><a href="#"> <img
                        src="resources/images/food3.jpg" class="img-responsive"
                        alt="Food">
                        <div class="menu-desc">
                           <span>
                              <h3>산지직송 햇토마토</h3> 산지에서 갓 수확한 신선한 햇토마토

                           </span>
                        </div>
                  </a>

                     <h2 class="white">7000원</h2></li>
                  <li class="item  special"><a href="views/test/testfood1.jsp">
                        <img src="resources/images/food4.jpg" class="img-responsive"
                        alt="Food">
                        <div class="menu-desc">
                           <span>
                              <h3>햅쌀20kg</h3> 김제쌀
                           </span>
                        </div>
                  </a>

                     <h2 class="white">30000원</h2></li>
                  <li class="item breakfast"><a href="#"> <img
                        src="resources/images/food5.jpg" class="img-responsive"
                        alt="Food">
                        <div class="menu-desc">
                           <span>
                              <h3>상추</h3> 간편하게 소분되어 먹기좋은 상추
                           </span>
                        </div>
                  </a>

                     <h2 class="white">3000원</h2></li>
                  <li class="item dinner special"><a href="#"> <img
                        src="resources/images/food6.jpg" class="img-responsive"
                        alt="Food">
                        <div class="menu-desc">
                           <span>
                              <h3>Chicken Dish</h3> Quis nostrud exercitation ullamco
                              laboris
                           </span>
                        </div>
                  </a>

                     <h2 class="white">$22</h2></li>
                  <li class="item desert"><a href="#"> <img
                        src="resources/images/food7.jpg" class="img-responsive"
                        alt="Food">
                        <div class="menu-desc">
                           <span>
                              <h3>Vegetable Noodles</h3> Nisi ut aliquip ex ea commodo
                           </span>
                        </div>
                  </a>

                     <h2 class="white">$32</h2></li>
                  <li class="item dinner"><a href="#"> <img
                        src="resources/images/food8.jpg" class="img-responsive"
                        alt="Food">
                        <div class="menu-desc">
                           <span>
                              <h3>Special Salad</h3> Duis aute irure dolor in reprehenderit
                           </span>
                        </div>
                  </a>

                     <h2 class="white">$38</h2></li>
                  <li class="item fruit"><a href="views/test/testfood2.jsp">
                        <img src="resources/images/food9.jpg" class="img-responsive"
                        alt="Food">
                        <div class="menu-desc">
                           <span>
                              <h3>고당도 딸기</h3> 국내산 고당도 딸기 산지직송
                           </span>
                        </div>
                  </a>

                     <h2 class="white">22,900원</h2></li>
               </ul>

               <!-- <div class="text-center">
                                    <a id="loadPricingContent" class="btn btn-middle hidden-sm hidden-xs">Load More <span class="caret"></span></a>
                            </div> -->

            </div>
         </div>
      </div>

   </div>


   <!--== 푸터 ==-->
   <%@ include file="./views/common/footer.jsp"%>


   <script src="resources/js/bootstrap.min.js"></script>
   <script src="resources/js/owl.carousel.min.js"></script>
   <script type="text/javascript" src="resources/js/jquery.mixitup.min.js"></script>
   <script src="resources/js/wow.min.js"></script>
   <script src="resources/js/jquery.validate.js"></script>
   <script type="text/javascript" src="resources/js/jquery.hoverdir.js"></script>
   <script type="text/javascript" src="resources/js/jQuery.scrollSpeed.js"></script>
   <script src="resources/js/script.js"></script>


</body>
</html>