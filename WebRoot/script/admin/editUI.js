$(document).ready(function() {
	
	$("input").keyup(function() {
		var inputNode = $(this);
		if(event.keyCode == 13) {
			if(!isNaN(inputNode.val())) {
				if(inputNode.val() != "") {
					//$.ligerDialog.success('成功');
					if(inputNode.attr("class") == "section") {
						//alert("section");
						$.ajax({
		  					type:"POST",
		  					url:"sectionAction_setSectionSort.action?id="+inputNode.attr("id")+"&sortNum="+inputNode.val(),
		  					success:function(data) {
		  						var json = eval("("+data+")"); 
		  						//alert(json.result);
		  						if(json.result == "success") {
		  							$.ligerDialog.success("分区排序更新成功！");
		  						}else {
		  							$.ligerDialog.error("分区排序更新失败！");
		  						}
		  						document.location.reload();
		  					},
		  					dataType:"json"
		  				});
					}else{
						//alert("board");
						$.ajax({
		  					type:"POST",
		  					url:"boardAction_setBoardSort.action?id="+inputNode.attr("id")+"&sortNum="+inputNode.val(),
		  					success:function(data) {
		  						var json = eval("("+data+")"); 
		  						if(json.result == "success") {
		  							$.ligerDialog.success("分区排序更新成功！");
		  						}else {
		  							$.ligerDialog.error("分区排序更新失败！");
		  						}
		  						document.location.reload();
		  					},
		  					dataType:"json"
		  				});
					}
				}else {
					$.ligerDialog.error('不能为空');
				}
				
			}else {
				$.ligerDialog.error('请填写数字');
			}
		}
	});
});

function confirm_my() {
	//var flag = true;
	$.ligerDialog.confirm(
			'您确定要删除该版块？',
			function (yes){
				return yes;
			}
	);
	//return flag;
}