package org.training.cache;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cache")
public class CacheApi {
    @Autowired
    private CacheService cacheService;

    @GetMapping("get")
    public User get(@RequestParam int id) {
        return cacheService.get(id);
    }

    @PostMapping ("set")
    public User set(@RequestBody UserQuery request) {
        User user = new User(request.getCode(), request.getName());
        return cacheService.set(request.getId(), user);
    }

    @DeleteMapping("del")
    public void del(@RequestParam int id){
        cacheService.del(id);
    }
}
