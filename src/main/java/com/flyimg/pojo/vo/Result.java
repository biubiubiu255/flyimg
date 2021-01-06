package com.flyimg.pojo.vo;

import com.alibaba.fastjson.JSONObject;
import com.flyimg.comm.enums.ResultCode;
import lombok.Data;

/**
 * 页面响应entity
 * @author  ahser
 * 创建时间	2020年03月21日
 */
@Data
//@ApiModel("返回类")
public class Result {

	private Integer code;

	private String msg;

	private Object data;

	public Result() {}

	public Result(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static Result success() {
		Result result = new Result();
		result.setResultCode(ResultCode.SUCCESS);
		return result;
	}

	public static Result success(Object data) {
		Result result = new Result();
		result.setResultCode(ResultCode.SUCCESS);
		result.setData(data);
		return result;
	}

	public static Result failure(ResultCode resultCode) {
		Result result = new Result();
		result.setResultCode(resultCode);
		return result;
	}

	public static Result failure(ResultCode resultCode, Object data) {
		Result result = new Result();
		result.setResultCode(resultCode);
		result.setData(data);
		return result;
	}


	public void setResultCode(ResultCode code) {
		this.code = code.getCode();
		this.msg  = code.getMessage();
	}

	@Override
	public String toString() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", this.code);
		jsonObject.put("msg",  this.msg);
		jsonObject.put("data", this.data);
		return jsonObject.toString();
	}




}