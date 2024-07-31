---
title: "redis基础与进阶"
date: 2022-03-28T02:47:49-07:00
draft: true
categories: ["数据库",]
---

# 一、引言

在Web应用发展的初期，那时关系型数据库受到了较为广泛的关注和应用，原因是因为那时候Web站点基本上访问和并发不高、交互也较少。而在后来，随着访问量的提升，使用关系型数据库的Web站点多多少少都开始在性能上出现了一些瓶颈，而瓶颈的源头一般是在磁盘的I/O上。而随着互联网技术的进一步发展，各种类型的应用层出不穷，这导致在当今云计算、大数据盛行的时代，对性能有了更多的需求，主要体现在以下几个方面：

> 1. 低延迟的读写速度：应用快速地反应能极大地提升用户的满意度
> 2. 支撑海量的数据和流量：对于搜索这样大型应用而言，需要利用PB级别的数据和能应对百万级的流量
> 3. 大规模集群的管理：系统管理员希望分布式应用能更简单的部署和管理

为了克服这一问题，NoSQL应运而生，它同时具备了高性能、可扩展性强、高可用等优点，受到广泛开发人员和仓库管理人员的青睐。

**关系型数据库（RMDBS）与非关系型数据库（NoSQL）的对比：**

数据库中表与表的数据之间存在某种关联的内在关系，因为这种关系，所以我们称这种数据库为关系型数据库。典型：Mysql/MariaDB、postgreSQL、Oracle、SQLServer、DB2、Access、SQLlite3。特点： 

> 1. 全部使用SQL（结构化查询语言）进行数据库操作。
> 2. 都存在主外键关系，表，等等关系特征。
> 3. 大部分都支持各种关系型的数据库的特性：事务、存储过程、触发器、视图、临时表、模式、函数

NOSQL：not only sql，泛指非关系型数据库。泛指那些不使用SQL语句进行数据操作的数据库，所有数据库中只要不使用SQL语句的都是非关系型数据库。典型：Redis、MongoDB、hbase、 Hadoop、elasticsearch、图数据库(Neo4j、GraphDB、SequoiaDB)

# 二、redis介绍

![img](assets/src=http%253A%252F%252Fwww.21cto.com%252Fuploads%252Farticle%252F20200702%252Fda86590b701eb357ae392a6be7d5bea7.png&refer=http%253A%252F%252Fwww.21cto-16508507362501.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto)

## 2.1、定义

> Redis（Remote Dictionary Server ，远程字典服务） 是一个使用ANSI C编写的开源、支持网络、基于内存、可选持久性的键值对存储数据库，是NoSQL数据库。

