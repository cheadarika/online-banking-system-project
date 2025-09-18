package org.springclass.onlinebankingsystem.repository.entity.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Currency {
    USD("01",1),
    KHR("02",4000);

    private final String code;
    private final int exchangeRate;

    public static Currency find(String name) {
        for (Currency currency : Currency.values()) {
            if (currency.name().equals(name)) {
                return currency;
            }
        }
        return null;
    }
}
