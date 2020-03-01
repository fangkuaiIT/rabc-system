# 基于SpringBoot+Vue.js的RABC权限系统
---

## 采用技术：
SpringBoot+SpringDataJPA+Springsecurity+JWT+vue.js+elementUI

## 你将学到
---

- Spring Boot 多模块项目的创建
- Vue.js 整合 ElementUI 前端项目的搭建
- Spring Seurity+JWT 的认证和鉴权
- 真实企业开发前后端分离数据的封装
- 实操一个多模块的前后端分离的 RABC 权限系统


## 进度
---

-RABC权限系统后端--主模块搭建完成--2020/2/22
-RABC权限系统后端--core模块搭建完成--2020/2/22
-RABC权限系统后端--公共属性jpa封装jpa模块搭建完成-2020/2/22
-RABC权限系统后端--util模块搭建完成-2020/2/22
-RABC权限系统后端-util模块的common模块搭建完成-2020/2/22

##  使用说明
---
- 将sql文件下的sql导入数据库
- 在主模块的server进行运行
- 启动项目
- 结合数据库的资源表，用户表，角色表，对应着看就明白什么角色有什么资源
- 然后对应角色表(用户:admin,密码：123456或者用户:liming,密码：123456)
- 对应着就能明白哪些用户有哪些角色然后用postman进行接口测试
- 结合IDEA的断点测试，慢慢的结合着我的gitchat关于验证和鉴权的思路慢慢的看springsecurity是如何执行的了。
