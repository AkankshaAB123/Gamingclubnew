package com.gaming.gamer;

public class MemberDTO {
    private String id;
    private String name;
    private String phone;
    private Double balance;

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhoneNumber() { return phone; }
    public void setPhoneNumber(String phone) { this.phone = phone; }

    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }
}
