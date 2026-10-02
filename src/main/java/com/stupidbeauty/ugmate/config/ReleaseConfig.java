package com.stupidbeauty.ugmate.config;

import com.stupidbeauty.sisterfuture.R;

/**
 * 针对线上发布版本的配置信息。
 * @author root 蔡火胜。
 *
 */
class ReleaseConfig extends Config
{


	/**
	 * 构造函数。
	 */
	public ReleaseConfig() 
	{
		debug = true;
		verbose = true;
		info = true;
		warn = true;
		error = true;

		//		String BaseUrl = "http://a.skyroam.com";


		// 静态网页相关的参数：

		CONFIG_URL="http://client.skyroam.com.cn/app/config"; //检查配置文件更新的URL。


		//设定本地打包的配置文件的ID：
		packagedConfigFileID= R.raw.release_config; //使用发布版本的配置文件。

	} //public ReleaseConfig() 
} //public class ReleaseConfig extends Config
