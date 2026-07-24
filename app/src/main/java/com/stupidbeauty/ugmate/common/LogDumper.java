package com.stupidbeauty.ugmate.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 日志转存器。
 * @author root 蔡火胜。
 *
 * 
 * 日志等级：*:v , *:d , *:w , *:e , *:f , *:s
 * 
 * 显示当前mPID程序的 E和W等级的日志.
 * 
 */
@SuppressWarnings({"WeakerAccess", "CanBeFinal"})
public class LogDumper extends Thread
{
	private Process logcatProc; //!<logcat进程。
	private BufferedReader mReader = null;
	private boolean mRunning = true; //!<是否处于运行状态。
	String cmds; //!<用于读取日志文件的命令。
	private String mPID; //!<本进程的进程号。

	/**
	 * 构造函数。
	 */
	public LogDumper() 
	{
		mPID = String.valueOf(android.os.Process.myPid()); //获取本进程的进程号。

		cmds = "logcat | grep \"(" + mPID + ")\""; //!<用于读取日志文件的命令。
	} //public LogDumper()

	/**
	 * 停止日志功能。
	 */
	public void stopLogs() 
	{
		mRunning = false; //不再处于运行状态了。
	} //public void stopLogs()

	@Override
	public void run() 
	{
		try 
		{
			logcatProc = Runtime.getRuntime().exec(cmds);
			mReader = new BufferedReader(new InputStreamReader(logcatProc.getInputStream()), 1024);
			String line;
			while (mRunning && (line = mReader.readLine()) != null) 
			{
				if (!mRunning) 
				{
					break;
				}
				if (line.length() == 0) 
				{
					continue;
				}
				if (line.contains(mPID)) 
				{
					LogHelper.writeToFile(line);
				}
				yield();
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if (logcatProc != null) 
			{
				logcatProc.destroy();
				logcatProc = null;
			}
			if (mReader != null) 
			{
				try 
				{
					mReader.close();
					mReader = null;
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
} //public class LogDumper extends Thread

