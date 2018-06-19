# a simple code generator | 简化版代码生成器 （S-Generator）
========================================

**S-Generator** 是一个简化版的代码生成器，功能、流程相对简单，没有相关配置，只需要启动服务进行相对简单配置即可生成代码。生成代码只提供了常规的增删改查操作，当然这些操作只针对与单表。这些增删改查操作是基于Mybatis、Mybatis 通用mapper、Spring JPA来实现。

**S-Generator** 使用技术：FreeMarker、Spring Boot

适用范围：java项目，数据访问层使用 Mybatis、Mybatis 通用Mapper、JPA框架。

**S-Generator** 根据MySql、Oracle、SQLServer数据库中表的设计来生成相应的实体、Mapper、Xml Mapper 、Server、ServerImpl等，当前版本Server、ServerImpl中暂未创建相关方法、只是创建文件而已，后续版本会初始相关单表方法。

**Mybatis 生成代码**：实体类、Mapper、xml Mapper、Server、ServerImpl。

**Mybatis 整合通用Mapper 生成代码**：实体类、Mapper、xml Mapper、Server、ServerImpl。与Mybatis生成代码实际差不多。

**JPA 生成代码**： 实体类、Repository、Server、ServerImpl。
    
## 作者信息
    
    作者邮箱：  190642964@qq.com
    个人网站：  http://www.luojie.site
    
## S-Generator 使用说明
    
    ![Image text](https://raw.githubusercontent.com/lj5635906/generator/master/explain/1.png)
    
