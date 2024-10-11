# My-fullstack-engineer-learning
# redis

# redis数据类型


Redis 支持五种主要的数据类型，每种类型适用于不同的使用场景。理解这些数据类型有助于优化 Redis 的使用和存储效率。

1. 字符串（String）
字符串是 Redis 最基本的数据类型，每个键对应的值是一个字符串。字符串类型可以存储任意形式的文本、数字、二进制数据（如图片或序列化对象）。字符串的最大长度为 512MB。

命令示例：
SET key value：设置键的值。
GET key：获取键的值。
INCR key：将值加 1（如果值是整数）。
APPEND key value：向键的值末尾追加字符串。
使用场景：

缓存数据，如网页内容。
计数器。
存储简单的文本或数值信息。


Redis 中的 String 类型是最常用的数据类型，存储的是键值对，值可以是字符串、数字或二进制数据。以下是一些常用的 Redis String 类型操作命令：

1. 设置与获取值
SET key value: 设置键的值。如果键不存在则创建，如果已存在则覆盖。
SET key "Hello, Redis"


GET key: 获取键的值。

SETNX key value: 仅当键不存在时，设置键的值（NX 表示 "Not Exist"）。
SETNX key "Only if not exist"

SETEX key seconds value: 设置带有过期时间的值，单位是秒。
SETEX key 10 "This will expire in 10 seconds"

GETSET key value: 设置键的新值并返回旧值。
GETSET key "New Value"

增加/减少数值
INCR key: 将键的值加 1（键的值必须是整数）。
INCR key

INCRBY key increment: 按指定增量增加键的值。
INCRBY key 5

DECR key: 将键的值减 1。
DECR key

DECRBY key decrement: 按指定减量减少键的值。
DECRBY key 5

3. 追加字符串
APPEND key value: 向现有值的末尾追加字符串。
APPEND key " more"

STRLEN key: 获取字符串值的长度。
STRLEN key

4. 多键操作
MSET key1 value1 key2 value2 ...: 一次设置多个键的值。
MSET key1 "value1" key2 "value2"

MGET key1 key2 ...: 一次获取多个键的值。
MGET key1 key2

MSETNX key1 value1 key2 value2 ...: 只有在所有键都不存在时，才设置多个键的值。
MSETNX key1 "value1" key2 "value2"


5. 位操作
SETBIT key offset value: 将字符串值的二进制位设置为 0 或 1。
SETBIT key 7 1


GETBIT key offset: 获取字符串中指定二进制位的值。
GETBIT key 7


BITCOUNT key [start end]: 计算字符串中值为 1 的位数量，可以通过 start 和 end 参数限定范围。
BITCOUNT key



BITOP operation destkey key1 [key2 ...]: 对多个键的二进制位进行指定操作（AND, OR, XOR, NOT）
BITOP AND resultKey key1 key2

6. 过期与生命周期
EXPIRE key seconds: 设置键的过期时间，单位为秒。
EXPIRE key 30

TTL key: 获取键的剩余过期时间（秒）。
TTL key

PERSIST key: 移除键的过期时间，使其变为持久存储。
EXPIRE key 30

TTL key: 获取键的剩余过期时间（秒）。
TTL key

PERSIST key: 移除键的过期时间，使其变为持久存储。
PERSIST key

7. 原子操作与锁
SET key value NX|XX EX seconds: 原子操作设置键的值并添加过期时间，NX 表示键不存在时设置，XX 表示键存在时设置。EX 用于设置过期时间（秒）。
SET key "value" NX EX 60  # 只在键不存在时设置，并设置过期时间为60秒




# 2. 哈希（Hash）
哈希类似于一个小型的键值对集合，键值对存储在 Redis 的一个键中。哈希表特别适用于存储对象的字段信息，例如用户的姓名、年龄、地址等。

命令示例：
HSET key field value：在哈希表中设置字段的值。
HGET key field：获取哈希表中字段的值。
HDEL key field：删除哈希表中的字段。
HGETALL key：获取哈希表中所有字段和值。
使用场景：

存储对象数据（例如用户、商品等），避免将每个字段作为独立的键存储。
适合需要存储较多字段的对象，且字段的更新较频繁。



# 3. 列表（List）
列表是按顺序存储的字符串链表，允许在两端（头部和尾部）进行高效的插入和删除操作。Redis 列表按插入顺序排序，因此可以使用它实现队列、堆栈等数据结构。

命令示例：
LPUSH key value：将值插入列表的头部。
RPUSH key value：将值插入列表的尾部。
LPOP key：移出并返回列表头部的元素。
LRANGE key start stop：获取列表中指定范围内的元素。
使用场景：

消息队列（队列、堆栈）。
记录最近的操作日志。
定时任务的处理队列。





# 4. 集合（Set）
集合是无序的字符串集合，集合中的元素是唯一的，不允许重复。Redis 集合支持交集、并集、差集等集合运算，非常适合需要去重的场景。

命令示例：
SADD key member：向集合中添加一个或多个元素。
SREM key member：从集合中移除一个或多个元素。
SMEMBERS key：返回集合中的所有元素。
SISMEMBER key member：检查元素是否在集合中。
使用场景：

去重操作，如去重用户 ID。
共同关注、共同好友等关系计算（通过集合的交集操作）。
随机获取集合中的元素。


# 5. 有序集合（Sorted Set）
有序集合类似于集合，但每个元素会关联一个浮点型的 score 值，Redis 会根据这个分数进行排序。与普通集合不同的是，有序集合的元素有序，并且允许元素根据分数排序。

命令示例：
ZADD key score member：向有序集合中添加元素并设置分数。
ZRANGE key start stop：按分数从低到高返回指定区间的元素。
ZREM key member：从有序集合中移除元素。
ZSCORE key member：获取元素的分数。
使用场景：

排行榜，如游戏排行榜、社交网络中用户的积分排名。
优先级队列（按分数设置优先级）。
计时器应用。



