function selectUserDialog() {
	var username = $("input[name='findName']").val();
	var findUserTr = $("#findUserTr");
	findUserTr.siblings().detach();
	$.ajax({
		type:"POST",
			url:"userAction_selectUserByUsername.action?username="+username,
			success:function(data) {
				//var json = eval("("+data+")"); 
				//alert(data);
				findUserTr.after(data);
				$("table[name='result']").find("tbody").find("tr").find("td").css("background-color","#f2f2f2");
	  			$("table[name='result']").find("tbody").find("tr").find("td:odd").css("background-color","white");
	  			$("table[name='result']").find("td").css("border-bottom","1px solid #BED5F3").css("height","25px");
			},
			dataType:"json"
	});
	$.ligerDialog.open({
		width:625,
		height:400,
		title:'查询结果',
		target:$("#selectResult"),
		buttons:[
			{
				text: '确定',
				onclick: function (item, dialog){ 
					var checked = "";
					var userIds = $("input[name='userId']:checked");
					var boardId = $("#boardId").val();
					userIds.each(function() {
			            checked = checked + $(this).val() + ",";
			        });
					if(checked == "") {
						alert("请至少选择一个用户！");
					}else {
						$.ajax({
							type:"POST",
							url:"boardAction_setModerator.action?id="+boardId+"&userIds="+checked,
							success:function(data) {
								alert(data);
								document.location.reload();
							},
							dataType:"json"
						});
					}
				}
			},
			{
				text:'取消',
				onclick: function (item, dialog) {
					dialog.hidden(); 
				} 
			} 
		]
	});
}
		