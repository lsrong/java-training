# 结构型模式
结构型模式主要涉及如何组合各种对象以便获得更好、更灵活的结构。 虽然面向对象的继承机制提供了最基本的子类扩展父类的功能，但结构型模式不仅仅简单地使用继承，而更多地通过组合与运行期的动态组合来实现更灵活的功能。

换个说法，合理灵活的代码结构带来更好的维护性和扩展性。

## 适配器模式 Adapter
将一个类的接口转换成另外一个要使用的接口，使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。

适配器的示例：有一个`Task`类，实现了`Callable`接口，通过一个线程去执行它
```java
public class Task implements Callable<Long> {
    private final long num;

    public Task(long num) {
        this.num = num;
    }
    
    @Override
    public Long call() {
        long r = 0;
        for(long n=1; n < this.num; n++) {
            r = r + n;
        }
        System.out.println("Result: " + r);

        return r;
    }
}
```
`Thread`接收`Runable`接口，但是不接受`Callable`接口, 最终程序会抛出错误。代码如下：
```java
public class Main {
    public static void main(String[] args) {
        Callable<Long> callable = new Task(1000L);
        // Thread 接收 Runnable接口，不接受Callable 接口，会出现报错。
        Thread thread = new Thread(callable); // error
        thread.start();
    }
}
```
处理此类报错的解决方案有两个方向：
- 第一种：改写`Task`类，把实现的`Callable`改成`Runable`, 但是如果其他地方有引用`Callable`,改写`Task`的接口，会导致其他正常工作的代码无法编译。
- 第二种：用一个`Adapter`，把这个`Callable`接口变成`Runable`接口即可。

第二种思路就是适配器模，编写一个Adapter的思路如下：
- 实现目标接口，这里是Runnable；
- 内部持有一个待转换接口的引用，这里是通过字段持有Callable接口；
- 在目标接口的实现方法内部，调用待转换接口的方法。
这样`Thread`就可以接受`RunableAdapter`，因为它实现了`Runnable`接口。
Thread -> RunnableAdapter.run() -> Callable.call()，Thread通过一层转换，间接调用了Callable的call()方法。
代码如下：
```java
/**
 * @description: 将 Callable 接口 适配为 Runnable 接口
 * @author: lsrong
 * @date: 2022/10/13 18:07
 **/
public class RunnableAdapter implements Runnable{

    // 被转换接口
    private final Callable<?> callable;

    public RunnableAdapter(Callable<?> callable){
        this.callable = callable;
    }
    
    @Override
    public void run() {
        // 调用被转换接口
        try {
            callable.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
```
适配过后的调用代码：
```java
public class Main {
    public static void main(String[] args) {
        Callable<Long> callable = new Task(123400L);
        Thread thread = new Thread(new RunnableAdapter(callable));
        thread.start();
    }
}
```