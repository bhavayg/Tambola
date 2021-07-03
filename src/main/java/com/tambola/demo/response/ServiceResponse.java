package com.tambola.demo.response;

import org.springframework.stereotype.Component;

@Component()
public class ServiceResponse<T> {
	
	private String message;
	private int errorCode;
	private T data;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "ServiceResponse [message=" + message + ", errorCode=" + errorCode + ", data=" + data + "]";
	}
	
	
}
