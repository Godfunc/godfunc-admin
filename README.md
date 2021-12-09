一个基于SpringBoot的快速项目开发模版，旨在为本人提升项目开发速度。

## 项目结构

- common 公共模块，提供公用的类。
- api 对外提供接口服务。
- manage 管理平台，提供最基本的用户和菜单管理等功能。
- db 项目的sql脚本

## 技术选型

1. SpringBoot
2. Mysql/Oracle
3. SpringSecurity
4. MyBatis-Plus
5. Swagger

## 快速开始

### 1. 创建数据

执行`db`文件夹下面的sql创建表，如果是mysql就执行`mysql.sql`，如果是oracle就执行`oracle.sql`。根据自身业务需求使用。

### 2. 使用开发工具打开项目

使用`idea`、`Eclipse`或者`Vscode`打开项目。

### 3.安装依赖

`maven install` 下载项目依赖

### 4. 修改项目配置

修改`api/src/main/resources/application.yml`和`manage/src/main/resources/application.yml`的配置。这里按照自己的实际情况配置。

ps:*注意修改数据库名称*

### 5. 启动项目

运行对应的`xxxApplication.java`运行项目

## 使用redis缓存

在`common/pom.xml`中将`spring-boot-starter-data-redis`的依赖的注释放开，然后在`application.yml`中添加`redis`配置。默认`manage`
的用户等信息会存进缓存，你也可以将验证码的缓存改到redis中。

## 自动生成代码
建议使用安装`MybatisX-Generator`插件进行代码生成，本项目符合该插件的命名规范。
