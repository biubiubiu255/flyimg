package com.flyimg.pojo.vo;

import com.flyimg.comm.enums.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 页面响应entity
 * 创建者 ahser
 * 创建时间	2020年03月21日
 * @author ahser
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class MyException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = -3948389268046368059L;

	private Integer code;

	private String msg;

	private Object data;

	public MyException() {}

	public MyException(Integer code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}

	public MyException(ResultCode resultCode) {
		super(resultCode.getMessage());
		this.code = resultCode.getCode();
		this.msg = resultCode.getMessage();
	}

	public static MyException at(ResultCode resultCode) {
		MyException result = new MyException(resultCode);
		result.setResultCode(resultCode);
		return result;
	}


	public void setResultCode(ResultCode code) {
		this.code = code.getCode();
		this.msg  = code.getMessage();
	}



}