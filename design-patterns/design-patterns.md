Design Patterns - Elements of Reusable Object-Oriented Software -  设计模式 - 可复用的面向对象软件元素

上面的设计模式书中提到了23种经典的模式，根据用途可以大体分为：创建型模式、结构型模式、行为型模式。

这些设计模式依赖于一些重要的设计原则 ：
- 面向接口编程，而不是面向实现。
- 职责单一原则。
- 开放封闭原则。

# 创建型模式
创建型模式关注点是如何创建对象，其核心思想是要把对象的创建和使用相分离，这样使得两者能相对独立地变换。

## 工厂方法 Factory Method
一个工厂类 XxxFactory ， 开放一个创建对象类的静态方法一般为 makeXxx， 传入不同的参数返回同一个父类（接口）的实例对象。

```java
package com.design.patterns.creation.factory.simple;

public class FoodFactory {
    /**
     * @description: 简单工厂模式，根据不同的参数，生成同一接口（父类）的不同派生的实例对象。
     * @param: [name]
     * @return: com.design.patterns.factory.simple.Food
     * @author: lsrong
     * @date: 2022/9/30 9:45
     **/
    public static Food makeFood(String name) {
        if (name.equals("noodle")) {
            Food noodle = new LanZhouNoodle();
            noodle.addSpicy("more");
            return noodle;

        } else if (name.equals("chicken")) {
            Food chicken = new HuangMenChicken();
            chicken.addCondiment("potato");
            return chicken;

        } else {
            return null;
        }
    }
}

```

> 单一职责原则： 一个类只提供一种功能，FoodFactory只负责提生产不同的Food派生实例对象。



##  工厂模式 Factory Interface

如果工厂方法可以满足需求的情况下，其实没有必要引入工厂模式，如果需要两个或者两个以上工厂的场景时，就需要使用工厂模式的思路：

```java
public interface FoodFactory {
    /**
     * @description: 工厂接口，负责生产Food的派生类
     * @param: [name]
     * @return: com.design.patterns.factory.standard.Food
     * @author: lsrong
     * @date: 2022/9/30 10:28
     **/
    Food makeFood(String name);
}

public class ChineseFoodFactory implements FoodFactory{
    /**
     * @description: 实现工厂FoodFactory，具有工厂职责。
     * @param: [name]
     * @return: com.design.patterns.factory.standard.Food
     * @author: lsrong
     * @date: 2022/9/30 10:31
     **/
    @Override
    public Food makeFood(String name) {
        if(name.equals("A")){
            return new ChineseFoodA();

        } else if (name.equals("B")) {
            return new ChineseFoodB();

        }else{
            return null;
        }
    }
}

public class AmericanFoodFactory implements FoodFactory{
    /**
     * @description: 实现工厂FoodFactory，具有工厂职责。
     * @param: [name]
     * @return: com.design.patterns.factory.standard.Food
     * @author: lsrong
     * @date: 2022/9/30 10:31
     **/
    @Override
    public Food makeFood(String name) {
        if(name.equals("A")){
            return new AmericanFoodA();

        } else if (name.equals("B")) {
            return new AmericanFoodB();

        }else{
            return null;
        }
    }
}
```

其中，ChineseFoodA、ChineseFoodB、AmericanFoodA、AmericanFoodB 都派生自 Food。

调用示例：

```java
public class FoodApplication {
    /**
     * @description: 使用工厂模式的方法
     * @param: [args]
     * @return: void
     * @author: lsrong
     * @date: 2022/9/30 10:34
     **/
    public static void main(String[] args){
        // 选择一个工厂
        FoodFactory factory = new ChineseFoodFactory();

        // 通过工厂选择具体的对象，不同的工厂造出不同派生的实例对象
        Food food = factory.makeFood("A");
    }
}
```

`makeFood(A)`制作A类的食物，但是不同的工厂生产出来的完全是不同的。

**工厂模式的核心：首先选好需要的工厂，然后就和简单工厂模式一样常见派生的实例对象。**

