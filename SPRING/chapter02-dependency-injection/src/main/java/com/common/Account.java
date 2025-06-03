package com.common;

public interface Account {
    /* 잔액 조회 */
    String getBalance();

    /* 입금 */
    boolean deposit(int money);

    /* 출금 */
    boolean withDraw(int money);

}
