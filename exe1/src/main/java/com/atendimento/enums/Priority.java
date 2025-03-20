package com.atendimento.enums;

public enum Priority {
  URGENCY(1),
  SCHEDULED(2),
  REGULAR(3);

  private final int value;

  Priority(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public static Priority getByValue(int value) {
    for (Priority priority : Priority.values()) {
      if (priority.getValue() == value) {
        return priority;
      }
    }
    throw new IllegalArgumentException("No enum constant with value " + value);
  }
}
