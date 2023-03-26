package com.epam.effectiveJava.util;

import java.util.ArrayList;
public class Util {
    private Util() {
    }

    public static Double averageTime(ArrayList<Double> spentTimeForPut) {
        Double averageTime = 0.0;
        if (spentTimeForPut.size() > 0) {
            Double sumTime = 0.0;
            for (Double spentTime : spentTimeForPut) {
                sumTime += spentTime;
            }
            return sumTime / spentTimeForPut.size() / 1000000;
        }
        return averageTime;
    }
}
