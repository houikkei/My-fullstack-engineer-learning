# import redis
# import threading
#
# r = redis.Redis(host='127.0.0.1')
#
#
# def recv_msg():
#     pub = r.pubsub()
#
#     pub.subscribe("room_1001")
#     pub.parse_response()
#
#     while 1:
#         res_msg = pub.parse_response()
#         print(">>>", res_msg)
#
#
# def send_msg():
#     msg = input(">>>")
#     r.publish("room_1001", msg)
#
#
# t = threading.Thread(target=send_msg)
# t.start()
#
# recv_msg()


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
    print("sss", msg)
    thread.stop()


pubsub = redis.pubsub()
pubsub.psubscribe(**{'__keyevent@0__:expired': event_handler})
thread = pubsub.run_in_thread(sleep_time=0.01)
