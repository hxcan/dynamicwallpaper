package com.stupidbeauty.ugmate.config;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.stupidbeauty.sisterfuture.SisterFutureApplication;
import com.stupidbeauty.ugmate.common.LogHelper;
import com.stupidbeauty.x2app.Constants;

 
/**
 * 配置信息类。
 * @author root
 *
 */
@SuppressWarnings({"FieldCanBeLocal", "WeakerAccess", "CanBeFinal"})
public class Config
{
	private static final int ENVIRONMENT_RELEASE = 1; //!<当前是要打包出发布版本。
	private static final int ENVIRONMENT_BETA = 2; //!<当前是要打包出测试版本。
	private static final String TAG = "Config"; //!<输出调试信息时使用的标记。
	
	// control
	public static int environmentConfig = ENVIRONMENT_BETA; //!<所要使用的环境配置。发布/测试/开发。

	// define
	public boolean debug = true; //!<输出调试信息。
	public boolean verbose = false; //!<输出详细跟踪信息。
	public boolean info = false; //!<输出信息型调试消息。
	public boolean warn = false; //!<输出警告消息。
	public boolean error = true; //!<输出错误消息。


	public 	int packagedConfigFileID; //!<已打包的配置文件的ID。
	public String CONFIG_URL; //!<配置文件检查更新的URL。

	// private
	private static Config mConfig; //!<实际的配置对象实例。

	/**
	 * 获取共享的单实例。
	 * @return 共享的单实例。
	 */
	public static Config shareInstance() 
	{
		if (mConfig == null) //配置信息实例还不存在。 
		{
			switch (environmentConfig) {
				case ENVIRONMENT_RELEASE:
//是发布版本。

					mConfig = new ReleaseConfig(); //创建发布版本的配置信息。

					break;
				case ENVIRONMENT_BETA:
//是测试版本。

					mConfig = new BetaConfig(); //创建测试版本的配置信息。

					break;
				default:
//是开发版本。

					mConfig = new DevelopConfig(); //创建开发版本的配置信息。

					break;
			}
			
			
			mConfig.loadConfig(); //载入配置文件。
			
		}
		return mConfig;

	} //public static Config shareInstance()

	/**
	 * 载入本地的配置文件。
	 */
	private void loadConfig() 
	{

		if (localConfigExists()) //存在着已经从更新服务器下载到的配置文件。
		{
			LogHelper.d(TAG,"载入本地已经下载的配置文件。"); //Debug.
			loadLocalConfig(); //载入已经下载的配置文件。
		} //if (localConfigExists()) //存在着已经从更新服务器下载到的配置文件。
		else //不存在已经下载的配置文件。
		{
			LogHelper.d(TAG,"载入打包的配置文件。"); //Debug.
			loadPackagedConfig(); //载入已经打包的配置文件。
		} //else //不存在已经下载的配置文件。
		
		
	} //private void loadConfig() 

	/**
	 * 载入预先打包的版本的配置文件。
	 */
	private void loadPackagedConfig() 
	{
		Context context= SisterFutureApplication.getAppContext(); //获取应用程序的上下文。
		
		InputStream myFile=context.getResources().openRawResource(packagedConfigFileID); //打开文件流。
		
		StringBuilder configFileContent= new StringBuilder(); //结果。
		
		BufferedReader br = new BufferedReader(new InputStreamReader(myFile)); // 带缓冲的读取器。
		String line; // 当前读取的行。
		try 
		{
		
			while ((line = br.readLine()) != null) // 未读完。
			{
				configFileContent.append(line).append("\n"); // 加入到结果中。
			}
			
			System.err.println("loadPackagedConfig,即将应用配置文件JSON："+configFileContent); //Debug.

			
			applyConfigJson(configFileContent.toString()); //应用配置文件JSON的内容。
		}
		catch (IOException e)  //捕获输入输出异常。
		{
			e.printStackTrace();
		} // while ((ch=is.read(buf)) !=-1)


		
	} //private void loadPackagedConfig()