redis的出现主要是为了替代早期的Memcache缓存系统的。map内存型(数据存放在内存中)的非关系型(nosql)key-value(键值存储)数据库，
支持数据的持久化(基于RDB和AOF，注: 数据持久化时将数据存放到文件中，每次启动redis之后会先将文件中数据加载到内存，经常用来做缓存、数据共享、购物车、消息队列、计数器、限流等。(最基本的就是缓存一些经常用到的数据，提高读写速度)。

redis特性：

> - 速度快
> - 持久化
> - 多种数据结构
> - 支持多种编程语言
> - 主从复制
> - 高可用、分布式

## 2.2、Redis的数据类型及主要特性

Redis提供的数据类型主要分为5种自有类型和一种自定义类型，这5种自有类型包括：String类型、哈希类型、列表类型、集合类型和顺序集合类型。

```

redis={
"name":"yuan",
"age":"23",
"scors":[78,79,98,],
"info":{"gender":"male","tel":"110"},
"set":{1,2,3},
"zset":{1,2,3,}
}

```



![img](assets/139239-20191126141006657-1969131669.png)



## 2.3、Redis的应用场景有哪些？

Redis 的应用场景包括：

> 缓存系统（“热点”数据：高频读、低频写）：缓存用户信息，优惠券过期时间，验证码过期时间、session、token等
>
> 计数器：帖子的浏览数，视频播放次数，评论次数、点赞次数等
>
> 消息队列，秒杀系统
>
> 社交网络：粉丝、共同好友（可能认识的人），兴趣爱好（推荐商品）
>
> 排行榜（有序集合）
>
> 发布订阅：粉丝关注、消息通知

# 三、redis环境安装

redis的官方只提供了linux版本的redis，window系统的redis是微软团队根据官方的linux版本高仿的。

官方原版: https://redis.io/

中文官网:http://www.redis.cn

## 3.1、下载和安装

下载地址：https://github.com/tporadowski/redis/releases

 ![img](assets/867021-20190120213315530-199992307.png)

![img](assets/867021-20190120213337245-1919434508.png)

![img](assets/867021-20190120213351947-466120029.png)

![img](assets/867021-20190120213613576-1092651557.png)

![img](assets/867021-20190120213732137-1070050780.png)

![img](assets/867021-20190120213836094-663215847.png)

![img](assets/867021-20190120213850621-1280736381.png)

![img](assets/867021-20190120214101037-1456534345.png)

 

![img](assets/867021-20190120214000974-189830387.png)

使用以下命令启动redis服务端

```
redis-server C:/tool/redis/redis.windows.conf
```

![image-20220415174704371](assets/image-20220415174704371-16500160265153.png)

关闭上面这个cmd窗口就关闭redis服务器服务了。

![image-20220415103036668](assets/image-20220415103036668.png)

**redis作为windows服务启动方式**

```text
redis-server --service-install redis.windows.conf
```

启动服务：redis-server --service-start
停止服务：redis-server --service-stop

```text
# 如果连接操作redis，可以在终端下，使用以下命令：
redis-cli
```

ubuntu下安装：

```bash
安装命令：sudo apt-get install -y redis-server
卸载命令：sudo apt-get purge --auto-remove redis-server 
关闭命令：sudo service redis-server stop 
开启命令：sudo service redis-server start 
重启命令：sudo service redis-server restart
配置文件：/etc/redis/redis.conf
```

## 3.2、redis的配置

```bash
cat /etc/redis/redis.conf
```

redis 安装成功以后,window下的配置文件保存在软件 安装目录下,如果是mac或者linux,则默认安装/etc/redis/redis.conf

### redis的核心配置选项

绑定ip：访问白名单，如果需要远程访问，可将此注释，或绑定1个真实ip

```bash
bind 127.0.0.1   xx.xx.xx.xx
```

端⼝，默认为6379

```bash
port 6379
```

是否以守护进程运行

- 如果以守护进程运行，则不会在命令阻塞，类似于服务
- 如果以守护进程运行，则当前终端被阻塞
- 设置为yes表示守护进程，设置为no表示⾮守护进程
- 推荐设置为yes

```bash
daemonize yes
```

RDB持久化的备份策略（RDB备份是默认开启的）

```bash
 # save 时间 读写次数
 save 900 1     # 当redis在900内至少有1次读写操作，则触发一次数据库的备份操作
 save 300 10    # 当redis在300内至少有10次读写操作，则触发一次数据库的备份操作
 save 60 10000  # 当redis在60内至少有10000次读写操作，则触发一次数据库的备份操作
```

RDB持久化的备份文件

```bash
dbfilename dump.rdb
```

RDB持久化数据库数据文件的所在目录

```bash
dir /var/lib/redis
```

日志文件所载目录

```bash
loglevel notice
logfile /var/log/redis/redis-server.log
```

进程ID文件

```bash
pidfile /var/run/redis/redis-server.pid
```

数据库，默认有16个，数据名是不能自定义的，只能是0-15之间，当然这个15是数据库数量-1

```bash
database 16
```

redis的登录密码，生产阶段打开，开发阶段避免麻烦，一般都是注释的。redis在6.0版本以后新增了ACL访问控制机制，新增了用户管理，这个版本以后才有账号和密码，再次之前只有没有密码没有账号

```bash
# requirepass foobared
```

注意：开启了以后，redis-cli终端下使用 `auth 密码`来认证登录。

![image-20211108102339592](assets/image-20211108102339592.png)

AOF持久化的开启配置项(默认值是no，关闭状态)

```bash
appendonly no
```

AOF持久化的备份文件（AOF的备份数据文件与RDB的备份数据文件保存在同一个目录下，由dir配置项指定）

```bash
appendfilename "appendonly.aof"
```

AOF持久化备份策略[时间]

```bash
# appendfsync always
appendfsync everysec    # 工作中最常用。每一秒备份一次
# appendfsync no
```

哨兵集群：一主二从三哨兵(3台服务器)

### Redis的使用

redis是一款基于**CS**架构的数据库，所以redis有客户端redis-cli，也有服务端redis-server。

其中，客户端可以使用go、java、python等编程语言，也可以终端下使用命令行工具管理redis数据库，甚至可以安装一些别人开发的界面工具，例如：RDM。

![1553246999266](assets/1553246999266.png)

redis-cli客户端连接服务器：

```bash
# redis-cli -h `redis服务器ip` -p `redis服务器port`
redis-cli -h 10.16.244.3 -p 6379
```

# 四、redis数据类型

![img](assets/139239-20191126141006657-1969131669.png)

```text
redis可以理解成一个全局的大字典，key就是数据的唯一标识符。根据key对应的值不同，可以划分成5个基本数据类型。

redis = {
    "name":"yuan",
    "scors":["100","89","78"],
    "info":{
        "name":"rain"
        "age":22
    },
    "s":{item1,itme2,..}
}

1. string类型:
	字符串类型，是 Redis 中最为基础的数据存储类型，它在 Redis 中是二进制安全的，也就是byte类型。
	单个数据的最大容量是512M。
		key: 值
	
2. hash类型:
	哈希类型，用于存储对象/字典，对象/字典的结构为键值对。key、域、值的类型都为string。域在同一个hash中是唯一的。
		key:{
            域（属性）: 值，
            域:值，            
            域:值，
            域:值，
            ...
		}
3. list类型:
	列表类型，它的子成员类型为string。
		key: [值1，值2, 值3.....]
4. set类型:
	无序集合，它的子成员类型为string类型，元素唯一不重复，没有修改操作。
		key: {值1, 值4, 值3, ...., 值5}

5. zset类型(sortedSet):
	有序集合，它的子成员值的类型为string类型，元素唯一不重复，没有修改操作。权重值(score,分数)从小到大排列。
		key: {
			值1 权重值1(数字);
			值2 权重值2;
			值3 权重值3;
			值4 权重值4;
		}


```

## 4.1. string（字符串）

> - SET/SETEX/MSET/MSETNX
> - GET/MGET
> - GETSET
> - INCR/DECR
> - DEL

#### 1. 设置键值

set 设置的数据没有额外操作时，是不会过期的。

```bash
set key value
```

设置键为`name`值为`yuan`的数据

```bash
set name yuan
set name rain # 一个变量可以设置多次
```

![image-20220415103809481](assets/image-20220415103809481-16499902909211.png)

注意：redis中的所有数据操作，如果设置的键不存在则为添加，如果设置的键已经存在则修改。

设置一个键，当键不存在时才能设置成功，用于一个变量只能被设置一次的情况。

```bash
setnx  key  value
```

一般用于给数据加锁(分布式锁)

```bash
127.0.0.1:6379> setnx goods_1 101
(integer) 1
127.0.0.1:6379> setnx goods_1 102
(integer) 0  # 表示设置不成功

127.0.0.1:6379> del goods_1
(integer) 1
127.0.0.1:6379> setnx goods_1 102
(integer) 1
```

#### 2. 设置键值的过期时间

redis中可以对一切的数据进行设置有效期。以秒为单位

```bash
setex key seconds value
```

设置键为`goods_1`值为`101`过期时间为10秒的数据

```bash
setex name goods_1 10
```

#### 3. 关于设置保存数据的有效期

setex 添加保存数据到redis，同时设置有效期，格式：

```bash
setex key time value
```

![image-20220415104126645](assets/image-20220415104126645-16499904877172.png)

#### 4. 设置多个键值

```bash
mset key1 value1 key2 value2 ...
```

例3：设置键为`a1`值为`goland`、键为`a2`值为`java`、键为`a3`值为`c`

```bash
mset a1 goland a2 java a3 c
```

#### 5. 字符串拼接值

常见于大文件上传

```bash
append key value
```

向键为`a1`中拼接值`haha`

```bash
set title "我的"
append title "redis"
append title "学习之路"
```

#### 6. 根据键获取值

根据键获取值，如果不存在此键则返回`nil`

```bash
get key
```

获取键`name`的值

```bash
get name
```

根据多个键获取多个值

```bash
mget key1 key2 ...
```

获取键`a1、a2、a3`的值

```bash
mget a1 a2 a3
```

getset：设置key的新值，返回旧值

```bash
redis> GETSET db mongodb    # 没有旧值，返回 nil
(nil)
redis> GET db
"mongodb"

redis> GETSET db redis      # 返回旧值 mongodb
"mongodb"

redis> GET db
"redis"
```

####  7. 自增自减

web开发中的电商抢购、秒杀。游戏里面的投票、攻击计数。系统中计算当前在线人数、

```bash
set id 1
incr id   # 相当于id+1
get id    # 2
incr id   # 相当于id+1
get id    # 3

set goods_id_1 10
decr goods_id_1  # 相当于 id-1
get goods_id_1    # "9"
decr goods_id_1   # 相当于id-1
get goods_id_1    # 8

 set age 22
 incrby age 2 # 自增自减大于1的值时候用incrby
```

#### 8. 获取字符串的长度

```bash
set name xiaoming
strlen name  # 8 
```

#### 9. 比特流操作  

————————

mykey  00000011

1字节=8比特  1kb = 1024字节  1mb = 1024kb 1gb = 1024mb

1个int8就是一个字节，一个中文：3个字节

```bash
SETBIT     # SETBIT key offset value 按从左到右的偏移量设置一个bit数据的值 
GETBIT     # 获取一个bit数据的值
BITCOUNT   # 统计字符串被设置为1的bit数.
BITPOS     # 返回字符串里面第一个被设置为1或者0的bit位。
```

案例1：

```bash
SETBIT mykey 7 1
# 00000001
getbit mykey 7
# 00000001
SETBIT mykey 4 1
# 00001001
SETBIT mykey 15 1
# 0000100100000001
```

我们知道 'a' 的ASCII码是 97。转换为二进制是：01100001。offset的学名叫做“偏移” 。二进制中的每一位就是offset值啦，比如在这里 offset 0 等于 ‘0’ ，offset 1等于 '1' ,offset 2 等于 '1',offset 6 等于 '0' ，没错，offset是从左往右计数的，也就是从**高位往低位**。

我们通过SETBIT 命令将 andy中的 'a' 变成 'b' 应该怎么变呢？

也就是将 01100001 变成 01100010 （b的ASCII码是98），这个很简单啦，也就是将'a'中的offset 6从0变成1，将offset 7 从1变成0 。

案例2：签到系统

````bash
setbit user_1 6 1
setbit user_1 5 1
setbit user_1 4 0
setbit user_1 3 1
setbit user_1 2 0
setbit user_1 1 1
setbit user_1 0 1
BITCOUNT user_1 # 统计一周的打卡情况
````

## 4.2. key操作

redis中所有的数据都是通过key（键）来进行操作，这里我们学习一下关于任何数据类型都通用的命令。

#### （1）查找键

参数支持简单的正则表达式

```bash
keys pattern
```

查看所有键

```bash
keys *
```

例子：

```bash
# 查看名称中包含`a`的键
keys *a*
# 查看以a开头的键
keys a*
# 查看以a结尾的键
keys *a
```

#### （2）判断键是否存在

如果存在返回`1`，不存在返回`0`

```bash
exists key1
```

判断键`title`是否存在

```bash
exists title
```

#### （3）查看键的的值的数据类型

```bash
type key

# string    字符串
# hash      哈希类型
# list      列表类型
# set       无序集合
# zset      有序集合
```

查看键的值类型

```bash
type a1
# string
sadd member_list xiaoming xiaohong xiaobai
# (integer) 3
type member_list
# set
hset user_1 name xiaobai age 17 sex 1
# (integer) 3
type user_1
# hash
lpush brothers zhangfei guangyu liubei xiaohei
# (integer) 4
type brothers
# list

zadd achievements 61 xiaoming 62 xiaohong 83 xiaobai  78 xiaohei 87 xiaohui 99 xiaolong
# (integer) 6
type achievements
# zset
```

#### （4）删除键以及键对应的值

```bash
del key1 key2 ...
```

#### （5）查看键的有效期

```bash
ttl key

# 结果结果是秒作为单位的整数
# -1 表示永不过期
# -2 表示当前数据已经过期，查看一个不存在的数据的有效期就是-2
```

#### （6）设置key的有效期

给已有的数据重新设置有效期，redis中所有的数据都可以通过expire来设置它的有效期。有效期到了，数据就被删除。

```bash
expire key seconds
```

#### （7）清空所有key

慎用，一旦执行，则redis所有数据库0~15的全部key都会被清除

```bash
flushall
```

#### （8）key重命名

```bash
rename  oldkey newkey
```

把name重命名为username

```bash
set name yuan
rename name username
get username
```

select切换数据库

```bash
redis的配置文件中，默认有0~15之间的16个数据库，默认操作的就是0号数据库
select <数据库ID>
```

操作效果：

```bash
# 默认处于0号库
127.0.0.1:6379> select 1
OK
# 这是在1号库
127.0.0.1:6379[1]> set name xiaoming
OK
127.0.0.1:6379[1]> select 2
OK
# 这是在2号库
127.0.0.1:6379[2]> set name xiaohei
OK
```

## 4.3. list（数组）

队列，列表的子成员类型为string

> lpush key value
>
> rpush key value
>
> linsert key after|before  指定元素 value
>
> lindex key index
>
> lrange key start stop
>
> lset key index value
>
> lrem key count value

#### （1）添加子成员

```bash
# 在左侧(前)添加一条或多条数据
lpush key value1 value2 ...
# 在右侧(后)添加一条或多条数据
rpush key value1 value2 ...

# 在指定元素的左边(前)/右边（后）插入一个或多个数据
linsert key before 指定元素 value1 value2 ....
linsert key after 指定元素 value1 value2 ....
```

从键为`brother`的列表左侧添加一个或多个数据`liubei、guanyu、zhangfei`

```bash
lpush brother liubei
# [liubei]
lpush brother guanyu zhangfei xiaoming
# [xiaoming,zhangfei,guanyu,liubei]
```

从键为brother的列表右侧添加一个或多个数据，`xiaohong,xiaobai,xiaohui`

```bash
rpush brother xiaohong
# [xiaoming,zhangfei,guanyu,liubei,xiaohong]
rpush brother xiaobai xiaohui
# [xiaoming,zhangfei,guanyu,liubei,xiaohong,xiaobai,xiaohui]
```

从key=brother的xiaohong的列表位置左侧添加一个数据，`xiaoA,xiaoB`

```bash
linsert brother before xiaohong xiaoA
# [xiaoming,zhangfei,guanyu,liubei,xiaoA,xiaohong,xiaobai,xiaohui]
linsert brother before xiaohong xiaoB
# [xiaoming,zhangfei,guanyu,liubei,xiaoA,xiaoB,xiaohong,xiaobai,xiaohui]
```

从key=brother，key=xiaohong的列表位置右侧添加一个数据，`xiaoC,xiaoD`

```bash
linsert brother after xiaohong xiaoC
# [xiaoming,zhangfei,guanyu,liubei,xiaoA,xiaohong,xiaoC,xiaobai,xiaohui]
linsert brother after xiaohong xiaoD
# [xiaoming,zhangfei,guanyu,liubei,xiaoA,xiaohong,xiaoD,xiaoC,xiaobai,xiaohui]
```

注意：当列表如果存在多个成员值一致的情况下，默认只识别第一个。

```bash
127.0.0.1:6379> linsert brother before xiaoA xiaohong
# [xiaoming,zhangfei,guanyu,liubei,xiaohong,xiaoA,xiaohong,xiaoD,xiaoC,xiaobai,xiaohui]
127.0.0.1:6379> linsert brother before xiaohong xiaoE
# [xiaoming,zhangfei,guanyu,liubei,xiaoE,xiaohong,xiaoA,xiaohong,xiaoD,xiaoC,xiaobai,xiaohui]
127.0.0.1:6379> linsert brother after xiaohong xiaoF
# [xiaoming,zhangfei,guanyu,liubei,xiaoE,xiaohong,xiaoF,xiaoA,xiaohong,xiaoD,xiaoC,xiaobai,xiaohui]
```

#### （2）基于索引获取列表成员

根据指定的索引(下标)获取成员的值，负数下标从右边-1开始，逐个递减

```bash
lindex key index
```

获取brother下标为2以及-2的成员

```bash
del brother
lpush brother guanyu zhangfei xiaoming
lindex brother 2
# "guanyu"
lindex brother -2
# "zhangfei"
```

#### （3）获取列表的切片

闭区间[包括stop]

```bash
lrange key start stop
```

操作：

```bash
del brother
rpush brother liubei guanyu zhangfei xiaoming xaiohong
# 获取btother的全部成员
lrange brother 0 -1
# 获取brother的前2个成员
lrange brother 0 1
# 获取brother的后2个成员
lrange brother -2 -1
```

#### （4）获取列表的长度

```bash
llen key
```

获取brother列表的成员个数

```bash
llen brother
```

#### （5）按索引设置值

```bash
lset key index value
# 注意：
# redis的列表也有索引，从左往右，从0开始，逐一递增，第1个元素下标为0
# 索引可以是负数，表示尾部开始计数，如`-1`表示最后1个元素
```

修改键为`brother`的列表中下标为`4`的元素值为`xiaohongmao`

```bash
lset brother 4 xiaohonghong
```

#### （6）删除指定成员

移除并获取列表的第一个成员或最后一个成员

```bash
lpop key  # 第一个成员出列
rpop key  # 最后一个成员出列
```

获取并移除brother中的第一个成员

```bash
lpop brother
# 开发中往往使用rpush和lpop实现队列的数据结构->实现入列和出列
```

```bash
lrem key count value

# 注意：
# count表示删除的数量，value表示要删除的成员。该命令默认表示将列表从左侧前count个value的元素移除
# count==0，表示删除列表所有值为value的成员
# count >0，表示删除列表左侧开始的前count个value成员
# count <0，表示删除列表右侧开始的前count个value成员

del brother
rpush brother A B A C A
lrem brother 0 A
["B","C"]

del brother
rpush brother A B A C A
lrem brother -2 A
["A","B","C"]

del brother
rpush brother A B A C A
lrem brother 2 A
["B","C","A"]
```

## 4.4. hash（哈希）

> hset key field value
>
> hget key field
>
> hgetall info
>
> hmget key field1 field2 ...
>
> hincrby key field number

专门用于结构化的数据信息。对应的就是map/结构体

结构：

```text
键key:{
   	域field: 值value,
   	域field: 值value,
   	域field: 值value,
}
```

#### （1）设置指定键的属性/域

设置指定键的单个属性

```bash
hset key field value
```

设置键 `user_1`的属性`name`为`xiaoming`

```bash
127.0.0.1:6379> hset user_1 name xiaoming   # user_1没有会自动创建
(integer) 1
127.0.0.1:6379> hset user_1 name xiaohei    # user_1中重复的属性会被修改
(integer) 0
127.0.0.1:6379> hset user_1 age 16          # user_1中不存在的属性会被新增
(integer) 1
127.0.0.1:6379> hset user:1 name xiaohui    # user:1会在redis界面操作中以:作为目录分隔符
(integer) 1
127.0.0.1:6379> hset user:1 age 15
(integer) 1
127.0.0.1:6379> hset user:2 name xiaohong age 16  # 一次性添加或修改多个属性
```

#### （2）获取指定键的域/属性的值

获取指定键所有的域/属性

```bash
hkeys key
```

获取键user的所有域/属性

```bash
127.0.0.1:6379> hkeys user:2
1) "name"
2) "age"
127.0.0.1:6379> hkeys user:3
1) "name"
2) "age"
3) "sex"
```

获取指定键的单个域/属性的值

```
hget key field
```

获取键`user:3`属性`name`的值

```bash
127.0.0.1:6379> hget user:3 name
"xiaohong"
```

获取指定键的多个域/属性的值

```bash
hmget key field1 field2 ...
```

获取键`user:2`属性`name`、`age`的值

```bash
127.0.0.1:6379> hmget user:2 name age
1) "xiaohong"
2) "16"
```

获取指定键的所有值

```bash
hvals key
```

获取指定键的所有域值对

```bash
127.0.0.1:6379> hvals user:3
1) "xiaohong"
2) "17"
3) "1"
```

#### （3）获取hash的所有域值对

```bash
127.0.0.1:6379> hset user:1 name xiaoming age 16 sex 1
(integer) 3
127.0.0.1:6379> hgetall user:1
1) "name"
2) "xiaoming"
3) "age"
4) "16"
5) "sex"
6) "1"
```

#### （4）删除指定键的域/属性

```bash
hdel key field1 field2 ...
```

删除键`user:3`的属性`sex/age/name`，当键中的hash数据没有任何属性，则当前键会被redis删除

```bash
hdel user:3 sex age name
```

#### （5）判断指定属性/域是否存在于当前键对应的hash中

```bash
hexists   key  field
```

判断user:2中是否存在age属性

```bash
127.0.0.1:6379> hexists user:3 age
(integer) 0
127.0.0.1:6379> hexists user:2 age
(integer) 1
127.0.0.1:6379> 
```

#### （6）属性值自增自减

```bash
hincrby key field number
```

给user:2的age属性在原值基础上+/-10，然后在age现有值的基础上-2

```bash
# 按指定数值自增
127.0.0.1:6379> hincrby user:2 age 10
(integer) 77
127.0.0.1:6379> hincrby user:2 age 10
(integer) 87

# 按指定数值自减
127.0.0.1:6379> hincrby user:2 age -10
(integer) 77
127.0.0.1:6379> hincrby user:2 age -10
```

## 4.5. set（集合）

无序集合，重点就是去重和无序。

#### （1）添加元素

```bash
sadd key member1 member2 ...
```

向键`authors`的集合中添加元素`zhangsan`、`lisi`、`wangwu`

```bash
sadd authors zhangsan lisi wangwu
```

#### （2）获取集合的所有的成员

```bash
smembers key
```

获取键`authors`的集合中所有元素

```bash
smembers authors
```

#### （3）获取集合的长度

```bash
scard keys 
```

获取s2集合的长度

```bash
sadd s2 a b c d e

127.0.0.1:6379> scard s2
(integer) 5
```

#### （4）随机抽取一个或多个元素

抽取出来的成员被删除掉 

```bash
spop key [count=1]

# 注意：
# count为可选参数，不填则默认一个。被提取成员会从集合中被删除掉
```

随机获取s2集合的成员

```bash
sadd s2 a c d e

127.0.0.1:6379> spop s2 
"d"
127.0.0.1:6379> spop s2 
"c"
```

#### （5）删除指定元素

```bash
srem key value
```

删除键`authors`的集合中元素`wangwu`

```bash
srem authors wangwu
```

#### （6）交集、差集和并集

推荐、（协同过滤，基于用户、基于物品）

```bash
sinter  key1 key2 key3 ....    # 交集、比较多个集合中共同存在的成员
sdiff   key1 key2 key3 ....    # 差集、比较多个集合中不同的成员
sunion  key1 key2 key3 ....    # 并集、合并所有集合的成员，并去重
```

```bash
del user:1 user:2 user:3 user:4
sadd user:1 1 2 3 4     # user:1 = {1,2,3,4}
sadd user:2 1 3 4 5     # user:2 = {1,3,4,5}
sadd user:3 1 3 5 6     # user:3 = {1,3,5,6}
sadd user:4 2 3 4       # user:4 = {2,3,4}

# 交集
127.0.0.1:6379> sinter user:1 user:2
1) "1"
2) "3"
3) "4"
127.0.0.1:6379> sinter user:1 user:3
1) "1"
2) "3"
127.0.0.1:6379> sinter user:1 user:4
1) "2"
2) "3"
3) "4"

127.0.0.1:6379> sinter user:2 user:4
1) "3"
2) "4"

# 并集
127.0.0.1:6379> sunion user:1 user:2 user:4
1) "1"
2) "2"
3) "3"
4) "4"
5) "5"

# 差集
127.0.0.1:6379> sdiff user:2 user:3
1) "4"  # 此时可以给user:3推荐4

127.0.0.1:6379> sdiff user:3 user:2
1) "6"  # 此时可以给user:2推荐6

127.0.0.1:6379> sdiff user:1 user:3
1) "2"
2) "4"
```

## 4.6. zset（有序集合）

有序集合（score/value），去重并且根据score权重值来进行排序的。score从小到大排列。

#### （1）添加成员

```bash
zadd key score1 member1 score2 member2 score3 member3 ....
```

设置榜单achievements，设置成绩和用户名作为achievements的成员

```bash
127.0.0.1:6379> zadd achievements 61 xiaoming 62 xiaohong 83 xiaobai  78 xiaohei 87 xiaohui 99 xiaolan
(integer) 6
127.0.0.1:6379> zadd achievements 85 xiaohuang 
(integer) 1
127.0.0.1:6379> zadd achievements 54 xiaoqing
```

#### （2）获取score在指定区间的所有成员

```python
zrangebyscore key min max     # 按score进行从低往高排序获取指定score区间
zrevrangebyscore key min max  # 按score进行从高往低排序获取指定score区间
zrange key start stop         # 按scoer进行从低往高排序获取指定索引区间
zrevrange key start stop      # 按scoer进行从高往低排序获取指定索引区间
```

```python
zrange achievements 0 -1  # 从低到高全部成员
```

#### （3）获取集合长度

```bash
zcard key
```

获取users的长度

```bash
zcard achievements
```

#### （4）获取指定成员的权重值

```bash
zscore key member
```

获取users中xiaoming的成绩

```bash
127.0.0.1:6379> zscore achievements xiaobai
"93"
127.0.0.1:6379> zscore achievements xiaohong
"62"
127.0.0.1:6379> zscore achievements xiaoming
"61"
```

#### （5）获取指定成员在集合中的排名

排名从0开始计算

```bash
zrank key member      # score从小到大的排名
zrevrank key member   # score从大到小的排名
```

获取achievements中xiaohei的分数排名，从大到小

```bash
127.0.0.1:6379> zrevrank achievements xiaohei
(integer) 4
```

#### （6）获取score在指定区间的所有成员数量

```bash
zcount key min max
```

获取achievements从0~60分之间的人数[闭区间]

```bash
127.0.0.1:6379> zcount achievements 0 60
(integer) 2
127.0.0.1:6379> zcount achievements 54 60
(integer) 2
```

#### （7）给指定成员增加增加权重值

```bash
zincrby key score member
```

给achievements中xiaobai增加10分

```bash
127.0.0.1:6379> ZINCRBY achievements 10 xiaobai
"93
```

#### （8）删除成员

```bash
zrem key member1 member2 member3 ....
```

从achievements中删除xiaoming的数据

```bash
zrem achievements xiaoming
```

#### （9）删除指定数量的成员

```bash
# 删除指定数量的成员，从最低score开始删除
zpopmin key [count]
# 删除指定数量的成员，从最高score开始删除
zpopmax key [count]
```

例子：

```bash
# 从achievements中提取并删除成绩最低的2个数据
127.0.0.1:6379> zpopmin achievements 2
1) "xiaoqing"
2) "54"
3) "xiaolv"
4) "60"

# 从achievements中提取并删除成绩最高的2个数据
127.0.0.1:6379> zpopmax achievements 2
1) "xiaolan"
2) "99"
3) "xiaobai"
4) "93"
```

# 五、Python操作redis

## 一、python对redis基本操作

#### （1）连接redis

```python
# 方式1
import redis

r = redis.Redis(host='127.0.0.1', port=6379)
r.set('foo', 'Bar')
print(r.get('foo'))


# 方式2
import redis

pool = redis.ConnectionPool(host='127.0.0.1', port=6379)
r = redis.Redis(connection_pool=pool)
r.set('bar', 'Foo')
print(r.get('bar'))
```

通常情况下, 当我们需要做redis操作时, 会创建一个连接, 并基于这个连接进行redis操作, 操作完成后, 释放连接,一般情况下, 这是没问题的, 但当并发量比较高的时候, 频繁的连接创建和释放对性能会有较高的影响。于是, 连接池就发挥作用了。连接池的原理是, 通过预先创建多个连接, 当进行redis操作时, 直接获取已经创建的连接进行操作, 而且操作完成后, 不会释放, 用于后续的其他redis操作。这样就达到了避免频繁的redis连接创建和释放的目的, 从而提高性能。

#### （2）数据类型操作

```python
import redis

pool = redis.ConnectionPool(host='127.0.0.1', port=6379, db=0, decode_responses=True)
r = redis.Redis(connection_pool=pool)

# （1）字符串操作：不允许对已经存在的键设置值
ret = r.setnx("name", "yuan")
print(ret)  # False
# （2）字符串操作：设置键有效期
r.setex("good_1001", 10, "2")
# （3）字符串操作：自增自减
r.set("age", 20)
r.incrby("age", 2)
print(r.get("age"))  # b'22'

# （4）hash操作：设置hash
r.hset("info", "name", "rain")
print(r.hget("info", "name"))  # b'rain'
r.hmset("info", {"gedner": "male", "age": 22})
print(r.hgetall("info"))  # {b'name': b'rain', b'gender': b'male', b'age': b'22'}

# （5）list操作：设置list
r.rpush("scores", "100", "90", "80")
r.rpush("scores", "70")
r.lpush("scores", "120")
print(r.lrange("scores", 0, -1))  # ['120', '100', '90', '80', '70']
r.linsert("scores", "AFTER", "100", 95)
print(r.lrange("scores", 0, -1))  # ['120', '100', '95', '90', '80', '70']
print(r.lpop("scores"))  # 120
print(r.rpop("scores"))  # 70
print(r.lindex("scores", 1)) # '95'

# （6）集合操作
# key对应的集合中添加元素
r.sadd("name_set", "zhangsan", "lisi", "wangwu")
# 获取key对应的集合的所有成员
print(r.smembers("name_set"))  # {'lisi', 'zhangsan', 'wangwu'}
# 从key对应的集合中随机获取 numbers 个元素
print(r.srandmember("name_set", 2))
r.srem("name_set", "lisi")
print(r.smembers("name_set"))  # {'wangwu', 'zhangsan'}

# （7）有序集合操作
# 在key对应的有序集合中添加元素
r.zadd("jifenbang", {"yuan": 78, "rain": 20, "alvin": 89, "eric": 45})
# 按照索引范围获取key对应的有序集合的元素
# zrange( name, start, end, desc=False, withscores=False, score_cast_func=float)
print(r.zrange("jifenbang", 0, -1))  # ['rain', 'eric', 'yuan', 'alvin']
print(r.zrange("jifenbang", 0, -1, withscores=True))  # ['rain', 'eric', 'yuan', 'alvin']
print(r.zrevrange("jifenbang", 0, -1, withscores=True))  # ['rain', 'eric', 'yuan', 'alvin']

print(r.zrangebyscore("jifenbang", 0, 100))
print(r.zrangebyscore("jifenbang", 0, 100, start=0, num=1))

# 删除key对应的有序集合中值是values的成员
print(r.zrem("jifenbang", "yuan"))  # 删除成功返回1
print(r.zrange("jifenbang", 0, -1))  # ['rain', 'eric', 'alvin']

# （8）键操作
r.delete("scores")
print(r.exists("scores"))
print(r.keys("*"))
r.expire("name",10)
```



## 二、关于redis的实战案例

### （1）案例1：KV缓存

![img](assets/src=http%253A%252F%252Ffilescdn.proginn.com%252F85891f3fb0eba3d2abd96f2558d70901%252Fdf95fabf1eba4716abaedd0a1e683bb2.webp&refer=http%253A%252F%252Ffilescdn.proginn.png)

第1个是最基础也是最常?的就是KV功能，我们可以用Redis来缓存用户信息、会话信息、商品信息等等。下面这段代码就是通过缓存读取逻辑。

```python
import redis

pool = redis.ConnectionPool(host='127.0.0.1', port=6379, db=6, decode_responses=True)
r = redis.Redis(connection_pool=pool)


def get_user(user_id):
    user = r.get(user_id)
    if not user:
        user = UserInfo.objects.get(pk=user_id)
        r.setex(user_id, 3600, user)

    return user
```

### （2）案例2：分布式锁

什么是分布式锁

> ❝
>
> 分布式锁其实就是，控制分布式系统不同进程共同访问共享资源的一种锁的实现。如果不同的系统或同一个系统的不同主机之间共享了某个临界资源，往往需要互斥来防止彼此干扰，以保证一致性。
>
> ❞

提到Redis的分布式锁，很多小伙伴马上就会想到`setnx`+ `expire`命令。即先用`setnx`来抢锁，如果抢到之后，再用`expire`给锁设置一个过期时间，防止锁忘记了释放。

> ❝
>
> SETNX 是SET IF NOT EXISTS的简写.日常命令格式是SETNX key value，如果 key不存在，则SETNX成功返回1，如果这个key已经存在了，则返回0。
>
> ❞

假设某电商网站的某商品做秒杀活动，key可以设置为key_resource_id,value设置任意值，伪代码如下：

#### 方案1

```python
import redis

pool = redis.ConnectionPool(host='127.0.0.1')
r = redis.Redis(connection_pool=pool)
ret = r.setnx("key_resource_id", "ok")
if ret:
    r.expire("key_resource_id", 5)  # 设置过期时间
    print("抢购成功！")
    r.delete("key_resource_id")  # 释放资源
else:
    print("抢购失败！")

```

但是这个方案中，`setnx`和`expire`两个命令分开了，**「不是原子操作」**。如果执行完`setnx`加锁，正要执行`expire`设置过期时间时，进程crash或者要重启维护了，那么这个锁就“长生不老”了，**「别的线程永远获取不到锁啦」**。

#### 方案2：SETNX + value值是(系统时间+过期时间)

为了解决方案一，**「发生异常锁得不到释放的场景」**，可以把过期时间放到`setnx`的value值里面。如果加锁失败，再拿出value值校验一下即可。加锁代码如下：

````python
import time


def foo():
    expiresTime = time.time() + 10
    ret = r.setnx("key_resource_id", expiresTime)
    if ret:
        print("当前锁不存在，加锁成功")
        return True

    oldExpiresTime = r.get("key_resource_id")
    if float(oldExpiresTime) < time.time():  # 如果获取到的过期时间，小于系统当前时间，表示已经过期
        # 锁已过期，获取上一个锁的过期时间，并设置现在锁的过期时间
        newExpiresTime = r.getset("key_resource_id", expiresTime)
        if oldExpiresTime == newExpiresTime:
            #  考虑多线程并发的情况，只有一个线程的设置值和当前值相同，它才可以加锁
            return True  # 加锁成功

    return False  # 其余情况加锁皆失败


foo()
````

#### 方案3

实际上，我们还可以使用Py的redis模块中的set函数来保证原子性（包含setnx和expire两条指令）代码如下：

```python
r.set("key_resource_id", "1", nx=True, ex=10)
```

### （3）案例4：延迟队列

延时队列可以通过Redis的zset(有序列表)来实现。我们将消息序列化为一个字符串作为zset的值。这个消息的到期时间处理时间作为score，然后用多个线程轮询zset获取到期的任务进行处理，多线程时为了保障可用性，万一挂了一个线程还有其他线程可以继续处理。因为有多个线程，所有需要考虑并发争抢任务，确保任务不能被多次执行。

![20200513191721238606](assets/20200513191721238606-16509537356372.png)

```python
import time
import uuid

import redis

pool = redis.ConnectionPool(host='127.0.0.1', port=6379, decode_responses=True)
r = redis.Redis(connection_pool=pool)


def delay_task(task_name, delay_time):
    # 保证value唯一
    task_id = task_name + str(uuid.uuid4())

    retry_ts = time.time() + delay_time
    r.zadd("delay-queue", {task_id: retry_ts})


def loop():
    print("循环监听中...")
    while True:
        # 最多取1条
        task_list = r.zrangebyscore("delay-queue", 0, time.time(), start=0, num=1)

        if not task_list:
            # 延时队列空的，休息1s
            print("cost 1秒钟")
            time.sleep(1)
            continue
        task_id = task_list[0]
        success = r.zrem("delay-queue", task_id)
        if success:
            # 处理消息逻辑函数
            handle_msg(task_id)

def handle_msg(msg):
    """消息处理逻辑"""
    print(f"消息{msg}已经被处理完成！")


import threading

t = threading.Thread(target=loop)
t.start()

delay_task("任务1延迟5", 5)
delay_task("任务2延迟2", 2)
delay_task("任务3延迟3", 3)
delay_task("任务4延迟10", 10)
```

redis的zrem方法是对多线程争抢任务的关键，它的返回值决定了当前实例有没有抢到任务，因为loop方法可能会被多个线程、多个进程调用， 同一个任务可能会被多个进程线程抢到，通过zrem来决定唯一的属主。同时，一定要对handle_msg进行异常捕获， 避免因为个别任务处理问题导致的循环异常退出。

### （4）案例5：发布订阅

```bash
subscribe channel # 订阅
publish channel mes # 发布消息
```

````python
import threading

import redis

r = redis.Redis(host='127.0.0.1')


def recv_msg():
    pub = r.pubsub()

    pub.subscribe("fm104.5")
    pub.parse_response()

    while 1:
        msg = pub.parse_response()
        print(msg)


def send_msg():
    msg = input(">>>")
    r.publish("fm104.5", msg)


t = threading.Thread(target=send_msg)
t.start()

recv_msg()
````

### （5）案例3：定时任务

利用 Redis 也能实现订单30分钟自动取消。

用户下单之后，在规定时间内如果不完成付款，订单自动取消，并且释放库存使用技术：Redis键空间通知（过期[回调](https://so.csdn.net/so/search?q=回调&spm=1001.2101.3001.7020)）用户下单之后将订单id作为key，任意值作为值存入redis中，给这条数据设置过期时间，也就是订单超时的时间启用键空间通知

#### 开启过期key监听

```python
from redis import StrictRedis

redis = StrictRedis(host='localhost', port=6379)

# 监听所有事件
# pubsub = redis.pubsub()
# pubsub.psubscribe('__keyspace@0__:*')
#
# print('Starting message loop')
# while True:
#     message = pubsub.get_message()
#     if message:
#         print(message)

# 监听过期key
def event_handler(msg):
    print("sss",msg)
    thread.stop()

pubsub = redis.pubsub()
pubsub.psubscribe(**{'__keyevent@0__:expired': event_handler})
thread = pubsub.run_in_thread(sleep_time=0.01)

```

### 
