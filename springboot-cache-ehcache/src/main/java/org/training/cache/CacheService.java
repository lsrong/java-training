package org.training.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class CacheService {
    private final Map<Integer, User> dataMap = new HashMap<Integer, User>(){
        {
            for(int i=1; i < 100; i++){
                User user = new User("code" + i,"name" + i);
                put(i, user);
            }
        }
    };

    // Ehcache缓存用户信息
    @Cacheable(value = "cache", key = "'user:' + #id")
    public User get(int id){
        log.info("使用id{}来查询用户：",id);
        return dataMap.get(id);
    }

    @CachePut(value="cache", key = "'user:'+#id")
    public User set(int id, User user){
        log.info("更新用户id{}数据" , id);
        dataMap.put(id, user);
        return user;
    }

    @CacheEvict(value = "cache", key = "'user:'+ #id")
    public void del(int id){
        log.info("删除id{}数据", id);
        dataMap.remove(id);
    }


}
