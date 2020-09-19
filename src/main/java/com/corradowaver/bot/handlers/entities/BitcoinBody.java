package com.corradowaver.bot.handlers.entities;

public class BitcoinBody {
  private String currency;
  private int price;
  private String symbol;

  public BitcoinBody(String currency, int price, String symbol) {
    this.currency = currency;
    this.price = price;
    this.symbol = symbol;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }
}
