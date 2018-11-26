package ru.stqa.pft.sandbox;

public class MySecondProgram {
    public static void main(String[] args) {
        //ниже код для первой части задания
        /*Point p1 = new Point(5.0,7.0);
        Point p2 = new Point(2.0,9.0);
        double result = distance(p1,p2);
        System.out.println(result);*/

        Point p1 = new Point(1.0,3.0);
        Point p2 = new Point(5.0,10.0);
        Point p3 = new Point(4.0,9.0);

        double result = p1.distance(p2);
        System.out.println("расстояние до точки 2 " + " = " + result);
        double result2 = p1.distance(p3);
        System.out.println("расстояние до точки 3 " + " = " + result2);


    }
    //метод для первой части задания
   /* public static double distance(Point p1, Point p2){
        double distance = Math.hypot(p1.pointA-p1.pointB, p2.pointA-p2.pointB);
        return distance;
    }*/
}