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

或者安装最新的版本：
```bash
sudo apt install openjdk-17-jdk

  ```

CentOS/RHEL 系统：
```bash
sudo yum install java-18-openjdk-devel

  ```

2.2 安装 Oracle JDK
Oracle JDK 提供了更全面的 Java 实现，适用于生产环境。你可以从 Oracle 官网下载对应版本的 JDK。

下载 Oracle JDK
访问 Oracle JDK 下载页面，根据系统架构选择对应的版本并下载。

解压并安装
例如下载了 jdk-17_linux-x64_bin.tar.gz 文件，可以使用以下命令解压并安装：

```bash
sudo tar -xvzf jdk-17_linux-x64_bin.tar.gz -C /opt/
配置环境变量
将解压后的 JDK 路径配置到系统的环境变量中：
  ```

```bash
sudo nano /etc/profile
  ```

在文件末尾添加以下内容：

```bash
export JAVA_HOME=/opt/jdk-17
export PATH=$JAVA_HOME/bin:$PATH
保存文件后，执行以下命令使配置生效：
  ```


```bash
source /etc/profile
  ```


## 3. 配置环境变量
无论是使用 OpenJDK 还是 Oracle JDK，都需要配置 JAVA_HOME 环境变量以正确指向 Java 安装路径。

3.1 配置 JAVA_HOME（全局配置）
编辑 /etc/profile 文件或 /etc/environment 文件：

```bash
sudo nano /etc/profile
  ```

添加以下行（假设 Java 安装路径为 /usr/lib/jvm/java-11-openjdk-amd64）：

```bash
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH
  ```
保存并退出后，执行以下命令以使配置生效：

```bash
source /etc/profile
  ```


3.2 配置 JAVA_HOME（仅当前用户）
编辑当前用户的 .bashrc 文件：

```bash
nano ~/.bashrc
  ```


添加以下行：

```bash
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH
  ```

保存后执行：

```bash
source ~/.bashrc
  ```

## 4. 验证 Java 安装
安装并配置完环境变量后，使用以下命令验证是否正确安装了 Java：

```bash
java -version
javac -version

  ```
你应该能够看到类似以下的输出（以 OpenJDK 11 为例）：

```bash
openjdk version "11.0.12" 2021-07-20
OpenJDK Runtime Environment (build 11.0.12+7)
OpenJDK 64-Bit Server VM (build 11.0.12+7, mixed mode)
  ```


如果成功输出 Java 版本信息，说明 Java 安装和配置成功。

## 5. 设置默认 Java 版本（多版本切换）
如果系统中安装了多个版本的 Java，可以通过 update-alternatives 命令来切换默认的 Java 版本：

```bash
sudo update-alternatives --config java
  ```
终端会列出所有可用的 Java 版本，并提示你选择默认版本。输入对应的数字选择所需的版本。

类似地，可以为 javac 配置：

```bash
sudo update-alternatives --config javac
  ```


## 6. 测试 Java 环境
可以编写一个简单的 Java 程序来测试你的环境：

```bash
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World");
    }
}
  ```

将该代码保存为 HelloWorld.java，然后执行以下命令来编译和运行程序：

```bash
javac HelloWorld.java
java HelloWorld
  ```

## 7. 设置 JAVA_HOME 的其他方法

除了在 `/etc/profile` 或 `.bashrc` 文件中设置 `JAVA_HOME`，你还可以通过其他方式来配置 `JAVA_HOME`，如在系统级别使用 `/etc/environment` 文件。

### 7.1 配置 JAVA_HOME 全局变量

编辑 `/etc/environment` 文件：

```bash
sudo nano /etc/environment
  ``` 
添加以下行（假设 Java 安装路径为 /usr/lib/jvm/java-11-openjdk-amd64）：

```bash
JAVA_HOME="/usr/lib/jvm/java-11-openjdk-amd64"
PATH="$JAVA_HOME/bin:$PATH"
  ```

保存并退出后，执行以下命令以使配置生效：

```bash
source /etc/environment
  ```

### 7.2 配置 JAVA_HOME 仅对某用户生效
如果只希望某个特定用户使用 Java 环境变量，可以编辑该用户的 .bash_profile 或 .bashrc 文件：

```bash
nano ~/.bash_profile
  ```

添加以下内容：

```bash
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH
  ```

保存文件并运行：

```bash
source ~/.bash_profile
  ```



## 8. 管理多个 Java 版本
如果你的系统中安装了多个版本的 Java，可以使用 update-alternatives 工具来管理和切换默认 Java 版本。

8.1 设置默认 Java 版本
通过以下命令，你可以列出并选择默认的 Java 版本：

```bash
sudo update-alternatives --config java
  ```
选择你想要的 Java 版本对应的数字。

8.2 设置默认 javac 版本
类似地，可以为 javac 配置默认版本：

```bash
sudo update-alternatives --config javac
  ```

## 9. 常见问题解决
9.1 Java 安装后未生效
如果在安装和配置 Java 后，仍然无法使用 java 或 javac 命令，可能是由于路径未正确配置。检查一下环境变量设置：

```bash
echo $JAVA_HOME
echo $PATH
  ```

确保 JAVA_HOME 指向正确的 Java 安装目录，且 PATH 包含了 $JAVA_HOME/bin。

9.2 多版本的冲突
如果系统中安装了多个 Java 版本并出现冲突，可以通过 update-alternatives 工具来设置默认的 Java 版本，确保每次调用 Java 时使用正确的版本。

## 10. 编写并运行 Java 程序
完成上述设置后，你可以编写并运行一个简单的 Java 程序来测试开发环境。

10.1 示例程序
创建一个简单的 Java 程序 HelloWorld.java：

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
  ```

10.2 编译和运行
在终端中，首先使用 javac 命令编译 Java 程序：

```bash
javac HelloWorld.java
  ```


然后使用 java 命令运行编译后的程序：

```bash
java HelloWorld
  ```

输出应该是：

```bash
Hello, World!
  ```





