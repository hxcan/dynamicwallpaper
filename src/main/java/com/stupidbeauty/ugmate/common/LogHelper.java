package com.stupidbeauty.ugmate.common;

import android.util.Log;

import com.stupidbeauty.ugmate.config.Config;
import com.stupidbeauty.ugmate.logserver.ClientLogFileWriter;

/**
 * 日志类，可打印到控制台，也可以记录到文件
 * 
 * @author justin
 * 
 */
@SuppressWarnings({"WeakerAccess", "CanBeFinal", "unused"})
public class LogHelper
{
	public static boolean debug = Config.debug();
	public static boolean verbose = Config.verbose();
	public static boolean info = Config.info();
	public static boolean warn = Config.warn();
	public static boolean error = Config.error();

	private static ClientLogFileWriter logToFile; //!<客户端日志文件输入器。
	private static LogDumper mLogDumper; //!<日志转存器。

	/**
	 * 初始化本地日志工具。
	 * 	// 应用启动之后有了上下文对象可以获取手机的mac 再来启动本地存储
	 */
	public static void initLocalLogUtil() 
	{
		try //进行初始化，并且捕获可能的异常。 
		{
			logToFile = ClientLogFileWriter.getInstance(); //日志文件输出器。
			mLogDumper = new LogDumper(); //日志转储器。
			mLogDumper.start(); //启动日志转储器。启动线程。
		} //try //进行初始化，并且捕获可能的异常。
		catch (Exception e) //捕获异常。 
		{
			e.printStackTrace(); //报告错误。
			Log.e("LogHelper", "create ClientLogUtil Exception" + e); //Debug.
		} //catch (Exception e) //捕获异常。
	} //public static void initLocalLogUtil()

	/**
	 * 退出日志管理器辅助类。
	 */
	public static void exitLogHelper() 
	{
		if(mLogDumper!=null) //日志转存器存在。
		{
			mLogDumper.stopLogs(); //停止日志功能。
		} //if(mLogDumper!=null) //日志转存器存在。
		
		if(logToFile !=null) //日志文件输出器存在。
		{
			logToFile.closeFile(); //关闭文件。
		} //if(logToFile !=null) //日志文件输出器存在。
		
	} //public static void exitLogHelper()

	public static void androidLogDebug(String tag, String msg) {
		if(debug){
			android.util.Log.d(tag, msg);
		}
	}

	// Plz let all error log print out
	/**
	 * 调用安卓日志工具输出错误消息。
	 * @param tag 输出时使用的标记。
	 * @param msg 要输出的消息内容。
	 */
	public static void androidLogError(String tag, String msg) 
	{
		if (msg!=null) //Message is not null.
		{
			android.util.Log.e(tag, msg); //输出消息。
		} //if (msg!=null) //Message is not null.
	} //public static void androidLogError(String tag, String msg)

	// ==end

	public static void v(String tag, String msg) {
		if (verbose) {
			LogHelper.androidLogDebug(tag, msg);
		}
	}

	public static void v(String tag, String msg, Throwable tr) {
		if (verbose) {
			LogHelper.androidLogDebug(tag, msg);
		}
	}

	public static void v(String tag, String format, Object... args) {
		if (verbose) {
			String msg = String.format(format, args);
			LogHelper.androidLogDebug(tag, msg);
		}
	}

	/**
	 * 以十六进制打印字节数组信息
	 * 
	 * @param prefix
	 *            前缀
	 * @param data
	 *            字节数组
	 */
	public static void v(String tag, String prefix, byte[] data) {
		if (verbose) {
			if (data == null) {
				LogHelper.androidLogDebug(tag, prefix + "");
			} else {
				StringBuilder msg = new StringBuilder();
				int temp;
				for (byte aData : data) {
					temp = 0x000000FF & aData;
					if (temp <= 0x0F) {
						msg.append("0");
					}
					msg.append(Integer.toHexString(temp));
					msg.append(" ");
				}
				LogHelper.androidLogDebug(tag, prefix + msg.toString());
			}
		}
	}

	public static void d(String tag, String msg) {
		if (debug) {
			LogHelper.androidLogDebug(tag, msg);
		}
	}

	public static void d(String tag, String msg, Throwable tr) {
		if (debug) {
			LogHelper.androidLogDebug(tag, msg);
		}
	}

	/**
	 * 按指定的格式字符串输出调试信息。
	 * @param tag 类标记。
	 * @param format 格式字符串。
	 * @param args 要格式化输出的参数列表。
	 */
	public static void d(String tag, String format, Object... args) 
	{
		String msg; //最终产生的字符串。
		
		if (debug) //允许输出调试级别的信息。
		{
			msg = String.format(format, args); //进行格式化生成。
			LogHelper.androidLogDebug(tag, msg); //真正输出。
		} //if (debug) //允许输出调试级别的信息。
	} //public static void d(String tag, String format, Object... args)

	/**
	 * 以十六进制打印字节数组信息
	 * 
	 * @param prefix 前缀
	 * @param data 字节数组
	 */
	public static void d(String tag, String prefix, byte[] data) {
		StringBuilder msg = new StringBuilder();
		if (debug) {
			if (data == null) {
				LogHelper.androidLogDebug(tag, prefix + "");
			} else {
				int temp;
				for (byte aData : data) {
					temp = 0x000000FF & aData;
					if (temp <= 0x0F) {
						msg.append("0");
					}
					msg.append(Integer.toHexString(temp));
					msg.append(" ");
				}
				LogHelper.androidLogDebug(tag, prefix + msg.toString());
			}
		}
	}

