package com.learnjava.collection.set;

public class Message {
  public final int sequence;
  public final String txt;

  public Message(int sequence, String txt) {
    this.sequence = sequence;
    this.txt = txt;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Message) {
      Message m = (Message) o;
      return this.txt.equals(m.txt);
    }

    return false;
  }

  @Override
  public int hashCode() {
    return this.sequence;
  }

  @Override
  public String toString() {
    return String.format("%d. %s ", this.sequence, this.txt);
  }
}
