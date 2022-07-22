# 主要配置：
 + JDK`1.8`
 + SpringBoot`2.7.1`
 + MybatisPlus`3.5.2`

# Dev版yaml配置：
 ```
 spring:
  datasource:
    druid:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://localhost:3306/testdb
      username: test
      password: test123
  freemarker:
    cache: false
  thymeleaf:
    cache: false
  mail:
    host: smtp.163.com//以163邮箱为例
    username: //你的邮箱
    password: //邮箱秘钥
    protocol: smtp
    port: 25
    default-encoding: utf-8
    properties:
      mail:
        debug: true

server:
  port: 80

mybatis-plus:
  global-config:
    db-config:
      id-type: auto
      table-prefix: tb_
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
 ```
# 实现功能：
 + [x] 实现登录
 + [x] 实现邮箱注册，链接验证激活
 + [ ] 解析学校生活板块网页重新生成数据
 + [ ] 论坛帖子功能：CRUD
 + [ ] 论坛评论功能：CRUD
 + [ ] 好友聊天
 + [ ] 举报功能