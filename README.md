# 贴吧 Lite（Tieba Lite）

<p align="center">
    <a href="https://github.com/jin66god/TiebaLite/actions/workflows/build.yml">
        <img alt="Build Status" src="https://github.com/jin66god/TiebaLite/actions/workflows/build.yml/badge.svg?branch=4.0-dev">
    </a>
    <a href="https://t.me/tblite_discuss">
        <img alt="Status" src="https://img.shields.io/badge/-Telegram-blue?logo=telegram&style=flat">
    </a>
</p>

**贴吧 Lite** 是一款**非官方**的百度贴吧客户端，主打简洁、无广告、高度可自定义。

> ⚠️ **免责声明：本软件及源码仅供学习交流使用，严禁用于商业用途。** 使用本客户端可能违反百度贴吧服务条款，请自行承担相关风险。

## ✨ 特性

- **纯净无广告**：无内置广告位，清爽浏览体验
- **一键签到**：支持多账号，可设置定时自动签到（需开启自启动并忽略电池优化）
- **消息提醒**：新回复 / @ 提醒（通知为可选功能，按需授权，不强制弹窗）
- **高度可自定义**：主题色、夜间模式、跟随系统、透明主题（实验性）、字体大小
- **强大的贴内功能**：只看楼主、倒序浏览、屏蔽设置、收藏楼层、小尾巴
- **内置浏览器**：贴吧链接内置打开，支持自定义打开方式
- **沉浸阅读**：纯文本阅读模式

## 📥 下载安装

- 从 **Releases** 页面下载最新版 `*.apk` 安装
- 通过 CI 自动构建的分支版本为 **预发布（prerelease）**，可能包含未稳定特性
- 安装前请确认来源可信，并在系统设置中允许安装未知来源应用

## 🛠️ 从源码构建

环境要求：**JDK 17**、Android SDK。

```bash
# 克隆仓库
git clone https://github.com/jin66god/TiebaLite.git
cd TiebaLite

# 构建 release APK 与混淆映射
./gradlew assembleRelease
# 产物位于 app/build/outputs/apk/release/
```

> 需发布正式签名版本时，将 `KEYSTORE`、`RELEASESTOREPASSWORD`、`RELEASEKEYPASSWORD` 等配置到仓库 Secrets 与 Variables 即可（推送 tag 或 `4.0-dev` 分支时自动构建并发布）。

## 🔄 CI 自动构建

推送 `4.0-dev` 分支或 `v*` tag 时，GitHub Actions 会自动执行：

1. 使用 JDK 17 编译 `assembleRelease`
2. 将产出 APK 与 `mapping.txt` 自动发布到 **Releases**

## 📄 更新日志

- **2026-08**：移除启动时的「正在请求发送通知权限」横幅提示，不再强制弹窗请求通知权限，改为按需授权

## 🔗 友情链接

- [Starry-OvO/aiotieba: Asynchronous I/O Client for Baidu Tieba](https://github.com/Starry-OvO/aiotieba)
- [n0099/tbclient.protobuf: 百度贴吧客户端 Protocol Buffers 定义文件合集](https://github.com/n0099/tbclient.protobuf)

## 🤝 交流讨论

Telegram：[tblite_discuss](https://t.me/tblite_discuss)
