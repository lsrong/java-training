# 创建型模式
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

**工厂模式的核心：首先选好需要的工厂，然后就和工厂方法一样创建派生的实例对象。**

再比如：有LogFactory接口，实现类有 FileLogFactory 和 KafkaLogFactory，分别对应将日志写入文件和写入 Kafka 中，先确定实例化FileLogFactory还是KafkaLogFactory，然后在确定接下来的操作。



## 抽象工厂 Abstract Factory

抽象工厂模式解决的问题比较复杂，不但工厂是抽象的，产品是抽象的，而且有多个产品需要创建，因此，这个抽象工厂会对应到多个实际工厂，每个实际工厂负责创建多个实际产品

![abstract-factory](D:\Code\Java\java-training\design-patterns\abstract-factory.png)

可以类比成，多个供应商负责提供一系列类型的产品。

示例说明，为用户提供一个Markdown文本装HTML和Word的服务，应用抽象工厂模式实现如下：

![abstract-example](D:\Code\Java\java-training\design-patterns\abstract-example.png)

抽象工厂`AbstractFactory `定义代码如下:

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

- 抽象工厂模式有个问题，如果需要增加产品的话那么所有的实际工厂都需要增加新方法，有点违反了**对修改关闭，对扩展开发的设计原则**



## 单例模式 Singleton

保证一个类有且仅有一个实例，并提供一个全局访问点，实现思路如下：

- 私有化构造方法，`private Singleton()`
- 静态属性来保存唯一实例，`private static final Singleton instance`
- 静态方法来获取唯一实例，`getInstance()`

```java
/**
 * @description: Singleton,最简单的实现
 * @author: lsrong
 * @date: 2022/9/30 17:25
 **/
public class Singleton {
    // 私有构造
    private Singleton(){}

    // 私有唯一实例
    private static final Singleton instance = new Singleton();

    // 公开实例获得方法
    public static Singleton getInstance(){
        return instance;
    }
}
```

上面需要注意不应该在Singleton类中塞入其他的静态方法，还有这种方法每次都会生成实例对象，有些情况是不需要生成的，这种情况称为延迟加载，实现方式如下：

```java
/**
 * @description: DeferSingleton,延迟加载单例模式
 * @author: lsrong
 * @date: 2022/9/30 17:25
 **/
public class DeferSingleton {
    // 私有构造
    private DeferSingleton(){}

    // 私有唯一实例
    private static volatile DeferSingleton instance;

    // 延迟加载
    public static DeferSingleton getInstance(){
        if(instance == null){
            synchronized (DeferSingleton.class){
                if(instance == null) {
                    instance = new DeferSingleton();
                }
            }
        }
        
        return instance;
    }
}
```

多线程下会出现并发的问题，因此需要加锁`synchronized`，一旦加锁就会严重影响性能，所以一般不会用这种方式。

嵌套类的方式也可以实现，和简单的单例模式类似：

```java
/**
 * @description: 嵌套类的单例模式
 * @author: lsrong
 * @date: 2022/9/30 17:40
 **/
public class HolderSingleton {
    private HolderSingleton(){}

    // 嵌套类可以访问外部类的静态属性和静态方法 的特性
    private static class Holder{
        private static HolderSingleton instance = new HolderSingleton();
    }

    public static HolderSingleton getInstance(){
        return Holder.instance;
    }
}
```

另一种实现Singleton的方式是利用Java的`enum`，因为Java保证枚举类的每个枚举都是单例，所以我们只需要编写一个只有一个枚举的类

```java
/**
 * @description: 枚举类的单例模式
 * @author: lsrong
 * @date: 2022/9/30 17:48
 **/
public enum EnumSingleton {
    INSTANCE;   // 唯一枚举
    private String name;
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

// 使用
// String name = World.INSTANCE.getName();
```

实际上,大部分框架都有提供实例化单例类的方式，如Spring的`Component`：

```java
@Component // 表示一个单例组件
public class MyService {
    ...
}
```



## 生成器模式 Builder

> 将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。

