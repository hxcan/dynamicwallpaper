package com.stupidbeauty.dynamicwallpaper.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.service.quicksettings.TileService;
import com.stupidbeauty.dynamicwallpaper.service.MyLiveWallpaperService;
import com.stupidbeauty.sisterfuture.SisterFutureApplication;

public class WallpaperRefreshTileService extends TileService {

    @Override
    public void onClick() {
        super.onClick();

        // 获取 MyEngine 实例
        SisterFutureApplication app = (SisterFutureApplication) getApplicationContext();
        MyLiveWallpaperService.MyEngine engine = (MyLiveWallpaperService.MyEngine) app.getMyEngine();

        if (engine != null) {
            engine.reloadWallpaper(false); // ✅ 换壁纸
        }

        // ✅ 触发 21点关机逻辑
        start21ClockShutdownService();
    }

    private void start21ClockShutdownService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(
            "com.stupidbeauty.shutdownat2100androidnative",
            "com.stupidbeauty.shutdownat2100androidnative.TimeCheckService"
        ));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent);
        } else {
            startService(intent);
        }
    }

    @Override
    public void onTileAdded() {
        super.onTileAdded();
    }

    @Override
    public void onStartListening() {
        super.onStartListening();
        // 可选：启动服务
    }

    @Override
    public void onStopListening() {
        super.onStopListening();
        // 可选：停止服务
    }

    @Override
    public void onTileRemoved() {
        super.onTileRemoved();
    }
}
