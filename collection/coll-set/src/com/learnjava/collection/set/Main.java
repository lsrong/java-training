package com.learnjava.collection.set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {

  // 在聊天软件中，发送方发送消息时，遇到网络超时后就会自动重发，因此，接收方可能会收到重复的消息，在显示给用户看的时候，需要首先去重。练习使用Set去除重复的消息
  public static void main(String[] args) {
    List<Message> list = new ArrayList<>();
    //            new Message(1, "Hello!"),
    //            new Message(2, "发工资了吗？"),
    //            new Message(2, "发工资了吗？"),
    //            new Message(3, "去哪吃饭？"),
    //            new Message(3, "去哪吃饭？"),
    //            new Message(4, "Bye")
    list.add(new Message(1, "Hello!"));
    list.add(new Message(2, "下班了吗?"));
    list.add(new Message(2, "下班了吗?"));
    list.add(new Message(3, "去哪吃饭?"));
    list.add(new Message(3, "去哪吃饭?"));
    list.add(new Message(4, "Bye"));

    List<Message> received = progress(list);
    for (Message m : received) {
      System.out.println(m);
    }
  }

  public static List<Message> progress(List<Message> received) {
    // 根据 sequence 去除重复的消息
    HashSet<Message> hs = new HashSet<>(received);

    return new ArrayList<>(hs);
  }
}
