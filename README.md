# Offerly

[![Java](https://img.shields.io/badge/Java-21-brightgreen)](https://www.java.com/)
[![Minecraft](https://img.shields.io/badge/Minecraft_Java_Edition-1.21.5_to_26.1.x-blue)](https://www.minecraft.net/de-de/article/minecraft-java-edition-26-1-2)
[![API](https://img.shields.io/badge/API-PaperMC-white)](https://papermc.io/)
[![bStats](https://img.shields.io/badge/bStats-Active-blue)](https://bstats.org/plugin/bukkit/Offerly/12345)
[![Plugin](https://img.shields.io/badge/Type-Plugin-yellow)](#)
[![License](https://img.shields.io/badge/License-GPLv3-lightgrey)](https://www.gnu.org/licenses/gpl-3.0.en.html#license-text)
[![Open Source](https://img.shields.io/badge/Open%20Source-Yes-brightgreen)](https://opensource.org/)
[![Modrinth](https://img.shields.io/badge/Available%20on-Modrinth-00AF5C?logo=modrinth)](https://modrinth.com/project/offerly)

SHORT DESCRIPTION

## Features
* ...
* ...
* ...
* ...

## Usage
1. ...
2. ...
3. ...

## Installation
1. Download the latest .jar from [Modrinth](https://modrinth.com/project/offerly/versions) or [GitHub](https://github.com/mxiwbr/offerly/releases)
2. Place it in your server's `plugins` folder
3. (Optional) Configure the plugin via `config.yml` and restart the server or use the plugin's reload command

## Commands

| Command                              | Description                                                                    | Permission |
|--------------------------------------|--------------------------------------------------------------------------------|------------|
| `/offerly disable`                   | Disables the plugin.                                                           | OP         |
| `/offerly enable`                    | Enables the plugin.                                                            | OP         |
| `/offerly reloadconfig`              | Reloads the plugin’s configuration (`config.yml`).                             | OP         |
| `/offerly resetconfig`               | Resets the plugin’s configuration (`config.yml`) and automatically reloads it. | OP         |
| `/offerly help`                      | Shows this command overview in the ingame chat.                                | OP         |
| `/offerly version`                   | Shows the current plugin version in the ingame chat and checks for updates.    | OP         |

## Supported Versions
- PaperMC on Minecraft Versions 1.21.5 - 26.1.2
- PurpurMC on Minecraft Versions 1.21.5 - 26.1.2

## Config
Edit the `config.yml` in the plugin's folder to adapt it to your preferences:
```
# =========================================
# Offerly - Configuration
# =========================================

enabled: true # Enable/disable plugin functionality

# =========================================
# Advanced Settings – only modify if you know what you're doing!
# Misconfiguring these could potentially break the plugin or cause unexpected behavior.
# =========================================

console:
  enable-logging: true
  enable-additional-logging: false # Extra debug logs

bstats:
  enabled: true

```

#### Resetting the config
In order to reset the plugin's config to its default values, either use the `/offerly resetconfig` command or delete the `config.yml` file in the plugin's folder and restart the server.

## Help
If you need any help, please feel free to open an issue: [Open an issue](https://github.com/mxiwbr/offerly/issues)

## bStats
This plugin uses [bStats](https://bstats.org/) to collect **anonymous statistics** like player counts and server versions.  
All data collected is **anonymous and secure**, helping to improve the plugin.  
If you don't want the plugin to send data, disable bStats in the config.yml.

## License
This plugin is licensed under the **GNU General Public License v3**.  
- You are free to **use, modify, and redistribute** the plugin **under the same GPLv3 license**.
- **Uploading** or sharing the plugin **without proper modifications** is **strictly prohibited**.
- You must always provide **credit to the original author**.  
- For more information, see the full license: [GNU GPL v3](https://www.gnu.org/licenses/gpl-3.0.en.html)

## Credits

Developed by [**mxiwbr**](https://github.com/mxiwbr)
