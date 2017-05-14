package cn.jxust.blog.constant;

public enum AdminStatus {
	LOCK("1","锁定"),
	UNLOCK("0","正常");
	
	private String code;
	private String desc;
	
	AdminStatus(String code,String desc){
		this.code = code;
		this.desc = desc;
	}
	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
