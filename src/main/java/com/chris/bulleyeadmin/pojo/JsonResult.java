package com.chris.bulleyeadmin.pojo;

import java.io.Serializable;

/**
 * 封装操作结果JSON数据
 *
 * @author zhao
 *
 */
public class JsonResult {

	private boolean success;

	private String message;

	private Integer error;

	private String url;

	private int errorCode;

	private Serializable resultId;

	private String forwardUrl;

	private Object data ;

	public JsonResult() {
	}

	public JsonResult(boolean success) {
		this.success = success;
	}

	public JsonResult(boolean success, String data, String message, String resultId) {
		super();
		this.success = success;
		this.data = data;
		this.message = message;
		this.resultId = resultId;
	}
	public JsonResult(Integer error, String message, String url){
		super();
		this.error = error;
		this.message = message;
		this.url = url;
	}

	public JsonResult(boolean success, Object obj){
		super();
		this.success = success;
		this.data = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}


	public int getErrorCode() {
		return errorCode;
	}

	public JsonResult setErrorCode(int errorCode) {
		this.errorCode = errorCode;
		return this;
	}

	public JsonResult errorCode(int errorCode) {
		this.errorCode = errorCode;
		return this;
	}

	public Serializable getResultId() {
		return resultId;
	}

	public JsonResult setResultId(Serializable resultId) {
		this.resultId = resultId;
		return this;
	}

	public JsonResult resultId(Serializable resultId) {
		this.resultId = resultId;
		return this;
	}

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	public JsonResult forwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
		return this;
	}


	public static JsonResult getFailInstance(String msg) {
		return new JsonResult(false, msg);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getError() {
		return error;
	}

	public void setError(Integer error) {
		this.error = error;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
