/**   
 * @Title: BaseData.java 
 * @Package cn.cash360.bean 
 * @Description: TODO 
 * @author DragonBall
 * @date 2015-5-11 ����12:06:37 
 * @version V1.0   
 */
package com.example.hello_okhttp;

import java.io.Serializable;

/**
 * @ClassName: BaseData
 * @date 2015-5-11 ����12:06:37
 * 
 */
public class BaseData<T> implements Serializable{


	private static final long serialVersionUID = 7676728193003214508L;
	private String returnMsg;
	private int returnCode;
	private String systemMsg;
	/**
	 * 系统返回的code
	 */
	private int systemCode;

	public boolean isSuccess() {
		return returnCode == 0 && systemCode == 9999;
	}

	private T t;

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	public String getSystemMsg() {
		return systemMsg;
	}

	public void setSystemMsg(String systemMsg) {
		this.systemMsg = systemMsg;
	}

	public int getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(int systemCode) {
		this.systemCode = systemCode;
	}

}
