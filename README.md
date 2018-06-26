# redis-object-spring-boot-starter

## 项目中使用redis序列化的时候，避免手动序列化对象和返回序列化。
    引入项目：
    <groupId>com.even</groupId>
        <artifactId>haixue-spring-boot-starter-redis</artifactId>
        <version>2.0.2-SNAPSHOT</version>

## 在springboot 配置文件中配置：
    haixue:
        redis:
            type: kryo #默认支持两种序列化方式 json或则 kryo
            whiteListPackage: com.haixue


    当配置文件中无此配置项的时候，默认采用spring-data-redis的原生的序列化方式
    当配置中加入了该配置项的时候会调用对应的序列化方式进行序列化和反序列化。
    whiteListPackage 主要是针对你要序列化的类的包名，多个包名逗号分隔。

    其中可根据项目需要，选择对应的序列化方式，json序列化方式，可读性高。kryo序列化方式可读性低，但是序列化速度快
    性能高
