package ru.stqa.pft.sandbox;

class MyFirstProgram{
    public static void main(String[] args){
        hello("world");
        hello("user");
        hello("Alexei");
        Square s = new Square(5);
        System.out.println("площадь квадрата со стороной " + s.l + "равна " + s.area());

        ru.stqa.pft.sandbox.Rectangle r = new Rectangle(4,6);
        System.out.println("площадь прямоугольника со сторонами " + r.a + " и " + r.b +" равна " + r.area());
    }
    public	static void hello(String somebody ) {

        System.out.println("Hello " + somebody + " !");
    }


}