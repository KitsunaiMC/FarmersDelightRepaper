# FarmersDelightRePaper - 农夫乐事 Paper 重置版

![License](https://img.shields.io/badge/License-GPLv3-blue)
![Status](https://img.shields.io/badge/Status-Alpha-red)

[English](README.md)

一个基于 PaperAPI + NeoArtisan 框架和客户端资源包实现的 [农夫乐事mod](https://modrinth.com/mod/farmers-delight) 复刻项目，完全通过服务端技术实现原版模组功能。

> 🟨 本项目基于 [NeoArtisan](https://github.com/KitsunaiMC/NeoArtisan) 实现，需要将其加载作为前置插件方可使用

## ✨ 核心特性

- **纯服务端实现**：仅需客户端资源包 + Paper 服务端插件
- **协议层魔法**：利用数据包和区块操作欺骗客户端渲染自定义方块
- **完美兼容性**：方块行为层基于 PaperAPI 开发，不依赖 NMS，充分考虑了兼容性
- **模块化架构**：易于扩展


## 📜 协议说明

本项目采用 **GNU General Public License v3.0** 开源协议:
- 允许自由使用和修改
- 要求衍生作品开源
- 禁止作为闭源商业软件的一部分分发

完整协议见 [LICENSE](LICENSE) 文件。

## 🚧 开发进度

**当前版本功能**:
- [x] 基础作物系统
- [x] 方块和物品管理
- [x] 对应客户端资源包
- [x] 厨锅配方及GUI实现
- [x] 砧板配方及简单实现
- [x] 部分合成表

**TODO List**:
- [ ] 添加原mod的多个物品与物品合成表
- [ ] 完善作物系统
- [ ] 完善刀、砧板相关行为
- [ ] ~~找bug~~ 编写单元测试用例

> ⚠️ 警告：本项目仍处于开发阶段，请勿在生产环境使用！

## 🤝 参与贡献

欢迎通过以下方式参与：
1. 提交 Pull Request （请先通过 `./gradlew check` 代码审查）
2. 在 Issues 讨论技术方案
3. 帮助完善文档

推荐开发环境：
- JDK 21+
- Paper 1.21.4+
- IntelliJ IDEA

## 💬 交流方式

欢迎提交 Issue 或发送邮件给我一起讨论： [MoYuOwO@outlook.com](mailto:MoYuOwO@outlook.com)