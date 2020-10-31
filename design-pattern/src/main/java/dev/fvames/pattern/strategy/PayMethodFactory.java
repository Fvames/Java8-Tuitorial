package dev.fvames.pattern.strategy;

/**
 * Created by James on 2018/11/23.
 */
public class PayMethodFactory {

    public static PayMethod getPayMethod(String type) {
        switch (type) {
            case "credit":
                return null;
            case "debit":
                return new DebitCard();
            default:
                throw new RuntimeException("can't find type ");
        }
    }
}
