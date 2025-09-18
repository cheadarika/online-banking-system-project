package org.springclass.onlinebankingsystem.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springclass.onlinebankingsystem.base.entity.BaseEntity;
import org.springclass.onlinebankingsystem.repository.entity.enumerate.AccountType;
import org.springclass.onlinebankingsystem.repository.entity.enumerate.Currency;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account extends BaseEntity {

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    private Currency currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    private Double balance;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Account() {}

    public Account(String accountNumber, AccountType accountType, Double balance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return  "\n---------------------------------------\n" +
                "Account Name = " + accountName + "\n" +
                "Account Number = " + accountNumber + "\n" +
                "Currency = " + currency + "\n" +
                "Account Type = " + accountType + "\n" +
                "Balance = " + balance + "\n" +
                "User ID = " + user.getId() +
                "\n---------------------------------------\n";
    }
}
