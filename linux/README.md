# Linux

## 基本 Linux 常用命令

- **ls**：列出当前目录下的文件和目录。  
  - 常用选项：  
    - `ls -l`：显示详细信息。  
    - `ls -a`：显示隐藏文件。
  
- **cd**：改变当前工作目录。  
  - 示例：`cd /home/user` 切换到 `/home/user` 目录。

- **cp**：复制文件或目录。  
  - 示例：`cp source.txt dest.txt` 将 `source.txt` 复制为 `dest.txt`。

- **mv**：移动或重命名文件或目录。  
  - 示例：`mv old.txt new.txt` 将 `old.txt` 重命名为 `new.txt`。

- **rm**：删除文件或目录。  
  - 示例：`rm file.txt` 删除 `file.txt`。

- **chmod**：修改文件或目录的权限。  
  - 示例：`chmod 755 file.sh` 设置 `file.sh` 的权限为 755。
  - `chmod 750 dev-document/`：组用户可读写，其他用户不允许访问。  
  - `chmod 777`：所有用户拥有完整权限。  
  - `chmod 700`：只有属主拥有完整权限。

- **chown**：更改文件或目录的所有者。  
  - 示例：`chown user:group file.txt` 更改 `file.txt` 的所有者为 `user` 和组为 `group`。  
  - `chown d1:developer dev-document`。

- **sudo**：以超级用户权限执行命令。  
  - 示例：`sudo apt update` 使用超级用户权限更新软件包。  
  - `visudo`  
  - 在普通模式下输入 `100gg` 快速定位到100行。  
  - `sudo useradd d3`。

- **ps**：查看当前运行的进程。  
  - 示例：`ps aux` 列出所有进程。

- **kill**：终止进程。  
  - 示例：`kill -9 1234` 终止进程 ID 为 1234 的进程。

- **df**：显示文件系统的磁盘使用情况。  
  - 示例：`df -h` 以人类可读格式显示磁盘使用情况。

- **du**：显示目录或文件占用的磁盘空间。  
  - 示例：`du -sh /var/log` 显示 `/var/log` 目录占用的空间。

- **top**：实时查看系统的资源使用情况和进程。

## 与用户和用户组相关的常用命令

- **useradd**：添加用户。  
  - 示例：`sudo useradd -m username` 添加一个名为 `username` 的用户，并创建 home 目录。

- **usermod**：修改用户属性。  
  - 示例：`sudo usermod -aG groupname username` 将用户 `username` 添加到 `groupname` 组中。  
  - `usermod -G developer,testor d1`。

- **userdel**：删除用户。  
  - 示例：`sudo userdel -r username` 删除用户 `username` 及其 home 目录。

- **groupadd**：创建新组。  
  - 示例：`sudo groupadd groupname` 创建一个名为 `groupname` 的用户组。

- **groupdel**：删除组。  
  - 示例：`sudo groupdel groupname` 删除名为 `groupname` 的用户组。

- **groups**：查看用户所属的组。  
  - 示例：`groups username` 查看 `username` 所属的所有组。

- **id**：显示用户的 UID、GID 及所属组信息。  
  - 示例：`id username` 显示用户 `username` 的 UID 和 GID 信息。

- **passwd**：修改用户密码。  
  - 示例：`sudo passwd username` 为 `username` 设置密码。

- **gpasswd**：管理组的管理员和成员。  
  - 示例：`sudo gpasswd -a username groupname` 将用户 `username` 添加到 `groupname` 组。

- **newgrp**：临时切换当前会话的有效组。  
  - 示例：`newgrp groupname` 切换到 `groupname` 用户组。

- **chgrp**：更改文件或目录的组。  
  - 示例：`sudo chgrp groupname file.txt` 将 `file.txt` 的组更改为 `groupname`。

## 文件权限和组相关

每个文件或目录都有所有者、组和权限，格式如 `rwxr-xr-x`：
- **r**：读权限。
- **w**：写权限。
- **x**：执行权限。

权限通常分为三类：所有者、组、其他用户。使用 `chmod`、`chown`、`chgrp` 来管理这些权限和所有权。

## linux 防火墙命令

在 Linux 系统中，防火墙用于控制网络流量，以确保系统安全。常用的防火墙工具有 `iptables`、`firewalld` 以及 `nftables`。以下是这些防火墙的常用命令：

### 1. iptables
- **查看现有规则**：  
  `sudo iptables -L`。
- **添加规则**：  
  `sudo iptables -A INPUT -p tcp --dport 22 -j ACCEPT` 允许所有进来的 SSH 流量（TCP 端口 22）。
- **删除规则**：  
  `sudo iptables -D INPUT -p tcp --dport 22 -j ACCEPT` 删除特定的规则。
- **拒绝某个端口的流量**：  
  `sudo iptables -A INPUT -p tcp --dport 80 -j REJECT` 拒绝 HTTP（端口 80）的流量。
- **保存规则**：  
  `sudo iptables-save > /etc/iptables/rules.v4`。
- **清除所有规则**：  
  `sudo iptables -F`。
- **链和策略**：  
  设置默认行为（如 `ACCEPT` 或 `DROP`）：  
  `sudo iptables -P INPUT DROP`。

### 2. firewalld
- **查看防火墙状态**：  
  `sudo firewall-cmd --state`。
