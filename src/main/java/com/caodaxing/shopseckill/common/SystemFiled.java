package com.caodaxing.shopseckill.common;

public class SystemFiled {
	
	/**登录CODE*/
	public static final String LOGIN_CODE = "300";
	/**登出CODE*/
	public static final String LOGOUT_CODE = "100";
	/**成功CODE*/
	public static final String SUCCESS_CODE = "200";
	/**失败CODE*/
	public static final String FAIL_CODE = "400";
	/**默认成功信息*/
	public static final String DEFAULT_SUCCESS_MSG = "执行成功";
	/**默认失败信息*/
	public static final String DEFAULT_FAIL_MSG = "执行失败";
	/**登录用户保存session*/
	public static final String USER_SESSION = "login_user";

	public enum DeleteStatus{
		NOT_DELETED(0,"未删除"),
		IS_DELETED(1,"已删除");

		private Integer code;
		private String status;
		DeleteStatus(Integer code, String status){
			this.code = code;
			this.status = status;
		}

		public Integer getCode() {
			return code;
		}

		public void setCode(Integer code) {
			this.code = code;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
	}

}
