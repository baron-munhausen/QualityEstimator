package com.ki;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Методи для прорідження контура
 */
public class PolySimplifier {

    // викидає точки з масиву з певним кроком
    public static MatOfPoint reduceSimple(MatOfPoint mop, int step) {
        List<Point> pointList = mop.toList();
        List<Point> tempPointList = new ArrayList<>();

        for (int i = 0; i < pointList.size(); i += step) {
            tempPointList.add(pointList.get(i));
        }

        MatOfPoint simplifiedMop = new MatOfPoint();
        simplifiedMop.fromList(tempPointList);

        System.out.println("Simple reduce. num of points = " + tempPointList.size());

        return simplifiedMop;
    }

    /*
        алгоритм Дугласа-Рамера-Пекера
        epsilon - максимальна довжина відхилення від перпендикуляра (чим більше тим більше спрощення)
     */
    public static List<Point> reduceRDP(List<Point> inputPointList, double epsilon) {
        List<Point> outputPointList = new ArrayList<>();

        int startSegIndex = 0;                  // індекс точки початку відрізка
        int endSegIndex = inputPointList.size() - 1;     // індекс точки кінця відрізка

        double dMax = 0;                        //максимальна перпендикуляна відстань
        int maxDistancePointIndex = 0;          // індекс точки до якої відстань найбільша

        // шукаємо точку з найбільшою перпендикуялрною відстанню
        for (int i = startSegIndex; i < endSegIndex ; i++) {

            Point currentPoint = inputPointList.get(i);
            // обчислюємо для кожної точки вхідної фігури перпендикулярну відстань до відрізка
            Point perpendicularBasePoint = GeometryUtils.getPerpendicularBasePoint(currentPoint
                    , inputPointList.get(startSegIndex), inputPointList.get(endSegIndex));

            // якщо p == null то неможливо провести перпендикуляр
            if (perpendicularBasePoint == null)
                continue;

            // відстань від поточної точки до основи перпендикуляра
            double distance = GeometryUtils.getEuclideanDistance(currentPoint, perpendicularBasePoint);
            //System.out.println("distance = " + distance);

            if (distance > dMax) {
                dMax = distance;
                maxDistancePointIndex = i;
            }
        }

        // якщо максимальна відстань більша за epsilon то рукурсивно визиваємо функцію на ділянках
        if(dMax > epsilon){
            // ділимо поточну ламану на дві частини
            List<Point> pointList1Half = reduceRDP(inputPointList.subList(startSegIndex, maxDistancePointIndex), epsilon);
            List<Point> pointList2Half = reduceRDP(inputPointList.subList(maxDistancePointIndex, endSegIndex), epsilon);

            outputPointList.addAll(pointList1Half);
            outputPointList.addAll(pointList2Half);
        } else {
            // додаємо тільки першу і останню точку ламаної
            outputPointList.add(inputPointList.get(startSegIndex));
            outputPointList.add(inputPointList.get(endSegIndex));
        }

        return outputPointList;
    }
}
