package com.sailfish.engineering.algorithm.bloom;

import com.google.common.collect.Lists;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;

import java.util.ArrayList;

/**
 * @author sailfish
 * @create 2020-01-07-4:47 PM
 */
public class BloomFilterDemo {

    public static void main(String[] args) {
        final ArrayList<Integer> intList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);

        final Funnel<Integer> funnel = Funnels.integerFunnel();
        BloomFilter<Integer> friends = BloomFilter.create(funnel, 500, 0.01);
        for (Integer num : intList) {
            friends.put(num);
        }
// much later
        int num = 3;
        if (friends.mightContain(num)) {
            // the probability that dude reached this place if he isn't a friend is 1%
            // we might, for example, start asynchronously loading things for dude while we do a more expensive exact check
            System.out.println("contain num = " + num);
        } else {
            System.out.println("not contain num = " + num);

        }
    }
}
