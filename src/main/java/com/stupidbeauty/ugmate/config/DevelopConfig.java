package com.stupidbeauty.ugmate.config;

import com.stupidbeauty.sisterfuture.R;

/**
 * 开发环境的配置信息。
 * @author root 蔡火胜。
 *
 */
class DevelopConfig extends Config
{

	/**
	 * 构造函数。
	 */
	public DevelopConfig() 
	{
		debug = true;
		verbose = false;
		info = false;
		warn = false;
		error = true;

		//		String BaseUrl = "http://test5.skyroam.com.cn:8083/ismp-ehall"; //!<开发部门的测试版的BOSS地址。
		//String BaseUrl = "http://test5.skyroam.com.cn:8083/ismp-ehall";
//		String BaseUrl = "http://192.168.5.93:8080/ismp-ehall";
//		String BaseUrl = "https://192.168.5.93:8443/ismp-ehall";
//		String BaseUrl = "https://simowireless.sinaapp.com/appdoc"; //新浪网站上的HTTPS测试地址。 

		//		BASE_LOGIN_PATH = BaseUrl+ "/requestLogin.php?module=a"; //新浪网站上的HTTPS测试路径。
		//WEB_LOGIN_PATH  = BaseUrl+ "/recharge/recharge.do";


		//静态网页相关的参数：


		CONFIG_URL="http://client.skyroam.com.cn/app/config"; //检查配置文件更新的URL。


		//设定本地打包的配置文件的ID：
		packagedConfigFileID= R.raw.develop_config; //使用测试版本的配置文件。

	} //public DevelopConfig()
} //public class DevelopConfig extends Config
