# Redis 是 key-Value 型 NoSQL 数据库

Redis 将数据存储在内存中，同时也能持久化到磁盘。  
Redis 常用于缓存，利用内存的高效提高程序的处理速度。

## Redis 特点

1. 速度快  
2. 广泛的语言支持  
3. 持久化  
4. 多种数据结构  
5. 主从复制  
6. 分布式与高可用

---

### 1.在 Linux (Ubuntu) 上安装和启动 Redis

### 安装 Redis

1. 更新本地包索引：

    ```bash
    sudo apt update
    ```

2. 安装 Redis：

    ```bash
    sudo apt install redis-server
    ```

3. 安装完成后，你可以检查 Redis 是否成功安装：

    ```bash
    redis-server --version
    ```

### 启动 Redis

Redis 安装后会自动启动并作为系统服务运行。你可以使用以下命令来启动、停止或检查 Redis 的状态：

- 启动 Redis：

    ```bash
    sudo systemctl start redis-server
    ```

- 查看 Redis 服务状态：

    ```bash
    sudo systemctl status redis-server
    ```

- 停止 Redis：

    ```bash
    sudo systemctl stop redis-server
    ```

- 重启 Redis：

    ```bash
    sudo systemctl restart redis-server
    ```

Redis 默认在 6379 端口上运行，你可以通过 `redis-cli` 客户端连接和操作 Redis：

```bash
redis-cli
  ```

### 2. 在 Mac 上安装和启动 Redis
你可以使用 Homebrew 来安装 Redis：

安装 Redis：
更新 Homebrew：
```bash
brew update
  ```

安装 Redis：
```bash
brew install redis
  ```

启动 Redis：
启动 Redis：
```bash
brew services start redis
  ```

停止 Redis：
 ```bash
brew services stop redis
  ```

你也可以手动启动 Redis：
```bash
redis-server /usr/local/etc/redis.conf
  ```

### 3. 在 Windows 上安装和启动 Redis
Redis 官方并不直接支持 Windows，但可以通过 WSL（Windows Subsystem for Linux）或使用第三方的 Windows 端口来安装 Redis。

使用 WSL 安装：
安装 WSL 和 Ubuntu，启动 Ubuntu 终端。
按照上面 Linux Ubuntu 安装部分的指引来安装 Redis。
使用 Redis for Windows：
下载安装 Redis for Windows：

下载地址：Microsoft Archive
解压并运行 redis-server.exe 来启动 Redis：
```bash
redis-server.exe
  ```

### 4. Docker 安装 Redis
如果你使用 Docker，你可以使用以下步骤来运行 Redis：

拉取 Redis 镜像：
```bash
docker pull redis
  ```

启动 Redis 容器：
    ```bash
docker run --name redis-container -d redis
  ```

进入 Redis CLI：
 ```bash
docker exec -it redis-container redis-cli
  ```

Redis 默认配置文件路径是 /etc/redis/redis.conf，你可以编辑该文件进行自定义配置。


## redis的常用基本配置

Redis 的配置文件（通常位于 /etc/redis/redis.conf 或通过自定义路径加载）包含了许多配置选项，可以用于调整 Redis 的行为。以下是一些 Redis 常用的基本配置选项及其作用。

### 1. 绑定 IP 地址
Redis 默认只监听本地地址（127.0.0.1），但如果你需要让 Redis 可以被远程访问，可以修改绑定的 IP 地址。

 ```bash
bind 127.0.0.1
  ```

如果你想让 Redis 接受所有外部请求，可以将其修改为：
 ```bash
bind 0.0.0.0
  ```

### 2. 端口号
Redis 默认运行在 6379 端口。你可以通过修改以下行来改变端口号：
 ```bash
port 6379
  ```

### 3. 后台运行 (daemonize)
如果你希望 Redis 作为守护进程在后台运行，将 daemonize 设置为 yes：
 ```bash
daemonize yes
  ```

### 4. 密码保护
Redis 默认是没有密码保护的。如果你希望为 Redis 设置访问密码，可以通过 requirepass 选项来配置：
 ```bash
requirepass yourpassword
  ```

