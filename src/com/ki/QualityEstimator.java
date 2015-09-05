package com.ki;

import com.ki.tools.GUI;
import com.ki.tools.ImageConverter;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;


public class QualityEstimator {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        Mat img = Imgcodecs.imread("images\\shapes.png");

        GUI.displayImage(ImageConverter.Mat2BufferedImage(img), "Input image");

        List<MatOfPoint> contours = new ArrayList<>();
        Mat imgBinary = new Mat();

        // перетворюємо в сіре
        Imgproc.cvtColor(img, img, Imgproc.COLOR_RGB2GRAY);
        // конвертуємо вхідне зображення в бінарне
        Imgproc.threshold(img, imgBinary, -1, 255, Imgproc.THRESH_BINARY_INV + Imgproc.THRESH_OTSU);

        GUI.displayImage(ImageConverter.Mat2BufferedImage(imgBinary), "Binary image");

        Imgproc.findContours(imgBinary, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);


        for(int i=0; i < contours.size(); i++){
            // створюємо поверхню для 1 контура
            Mat imgContour = new Mat(img.height(), img.width(), CvType.CV_8UC3);
            // малюємо контур на поверхні
            Imgproc.drawContours(imgContour, contours, i, new Scalar(20, 150, 0), 1);
            printContourInfo(contours.get(i), "#" + i);
            // виводимо на екран
            GUI.displayImage(ImageConverter.Mat2BufferedImage(imgContour), "Contour #" + i);

            Mat imgSimpContour = new Mat(img.height(), img.width(), CvType.CV_8UC3);
            //Imgproc.drawContours(imgSimpContour, PolySimplifier.thinOut(contours.get(i), 2), 0, new Scalar(20, 150, 0), 1);
            GUI.displayImage(ImageConverter.Mat2BufferedImage(imgSimpContour), "Simplified Contour #" + i);

        }
    }

    public static void printContourInfo(MatOfPoint matOfPoint, String name){
        List<Point> pointList = matOfPoint.toList();
        System.out.println("contour " + name);
        System.out.println("num of points = " + pointList.size() + " \n");

        /* в pointList точки знаходяться в наступному порядку
        приклад:
                1 8 7
                2   6
                3 4 5
        перша точка завжди найближча до лівого верхнього краю зображення
         */

        if(pointList.size() < 10){
            for(int i=0; i < pointList.size(); i++)
                System.out.println("vertex #" + i + " - "
                        + "("  + pointList.get(i).x
                        + ", " + pointList.get(i).y
                        + ");");
        }
    }




}
