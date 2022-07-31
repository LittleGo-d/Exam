package com.wh.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author whstart
 * @creat 2022--15-14:43
 */
public class Random {
    public static void main(String[] args) {
        Random random = new Random();
        int[] ints = random.randomCommon(10001, 10030, 10);
        for(int i = 0;i < ints.length;i++){
            System.out.println(ints[i]);
        }
    }
    public int[] randomCommon(int min, int max, int n){
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        int[] result = new int[n];
        int count = 0;
        while(count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if(num == result[j]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                result[count] = num;
                count++;
            }
        }
        return result;
    }
}
