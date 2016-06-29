package com.qiqiao.view.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.qiqiao.base.BaseAction;
import com.qiqiao.util.ImageUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;


public class ImageAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	private InputStream imageStream;
	
	public InputStream getImageStream() {
		return imageStream;
	}

	public void setImageStream(InputStream imageStream) {
		this.imageStream = imageStream;
	}

	public String execute(){
		
		Map<String,BufferedImage> map = ImageUtil.getImage();
		//获取图片上的字符保存到session
		String key = map.keySet().iterator().next();
		ActionContext.getContext().getSession().put("yzm", key);
		//获取验证图片，以stream方式相应
		BufferedImage image = map.get(key);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		JPEGImageEncoder jpeg = JPEGCodec.createJPEGEncoder(bos);
		try {
			jpeg.encode(image);
			byte[] bts = bos.toByteArray();
			imageStream = new ByteArrayInputStream(bts);
			return "success";
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
	}
	
}
