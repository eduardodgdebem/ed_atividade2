package com.global.structures;

public class MyQueue<T> {
  private Node<T> head;
  private Node<T> tail;
  private Integer size = 0;

  public T peek() {
    if (head == null) {
      return null;
    }
    return head.value;
  }

  public Integer getSize() {
    return this.size;
  }

  public void enqueue(T value) {
    Node<T> newNode = new Node<>(value);
    this.size++;

    if (size == 1) {
      this.head = newNode;
      this.tail = newNode;
      return;
    }

    this.tail.child = newNode;
    this.tail = this.tail.child;
  }

  public T dequeue() {
    Node<T> tempNode = head;
    this.size--;

    if (size == 0) {
      this.head = null;
      this.tail = null;
      return tempNode.value;
    } else if (size < 1) {
      return null;
    }

    this.head = this.head.child;
    return tempNode.value;
  }

  public void print() {
    Node<T> tempNode = head;

    System.out.println();
    while (tempNode != null) {
      System.out.println(tempNode.value);
      tempNode = tempNode.child;
    }
    System.out.println();
  }
}