设置密码后，每次客户端连接都需要通过以下方式认证：
 ```bash
AUTH yourpassword
  ```

### 5. 最大内存限制
Redis 是一个内存数据库，所有数据存储在内存中。如果你希望限制 Redis 使用的最大内存，可以设置 maxmemory：
 ```bash
maxmemory 256mb
  ```

配置 Redis 在达到最大内存限制时的行为，可以设置 maxmemory-policy：
 ```bash
maxmemory-policy noeviction
  ```

常用策略包括：

noeviction：不删除任何键，达到最大内存限制后返回错误。
allkeys-lru：从所有键中挑选最近最少使用（LRU）的键删除。
volatile-lru：只从有过期时间的键中删除最近最少使用的键。
allkeys-random：从所有键中随机删除一些键。
volatile-random：只从有过期时间的键中随机删除一些键。

### 6. 日志级别
Redis 支持不同的日志级别，可以通过 loglevel 来设置：
 ```bash
loglevel notice
  ```

级别选项包括：

debug：记录调试级别信息。
verbose：记录详细信息。
notice：记录普通的运行日志（默认）。
warning：仅记录警告和错误信息。

### 7. 日志文件
Redis 默认会将日志输出到标准输出。如果你希望将日志输出到文件，可以设置 logfile 选项：
 ```bash
logfile /var/log/redis.log
  ```

如果不需要日志文件，可以设置为：
 ```bash
logfile ""
  ```

### 8. 数据库文件保存 (RDB)
Redis 会定期将内存中的数据快照保存到磁盘。你可以通过以下配置控制保存频率：
 ```bash
save 900 1
save 300 10
save 60 10000
  ```

这表示：

每 900 秒至少有 1 次写操作时进行保存。
每 300 秒至少有 10 次写操作时进行保存。
每 60 秒至少有 10,000 次写操作时进行保存。
如果你不想自动保存，可以注释掉 save 选项。

### 9. 持久化机制 (AOF)
除了 RDB 之外，Redis 还支持 Append-Only File (AOF) 持久化机制。它会记录每个写入命令到日志文件。你可以通过以下方式启用 AOF：
 ```bash
appendonly yes
  ```

默认的 AOF 文件名为 appendonly.aof。你可以通过以下选项指定文件名：
 ```bash
appendfilename "appendonly.aof"
  ```

### 10. 过期键的删除策略
Redis 的键可以设置过期时间，配置文件中可以通过 hz 来控制过期键扫描的频率。更高的 hz 值会使 Redis 更频繁地检查过期键：
 ```bash
hz 10
  ```

### 11. 客户端最大连接数
为了防止 Redis 被过多的客户端连接占用资源，可以设置最大客户端连接数：
 ```bash
maxclients 10000
  ```

### 12. 关闭 Redis 的保护模式
Redis 默认会在没有密码保护或绑定到非本地网络时，开启保护模式以防止非授权访问。你可以通过以下方式关闭保护模式：
 ```bash
protected-mode no
  ```


### 13. 数据库数量
Redis 默认有 16 个数据库（索引为 0 到 15），你可以通过 databases 选项设置数据库的数量：
 ```bash
databases 16
  ```

### 14. 慢查询日志
可以配置 Redis 记录执行时间超过一定阈值的慢查询，以帮助发现性能瓶颈：
 ```bash
slowlog-log-slower-than 10000  # 记录执行时间超过 10ms 的命令
slowlog-max-len 128            # 最大日志长度为 128 条
  ```

这些是 Redis 常用的一些基本配置。可以根据你的使用场景对配置文件进行调整，以优化 Redis 的性能和稳定性。


## redis通用命令

Redis 提供了丰富的命令用于操作和管理数据。以下是一些常用的通用命令：

### 1. 键（Key）操作命令
keys *：查看所有键。
 ```bash
redis> keys *
1) "key1"
2) "key2"
  ```

exists key：检查指定键是否存在。
 ```bash
redis> exists key1
(integer) 1
  ```

