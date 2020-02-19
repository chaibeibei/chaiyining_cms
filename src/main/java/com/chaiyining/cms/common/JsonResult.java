package com.chaiyining.cms.common;

import java.io.Serializable;

/**
 * @Description:定义json接口的统一返回结果  
 * @author: zhanggm1002
 * @date:   2019年12月13日 下午3:05:33
 */
public class JsonResult implements Serializable{
	/**   
	 * @Fields serialVersionUID : TODO(这个变量表示什么)   
	 */  
	private static final long serialVersionUID = 1L;
	private boolean result;
	private int errorCode;
	private String message;
	private Object data;
	/**
	 * @Title: sucess   
	 * @Description: 成功   
	 * @param: @param data
	 * @param: @return      
	 * @return: JsonResult      
	 * @throws
	 */
	public static JsonResult sucess(Object data) {
		return new JsonResult(true,0,null,data);
	}
	
	public static JsonResult sucess() {
		return new JsonResult(true,0,null,null);
	}
	/**
	 * @Title: fail   
	 * @Description: 失败   
	 * @param: @param errorCode
	 * @param: @param message
	 * @param: @return      
	 * @return: JsonResult      
	 * @throws
	 */
	public static JsonResult fail(int errorCode,String message) {
		return new JsonResult(false,errorCode,message,null);
	}
	
	
	public JsonResult(boolean result, int errorCode, String message, Object data) {
		super();
		this.result = result;
		this.errorCode = errorCode;
		this.message = message;
		this.data = data;
	}




	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
