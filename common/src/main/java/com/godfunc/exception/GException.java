package com.godfunc.exception;

import com.godfunc.result.ApiCode;
import com.godfunc.result.ApiCodeMsg;

/**
 * 自定义异常
 * @author godfunc
 * @email godfunc@outlook.com
 */
public class GException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String msg;
    private long code = ApiCode.FAIL;
    
    public GException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public GException(ApiCodeMsg apiCodeMsg) {
		super(apiCodeMsg.getMsg());
		this.msg = apiCodeMsg.getMsg();
		this.code = apiCodeMsg.getCode();
	}
	
	public GException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public GException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}
	
	public GException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}
}
