package com.chaiyining.cms.controller.file;
/** 
* @author 作者:
* @version 创建时间：2019年12月13日 下午1:31:30 
* 类功能说明 
*/
public class FileResult {
	public FileResult(int error, String url) {
		super();
		this.error = error;
		this.url = url;
	}

	int error = 0;
	String url = "";

	public FileResult() {
		super();
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
