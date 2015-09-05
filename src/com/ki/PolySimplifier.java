package com.ki;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;

import java.util.ArrayList;
import java.util.List;

/**
 *  Методи для прорідження контура
 */
public class PolySimplifier {

    // викидає точки з масиву з певним кроком
    public static MatOfPoint reduceSimple(MatOfPoint mop, int step){
        List<Point> pointList = mop.toList();
        List<Point> tempPointList = new ArrayList<>();

        for(int i=0; i<pointList.size(); i+=step){
            tempPointList.add(pointList.get(i));
        }

        MatOfPoint simplifiedMop = new MatOfPoint();
        simplifiedMop.fromList(tempPointList);

        return simplifiedMop;
    }

    /* алгоритм Дугласа-Рамера-Паркера
        step - кількість точок, для одного проходу (чим більше тим сильніше спрощення)
        epsilon - максимальна довжина відхилення від перпендикуляра (чим більше тим менше спрощення)
     */
    public static MatOfPoint reduceRDP(MatOfPoint mop, int step ,double epsilon){
        List<Point> pointList = mop.toList();
        List<Point> tempPointList = new ArrayList<>();

        return null;
    }
}