使用多个“小工厂”（属性构建方法）来最终创建出一个完整的对象，一般见到`XxxBuilder`的类就是构建者模式的类，用于创建一个对象的步骤比较多的时候，每个步骤都需要创建不同属性或者操作，最终组成一个完整的对象。

`Builder`生成器一般的使用方式：
```java
User user = UserBuilder.name("builder").password("pwd123").age(18).build();
```
自建的生成器模式示例：
```java
public class User {
    // 用户名私有属性
    private String name;
    private String password;
    private Integer age;

    private User(String name, String password, Integer age){
        this.name = name;
        this.password = password;
        this.age = age;
    }
    
    public static UserBuilder builder(){
        return new UserBuilder();
    }
    
    public static class UserBuilder{
        private String name;
        private String password;
        private Integer age;

        private UserBuilder(){
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder password(String pwd) {
            this.password = pwd;
            return this;
        }

        public UserBuilder age(Integer age) {
            this.age = age;
            return this;
        }

        public User build(){
            if(name == null || password == null) {
                throw new RuntimeException("用户名和密码必须");
            }

            if(age <= 0 || age >= 150) {
                throw new RuntimeException("年龄不合法");
            }

            return new User(name, password, age);
        }
    }
}
```
使用方式：先创建一个Builder,然后链式调用一系列构建方法，最终调用一次`build()`方法，最后创建对象。
```java
public class BuilderApp {
  public static void main(String[] args) {
    // 自定义的Builder示例
    User user = User.builder()
            .name("builder-custom")
            .password("pwd123")
            .age(28)
            .build();
    System.out.println(user);
  }
}
```
生成器模式如果属性比较多的时候，会多写很多“无用”的builder的代码，Builder的构造方法可以强制让调用者提供必填字段，`build()`方法中校验各个参数。

可以使用lombok的@Builder标注可以忽略很多builder代码
```java
/**
 * @description: 使用 Lombok 的构建者模式
 * @author: lsrong
 * @date: 2022/10/8 11:02
 **/
@Builder
public class UserLombok {
    private String name;
    private String password;
    private int age;
    
    public String toString() {
        return String.format("UserLombokBuilder:name=%s, password=%s, age=%d", name, password, age);
    }
}
```

## 原型模式（Clone）
基于已存在的原型实例产生新的实例，可以理解为“克隆”一个已存在的对象，来生产同类的对象实例。
```java
/**
 * @description: 实现原型模式，Cloneable
 * @author: lsrong
 * @date: 2022/10/13 11:00
 **/
public class Student implements Cloneable{
    private int id;
    private String name;
    private int score;
    
    public void id(int id) {
        this.id = id;
    }
    
    public void name(String name) {
        this.name = name;
    }
    
    public void score(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return String.format("{Student: id=%s, name=%s, score=%s}@%s", this.id, this.name, this.score,
                Integer.toHexString(hashCode()));
    }
    
    @Override
    public Student clone() {
        Student std = new Student();
        std.id = id;
        std.name = name;
        std.score = score;

        return std;
    }
}
```

> java 的克隆是浅克隆，碰到对象引用的时候，克隆出来的对象和原对象中的引用将指向同一个对象。通常实现深克隆的方法是将对象进行序列化，然后再进行反序列化。

## 创建型模式总结
创建型模式在于如何创建对象，核心思想是要把对象的创建和使用分离，这样使得两者能相对独立的变换。

- 工厂方法：在单一职能原则上，工厂类中存在一个创建使用对象的方法来创建对应的使用对象实例。
- 工厂模式：在简单工厂方法的基础上，添加选择工厂的维度，使用方式上选择合适的工厂，然后通过工厂创建具体的产品对象。
- 抽象工厂模式：具有整个产品线的模式，从工厂到产品都有进一步的抽象。
- 单例模式：保证全局只有唯一的使用对象，安全并且节约内存资源。
- 生产器模式：针对那些属性很多的类，好用的扩展`Lombok`。
- 原型模式：用的比较少，了解Object的clone方法即可。
