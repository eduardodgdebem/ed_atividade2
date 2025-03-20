package com.global.structures;

public class Node<T> {
  public T value;
  public Node<T> child;

  public Node(T value) {
    this.value = value;
  }
}