- **启用或禁用 firewalld 服务**：  
  `sudo systemctl start firewalld`  
  `sudo systemctl stop firewalld`。
- **允许某个端口的流量**：  
  `sudo firewall-cmd --permanent --add-port=80/tcp`  
  `sudo firewall-cmd --reload`。
- **查看开放的端口**：  
  `sudo firewall-cmd --list-ports`。

### 3. nftables
- **查看现有规则**：  
  `sudo nft list ruleset`。
- **添加规则**：  
  `sudo nft add rule inet filter input tcp dport 22 accept`。
- **删除规则**：  
  `sudo nft delete rule inet filter input tcp dport 22 accept`。
- **保存规则**：  
  `sudo nft list ruleset > /etc/nftables.conf`  
  `sudo systemctl enable nftables`。

### 4. ufw（Uncomplicated Firewall）
- **启用防火墙**：  
  `sudo ufw enable`。
- **禁用防火墙**：  
  `sudo ufw disable`。
- **允许某个端口**：  
  `sudo ufw allow 80/tcp`。
- **拒绝某个端口的流量**：  
  `sudo ufw deny 80/tcp`。
- **查看防火墙状态**：  
  `sudo ufw status`。

## Bash Shell

### 1. Bash Shell 脚本基础
- Bash 脚本是一种文本文件，包含一系列命令和控制结构。  
  - 文件通常以 `.sh` 为扩展名，脚本开头使用 `#!/bin/bash` 声明来指定解释器。

### 2. 变量与参数
- **变量示例**：  
  ```bash
  #!/bin/bash
  myvar="Hello, World"
  echo $myvar



使用命令行参数：
Bash 脚本可以接收命令行参数。参数 $1、$2 等表示传递给脚本的参数。
  ```bash
  #!/bin/bash
  echo "First argument: $1"
  echo "Second argument: $2"
  ```

特殊变量：
$0：脚本名
$#：传递给脚本的参数个数
$@：所有参数
$?：上一条命令的退出状态码

### 3. 条件判断
Bash 支持多种条件判断结构，用于根据特定条件执行不同的操作。

if 语句：
  ```bash
#!/bin/bash
if [ $1 -gt 100 ]; then
    echo "The number is greater than 100"
else
    echo "The number is 100 or less"
fi


-gt：大于（greater than）
-lt：小于（less than）
-eq：等于（equal）
文件判断：

  ```bash
if [ -f /path/to/file ]; then
    echo "File exists"
fi

-f：检查文件是否存在
-d：检查目录是否存在


逻辑操作符：
&&：逻辑与
||：逻辑或
!：取反


### 4. 循环结构
Bash 支持多种循环结构来重复执行命令。

for 循环：
  ```bash
#!/bin/bash
for i in 1 2 3 4 5; do
    echo "Number: $i"
done

while 循环：
  ```bash
#!/bin/bash
count=1
while [ $count -le 5 ]; do
    echo "Count: $count"
    count=$((count + 1))
done

until 循环：
until 循环会一直执行，直到条件为真。

  ```bash
#!/bin/bash
count=1
until [ $count -gt 5 ]; do
    echo "Count: $count"
    count=$((count + 1))
done


### 5. 函数
Bash 支持定义和调用函数，便于复用代码。

  ```bash
#!/bin/bash
myfunc() {
    echo "This is a function"
}


myfunc  # 调用函数

带参数的函数：
  ```bash
#!/bin/bash
myfunc() {
    echo "First argument: $1"
    echo "Second argument: $2"
}

myfunc "Hello" "World"



### 6. 脚本控制
exit 命令：
exit 用于退出脚本并返回状态码。

  ```bash
#!/bin/bash
echo "Exiting script"
exit 0  # 返回状态码 0 表示成功



break 和 continue：
break 用于退出循环。
continue 用于跳过当前循环的剩余部分并进入下一次循环。

  ```bash
#!/bin/bash
for i in 1 2 3 4 5; do
    if [ $i -eq 3 ]; then
        continue  # 跳过 3
    fi
    echo "Number: $i"
done


### 7. I/O 重定向
Bash 支持将命令的输出重定向到文件，或从文件读取输入。

输出重定向：
echo "Hello, World" > output.txt  # 覆盖写入文件
echo "Another line" >> output.txt  # 追加写入文件


输入重定向：
  ```bash
#!/bin/bash
while read line; do
    echo $line
done < input.txt

管道（|）：
管道将一个命令的输出作为另一个命令的输入。
cat file.txt | grep "pattern"


### 8. 错误处理
通过检查命令的返回值，Bash 可以进行简单的错误处理。

  ```bash
#!/bin/bash
command_that_might_fail
if [ $? -ne 0 ]; then
    echo "Command failed"
fi


或者使用 set 命令：
set -e  # 如果任何命令失败，立即退出脚本


### 9. 定时任务（crontab）
Bash 脚本可以与 cron 定时任务结合使用，实现定时批处理任务。

编辑定时任务：
crontab -e


添加定时任务：
0 5 * * * /path/to/script.sh  # 每天早上 5 点执行


### 10. Bash 调试
可以通过 -x 选项来调试 Bash 脚本，显示每条命令及其参数。
  ```bash
#!/bin/bash
set -x  # 启用调试
echo "This is a debug example"
set +x  # 禁用调试
