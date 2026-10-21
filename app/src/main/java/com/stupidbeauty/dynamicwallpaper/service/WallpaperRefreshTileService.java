package com.stupidbeauty.dynamicwallpaper.service;
import android.os.Build;
import android.service.quicksettings.TileService;
import android.widget.Toast;
import com.stupidbeauty.dynamicwallpaper.service.MyLiveWallpaperService;
import com.stupidbeauty.hxlauncher.interfaces.ShutDownAt2100LogicInterface;
import com.stupidbeauty.hxlauncher.logic.ShutDownAt2100Logic;
import com.stupidbeauty.sisterfuture.SisterFutureApplication;
import java.util.Calendar;
public class WallpaperRefreshTileService extends TileService implements ShutDownAt2100LogicInterface {
    private static final int SHUTDOWN_START_HOUR = 21;
    private static final int SHUTDOWN_END_HOUR = 6;
    private boolean isInShutdownPeriod() {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour >= SHUTDOWN_START_HOUR) {
            return true;
        }
        if (hour < SHUTDOWN_END_HOUR) {
            return true;
        }
        return false;
    }
    @Override
    public void onClick() {
        super.onClick();
        if (isInShutdownPeriod()) {
            Toast.makeText(this, "当前是21点关机时段，无法切换壁纸", Toast.LENGTH_SHORT).show();
            return;
        }
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
     * */
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