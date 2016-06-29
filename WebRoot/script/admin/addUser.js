function addUser(rid) {
	$.ligerDialog.open({
		width:240,
		height:370,
		title:'选择人员',
		target:$("#findUserDiv"),
		buttons:[
			{
				text: '确定',
				onclick: function (item, dialog){ 
					var tids = "";
					if(checkSelect()) {
			  			$("input[type='checkbox']:checked").each(function() {
			  				tids = tids+$(this).val()+",";
			  			});
						$.ajax({
							type:"POST",
							url:"userAction_setRoletoUser.action",
							data:"rid="+rid+"&tids="+tids,
							success:function(data) {
								var aa = $(data).text();
								alert(aa);
								dialog.hidden();
							},
							dataType:"xml"
						});
			  			//alert(tids);
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

function findUser() {
	//alert("adsas");
	var username = $("#usernametext").val();
	if(username != null && username.trim() != "") {
		$.ajax({
			type:"POST",
			url:"userAction_selectUser.action",
			data:"username="+username,
			success:function(data) {
				var aa = $(data).text();
				var showresult = $("#showresultdiv");
				showresult.html("");
				var names = aa.split(",");
				for(var i=0;i<names.length;i++) {
					//alert(names[i]);
					var str = "<div><input type='checkbox' id='"+names[i]+"' value='"+names+"' style='margin-right: 10px;' /><label for='"+names[i]+"'>"+names[i]+"</label></div>";
					showresult.append(str);
				}
			},
			dataType:"xml"
		});
	}
	
}

function checkSelect() {
	var selecttids = $("input[type='checkbox']:checked");
	if(selecttids.length == 0) {
		alert("请选择用户");
		return false;
	}
	return true
}



