package com.ki;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;

import java.util.ArrayList;
import java.util.List;

/**
 *  ������ ��� ���������� �������
 */
public class PolySimplifier {

    // ������ ����� � ������ � ������ ������
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

    /* �������� �������-������-�������
        step - ������� �����, ��� ������ ������� (��� ����� ��� ������� ���������)
        epsilon - ����������� ������� ��������� �� �������������� (��� ����� ��� ����� ���������)
     */
    public static MatOfPoint reduceRDP(MatOfPoint mop, int step ,double epsilon){
        List<Point> pointList = mop.toList();
        List<Point> tempPointList = new ArrayList<>();

        return null;
    }
}
