package com.zxyun.common.db.utils;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @des: ID生成器
 * @Author: given
 * @Date 2020/4/9 18:11
 */
public class IdGenerator {
    public static IdGenerator id;
    private AtomicLong count = new AtomicLong(0L);
    private static int bitTotal = 55;
    private static int bitForTime = 41;
    private static int bitForAppNumber = 3;
    private static int bitForCounting = 11;
    private int subSysId;
    private long appNumber;
    private long maskForTime;
    private long maskForSubSysId;
    private long maskForAppNumber;
    private long maskForCounting;
    private long idFixedPart;
    private long shiftBitForTime;
    public final long statrTime = 1525104000000L;

    public void setSubSysId(int subSysId) {
        this.subSysId = subSysId;
    }

    public void setAppNumber(long appNumber) {
        this.appNumber = appNumber;
    }

    private IdGenerator() {
    }

    public static void init() {
        id = new IdGenerator();
//        id.setAppNumber(RedisUtil.incrBy("JBD_CENTRAL_APP_NUMBER_COUNTER", 1L)); todo ...分布式ID生成策略
        id.setSubSysId(0);
        if (bitTotal != bitForTime + bitForAppNumber + bitForCounting) {
            throw new RuntimeException("IdGenerator的位数设置不匹配，bitTotal != bitForTime + bitForSubSysId + bitForAppNumber + bitForCounting");
        } else if (bitTotal > 64) {
            throw new RuntimeException("IdGenerator的位数设置错误，bitTotal > 63，bitTotal = " + bitTotal);
        } else {
            id.preCalculation();
        }
    }

    public long generate() {
        long currTime = System.currentTimeMillis() - 1525104000000L;
        long tmp = currTime & this.maskForTime;
        long ret = tmp << (int)this.shiftBitForTime | this.count.incrementAndGet() & this.maskForCounting | this.idFixedPart;
        return ret;
    }

    public long getTimeById(long id) {
        return (id >> (int)this.shiftBitForTime) + 1525104000000L;
    }

    private void preCalculation() {
        this.maskForTime = (1L << bitForTime) - 1L;
        this.maskForAppNumber = (1L << bitForAppNumber) - 1L;
        this.maskForCounting = (1L << bitForCounting) - 1L;
        this.idFixedPart = (long)this.subSysId & this.maskForSubSysId;
        this.idFixedPart = this.idFixedPart << bitForAppNumber | this.appNumber & this.maskForAppNumber;
        this.idFixedPart <<= bitForCounting;
        this.shiftBitForTime = (long)(bitForAppNumber + bitForCounting);
    }

    public static void main(String[] args) {
        init();

        for(int i = 0; i < 10; ++i) {
            System.out.println(id.generate());
        }

    }
}
