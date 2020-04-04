package dev.fvames.base.cloneable;

/**
 * Created by James on 2018/11/23.
 */
public class Email implements Cloneable {
    private String context;

    public Email(String context) {
        this.context = context;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Email{" +
                "context='" + context + '\'' +
                '}';
    }
}
