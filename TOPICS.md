# GitHub Topics 配置指南

此文件记录仓库的 Topics 设置，用于 GitHub 仓库页面的搜索发现。

## 🏷️ 仓库 Topics

通过 GitHub API `PUT /repos/hxcan/dynamicwallpaper/topics` 设置：

```json
{
  "names": [
    "android",
    "wallpaper",
    "dynamic-wallpaper",
    "live-wallpaper",
    "kotlin",
    "java"
  ]
}
```

## 📋 设置说明

| Topic | 说明 |
|-------|------|
| `android` | Android 平台 |
| `wallpaper` | 壁纸应用 |
| `dynamic-wallpaper` | 动态壁纸（核心功能）|
| `live-wallpaper` | 实时动态壁纸（技术术语）|
| `kotlin` | 支持 Kotlin（虽然代码主要用 Java） |
| `java` | 主要开发语言 |

## 🔧 手动设置方法（如果 API 失败）

1. 进入 GitHub 仓库页面：`https://github.com/hxcan/dynamicwallpaper`
2. 点击右上角 ⚙️ 齿轮图标（About 部分）
3. 在 Topics 输入框输入：android, wallpaper, dynamic-wallpaper, live-wallpaper, kotlin, java
4. 点击 Save changes

## 🔗 关联任务

- #848958361645 添加 issue 模板、PR 模板、Topics 标签