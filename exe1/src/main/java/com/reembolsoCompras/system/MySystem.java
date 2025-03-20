package com.reembolsoCompras.system;

import java.util.Scanner;
import com.global.structures.*;
import com.reembolsoCompras.models.*;
import com.reembolsoCompras.enums.RefundRequestStatus;

public class MySystem {
  private Boolean isRunning;
  private Scanner scan;
  private MyQueue<RefundRequest> requestQueue;

  public MySystem() {
    this.scan = new Scanner(System.in).useDelimiter("\n");
    this.requestQueue = new MyQueue<>();
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

  public void optionsInput() {
    System.out.println("\n------------< MENU >------------");
    System.out.println("1 - Adicionar pedido de reembolso");
    System.out.println("2 - Analisar proximo pedido de reembolso");
    System.out.println("3 - Fechar");
    System.out.print("Digite a opção: ");
    Integer selectedOption = scan.nextInt();

    switch (selectedOption) {
      case 1:
        this.enqueueRequest();
        break;
      case 2:
        this.dequeueRequest();
        break;
      case 3:
        this.stop();
        break;
      case 4:
        this.requestQueue.print();
        break;
      default:
        System.out.println("Opção Invalida");
        this.optionsInput();
        break;
    }
  }

  private void enqueueRequest() {
    System.out.println("\n------------< ADICIONANDO >------------");
    System.out.print("Digite o numero do pedido: ");
    Integer requestNumber = scan.nextInt();
    System.out.print("Digite o numero da compra: ");
    Integer orderNumber = scan.nextInt();
    System.out.print("Digite a descrição da solicitação: ");
    String description = scan.next();

    RefundRequest newRequest = new RefundRequest(requestNumber, orderNumber, description);
    this.requestQueue.enqueue(newRequest);
  }

  private void dequeueRequest() {
    if (this.requestQueue.peek() == null) {
      System.out.println("Sem pedidos para serem analisados");
      return;
    }

    System.out.println("\n------------< ANALISANDO >------------");
    System.out.println("Pedido para ser analisado\n");
    System.out.println(this.requestQueue.peek());
    System.out.println("\nQual a sua decisão?");
    System.out.println("1 - Aprovado");
    System.out.println("2 - Reprovado");
    System.out.println("3 - Cancelar");
    System.out.print("Digite a opção: ");
    Integer input = scan.nextInt();

    RefundRequestStatus status;
    String justification = null;
    switch (input) {
      case 1:
        status = RefundRequestStatus.APROVED;
        break;
      case 2:
        status = RefundRequestStatus.DENIED;
        System.out.print("Digite a justificativa: ");
        justification = this.scan.next();
        break;
      case 3:
        return;
      default:
        System.out.println("Opção Invalida");
        this.dequeueRequest();
        return;
    }

    RefundRequest removedRequest = this.requestQueue.dequeue();
    removedRequest.setStatus(status);
    if (justification != null) {
      removedRequest.setJustification(justification);
    }

    System.out.println("\nPedido analisado: \n");
    System.out.println(removedRequest);
  }
}
