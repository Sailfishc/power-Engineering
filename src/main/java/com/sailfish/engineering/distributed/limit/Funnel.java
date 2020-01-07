package com.sailfish.engineering.distributed.limit;

import lombok.Data;

/**
 * 漏斗模型
 *
 * @author sailfish
 * @create 2019-10-18-14:14
 */
@Data
public class Funnel {

    /**
     * 漏斗容量
     */
    private int capacity;

    /**
     * 流水速率
     */
    private Double leakingRate;
    /**
     * 剩余容量
     */
    private int leftQuota;
    /**
     * 上一次漏水时间
     */
    private long leakingTs;

    public Funnel(int capacity, Double leakingRate) {
        this.capacity = capacity;
        this.leakingRate = leakingRate;
        this.leftQuota = capacity;
        this.leakingTs = System.currentTimeMillis();
    }


    /**
     * 加水
     * @param quota
     * @return
     */
    public boolean watering(int quota) {
        makeSpace();
        if (this.leftQuota >= quota) {
            this.leftQuota -= quota;
            return true;
        }
        return false;
    }

    private void makeSpace() {
        final long nowTs = System.currentTimeMillis();
        final long deltaTs = nowTs - this.leakingTs;
        final int deltaQuota = (int) (deltaTs * this.leakingRate);
        // 如果间隔时间过大，导致整数数字过大溢出
        if (deltaQuota < 0) {
            this.leftQuota = capacity;
            this.leakingTs = nowTs;
            return;
        }

        // 腾出的空间太小，不能放下
        if (deltaQuota < 1) {
            return;
        }
        this.leftQuota += deltaQuota;
        this.leakingTs = nowTs;
        if (this.leftQuota > this.capacity) {
            this.leftQuota = this.capacity;
        }
    }
}
