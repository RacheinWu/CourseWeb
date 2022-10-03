// 侧边栏
$(function() {
  $(".side_box").hide();
  window.onscroll = function() {
    var autoheight =
      document.body.scrollTop || document.documentElement.scrollTop;
    if (autoheight > 240) {
      $(".side_box").fadeIn(500);
    } else {
      $(".side_box").fadeOut(500);
    }
  };
  $(".sidetop").click(function() {
    $("body,html").animate({ scrollTop: 0 }, 500);
  });
});
