package com.zxyun.common.db.mysql.annotation;

/**
 * @des:
 * @Author: given
 * @Date 2020/4/14 15:38
 */
public enum IdType {
    AUTO(0),
    NONE(1),
    INPUT(2),
    ID_WORKER(3),
    UUID(4),
    ID_WORKER_STR(5);

    private final int key;

    private IdType(int key) {
        this.key = key;
    }

    public int getKey() {
        return this.key;
    }
}
