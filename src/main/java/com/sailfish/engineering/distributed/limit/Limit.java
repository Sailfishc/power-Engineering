package com.sailfish.engineering.distributed.limit;

/**
 * @author sailfish
 * @create 2019-10-18-11:08
 */
public interface Limit {

    /**
     * 对某个用户的某个操作在指定时间最大可以操作maxNum次
     *
     * @param userId
     * @param requestId
     * @param period
     * @param maxNum
     * @return
     */
    boolean singleLimit(String userId, String requestId, int period, int maxNum);


    /**
     * 漏斗模型
     *
     * @param userId
     * @param requestId
     * @param capacity
     * @param leakingRate
     * @return
     */
    boolean funnelLimit(String userId, String requestId, int capacity, double leakingRate);
}
