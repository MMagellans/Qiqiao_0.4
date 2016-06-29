$(document).ready(function(){
	$(window).scroll(function(){
		if($(this).scrollTop()!=0){
			var st = $(document).scrollTop(), winh = $(window).height(),winw = document.body.clientWidth;
			$('#backTop').css("top",st + winh - 100).css("left", (winw+980)/2+"px");
			//$('#backTop').animate({top:st + winh - 100},10);
			$('#backTop').fadeIn();
		}
		else{
			$('#backTop').fadeOut();
		} 
	});
	$('#backTop').click(function(){  //back to top
	$('body,html').animate({scrollTop:0},400);});


//自定义下拉菜单
//	
//		var node = $("#image_c");
//		var textoffset = $("#select_c").offset();
//		var selectbox = $("#option_c");
//		var flag = true;
//		var selectype = $("#option_c").children();
//		node.click(function() {
//			if(flag) {
//				node.css("background","url(images/manyico.png) no-repeat -91px -344px");
//				selectbox.css("top",textoffset.top+20+"px").css("left",textoffset.left+"px");
//				selectbox.slideDown("slow");
//				flag = false;		
//			}else {
//				node.css("background","url(images/manyico.png) no-repeat -46px -342px");
//				selectbox.slideUp("slow");
//				flag = true;
//			}
//		});
//		selectype.bind({
//			mouseover:function() {
//				$(this).css("background-color","#F0F8FF");
//			},
//			mouseout:function() {
//				$(this).css("background-color","white");
//			},
//			click:function() {
//				$("#select_c").children("span").text($(this).text());
//				node.css("background","url(images/manyico.png) no-repeat -46px -342px");
//				selectbox.slideUp("slow");
//				flag = true;
//			}
//		});
	});
function toButtom() {
	var scrollY = $("body").height() - $(window).height();
	$('body,html').animate({scrollTop: scrollY}, 400);
}