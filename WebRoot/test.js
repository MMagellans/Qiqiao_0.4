$("#date_range>ul>li").bind("mouseover", function() {
	$("#date_range>ul>li").removeClass("sel");
	$("#date_range>ul>li").removeAttr("alt");
	$(this).addClass("sel");
	$(this).attr("alt", "show");
	$("#date_range>ul>li:nth-child(20)").addClass("end");
	$(this).children("span:first-child").removeClass();
	$(this).children("span:last-child").removeClass();
	$("#date_range>ul>li:nth-child(1)").children().addClass("first");
	$(this).children("span:first-child").addClass("hide")
});
$("#date_range>ul>li").bind("mouseout", function() {
	$("#date_range>ul>li").each(function(bR) {
		$(this).children("span:first").removeClass();
		$("#date_range>ul>li:nth-child(1)").children().addClass("first");
		$(this).children("span:last").addClass("hide")
	})
});
$("#date_range").bind(
		"mouseleave",
		function() {
			for ( var bR = 1; bR <= 20; bR++) {
				var bS = $("#date_range>ul>li:nth-child(" + bR + ")").children(
						"span:first-child").text();
				if (bt == bS) {
					$("#date_range>ul>li").removeClass("sel");
					$("#date_range>ul>li").removeAttr("alt");
					$("#date_range>ul>li:nth-child(" + bR + ")")
							.addClass("sel");
					$("#date_range>ul>li:nth-child(" + bR + ")").attr("alt",
							"show");
					$("#date_range>ul>li:nth-child(20)").addClass("end");
					$("#date_range>ul>li:nth-child(" + bR + ")").children(
							"span:first-child").removeClass();
					$("#date_range>ul>li:nth-child(" + bR + ")").children(
							"span:last-child").removeClass();
					$("#date_range>ul>li:nth-child(1)").children().addClass(
							"first");
					$("#date_range>ul>li:nth-child(" + bR + ")").children(
							"span:first-child").addClass("hide");
					break
				}
			}
		});