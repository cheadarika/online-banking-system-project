package org.springclass.onlinebankingsystem.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springclass.onlinebankingsystem.base.entity.BaseEntity;
import org.springclass.onlinebankingsystem.repository.entity.enumerate.AccountType;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account extends BaseEntity {

    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    private Double balance;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Account() {}

    public Account(String accountNumber, AccountType accountType, Double balance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
    }
}
