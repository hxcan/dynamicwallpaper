package com.stupidbeauty.ugmate.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.stupidbeauty.ugmate.service.NlpChatData;

/**
 * 自动启动广播接收器。
 * @author root 蔡火胜。
 *
 */
public class AutoStartReceiver extends BroadcastReceiver 
{
	@Override
	/*
	  接收到广播。
	 */
	public void onReceive(Context context, Intent intent) 
	{
		if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) //启动完毕。 
		{
			// 启动服务
			context.startService(new Intent(context, NlpChatData.class)); //启动后台服务。
		} //if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) //启动完毕。
	} //public void onReceive(Context context, Intent intent)
} //public class AutoStartReceiver extends BroadcastReceiver
