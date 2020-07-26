package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {

  private static final long serialVersionUID = 1L;

  private String name;

  private Long contactNo;

  private String bankName;

  private Double balance;

  private boolean alertIsThere;

  private LocalDate cardIssuedDate;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getContactNo() {
    return contactNo;
  }

  public void setContactNo(Long contactNo) {
    this.contactNo = contactNo;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public LocalDate getCardDate() {
    return cardIssuedDate;
  }

  public void setCardDate(LocalDate cardDate) {
    this.cardIssuedDate = cardDate;
  }

  public boolean isAlertIsThere() {
    return alertIsThere;
  }

  public void setAlertIsThere(boolean alertIsThere) {
    this.alertIsThere = alertIsThere;
  }

  @Override
  public String toString() {
    return "Person{"
        + "name='"
        + name
        + '\''
        + ", contactNo="
        + contactNo
        + ", bankName='"
        + bankName
        + '\''
        + ", balance="
        + balance
        + ", cardIssuedDate="
        + cardIssuedDate
        + '}';
  }
}
