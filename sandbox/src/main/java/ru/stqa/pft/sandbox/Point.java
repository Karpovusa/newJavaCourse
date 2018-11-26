package ru.stqa.pft.sandbox;

public class Point {
    double pointA;
    double pointB;

    public Point(double pointA, double pointB){
        this.pointA = pointA;
        this.pointB = pointB;
    }
    //показывает расстояние между этой точкой и точкой которую передаем в метод
    public  double distance( Point p){
        double px = p.pointA - this.pointA;
        double py =  p.pointB - this.pointB;
        return Math.sqrt(px * px + py * py);

    }
}
