# Changelog

动态壁纸项目的所有重要变更都会记录在这个文件。

格式基于 [Keep a Changelog](https://keepachangelog.com/zh-CN/1.1.0/)，
本项目遵循 [语义化版本](https://semver.org/lang/zh-CN/)。

---

## [Unreleased]

### 待发布
- README 文档完善
- CHANGELOG 初始化（本文件）

---

## [0.3.0] - 2026-09-18

### ✨ 新增（Added）
- **钉住当前壁纸功能**：在见客户等场景下，可锁定当前壁纸避免尴尬切换
  - 设置界面新增"钉住"开关
  - 钉住状态持久化到 SharedPreferences，重启 App 后保持
  - 切换钉住状态时立即生效，无需重启
- **外置存储配置读取权限**（部分实现）：为读取 21 点关机配置做准备
- **Issue / PR 模板**：添加 `.github/ISSUE_TEMPLATE/` 和 `PULL_REQUEST_TEMPLATE.md`
- **仓库 Topics**：`android`, `dynamic-wallpaper`, `java`, `kotlin`, `live-wallpaper`, `wallpaper`

### 🔧 重构（Refactored）
- **21 点关机逻辑**：从直接调用 `Intent.ACTION_SHUTDOWN` 重构为独立类 `ShutDownAt2100Logic`
  - 与壁纸渲染逻辑解耦
  - 修复"壁纸锁定时阻断 21 点关机"的 Bug
  - 后续系统类动作（音量、亮度等）独立调度

### 📝 文档（Documentation）
- 完善代码注释
- 添加 `CONTRIBUTING.md` 风格的开发规范说明

---

## [0.2.0] - 2026-07-24

### ✨ 新增（Added）
- **GitHub 仓库开源**：项目从本地迁移到 https://github.com/hxcan/dynamicwallpaper
- **Apache 2.0 License**：仓库采用 Apache License 2.0
- **标准 `.gitignore`**：排除 `build/`、`.gradle/`、`local.properties` 等
- **钉住当前壁纸 UI 控件**（基础版）

### 🐛 修复（Fixed）
- 修复相册扫描性能问题
- 修复开机后壁纸恢复延迟问题

---

## [0.1.0] - 2026-04-20（Initial Commit）

### ✨ 新增（Added）
- 项目初始版本
- **核心功能**：动态壁纸服务（`MyLiveWallpaperService`）
- **开机恢复**：启动后快速恢复上次显示的壁纸
- **相册扫描**：从系统相册自动选取壁纸
- **定时切换**：周期性切换壁纸内容
- **基础设置界面**：`SisterFutureActivity`

### 🛠 技术栈
- Android 7.0+ (API 24+)
- Java + Kotlin 混合开发
- Gradle 构建系统
- WallpaperService 框架

---

## 版本号规则

本项目采用 [语义化版本](https://semver.org/lang/zh-CN/)：

- **MAJOR**：不兼容的 API 变更
- **MINOR**：向下兼容的功能新增
- **PATCH**：向下兼容的问题修复

预发布版本使用 `-alpha.N` / `-beta.N` 后缀。

---

## 变更类型说明

- `Added` ✨ - 新增功能
- `Changed` 🔄 - 现有功能变更
- `Deprecated` ⚠️ - 即将移除的功能
- `Removed` ❌ - 已移除的功能
- `Fixed` 🐛 - Bug 修复
- `Security` 🔒 - 安全相关修复
- `Refactored` 🔧 - 代码重构（无功能变更）
- `Documentation` 📝 - 仅文档变更

---

## 关联链接

- 📖 [README](README.md)
- 🐛 [Issue 跟踪](https://github.com/hxcan/dynamicwallpaper/issues)
- 🔀 [Pull Requests](https://github.com/hxcan/dynamicwallpaper/pulls)
- 📜 [License](LICENSE)

---

*本文件由未来姐姐协助维护* 🤖