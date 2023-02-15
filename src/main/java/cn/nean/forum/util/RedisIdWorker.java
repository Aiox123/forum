package cn.nean.forum.util;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/*
*  ID生成器
* */
@Component
public class RedisIdWorker {

    private static final long BEGIN_TIMESTAMP = 1672531200L;

    private static final int COUNT_BITS = 32;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    public long nextId(String keyPrefix){
        // 生成时间戳
        LocalDateTime now = LocalDateTime.now();
        // 当前秒数
        long nowSecond = now.toEpochSecond(ZoneOffset.UTC);
        // 时间戳
        long timestamp =  nowSecond - BEGIN_TIMESTAMP;
        // 获取当前日期，精确到天
        String data = now.format(DateTimeFormatter.ofPattern("yyyy:MM:dd"));
        // 生成序列号
        long count = stringRedisTemplate.opsForValue().increment("icr:" + keyPrefix + ":" + data);
        // 返回并拼接
        return timestamp << COUNT_BITS | count;
    }

    public static void main(String[] args) {
        LocalDateTime time = LocalDateTime.of(2023, 1, 1, 0, 0, 0);
        // 1672531200
        long second = time.toEpochSecond(ZoneOffset.UTC);
    }
}