再比如：有LogFactory接口，实现类有 FileLogFactory 和 KafkaLogFactory，分别对应将日志写入文件和写入 Kafka 中，先确定实例化FileLogFactory还是KafkaLogFactory，然后在确定接下来的操作。



## 抽象工厂 Abstract Factory

抽象工厂模式解决的问题比较复杂，不但工厂是抽象的，产品是抽象的，而且有多个产品需要创建，因此，这个抽象工厂会对应到多个实际工厂，每个实际工厂负责创建多个实际产品

![abstract-factory](D:\Code\Java\java-training\design-patterns\abstract-factory.png)

可以类比成，多个供应商负责提供一系列类型的产品。

示例说明，为用户提供一个Markdown文本装HTML和Word的服务，应用抽象工厂模式实现如下：

![abstract-example](D:\Code\Java\java-training\design-patterns\abstract-example.png)

```java
/**
 * @description: 抽象工厂
 * @author: lsrong
 * @date: 2022/9/30 16:10
 **/
public interface AbstractFactory {
    /**
     * @description: 生产HTML文档
     * @param: [md]
     * @return: com.design.patterns.creation.factory.service.HtmlDocument
     * @author: lsrong
     * @date: 2022/9/30 16:04
     **/
    HtmlDocument makeHtml(String md);
    
    /**
     * @description: 生产WORD文档
     * @param: [md]
     * @return: com.design.patterns.creation.factory.service.WordDocument
     * @author: lsrong
     * @date: 2022/9/30 16:04
     **/
    WordDocument makeWord(String md);
}
```

上面定义抽象工厂接口 `AbstractFactory`，提供生产的文档产品方法`makeHtml`和`makeWord`，分别创建`HtmlDocument`和`WordDocument`。

```java\
/**
 * @description: HTML文档接口
 * @author: lsrong
 * @date: 2022/9/30 16:03
 **/
public interface HtmlDocument {
    /**
     * @description: MD转成HTML格式
     * @param: []
     * @return: java.lang.String
     * @author: lsrong
     * @date: 2022/9/30 16:06
     **/
    String toHtml();

    /**
     * @description: 保存HTML文档
     * @param: [path]
     * @return: void
     * @author: lsrong
     * @date: 2022/9/30 16:07
     **/
    void save(Path path) throws IOException;
}

/**
 * @description: WORD文档接口
 * @author: lsrong
 * @date: 2022/9/30 16:03
 **/
public interface WordDocument {
    /**
     * @description: 保存WORD文档
     * @param: [path]
     * @return: void
     * @author: lsrong
     * @date: 2022/9/30 16:07
     **/
    void save(Path path) throws IOException;
}
```

接下来是第一个"供应商"`FastDoc`的实现`FastFactory`，实际的产品类为 `FastHtmlDocument`和`FastWordDocument`

```java
/**
 * @description:  Fast工厂
 * @author: lsrong
 * @date: 2022/9/30 16:04
 **/
public class FastFactory implements AbstractFactory {
    @Override
    public HtmlDocument makeHtml(String md) {
        // 创建Fast的HTML文档
        return new FastHtmlDocument(md);
    }

    @Override
    public WordDocument makeWord(String md) {
        // 创建Fast的WORD文档
        return new FastWordDocument(md);
    }
}

/**
 * @description: Fast的HtmlDocument实现类
 * @author: lsrong
 * @date: 2022/9/30 16:20
 **/
public class FastHtmlDocument implements HtmlDocument {
    /**
     * @description: Fast的转换成HTML文档
     * @param: [path]
     * @return: void
     * @author: lsrong
     * @date: 2022/9/30 16:08
     **/
    @Override
    public String toHtml() {
        ...
    }
    
    @Override
    public void save(Path path) throws IOException {
        ...
    }
}

/**
 * @description: Fast的WordDocument实现类
 * @author: lsrong
 * @date: 2022/9/30 16:20
 **/
public class FastWordDocument implements WordDocument {
    /**
     * @description: Fast的转换成WORD文档
     * @param: [path]
     * @return: void
     * @author: lsrong
     * @date: 2022/9/30 16:08
     **/
    @Override
    public void save(Path path) throws IOException {
    	...
    }
}
```

