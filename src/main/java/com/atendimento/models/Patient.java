package com.atendimento.models;

import com.atendimento.enums.Priority;

public class Patient {
  private String name;
  private String cardNumber;
  private Priority priority;

  public Patient(String name, String cardNumber, Priority priority) {
    this.name = name;
    this.cardNumber = cardNumber;
    this.priority = priority;
  }

  public String getName() {
    return name;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public Priority getPriority() {
    return priority;
  }

  @Override
  public String toString() {
    return "Paciente {" +
        "Nome: '" + name + '\'' +
        ", Numero da carterinha: '" + cardNumber + '\'' +
        ", Prioridade:" + priority +
        '}';
  }
}
