package com.design.patterns.creation.builder.url;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

/**
 * @description: URL 构建
 * @author: lsrong
 * @date: 2022/10/12 15:16
 **/
public class UrlBuilder implements Builder {
    private String scheme = "http";
    private Integer port = -1;
    private String host = null;
    private String path = "/";
    private String username = null;
    private String password = null;
    private Map<String, String> query = null;

    public static UrlBuilder builder() {
        return new UrlBuilder();
    }

    private String encode(String val) {
        try {
            return URLEncoder.encode(val, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            return val;
        }
    }

    /**
     * @description:
     * @param: [line]
     * @return: java.lang.String
     * @author: lsrong
     * @date: 2022/10/12 17:19
     **/
    @Override
    public String build() {
        // url标准格式： scheme:[//[user:password@]host[:port]][/]path[?query]
        // 参考链接：https://www.baeldung.com/java-url-encoding-decoding
        StringBuilder url = new StringBuilder();
        url.append(scheme).append("://");
        if(username != null && password != null){
            url.append(username).append(":").append(password).append("@");
        }
        url.append(host);
        if(port >= 0 && port != 80 && port != 443) {
            url.append(":").append(port);
        }
        url.append("/").append(path);
        if(query != null && !query.isEmpty()) {
            String encodeQuery = query.keySet()
                    .stream()
                    .map(key -> key + "=" + this.encode(query.get(key)))
                    .collect(joining("&"));
            url.append("?").append(encodeQuery);
        }

        return url.toString();
    }

    public UrlBuilder scheme(String scheme){
        this.scheme = Objects.requireNonNull(scheme);

        return this;
    }

    public UrlBuilder port(Integer port){
        // 端口范围：0 ~ 65535
        if(port < 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid port");
        }
        this.port = Objects.requireNonNull(port);

        return this;
    }

    public UrlBuilder host(String host){
        this.host = Objects.requireNonNull(host);

        return this;
    }

    public UrlBuilder path(String path){
        this.path = Objects.requireNonNull(path);

        return this;
    }

    public UrlBuilder credential(String username, String password){
        this.username = Objects.requireNonNull(username);
        this.password = Objects.requireNonNull(password);

        return this;
    }

    public UrlBuilder query(Map<String, String> query){
        this.query = query;

        return this;
    }
}