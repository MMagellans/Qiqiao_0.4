//校验图片文件
function validatePic(node){
	var filepath=$(node).val();
	var extStart=filepath.lastIndexOf(".");
	var ext=filepath.substring(extStart,filepath.length).toUpperCase();
	if(ext!=".BMP"&&ext!=".PNG"&&ext!=".GIF"&&ext!=".JPG"&&ext!=".JPEG"){
		alert("图片限于bmp,png,gif,jpeg,jpg格式");
		return false;
	}
	var file_size = 0;          
	if ( $.browser.msie) {
		var img=new Image();
		img.src=filepath;
		if(img.fileSize > 0){
			if(img.fileSize>2*1024){   
				alert("图片不能大于2MB。");
				return false;
			}
		}
    } else {
    	file_size = this.files[0].size;   
        var size = file_size / 1024;  
        if(size > 2*1024){  
        	alert("上传的文件大小不能超过2M！");
        	return false;
        }
	}
	return true;
}

