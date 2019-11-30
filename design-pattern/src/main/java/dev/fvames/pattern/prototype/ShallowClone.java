package dev.fvames.pattern.prototype;

/**
 * 浅克隆
 */

public class ShallowClone implements Cloneable {

    private Long id;
    private User user;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        ShallowClone shallowClone = new ShallowClone();
        shallowClone.setId(this.id);
        shallowClone.setUser(this.user);
        return shallowClone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
