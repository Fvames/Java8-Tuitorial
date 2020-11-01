package dev.fvames.lock.lock22;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.concurrent.locks.ReentrantLock;

@Data
@RequiredArgsConstructor
public class Item {
    // 商品名
    final String name;
    // 库存剩余
    int remaining = 1000;
    // ToString 不包含这个字段
    @ToString.Exclude
    ReentrantLock lock = new ReentrantLock();
}
