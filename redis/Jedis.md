# My-fullstack-engineer-learning
# redis

# Jedis

Jedis 是 Redis 的官方 Java 客户端库，它提供了对 Redis 服务器的高效交互接口。通过 Jedis，Java 应用程序可以与 Redis 服务器进行通信，执行常见的 Redis 命令。Jedis 支持 Redis 的所有数据类型操作，并且提供了连接池等实用工具，适合在高并发环境下使用。

### 1. Jedis 的基本使用
#### 1.1 添加 Jedis 依赖
在 Maven 项目中，使用 Jedis 需要先在 pom.xml 中添加依赖：
 ```xml
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>4.3.1</version> <!-- 或最新版本 -->
</dependency>
 ```


如果使用 Gradle，依赖如下：
 ```groovy
implementation 'redis.clients:jedis:4.3.1'  // 或最新版本
 ```


#### 1.2 创建与 Redis 的连接
可以通过 Jedis 连接到 Redis 服务器，执行常见的 Redis 操作。
 ```java
import redis.clients.jedis.Jedis;

public class RedisExample {
    public static void main(String[] args) {
        // 创建连接到 Redis 的实例
        Jedis jedis = new Jedis("localhost", 6379); // 默认端口 6379

        // 测试连接是否成功
        System.out.println("Connection to Redis server: " + jedis.ping());

        // 设置和获取 Redis 的键值对
        jedis.set("myKey", "Hello Jedis");
        String value = jedis.get("myKey");
        System.out.println("Value of 'myKey': " + value);

        // 关闭连接
        jedis.close();
    }
}
 ```

### 2. 常用的 Jedis 操作
Jedis 支持 Redis 的所有操作，包括对 String、List、Set、Hash、ZSet 和 HyperLogLog 的处理。以下是一些常用的 Redis 数据类型操作示例：

#### 2.1 字符串操作（String）

 ```java
// 设置和获取字符串
jedis.set("name", "Alice");
String name = jedis.get("name");
System.out.println(name); // 输出 "Alice"

// 自增操作
jedis.set("counter", "1");
jedis.incr("counter");
System.out.println(jedis.get("counter")); // 输出 "2"
 ```


#### 2.2 哈希操作（Hash）

 ```java
// 设置和获取哈希表
jedis.hset("user:1001", "name", "John");
jedis.hset("user:1001", "age", "30");

String userName = jedis.hget("user:1001", "name");
System.out.println("User name: " + userName);  // 输出 "John"

// 获取整个哈希表
Map<String, String> user = jedis.hgetAll("user:1001");
System.out.println(user);  // 输出 {name=John, age=30}
 ```


#### 2.3 列表操作（List）
 ```java
// 从左侧插入列表
jedis.lpush("tasks", "Task1", "Task2", "Task3");

// 从列表中获取元素
List<String> tasks = jedis.lrange("tasks", 0, -1);
System.out.println(tasks);  // 输出 [Task3, Task2, Task1]

// 弹出列表头部元素
String task = jedis.lpop("tasks");
System.out.println(task);  // 输出 "Task3"
 ```

#### 2.4 集合操作（Set）

 ```java
// 添加集合元素
jedis.sadd("fruits", "apple", "banana", "orange");

// 获取集合中的所有元素
Set<String> fruits = jedis.smembers("fruits");
System.out.println(fruits);  // 输出 [banana, orange, apple]

// 检查某个元素是否存在于集合中
boolean isMember = jedis.sismember("fruits", "apple");
System.out.println(isMember);  // 输出 true
 ```


#### 2.5 有序集合操作（Sorted Set）
 ```java
// 添加有序集合的成员和分数
jedis.zadd("scores", 100, "Player1");
jedis.zadd("scores", 200, "Player2");

// 获取有序集合的成员
Set<String> players = jedis.zrange("scores", 0, -1);
System.out.println(players);  // 输出 [Player1, Player2]

// 获取成员的分数
Double score = jedis.zscore("scores", "Player1");
System.out.println(score);  // 输出 100.0
 ```


### 3. 使用 Jedis 连接池
在高并发环境中，频繁地创建和关闭 Jedis 连接会造成性能问题。Jedis 提供了连接池来优化连接管理。
 ```java
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolExample {
    public static void main(String[] args) {
        // 创建连接池配置
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(10); // 最大连接数
        poolConfig.setMaxIdle(5);   // 最大空闲连接数
        poolConfig.setMinIdle(2);   // 最小空闲连接数

        // 创建连接池
        JedisPool jedisPool = new JedisPool(poolConfig, "localhost", 6379);

        // 从连接池中获取 Jedis 连接
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set("foo", "bar");
            System.out.println(jedis.get("foo"));  // 输出 "bar"
        }

        // 关闭连接池
        jedisPool.close();
    }
}
 ```


### 4. Jedis 事务（Transactions）
Jedis 支持 Redis 的事务操作，允许在事务块中执行一组命令，确保其原子性。
 ```java
// 开启事务
Transaction transaction = jedis.multi();

// 执行多个 Redis 操作
transaction.set("key1", "value1");
transaction.incr("counter");

// 提交事务
transaction.exec();
 ```

### 5. Jedis 管道（Pipelining）
通过管道技术，可以一次性发送多条命令到 Redis 服务器，减少网络延迟。Jedis 提供了 Pipeline 类来实现这一功能。

 ```java
Pipeline pipeline = jedis.pipelined();

// 执行多个命令
pipeline.set("key1", "value1");
pipeline.incr("counter");

// 同步执行所有命令
pipeline.sync();
 ```


### 6. 异常处理
在使用 Jedis 时，常见异常包括 Redis 连接失败、超时等情况。处理这些异常可以提高应用的稳定性。
 ```java
try (Jedis jedis = jedisPool.getResource()) {
    jedis.set("key", "value");
} catch (Exception e) {
    System.err.println("Error while connecting to Redis: " + e.getMessage());
}
 ```

### 7. Jedis 与 Redis 哨兵（Sentinel）
Jedis 支持 Redis 的 Sentinel 模式，帮助在主从模式下进行故障切换。
 ```java
Set<String> sentinels = new HashSet<>();
sentinels.add("127.0.0.1:26379");
JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster", sentinels);

try (Jedis jedis = sentinelPool.getResource()) {
    jedis.set("key", "value");
    System.out.println(jedis.get("key"));
}

sentinelPool.close();
 ```