type key：返回键的值类型。
 ```bash
redis> type key1
string
  ```

del key：删除指定键。
 ```bash
redis> del key1
(integer) 1
  ```

expire key seconds：设置键的过期时间。
 ```bash
redis> expire key2 60
(integer) 1
  ```

ttl key：查看键的剩余生存时间。
 ```bash
redis> ttl key2
(integer) 60
  ```

rename key newkey：重命名键。
 ```bash
redis> rename key2 newkey
OK
  ```

### 2. 字符串（String）操作命令
set key value：设置键的值。
 ```bash
redis> set name "Alice"
OK
  ```

get key：获取键的值。
 ```bash
redis> get name
"Alice"
  ```

append key value：将值追加到现有值的末尾
 ```bash
redis> append name "Bob"
(integer) 8
  ```

strlen key：获取字符串值的长度。
 ```bash
redis> strlen name
(integer) 8
  ```

incr key：将键的值自增1。
 ```bash
redis> set age 30
OK
redis> incr age
(integer) 31
  ```

decr key：将键的值自减1。
 ```bash
redis> decr age
(integer) 30
  ```

### 3. 列表（List）操作命令
lpush key value：将值插入到列表的头部。
 ```bash
redis> lpush mylist "first"
(integer) 1
  ```

rpush key value：将值插入到列表的尾部。
 ```bash
redis> rpush mylist "second"
(integer) 2
  ```

lpop key：移出并获取列表的第一个元素。
 ```bash
redis> lpop mylist
"first"
  ```

redis> rpop mylist
"second"
 ```bash
redis> rpop mylist
"second"
  ```

llen key：获取列表长度。
 ```bash
redis> llen mylist
(integer) 0
  ```

### 4. 哈希（Hash）操作命令
hset key field value：设置哈希表字段的值。
 ```bash
redis> hset user name "Alice"
(integer) 1
  ```

hget key field：获取哈希表字段的值。
 ```bash
redis> hget user name
"Alice"
  ```

hgetall key：获取哈希表的所有字段和值。
 ```bash
redis> hgetall user
1) "name"
2) "Alice"
  ```

hlen key：获取哈希表字段数量。
 ```bash
redis> hlen user
(integer) 1
  ```

### 5. 集合（Set）操作命令
sadd key member：向集合添加成员。
 ```bash
redis> sadd myset "member1"
(integer) 1
  ```

smembers key：返回集合中的所有成员。
 ```bash
redis> smembers myset
1) "member1"
  ```

sismember key member：检查元素是否是集合的成员。
 ```bash
redis> sismember myset "member1"
(integer) 1
  ```

scard key：获取集合的成员数。
 ```bash
redis> scard myset
(integer) 1
  ```


### 6. 有序集合（Sorted Set）操作命令
zadd key score member：向有序集合添加成员或更新其分数。
 ```bash
redis> zadd myzset 1 "member1"
(integer) 1
  ```

zrange key start stop：返回有序集合中指定范围内的成员。
 ```bash
redis> zrange myzset 0 -1
1) "member1"
  ```

zscore key member：返回有序集合中成员的分数。
 ```bash
redis> zscore myzset "member1"
(integer) 1
  ```

zrank key member：返回有序集合中成员的排名。
 ```bash
redis> zrank myzset "member1"
(integer) 0
  ```

### 7. 其他命令
ping：测试连接是否正常。
 ```bash
redis> ping
PONG
  ```

info：获取 Redis 服务器的各种信息和统计数值。
 ```bash
redis> info
# Server
redis_version:6.0.9
  ```

flushdb：清空当前数据库。
 ```bash
redis> flushdb
OK
  ```

flushall：清空所有数据库。
 ```bash
redis> flushall
OK
  ```

save：同步保存数据到磁盘。
 ```bash
redis> save
OK
  ```

bgsave：在后台异步保存当前数据库的数据到磁盘。
 ```bash
redis> bgsave
OK
  ```

shutdown：关闭 Redis 服务器。
 ```bash
redis> shutdown
  ```


：查看键的剩余生存时间。

    ```bash
    redis> ttl key2
    (integer) 60
    ```

