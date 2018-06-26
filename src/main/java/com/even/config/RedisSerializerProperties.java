package com.haixue.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "haixue.redis")
public class RedisSerializerProperties {
    /** 序列化方式，json&kryo */
    private String type;
    /** 序列化白名单包，多个用逗号分隔 */
    private String whiteListPackage;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWhiteListPackage() {
        return whiteListPackage;
    }

    public void setWhiteListPackage(String whiteListPackage) {
        this.whiteListPackage = whiteListPackage;
    }
}
