package org.springclass.onlinebankingsystem.repository.entity;

import jakarta.persistence.*;
import org.springclass.onlinebankingsystem.base.entity.BaseEntity;
import org.springclass.onlinebankingsystem.repository.entity.enumerate.TransactionType;

import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction extends BaseEntity {

    private Double amount;
    private String description;
    private Date transactionDate;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_account_id")
    private Account fromAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_account_id")
    private Account toAccount;
}