- `rename key newkey`：重命名键。

    ```bash
    redis> rename key2 newkey
    OK
    ```

---

### 字符串（String）操作命令

- `set key value`：设置键的值。

    ```bash
    redis> set name "Alice"
    OK
    ```

- `get key`：获取键的值。

    ```bash
    redis> get name
    "Alice"
    ```

- `append key value`：将值追加到现有值的末尾。

    ```bash
    redis> append name "Bob"
    (integer) 8
    ```

- `strlen key`：获取字符串值的长度。

    ```bash
    redis> strlen name
    (integer) 8
    ```

- `incr key`：将键的值自增 1。

    ```bash
    redis> set age 30
    OK
    redis> incr age
    (integer) 31
    ```

- `decr key`：将键的值自减 1。

    ```bash
    redis> decr age
    (integer) 30
    ```

---

### 列表（List）操作命令

- `lpush key value`：将值插入到列表的头部。

    ```bash
    redis> lpush mylist "first"
    (integer) 1
    ```

- `rpush key value`：将值插入到列表的尾部。

    ```bash
    redis> rpush mylist "second"
    (integer) 2
    ```

- `lpop key`：移出并获取列表的第一个元素。

    ```bash
    redis> lpop mylist
    "first"
    ```

- `rpop key`：移出并获取列表的最后一个元素。

    ```bash
    redis> rpop mylist
    "second"
    ```

- `llen key`：获取列表长度。

    ```bash
    redis> llen mylist
    (integer) 0
    ```

---

### 哈希（Hash）操作命令

- `hset key field value`：设置哈希表字段的值。

    ```bash
    redis> hset user name "Alice"
    (integer) 1
    ```

- `hget key field`：获取哈希表字段的值。

    ```bash
    redis> hget user name
    "Alice"
    ```

- `hgetall key`：获取哈希表的所有字段和值。

    ```bash
    redis> hgetall user
    "name"
    "Alice"
    ```

- `hlen key`：获取哈希表字段数量。

    ```bash
    redis> hlen user
    (integer) 1
    ```

---

### 集合（Set）操作命令

- `sadd key member`：向集合添加成员。

    ```bash
    redis> sadd myset "member1"
    (integer) 1
    ```

- `smembers key`：返回集合中的所有成员。

    ```bash
    redis> smembers myset
    "member1"
    ```

- `sismember key member`：检查元素是否是集合的成员。

    ```bash
    redis> sismember myset "member1"
    (integer) 1
    ```

- `scard key`：获取集合的成员数。

    ```bash
    redis> scard myset
    (integer) 1
    ```

---

### 有序集合（Sorted Set）操作命令

- `zadd key score member`：向有序集合添加成员或更新其分数。

    ```bash
    redis> zadd myzset 1 "member1"
    (integer) 1
    ```

- `zrange key start stop`：返回有序集合中指定范围内的成员。

    ```bash
    redis> zrange myzset 0 -1
    "member1"
    ```

- `zscore key member`：返回有序集合中成员的分数。

    ```bash
    redis> zscore myzset "member1"
    (integer) 1
    ```

- `zrank key member`：返回有序集合中成员的排名。

    ```bash
    redis> zrank myzset "member1"
    (integer) 0
    ```

---

### 其他命令

- `ping`：测试连接是否正常。

    ```bash
    redis> ping
    PONG
    ```

- `info`：获取 Redis 服务器的各种信息和统计数值。

    ```bash
    redis> info
    Server
    redis_version:6.0.9
    ```

- `flushdb`：清空当前数据库。

    ```bash
    redis> flushdb
    OK
    ```

- `flushall`：清空所有数据库。

    ```bash
    redis> flushall
    OK
    ```

- `save`：同步保存数据到磁盘。

    ```bash
    redis> save
    OK
    ```

- `bgsave`：在后台异步保存当前数据库的数据到磁盘。

    ```bash
    redis> bgsave
    OK
    ```

- `shutdown`：关闭 Redis 服务器。

    ```bash
    redis> shutdown
    ```

---

