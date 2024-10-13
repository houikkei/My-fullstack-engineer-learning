# 在 Linux 系统下搭建 Java 开发环境

本文介绍如何在 Linux 系统下搭建 Java 开发环境，主要步骤包括下载和安装 Java Development Kit (JDK)、配置环境变量以及验证安装。以下以常见的 Oracle JDK 或 OpenJDK 为例。

## 1. 检查系统是否已安装 Java

在开始之前，先检查系统上是否已安装 Java：

```bash
java -version
  ```

如果系统中已有 Java 安装，它会显示已安装的 Java 版本。如果未安装，终端会提示未找到该命令。

## 2. 安装 JDK
2.1 安装 OpenJDK
OpenJDK 是一个开源的 Java 实现，Linux 发行版通常包含 OpenJDK，可以直接通过包管理器安装。

Debian/Ubuntu 系统：
```bash
sudo apt update
sudo apt install openjdk-11-jdk

  ```










