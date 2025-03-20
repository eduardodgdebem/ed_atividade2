package com.atendimento.system;

import java.util.Scanner;
import com.global.structures.MyQueue;
import com.atendimento.models.Patient;
import com.atendimento.enums.Priority;

public class MySystem {
  private MyQueue<Patient> urgentQueue;
  private MyQueue<Patient> scheduleQueue;
  private MyQueue<Patient> regularQueue;
  private Scanner scan;
  private boolean isRunning;

  public MySystem() {
    this.scan = new Scanner(System.in).useDelimiter("\n");
    this.urgentQueue = new MyQueue<>();
    this.scheduleQueue = new MyQueue<>();
    this.regularQueue = new MyQueue<>();
  }

  public void start() {
    this.isRunning = true;
    while (isRunning) {
      this.optionsInput();
    }
  }

  public void stop() {
    this.isRunning = false;
    this.scan.close();
  }

  private void optionsInput() {
    System.out.println("\n------------< MENU >------------");
    System.out.println("1 - Adicionar paciente");
    System.out.println("2 - Chamar proximo paciente");
    System.out.println("3 - Fechar");
    System.out.print("Digite a opção: ");
    Integer selectedOption = scan.nextInt();

    switch (selectedOption) {
      case 1:
        this.enqueuePatient();
        break;
      case 2:
        this.dequeuePatient();
        break;
      case 3:
        this.stop();
        break;
      case 4:
        this.urgentQueue.print();
        this.scheduleQueue.print();
        this.regularQueue.print();
        break;
      default:
        System.out.println("Opção Invalida");
        this.optionsInput();
        break;
    }
  }

  private void enqueuePatient() {
    System.out.println("\n------------< Adicionando Paciente >------------");
    System.out.print("Digite o nome do paciente: ");
    String pacienteName = scan.next();
    System.out.print("Digite o numero da carterinha: ");
    String cardNumber = scan.next();
    System.out.println("Escolha a prioridade");
    System.out.println("1 - Urgente");
    System.out.println("2 - Agendado");
    System.out.println("3 - Regular");
    System.out.print("Digite a opção: ");
    Priority selectedPriotity = Priority.getByValue(scan.nextInt());

    Patient newPatient = new Patient(pacienteName, cardNumber, selectedPriotity);

    switch (newPatient.getPriority()) {
      case URGENCY:
        urgentQueue.enqueue(newPatient);
        break;
      case SCHEDULED:
        scheduleQueue.enqueue(newPatient);
        break;
      case REGULAR:
        regularQueue.enqueue(newPatient);
        break;
      default:
        regularQueue.enqueue(newPatient);
        break;
    }

    System.out.println("\nAdicionado paciente:");
    System.out.println(newPatient.toString());
  }

  private void dequeuePatient() {
    System.out.println("\n------------< Paciente Chamado >------------");
    if (urgentQueue.getSize() > 0) {
      System.out.println(urgentQueue.dequeue());
    } else if (scheduleQueue.getSize() > 0) {
      System.out.println(scheduleQueue.dequeue());
    } else if (regularQueue.getSize() > 0) {
      System.out.println(regularQueue.dequeue());
    } else {
      System.out.println("Nem um paciente na fila");
    }
  }
}
