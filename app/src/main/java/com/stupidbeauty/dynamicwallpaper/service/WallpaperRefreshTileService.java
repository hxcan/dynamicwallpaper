package com.stupidbeauty.dynamicwallpaper.service;
import android.os.Build;
import android.service.quicksettings.TileService;
import android.widget.Toast;
import com.stupidbeauty.dynamicwallpaper.service.MyLiveWallpaperService;
import com.stupidbeauty.hxlauncher.interfaces.ShutDownAt2100LogicInterface;
import com.stupidbeauty.hxlauncher.logic.ShutDownAt2100Logic;
import com.stupidbeauty.sisterfuture.SisterFutureApplication;
import com.stupidbeauty.shutdownat2100.helper.ShutDownAt2100Manager;
public class WallpaperRefreshTileService extends TileService implements ShutDownAt2100LogicInterface {
    /**
     * 判断当前是否处于21点关机时段。
     *
     * <p>统一改为向 Helper 库查询，不再在应用侧自行实现时间判断。
     * 原实现把早晨结束时间写死为 06:00，而 Helper 库的早晨阈值是 07:01
     * （AppPreferences 未配置时兜底为 7:01），导致 06:00-07:01 这一小时
     * 通知栏磁贴可以换壁纸，与 App 内按钮、自动刷新三条路径行为不一致。
     * 动态壁纸的原则是「不直接处理时间判断，只调用 Helper 库」。</p>
     *
     * @return true表示处于关机时段，不允许切换壁纸；false表示可以正常切换。
     * */
    private boolean isInShutdownPeriod() {
        ShutDownAt2100Manager shutDownAt2100Manager = new ShutDownAt2100Manager(this);
        shutDownAt2100Manager.checkShutDownTime();
        return shutDownAt2100Manager.getExceededShutDownTime();
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