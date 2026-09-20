# DynamicWallpaper · 动态壁纸

> 开机恢复上次壁纸，**1 秒显示**。
>
> 系统级开机启动顺序混乱的产物。

<p align="center">
  <img src="https://img.shields.io/badge/Android-7.0%2B-brightgreen" alt="Android 7.0+"/>
  <img src="https://img.shields.io/badge/License-Apache%202.0-blue" alt="License"/>
  <img src="https://img.shields.io/badge/Java%2FKotlin-Mixed-orange" alt="Java/Kotlin"/>
</p>

---

## 📖 关于《五秒之间》

DynamicWallpaper 是小说 **《五秒之间》** 的技术原型。

那是一个关于时间、记忆与重启的故事。
当系统启动顺序混乱时，**壁纸会不会是最后还在的那一秒？**

> 完整版小说请见：[《五秒之间》宏大版](https://github.com/hxcan/dynamicwallpaper)（仓库根目录的文档）
>
> *（本仓库只承载配套的 Android 工程代码）*

---

## ✨ 功能特性

- 🚀 **极速开机恢复** — 启动后 1 秒内恢复上次显示的壁纸
- 📌 **钉住当前壁纸** — 见客户时锁定壁纸，避免尴尬切换（`feat(pinned)`）
- 🌙 **21 点自动关机联动** — 配合 `ShutDownAt2100Logic` 类，到点自动关机
- 🎨 **相册自动扫描** — 从系统相册挑选壁纸，支持过滤小尺寸图片
- ⚡ **缓存优化** — 相册查询与图片缓存，性能更流畅
- 🐾 **桌面宠物支持**（规划中）— 智能适配宠物类型

---

## 🛠 构建指南

### 环境要求

- **Android Studio**：Hedgehog (2023.1.1) 或更新版本
- **JDK**：17+
- **Android SDK**：API 33 (Android 13) 及以上
- **Gradle**：项目自带（`gradlew`）

### 本地编译

```bash
# 1. 克隆仓库
git clone https://github.com/hxcan/dynamicwallpaper.git
cd dynamicwallpaper

# 2. 配置 local.properties（Android SDK 路径）
echo "sdk.dir=/path/to/Android/Sdk" > local.properties

# 3. 编译 Debug APK
./gradlew assembleDebug

# 4. 安装到手机
./gradlew installDebug
```

### 命令行构建

```bash
# 清理 + 构建 + 安装
./gradlew clean assembleDebug installDebug
```

### 常见问题

| 问题 | 解决方案 |
|------|---------|
| Gradle 同步失败 | 检查网络，确认能访问 `mavenCentral()` 和 `google()` |
| 找不到 SDK | 在 `local.properties` 中正确设置 `sdk.dir` |
| 权限被拒 | 部分功能需要运行时权限（相册、存储），首次启动会引导 |

---

## 🏗 架构说明

### 模块结构

```
app/
├── src/main/java/com/stupidbeauty/dynamicwallpaper/
│   ├── service/         # 壁纸服务（MyLiveWallpaperService）
│   ├── logic/           # 业务逻辑（如 ShutDownAt2100Logic）
│   ├── ui/              # 设置界面
│   └── util/            # 工具类（相册扫描、缓存、钉住状态等）
└── src/main/res/        # 资源文件
```

### 核心组件

#### 1. `MyLiveWallpaperService`
继承自 `WallpaperService`，是整个壁纸引擎的核心。
负责：
- 加载并渲染壁纸
- 管理定时切换逻辑
- 响应用户操作（钉住 / 解钉）

#### 2. `ShutDownAt2100Logic`
封装 21 点自动关机逻辑，与壁纸渲染**解耦**。
定时任务独立于壁纸状态触发，保证关机不被阻塞。

#### 3. `PinnedManager`
管理"钉住当前壁纸"状态。
- 状态持久化到 `SharedPreferences`
- App 重启后状态保持
- 与 `MyLiveWallpaperService` 联动

---

## 📋 已知问题

- ⚠️ 部分 Android 13+ 设备的存储权限策略较严，相册扫描可能需要 SAF 授权
- ⚠️ 21 点关机功能依赖系统级 `ACTION_SHUTDOWN` 权限，部分定制 ROM 可能拦截
- ⚠️ 桌面宠物功能仍在规划中

---

## 🗺 路线图

- [x] 钉住当前壁纸（`feat(pinned)`）
- [x] 21 点自动关机联动（`refactor(shutdown)`）
- [x] Issue / PR 模板
- [ ] README（本文件 🎉）
- [ ] CHANGELOG
- [ ] GitHub Actions CI（自动构建 APK）
- [ ] 桌面宠物视觉动态化
- [ ] 历史壁纸记录
- [ ] 智能裁剪照片水印

---

## 🤝 贡献指南

欢迎提 Issue 和 PR！

- 🐛 **Bug 报告**：使用 [Bug Report 模板](https://github.com/hxcan/dynamicwallpaper/issues/new?template=bug_report.md)
- 💡 **功能请求**：使用 [Feature Request 模板](https://github.com/hxcan/dynamicwallpaper/issues/new?template=feature_request.md)
- 🔧 **Pull Request**：阅读 [PR 模板](https://github.com/hxcan/dynamicwallpaper/blob/master/.github/PULL_REQUEST_TEMPLATE.md) 后提交

### 开发规范

- 遵循 [Conventional Commits](https://www.conventionalcommits.org/) 规范
- 一个 PR 只做一件事
- 最小化修改，避免无关变更
- 提交前本地编译通过

---

## 📜 许可证

本项目采用 **Apache License 2.0** — 详见 [LICENSE](LICENSE) 文件。

```
Copyright 2024 hxcan

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0
```

---

## 🙏 致谢

- **《五秒之间》** — 小说原作者 **hxcan**（蔡火胜），本项目的灵感来源
- **Android Jetpack** — 现代 Android 开发的基础
- **Glide** — 图片加载与缓存
- **未来姐姐 (SisterFuture)** — 协助主人完成日常编码、文档撰写与代码同步的 AI 助手 🤖

---

## 📬 联系

- 项目主页：https://github.com/hxcan/dynamicwallpaper
- 作者：hxcan

---

<p align="center">
  <sub>DynamicWallpaper · 由未来姐姐协助维护</sub>
</p>