使用FaseDoc的服务的客户端方式如下：

```java
String md = "#Hello\nHello, world!";
// Fast factory
AbstractFactory fastFactory = new FastFactory();
// Fast to html
HtmlDocument fastHtml = fastFactory.makeHtml(md);
fastHtml.save(Paths.get(".", "fast.html"));
// Fast to word
WordDocument fastWord = fastFactory.makeWord(md);
fastWord.save(Paths.get(".", "fast.doc"));
```

用了抽象工厂模式，就可以用同样的方式定义第二个“供应商”服务GoodDoc，根据定义好的抽象工厂和抽象产品接口，实现GoodDoc的实际工厂和实际产品：

```java
/**
 * @description:  Good工厂
 * @author: lsrong
 * @date: 2022/9/30 16:04
 **/
public class GoodFactory implements AbstractFactory {

    @Override
    public HtmlDocument makeHtml(String md) {
        // 创建Good的HTML文档
        return new GoodHtmlDocument(md);
    }

    @Override
    public WordDocument makeWord(String md) {
        // 创建Good的WORD文档
        return new GoodWordDocument(md);
    }
}

/**
 * @description: Good的HtmlDocument实现类
 * @author: lsrong
 * @date: 2022/9/30 16:20
 **/
public class GoodHtmlDocument implements HtmlDocument {
    /**
     * @description: Good的装换HTML方法
     * @param: [path]
     * @return: void
     * @author: lsrong
     * @date: 2022/9/30 16:07
     **/
    @Override
    public String toHtml() {
        ...
    }

    @Override
    public void save(Path path) throws IOException {
        ...
    }
}

/**
 * @description: Good的WordDocument实现类
 * @author: lsrong
 * @date: 2022/9/30 16:20
 **/
public class GoodWordDocument implements WordDocument {
    /**
     * @description: Good的转换并保存WORD文档
     * @param: [path]
     * @return: void
     * @author: lsrong
     * @date: 2022/9/30 16:08
     **/
    @Override
    public void save(Path path) throws IOException {
        ...
    }
}
```

客户端调用GoodDoc服务的代码如下：

```java
String md = "#Hello\nHello, world!";
// Good Factory
AbstractFactory goodFactory = new GoodFactory();
// Good to html
HtmlDocument goodHtml = goodFactory.makeHtml(md);
goodHtml.save(Paths.get(".", "good.html"));
// Good to word
WordDocument goodWord = goodFactory.makeWord(md);
goodWord.save(Paths.get(".", "good.doc"));
```

`GoodDoc`和`FastDoc`创建的`WordDocument`的实际效果对比：

![image-20220930163321304](C:\Users\53438\AppData\Roaming\Typora\typora-user-images\image-20220930163321304.png)

需要注意的点：

- 客户端除了使用new 创建`GoodFactory`或者`FastFactory`之外，其余直接使用产品接口，不应该引用任何实际方法。

- 如果把创建工厂的代码也放到`AbstractFactory`的话就可以屏蔽实际工厂。

  ```java
  /**
       * @description: 创建实际工厂
       * @param: [name]
       * @return: com.design.patterns.creation.factory.service.AbstractFactory
       * @author: lsrong
       * @date: 2022/9/30 16:38
       **/
      static AbstractFactory createFactory(String name){
          if(name.equalsIgnoreCase("fast")){
              return new FastFactory();
          } else if (name.equalsIgnoreCase("good")) {
              return new GoodFactory();
          }else {
              throw new IllegalArgumentException("Unsupported factory!");
          }
      }
  ```

  > 示例代码：[design-patterns-factory](https://github.com/lsrong/java-training/tree/master/design-patterns/src/main/java/com/design/patterns/creation/factory)
