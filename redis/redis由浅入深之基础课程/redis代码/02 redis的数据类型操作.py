import redis

pool = redis.ConnectionPool(host='127.0.0.1', port=6379)
r = redis.Redis(connection_pool=pool)

# 一 字符串操作
# r.set('bar', 'Foo')
# print(r.get('bar'))
#
# # 不允许对已经存在的键设置值
# ret = r.setnx("name", "yuan")
# print(ret)  # False
#
# # 设置键有效期
# r.setex("good_1001", 10, "2")
#
# # 自增自减
# r.set("age", 22)
# r.incr("age", 10)
# print(r.get("age"))

# 二、hash操作

# r.hset("info", "name", "rain")
# print(r.hget("info", "name"))


# r.hmset("info", {"gedner": "male", "age": 22})
# print(r.hgetall("info")) # {b'name': b'rain', b'gedner': b'male', b'age': b'22'}


# 三、list操作：设置list

# r.delete("scores")
# r.rpush("scores", "100", "90", "80")  # "scores" :["120","100, "90", "80","70"]
# r.rpush("scores", "70")
# r.lpush("scores", "120")
# print(r.lrange("scores", 0, -1))  # ['120', '100', '90', '80', '70']
#
# r.linsert("scores", "AFTER", "100", 95)
# print(r.lrange("scores", 0, -1))  # ['120', '100', '95', '90', '80', '70']
# print(r.lpop("scores"))  # 120
# print(r.rpop("scores"))  # 70
# print(r.lrange("scores", 0, -1))
# print(r.lindex("scores", 1))  # '95'
#
# # （6）集合操作
# # key对应的集合中添加元素
# r.delete("name_set")
# r.sadd("name_set", "zhangsan", "lisi", "wangwu")
# # 获取key对应的集合的所有成员
# print(r.smembers("name_set"))  # {'lisi', 'zhangsan', 'wangwu'}
# # 从key对应的集合中随机获取 numbers 个元素
# print(r.srandmember("name_set", 2))
# print(r.smembers("name_set"))
# r.srem("name_set", "lisi")
# print(r.smembers("name_set"))  # {'wangwu', 'zhangsan'}
#
# # （7）有序集合操作
#
# # 在key对应的有序集合中添加元素
# r.delete("jifenbang")
# r.zadd("jifenbang", {"yuan": 78, "rain": 20, "alvin": 89, "eric": 45})
# # 按照索引范围获取key对应的有序集合的元素
# # zrange( name, start, end, desc=False, withscores=False, score_cast_func=float)
# print(r.zrange("jifenbang", 0, -1))  # ['rain', 'eric', 'yuan', 'alvin']
# print(r.zrange("jifenbang", 0, -1,
#                withscores=True))  # [(b'rain', 20.0), (b'eric', 45.0), (b'yuan', 78.0), (b'alvin', 89.0)]
# print(r.zrevrange("jifenbang", 0, -1, withscores=True))  # ['rain', 'eric', 'yuan', 'alvin']

# print(r.zrangebyscore("jifenbang", 60, 100))  # [b'yuan', b'alvin']
# print(r.zrangebyscore("jifenbang", 60, 100, start=0, num=1))  # [b'yuan']

# # 删除key对应的有序集合中值是values的成员
# print(r.zrem("jifenbang", "yuan"))  # 删除成功返回1
# print(r.zrange("jifenbang", 0, -1))  # ['rain', 'eric', 'alvin']

# # （8）键操作
# r.delete("scores")
# print(r.exists("scores"))
# print(r.keys("*"))
# r.expire("name",10)
