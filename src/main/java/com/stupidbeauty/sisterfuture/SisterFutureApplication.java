package com.stupidbeauty.sisterfuture;

import com.stupidbeauty.dynamicwallpaper.service.MyLiveWallpaperService.MyEngine;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * 应用程序对象。
 * @author root 蔡火胜。
 *
 */
public class SisterFutureApplication extends Application
{
	@SuppressLint("StaticFieldLeak")
	private static Context mContext;
	    private MyEngine myEngine;


    public void setMyEngine(MyEngine engine) {
        this.myEngine = engine;
    }

    public MyEngine getMyEngine() {
        return myEngine;
    }

	@SuppressLint("StaticFieldLeak")
	private static SisterFutureApplication mInstance = null;

	public static SisterFutureApplication getInstance()
	{
      if (mInstance == null)
      {
        mInstance = new SisterFutureApplication();
      }
      return mInstance;
	}


	@Override
	/*
	  程序被创建。
	 */
	public void onCreate() 
	{
		super.onCreate(); //创建超类。
		mContext = getApplicationContext(); //获取应用程序上下文。 
		mInstance = this;
	} //public void onCreate()

	/**
	 * 获取应用程序上下文。
	 * @return 应用程序上下文。
	 */
	public static Context getAppContext() 
	{ 
		return mContext; 
	}  //public static Context getAppContext()
} //public class SisterFutureApplication extends Application

