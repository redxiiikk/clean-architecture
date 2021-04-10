# Clean Architecture

本项目通过使用 Java 模块化特性更好的实现 Clean Architecture

## 什么是整洁架构？

整洁架构是Bob 大叔在 2012 年提出的一种适用于复杂业务系统的软件架构方式，具体的详细信息可以参考 Bob 大叔的博客：[The Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

![image](https://user-images.githubusercontent.com/72877527/114264731-35ab1400-9a1f-11eb-9bda-54d64c0e0e8d.png)

## 为什么采用 Java 模块化可以更好地实现整洁架构？

在考虑为什么采取 Java 模块化的特别可以更好地帮助我们实现整洁架构的之前我们先聊一聊什么是 Java 模块化

### 什么是 Java 模块化？

Java 模块化是 Java 9 推出的一项新的特性， 是在 *包* 这个级别之上的更高一级的聚合，通过在项目中使用 Java 模块化可以实现对于代码访问权限更加精细化的控制

作为一个类库（框架或者业务类库）的开发者，通常我们只期望类库对外开发的接口或者类被使用，而一些内部的类我们并不希望被使用，但是在 Java 模块化之前并没有一个比较好的方法去实现这一点，因为在模块化之前 Java 类的访问权限只有 `public` 、 `private` 、 `protected` 和默认这四种，我们所有的 `public` 类和接口都是可以被随意使用，而模块化则在原有的访问权限的基础之上可以让我们实现对于访问权限的进一步的控制

### 模块化的关键： modules-info.java

Java 模块化的关键是 modules-info.java 文件，该文件使用 modules declarations 进行定义，接下来我们将根据目前这个实际的项目来进一步了解这个文件的作用

#### 项目的架构以及一些原则

```text
.
├── README.md
├── adapter
├── domain
├── service
```

## 参考资料

[干净架构最佳实践](https://blog.jaggerwang.net/clean-architecture-in-practice/)
