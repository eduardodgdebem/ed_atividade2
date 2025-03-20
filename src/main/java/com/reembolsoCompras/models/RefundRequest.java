package com.reembolsoCompras.models;

import com.reembolsoCompras.enums.RefundRequestStatus;

public class RefundRequest {
  private Integer requestNumber;
  private Integer orderNumber;
  private String description;
  private RefundRequestStatus status;
  private String justification;

  public RefundRequest(Integer requestNumber, Integer orderNumber, String description) {
    this.requestNumber = requestNumber;
    this.orderNumber = orderNumber;
    this.description = description;
    this.status = RefundRequestStatus.PENGING;
  }

  public Integer getRequestNumber() {
    return this.requestNumber;
  }

  public Integer getOrderNumber() {
    return this.orderNumber;
  }

  public String getDescription() {
    return this.description;
  }

  public void setStatus(RefundRequestStatus status) {
    this.status = status;
  }

  public void setJustification(String justification) {
    this.justification = justification;
  }

  @Override
  public String toString() {
    String returnString = "";
    returnString += "Numero do pedido: " + this.requestNumber + "\n";
    returnString += "Numero da compra: " + this.orderNumber + "\n";
    returnString += "Descrição: " + this.description + "\n";
    returnString += "Status: " + this.status + "\n";

    if (this.status == RefundRequestStatus.DENIED) {
      returnString += "Justificativa: " + this.justification;
    }

    return returnString;
  }
}
