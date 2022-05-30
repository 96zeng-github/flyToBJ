

### redis

#### redis基本数据类型

> String(字符串)：redisTemplate.opsForValue() 

> List(列表)：redisTemplate.opsForList() 

> Hash(哈希)：redisTemplate.opsForHash() 

> Set(集合)：redisTemplate.opsForSet() 

> ZSet(有序集合)：redisTemplate.opsForZSet()

```yml
#特殊数据类型

Geospatial(位置)

Hyperloglog

Bitmap(位图)
```
#### String(字符串)
简介：String是redis最基本的数据结构类型，它是二进制安全的，可以存储图片或者序列化的对象，值最大存储为512M    
操作方式：redisTemplate.opsForValue()   
内部编码：int(8字节长整形),embstr(小于等于39字节字符串),raw(大于39字节字符串)     
应用场景：共享session，分布式锁，计数器，限流  

分布式锁
```
使用setnx命令（SETNX key value）,key不存在，设置key的值为value；若key存在，则SETNX不做任何操作。如果设置成功
则返回1，设置失败返回0    
```
计数器、限流
```
使用incr命令对key的值进行+1操作，如果key不存在则会设为1
限流即在时间段内使用incr统计请求数量，当超过时进行拦截
```

#### List(列表)
简介：List是用来存储多个有序的字符串    
操作方式：redisTemplate.opsForList() 
内部编码：zipList(压缩列表)、linkedList(链表)   
应用场景：消息队列，有序列表，奖池抽奖 

```
lpush + lpop = 先进后出（栈）Stack
lpush + rpop = 先进先出（队列）Queue
lpush + ltrim(截取) = Capped Collection（有限集合）
lpush + brpop（阻塞式弹出）= Message Queue（消息队列）
```






