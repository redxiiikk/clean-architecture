# Clean Architecture

本项目主要是对于整洁架构的一个实践，主要基于以下文章：

1. [干净架构最佳实践](https://blog.jaggerwang.net/clean-architecture-in-practice/)

## 什么是整洁架构？

整洁架构是Bob 大叔在 2012 年提出的一种适用于复杂业务系统的软件架构方式，具体的详细信息可以参考 Bob 大叔的博客：[The Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

![image](https://user-images.githubusercontent.com/72877527/114264731-35ab1400-9a1f-11eb-9bda-54d64c0e0e8d.png)

Bob 大叔在整洁架构中提出了一种由外至内的架构形态，越往内则表示代码的抽象层次越高，是对领域支持的高度抽象

## 整洁架构的目标

1. 框架独立性
2. 可测试性
3. UI 独立性
4. 数据库独立性
5. 外部代理独立性

## 架构整洁的衡量指标

代码长成什么样子才可以被认为是整洁的架构、才可以达成我们上述提到的目的？及我们应该以什么样的标准去衡量架构的整洁程度？个人认为我们可以从以下几点进行深入的考虑：

1. **合理地分层**
2. **清晰的业务规则**
3. **较好的复用性**
4. **避免代码的坏味道**
5. **较高的测试覆盖率**
6. **开发先难后易，整体效率不断提升然后趋于稳定**

## 整洁架构的组成

具体的概念可以查看本文中 Bob 大叔的原文，这里不再做更加详细的介绍了，本文更多的是对自己的一些思考的记录

### Entities

* **最小依赖性**：Entity 除了语言提供的能力以及一些通用的工具类或框架之外不应该依赖任何的其他的框架或者业务组件，这样我们可以获得一个通用的能力，在不同的上下文中灵活的使用

    > 目前大多数语言都是全类型语言，本身便提供了很多的东西，但这并不意味着语言所有的能力都是可以被添加在 Entity 中的，应该尽量少地去依赖太多特定的功能，保证 Entity 最大的通用性，是的可以其可以在复杂的业务环境中做到通用性

    > 最好是通过技术的方式强制做到这一点，比如建立一个新的模块并且只引用必要的模块
    
* **持续演进性**：Entity 中的业务规则是最为核心的也是最为通用的业务规则，通常是在业务的演进的过程中不断识别出可以放置在 Entity 中的业务规则

### User Case

User Case 是业务最为核心的部分，是业务的具体的实现，有时候也可以将其称之为 Service 或者领域层，所表达的意思都是相近的，这一层通常也会是系统中最为复杂的一部分，如何减少其复杂度应该是一个整洁架构关注的重点，当然也是一件非常困的事情

为了解决之间困难的事情我们就需要一些原则对我们进行指导：

1. **单一职责**：这个原则基本上所有的程序员都有所耳闻，单一职责要求我们一个方类或者一个方法只能做一件事情，但是在真实的项目中实践这项原则并不容易，这是因为通常情况下我们面对的都是极为复杂的业务逻辑，在复杂的业务逻辑清晰的划分出职责边界是一件费力且费时的事情，而且我们还要受到 *烂代码* 的影响，那么在这种比较糟糕的一个情况之下我们该如何进行处理呢，在这里我简单谈一下自己的经验：

   1. **要有重构代码的勇气** ：烂代码是具备很强的传染性的，但是你是否真的存在勇气去对代码进行重构呢？团队成员以及项目管理者是否支持你去重构代码呢？

   2. **一图胜千言** ：一张流程图可以帮助我们更好的梳理业务，业务明确以及思路清晰可以帮我们更好的拆分职责，完成良好的代码设计

      > 流程图还可以积累成业务文档，做到业务知识的传递

      > 个人认为初级程序员与高级程序员最大的一个区别就是初级程序员想得少干得多，看似忙碌但是更多的时候是瞎忙活；而高级程序员则与之相反，不鸣则已一鸣惊人，看似清闲但是可以做到高效的且高质量的编码
      >
      > 而在画流程图的过程就是对于业务的思考，可以帮助初级程序员更好的探究业务的本质，从而写出更好的代码

   3. **职责的层次划分** ：职责也是有着层次的划分的，例如一次数据库操作，你可以将所有的代码写成一个方法，但是我们可以将其分为不同的阶段（准备、执行、结果解析、资源关闭），可以对一次操作（也可以是单独的业务流程）进行更加细致的划分，这样虽然顶层的方法代码一个大的职责，但是我们可以再内部进行更加细致化的划分，从而使得代码更加清晰
   
      > 如何评判自己的代码时候做到单一职责最好的标准就是：该方法修改的原因有且只有一个
   
      > 个人倾向于将职责划分粒度细致到方法的级别
   
2. **保持独立** ：保持独立的好处相比已经不用多说了，个人认为可以考虑两个层次的：业务之间独立性、系统之间的独立性

   1. **业务之间的独立性** ：
   2. **系统之间的独立性** ：系统之间的独立性主要表现在外部依赖上，目前微服务架构正在大行其道，通过将大单体拆分为多个微小的服务确实可以带来诸多的好处，但也不可避免的增加了整个系统架构的复杂性，而在开发过程中与三方服务保持强相关的依赖会导致我们与其他服务紧密耦合，最终会造成动一发牵全身，使得整个系统成为一个大型的分布式单体，反而违背了微服务架构的初衷。那么我们该如何避免这种困境呢？个人认为可以从以下两个角度出发：
      1. 隔离依赖关系：服务之间的相互依赖关系是不可避免的，毕竟我们写代码的目的是实现业务，那么我们就只能通过某种模式尽可能的将这种强依赖关系进行弱化，我们可以将外部依赖抽象为一个内部代理，将三方的依赖关系进行转化，使其更加适合当前服务的上下文
      2. 服务间契约：唯一不变的就是变化，拆分为微服务之后，不同的团队负责不同的服务，彼此

## 实践

### 如何进行单元测试

如何进行有效的单元测试？看似简单的一个问题但是其实我们在很多的时候做的并不完善，自我总结在主要的原因在一下的几点上：

1. 没有养成良好的 TDD 的习惯
2. 对于单元测试的范围划分不清晰
3. 对于常见测试框架及其实践经验不够熟悉
4. 现有的代码架构不太适合进行 TDD 实践

### 如何进行职责的划分

### 如何进行 Code Review

## 其他

### 弃用 Java 9 模块系统的原因

如果你翻阅本项目的历史提交记录你就会发现在最开始的时候是准备采用 Java 9 模块化体系来实现的，但是后来因为在整合大量的外部的三方框架和依赖的时候对于 Java 9 模块化的配置出现了一定程度上的不兼容，主要存在两个原因：

1. 大量的三方框架和依赖对于 Java 9 模块化系统并未进行适配
2. 当时查阅到的资料都是对于模块化的入门理论，对于生产实践中的大型项目的经验并没有多少

> 当然最大的原因是本人技术能力仍有不足 😂