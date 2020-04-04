package dev.fvames.pattern.strategy;

import org.springframework.stereotype.Service;

/**
 * Created by James on 2018/11/23.
 */
@Service
public class DebitCard extends Card {
    @Override
    protected String getType() {
        return "debit";
    }

    @Override
    protected void executeTransaction(int cents) {
        System.out.println("doTransaction with debit " + cents);
    }
}
