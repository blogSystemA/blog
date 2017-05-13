package cn.jxust.redis;

/**
 * Redis分组定义
 */
public enum JedisCacheGroup {

	//公积金登录任务队列
	FUNDHOUSE_LOGINTASK_QUEUE("FundHouse:LQ", -1, true),
	//公积金登录接口
	FUNDHOUSE_API("FundHouse", 10 * 60, true),
	//公积金session
	FUNDHOUSE_SESSION("FundHouse:Session", 10 * 60 * 12, true),
	//公积金当前正在执行的任务
	FUNDHOUSE_CRAWLER("FundHouse:Crawler", 1 * 60, true),
	//公积金推送任务队列
	FUNDHOUSE_NOTIFYTASK_QUEUE("FundHouse:Notify", -1, true),
	//公积金日志队列
	FUNDHOUSE_LOG_QUEUE("FundHouse:Log", -1, false),
	//公积金预警
	FUNDHOUSE_WARNING("FundHouse:Warning", 1 * 60, true),
    //百度图片识别
	FUNDHOUSE_BAIDU_OCR("FundHouse:BaiDuOCR",30 * 24 * 60 * 60,true),
	//公积金下发数据任务队列
	FUNDHOUSE_DATAPULLTASK_QUEUE("FundHouse:pullData", 30 * 24 * 60 * 60, false);
	
	private String groupName;
	private int timeOut;
	// 是否加入索引
	private boolean hasIndex;

	JedisCacheGroup(String groupName, int timeOut, boolean hasIndex) {
		this.groupName = groupName;
		this.timeOut = timeOut;
		this.hasIndex = hasIndex;
	}

	public boolean isHasIndex() {
		return hasIndex;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
