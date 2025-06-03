Here's the English translation of your README.md:

---

# FarmersDelightRePaper - Farmer's Delight Paper Rewrite

![License](https://img.shields.io/badge/License-GPLv3-blue)
![Status](https://img.shields.io/badge/Status-Alpha-red)

[中文](README.md)

A faithful recreation of the [Farmer's Delight mod](https://modrinth.com/mod/farmers-delight) implemented using PaperAPI + NeoArtisan framework and client-side resource packs, delivering full mod functionality through server-side technology.

> 🟨 This project requires [NeoArtisan](https://github.com/KitsunaiMC/NeoArtisan) as a dependency plugin

## ✨ Core Features

- **Pure Server-Side Implementation**: Only requires client resource packs + Paper server plugin
- **Protocol-Level Magic**: Uses data packs and chunk manipulation to trick clients into rendering custom blocks
- **Perfect Compatibility**: Block behavior layer built on PaperAPI without NMS dependencies
- **Modular Architecture**: Easy to extend

## 📜 License

This project is licensed under **GNU General Public License v3.0**:
- Freedom to use and modify
- Derivative works must be open source
- Cannot be distributed as part of closed-source commercial software

Full license text available at [LICENSE](LICENSE).

## 🚧 Development Progress

**Current Version Features**:
- [x] Basic crop system
- [x] Block and item management
- [x] Corresponding client resource pack
- [x] Cooking pot recipes and GUI
- [x] Cutting board recipes (basic implementation)
- [x] Partial crafting recipes

**TODO List**:
- [ ] Add remaining items and recipes from original mod
- [ ] Improve crop system
- [ ] Enhance knife and cutting board behaviors
- [ ] ~~Find bugs~~ Write unit tests

> ⚠️ Warning: This project is still in development - not production ready!

## 🤝 Contributing

We welcome contributions through:
1. Pull Requests (please pass `./gradlew check` first)
2. Technical discussions in Issues
3. Documentation improvements

Recommended development environment:
- JDK 21+
- Paper 1.21.4+
- IntelliJ IDEA

## 💬 Communication

Feel free to:
- Open an Issue
- Email me at [MoYuOwO@outlook.com](mailto:MoYuOwO@outlook.com)