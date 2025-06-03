package com.common;

public class PersonalAccount implements Account{

    private String accNo; // 계좌 번호
    private int balance; // 잔액

    public PersonalAccount() {}

    public PersonalAccount(String accNo, int balance) {
        this.accNo = accNo;
        this.balance = balance;
    }

    @Override
    public String getBalance() {
        return this.balance + "";
    }

    @Override
    public boolean deposit(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("입금할 금액이 잘못되었습니다.");
        }

        this.balance += money;

        return true;
    }

    @Override
    public boolean withDraw(int money) {

        if (money > this.balance) {
            throw new IllegalArgumentException("잔액보다 큰 금액을 출금할 수 없습니다.");
        }

        this.balance -= money;

        return true;
    }
}
