package com.stupidbeauty.x2app;


import com.stupidbeauty.ugmate.service.NlpChatData;

/**
 * RequestConfig这个request所返回的回复对象。
 * @author root 蔡火胜。
 *
 */
public class BossResponse 
{
	private String msg; //!<服务器返回的对于当前结果的解释。

	public NlpChatData getData() {
		return data;
	}

	private NlpChatData data; //!<回复数据。

	/**
	 * 获取其它平板的IP列表。
	 * @return 其它平板的IP列表。
	 */
	public String getMsg() 
	{
		return msg;
	} //public ArrayList<String> getOtherIpList()

} //public class ReportLanIpResponse