	public static void i(String tag, String msg) {
		if (info) {
			android.util.Log.i(tag, msg);
		}
	}

	public static void i(String tag, String msg, Throwable tr) {
		if (info) {
			android.util.Log.i(tag, msg, tr);
		}
	}

	public static void i(String tag, String format, Object... args) {
		String msg;
		if (info) {
			msg = String.format(format, args);
			android.util.Log.i(tag, msg);
		}
	}

	/**
	 * 以十六进制打印字节数组信息
	 * 
	 * @param prefix
	 *            前缀
	 * @param data
	 *            字节数组
	 */
	public static void i(String tag, String prefix, byte[] data) {
		StringBuilder msg = new StringBuilder();
		if (info) {
			if (data == null) {
				android.util.Log.i(tag, prefix + "");
			} else {
				int temp;
				for (byte aData : data) {
					temp = 0x000000FF & aData;
					if (temp <= 0x0F) {
						msg.append("0");
					}
					msg.append(Integer.toHexString(temp));
					msg.append(" ");
				}
				android.util.Log.i(tag, prefix + msg.toString());
			}
		}
	}

	public static void w(String tag, String msg) {
		if (warn) {
			android.util.Log.w(tag, msg);
		}
	}

	public static void w(String tag, String msg, Throwable tr) {
		if (warn) {
			android.util.Log.w(tag, msg, tr);
		}
	}

	public static void w(String tag, String format, Object... args) {
		if (warn) {
			String msg = String.format(format, args);
			android.util.Log.w(tag, msg);
		}
	}

	/**
	 * 以十六进制打印字节数组信息
	 * 
	 * @param prefix
	 *            前缀
	 * @param data
	 *            字节数组
	 */
	public static void w(String tag, String prefix, byte[] data) {
		if (warn) {
			if (data == null) {
				android.util.Log.i(tag, prefix + "");
			} else {
				StringBuilder msg = new StringBuilder();
				int temp;
				for (byte aData : data) {
					temp = 0x000000FF & aData;
					if (temp <= 0x0F) {
						msg.append("0");
					}
					msg.append(Integer.toHexString(temp));
					msg.append(" ");
				}
				android.util.Log.i(tag, prefix + msg.toString());
			}
		}
	}

	public static void e(String tag, String msg) {
		LogHelper.androidLogError(tag, msg);
	}

	// for release log
	public static void r(String tag, String msg) {
		android.util.Log.d(tag, msg);
	}

	/**
	 * Output message with log level "error".
	 * @param tag The tag used to identify the log message provider.
	 * @param msg The message content.
	 * @param tr The throwable related.
	 */
	public static void e(String tag, String msg, Throwable tr) 
	{
		if (error) //Should output "error " message. 
		{
			LogHelper.androidLogError(tag, msg); //Output the message.
		} //if (error) //Should output "error " message.
	} //public static void e(String tag, String msg, Throwable tr)

	public static void e(String tag, String format, Object... args) {
		String msg;
		if (error) {
			msg = String.format(format, args);
			LogHelper.androidLogError(tag, msg);
		}
	}

	public static void writeToFile(String msg) {
		if (logToFile != null) {
			logToFile.writeFileLog(msg + "\n");
		}
	}

	/**
	 * 以十六进制打印字节数组信息
	 * 
	 * @param prefix
	 *            前缀
	 * @param data
	 *            字节数组
	 */
	public static void e(String tag, String prefix, byte[] data) {
		StringBuilder msg = new StringBuilder();
		if (error) {
			if (data == null) {
				android.util.Log.i(tag, prefix + "");
			} else {
				int temp;
				for (byte aData : data) {
					temp = 0x000000FF & aData;
					if (temp <= 0x0F) {
						msg.append("0");
					}
					msg.append(Integer.toHexString(temp));
					msg.append(" ");
				}
				android.util.Log.i(tag, prefix + msg.toString());
			}
		}
	}

	/**
	 * 打印信息至控制台
	 * 
	 * @param msg
	 *            信息
	 */
	public static void print(String msg) {
		LogHelper.androidLogDebug("sisterfuture", msg);
	}

	/**
	 * 打印信息至控制台
	 *
	 */
	public static void printf(String format, Object... args) {
		String str = String.format(format, args);
		android.util.Log.i("sisterfuture", str);
	}

	/**
	 * 记录信息至文件
	 * 
	 * @param msg
	 *            信息
	 */
	public static void log(String msg) {
		LogHelper.androidLogDebug("sisterfuture", msg);
	}

	/**
	 * 打印信息至控制台
	 *
	 */
	public static void logf(String format, Object... args) 
	{
		if(debug) //调试开关被开启。
		{
			String str = String.format(format, args); //格式化成一个字符串。
			android.util.Log.i("sisterfuture", str); //输出。
		} //if(debug) //调试开关被开启。		
	} //public static void logf(String format, Object... args)

	/**
	 * 以十六进制打印字节数组信息
	 * 
	 * @param flag
	 *            log标签
	 * @param data
	 *            字节数组
	 */
	public static void log16(String flag, byte[] data) {
		StringBuilder str = new StringBuilder();
		int temp;
		for (byte aData : data) {
			temp = 0x000000FF & aData;
			if (temp <= 0x0F) {
				str.append("0");
			}
			str.append(Integer.toHexString(temp));
			str.append(" ");
		}
		android.util.Log.i("log16", flag + ": " + str.toString());
	}

}
