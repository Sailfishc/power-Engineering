package com.sailfish.engineering.distributed.limit;

/**
 * @author sailfish
 * @create 2019-10-18-13:04
 */
public class FunnelLimit implements Limit {


    @Override
    public boolean singleLimit(String userId, String requestId, int period, int maxNum) {
        return false;
    }


    @Override
    public boolean funnelLimit(String userId, String requestId, int capacity, double leakingRate) {

        return false;
    }
}
