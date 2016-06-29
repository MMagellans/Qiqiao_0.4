function changeColor(node,num) {/*0表示移进，1表示移出*/
	trNode = $(node);
	if(num == 0) {
		trNode.css("background-color","#f2f2f2");
	}else {
		trNode.css("background-color","white");
	}
}
		function subLogin() {
  			var username = $("#username").val();
  			var password = $("#password").val();
			//alert("用户名："+username+"   密码："+password);
  			if(username==""||username.length==0) {
  				alert("用户名不能为空！");
  			}else if(password==""||password.length==0) {
  				alert("密码不能为空！");
  			}else {
  				$.ajax({
  					type:"POST",
  					url:"login.action?username="+username+"&password="+password,
  					success:function(data) {
  						var json = eval("("+data+")"); 
  						alert(json.result);
  						document.location.reload();
  					},
  					dataType:"json"
  				});
  			}
  			
  		}
		function Logout() {
  			$.ajax({
  				type:"POST",
  				url:"logout.action",
  				success:function(data) {
  					var json = eval("("+data+")"); 
  					alert(json.result);
  					document.location.reload();
  				},
  				dataType:"json"
  			});
  		}