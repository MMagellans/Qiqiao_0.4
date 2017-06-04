package com.qiqiao.view.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.qiqiao.base.ModelDrivenBaseAction;
import com.qiqiao.model.Attach;

@Controller
@Scope("prototype")
public class AttachAction extends ModelDrivenBaseAction<Attach> {

	private InputStream inputStream;
	
	/**
	 * 附件下载
	 * @return
	 */
	public String download() throws Exception {
		Attach attach = attachService.getById(model.getId());
		inputStream = new FileInputStream(attach.getPath());
		// 准备文件名（解决乱码问题）
		String fileName = URLEncoder.encode(attach.getName(), "utf-8"); // 方法一
		// String fileName = new String(applicationTemplate.getName().getBytes("gbk"), "iso8859-1"); // 方法二
		ActionContext.getContext().put("fileName", fileName);
		//System.out.println("--------------->xxxx");
		return "download";
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	

}
