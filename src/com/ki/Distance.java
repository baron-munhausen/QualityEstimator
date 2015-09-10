package com.ki;

import com.ki.GeometryUtils;
import org.opencv.core.Point;

import java.util.List;

/**
 * Методи для обчислення метричних відстаней
 * як за стандартними формулами так і модифікованими
 */
public class Distance {

    public static double getHausdorffDistance(List<Point> contour1, List<Point> contour2) {

        // визначаэмо максимальне відхилення контурів
        return Math.max(getDeviation(contour1, contour2), getDeviation(contour2, contour1));
    }

    /*
    ***************************************************************************************
    *                               Допоміжні методи, які використовуються вище
     */

    // відхилення точки від контура (для Хаусдорфа), повертає відстань
    private static double getDeviation(Point p, List<Point> contour) {

        double minDistance = Double.MAX_VALUE;      // мінімальна відстань по Евкліду, від p до contour

        // визначаэмо відстань від вхідної точки до кожної точки контура
        // і зберігаємо найменшу
        for (int i = 0; i < contour.size(); i++) {
            double distance = GeometryUtils.getEuclideanDistance(p, contour.get(i));

            if (distance < minDistance) {
                minDistance = distance;
            }
        }

        return minDistance;
    }

    // відхилення контура від контура (для Хаусдорфа), повертає відстань
    // порядок аргументів має значення getDeviation(c1, c2) != getDeviation(c2, c1)
    private static double getDeviation(List<Point> contour1, List<Point> contour2) {

        double maxDistance = 0;             // максимальна відстань по Евкліду, від c1 до с2

        // визначаємо відхилення кожної точки c1 до с2
        // зберігаємо найбільшу
        for (int i = 0; i < contour1.size(); i++) {

            // визначаємо відхилення точки від контура
            double distance = Distance.getDeviation(contour1.get(i), contour2);

            if (distance > maxDistance) {
                maxDistance = distance;
            }
        }

        return maxDistance;
    }
}
