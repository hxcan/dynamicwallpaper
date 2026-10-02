package com.stupidbeauty.dynamicwallpaper.service;
import android.os.Build;
import android.service.quicksettings.TileService;
import com.stupidbeauty.dynamicwallpaper.service.MyLiveWallpaperService;
import com.stupidbeauty.hxlauncher.interfaces.ShutDownAt2100LogicInterface;
import com.stupidbeauty.hxlauncher.logic.ShutDownAt2100Logic;
import com.stupidbeauty.sisterfuture.SisterFutureApplication;
public class WallpaperRefreshTileService extends TileService implements ShutDownAt2100LogicInterface {
    @Override
    public void onClick() {
        super.onClick();
        // 获取 MyEngine 实例
        SisterFutureApplication app = (SisterFutureApplication) getApplicationContext();
        MyLiveWallpaperService.MyEngine engine = (MyLiveWallpaperService.MyEngine) app.getMyEngine();
        if (engine != null) {
            engine.reloadWallpaper(false); // ✅ 换壁纸
        }
        // ✅ 触发 21点关机逻辑（按灵桌面的新方式，使用 ShutDownAt2100Logic）
        ShutDownAt2100Logic shutDownAt2100Logic = new ShutDownAt2100Logic(this);
        shutDownAt2100Logic.checkShutDownTime();
    }

    /**
     * 实现 ShutDownAt2100LogicInterface 接口。
     * 当前 TileService 暂不实现下载安装逻辑，返回 false。
     */
    @Override
    public boolean requestDownloadApk(String shutDownAt2100PackageName) {
        return false;
    } // public boolean requestDownloadApk(String shutDownAt2100PackageName)

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