	/**
	 * 载入已经下载的配置文件。
	 */
	private void loadLocalConfig() 
	{
		try //进行文件操作。
		{
			Context context= SisterFutureApplication.getAppContext(); //获取应用程序的上下文。
			
			FileInputStream fin=context.openFileInput(Constants.Config.LOCAL_CONFIG_FILE_NAME); //打开配置文件。
			
			BufferedReader br = new BufferedReader(new InputStreamReader(fin)); // 带缓冲的读取器。
			
			String line; //当前读入的一行内容。
			
			StringBuilder configFileContent= new StringBuilder(); //配置文件的内容。
			
			while ((line = br.readLine()) != null) // 未读完。
			{
				configFileContent.append(line).append("\n"); // 加入到结果中。
			} //while ((line = br.readLine()) != null) // 未读完。
			
			applyConfigJson(configFileContent.toString()); //应用配置文件JSON的内容。
		} //try //进行文件操作。
		catch (Exception e)
		{
			e.printStackTrace(); //报告错误。
		} //catch (Exception e)
	} //private void loadLocalConfig()

	/**
	 * 将从JSON配置文件中读入的内容应用到配置信息对象中。
	 * @param configFileContent 要应用的JSON字符串。
	 */
	public void applyConfigJson(String configFileContent) 
	{
		try //尝试进行解析。 
		{
			JSONObject jsonObj = new JSONObject(configFileContent); // 创建JSON对象。
			
			applyConfigJson(jsonObj); //应用配置。
			

				
				
			
		} //try //尝试进行解析。
		catch (JSONException e) 
		{
			e.printStackTrace();
			
			
			System.err.println("等解析的数据："+configFileContent); //Debug.
		}				//catch (JSONException e)

		
	} //private void applyConfigJson(String configFileContent)

	public void applyConfigJson(JSONObject jsonObj)  
	{
		try {
			CONFIG_URL=jsonObj.getString("config_url"); //配置文件检查更新的URL。
			
			
			LogHelper.d(TAG,"配置文件更新地址："+CONFIG_URL); //Debug.
			
		} catch (JSONException e) {
			e.printStackTrace();
		}//客户端发送登录请求的URL。					
		
	}

	/**
	 * 是否已经从配置服务器下载到了配置文件。
	 * @return 是否已经从配置服务器下载到了配置文件。
	 */
	private boolean localConfigExists() 
	{
		boolean result= false; //结果。

		Context context= SisterFutureApplication.getAppContext(); //获取应用程序的上下文。
		
		String [] fileNameArray = context.fileList(); //文件名数组。

		for (String aFileNameArray : fileNameArray) {
			if (aFileNameArray.equals(Constants.Config.LOCAL_CONFIG_FILE_NAME)) //正是这个文件。
			{
				result = true; //存在。
			} //if(fileNameArray[fileCounter].equals(fileName)) //正是这个文件。
		} //for(int i = 0; i< fileNameArray.length; i++) //一个个地检查。
		
	
		  return result;
	} //private boolean localConfigExists()

	/**
	 * 是否输出话痨级别的调试信息。
	 * @return 是否输出话痨级别的调试信息。
	 */
	public static boolean verbose() 
	{
		return Config.shareInstance().verbose;
	} //public static boolean verbose()

	
	/**
	 * 是否输出调试级别的调试信息。
	 * @return 是否输出调试级别的调试信息。
	 */
	public static boolean debug() 
	{
		return Config.shareInstance().debug;
	} //public static boolean debug() 

	/**
	 * 是否输出信息级别的调试信息。
	 * @return 是否输出信息级别的调试信息。
	 */
	public static boolean info() 
	{
		return Config.shareInstance().info;
	} //public static boolean info() 

	/**
	 * 是否输出错误级别的调试信息。
	 * @return 是否输出错误级别的调试信息。
	 */
	public static boolean error() 
	{
		return Config.shareInstance().error;
	} //public static boolean error()

	/**
	 * 是否输出警告级别的调试信息。
	 * @return 是否输出警告级别的调试信息。
	 */
	public static boolean warn() 
	{
		return Config.shareInstance().warn;
	} //public static boolean warn() 

} //public class Config

