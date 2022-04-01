package org.training;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.boot.SpringApplication;

@EnableCaching
@SpringBootApplication
public class EhcacheApplication {
    public static void main(String[] args) {
        String tmpDir = System.getProperty("java.io.tmpdir");
        System.out.println("临时缓存路径：" + tmpDir);
        SpringApplication.run(EhcacheApplication.class, args);
    }
}
