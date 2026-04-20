import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


class Point{
    int x;
    int y;

    public Point(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public double distance(Point other){
        int dx = this.x - other.x;
        int dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
class Shape{
    ArrayList<Point> points;
    public Shape(File file) throws FileNotFoundException{
        points = new ArrayList<>();
        Scanner sc = new Scanner(file);


        while (sc.hasNext()){
            int x = sc.nextInt();
            int y = sc.nextInt();
            points.add(new Point(x,y));
        }
        sc.close();
    }

    public ArrayList<Point> getPoint(){
        return points;
    }

    public Point getLastPoint(){
        return points.get(points.size() - 1);
    }
}

public class PerimeterEvaluation{

    public double getPerimeter(Shape s){
        double totalPeri = 0.0;
        Point pre = s.getLastPoint();
        for (Point cur : s.getPoint()){
            double dist = pre.distance(cur);
            totalPeri += dist;
            pre = cur;
        }
        return totalPeri;
    }



    public int getnumPoint(Shape s){
        int count = 0;

        for(Point p : s.getPoint()){
            count++;
        }
        return count;
    }

    public double getAverageLength(Shape s){
        double peri = getPerimeter(s);
        int numPoint = getnumPoint(s);
        return peri / numPoint;

    }

    public double getLargestSide(Shape s){
        double largestSide = 0.0;
        Point pre = s.getLastPoint();
        for(Point cur : s.getPoint()){
            double dist = pre.distance(cur);

            if (dist > largestSide){
                largestSide = dist;
            }
        }
        return largestSide;
    }

    public double getlargestX(Shape s){
        double largestX = 0.0;

        for(Point p : s.getPoint()){
            double x = p.getX();

            if(x > largestX){
                largestX = x;
            }
        }
        return  largestX;
    }

    public double getLargestPerimeterMultipleFile(File[]files) throws FileNotFoundException{
        double largestPerimeter = 0.0;

        for(File file : files){
            Shape s = new Shape(file);
            double peri = getPerimeter(s);

            if(peri > largestPerimeter){
                largestPerimeter = peri;
            }
        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter(File[] files) throws FileNotFoundException{
        double largestPerimter = 0.0;
        File largestFile = null;

        for(File file : files){
            Shape s = new Shape(file);
            double peri = getPerimeter(s);

            if(peri > largestPerimter){
                largestPerimter = peri;
                largestFile = file;
            }
        }
        if (largestFile != null){
            return largestFile.getName();
        }
        else {
            return "no File Found!";
        }
    }


    public void testPerimeter() throws FileNotFoundException{

        File file = new File( "shape1.txt");

        Shape s =new Shape(file);


        double length = getPerimeter(s);
        System.out.println("Perimeter is " + length);

        int num = getnumPoint(s);
        System.out.println("Number of Points is " + num);

        double avglength = getAverageLength(s);
        System.out.println("Average of Length is " + avglength);

        double largestSide = getLargestSide(s);
        System.out.println("Largest Side of Point is " + largestSide);

        double largestX = getlargestX(s);
        System.out.println("Largest X of Point is " + largestX);

    }

    public void testMultipleFile() throws FileNotFoundException{
        File folder = new File("Test2");
        File[] files = folder.listFiles();


        double largestPerimeter = getLargestPerimeterMultipleFile(files);
        System.out.println("Largest Perimeter of Multiple File is " + largestPerimeter);

        String fileName = getFileWithLargestPerimeter(files);
        System.out.println("File with Largest Perimeter is " + fileName);

    }

    public static void main(String[] args) throws FileNotFoundException {
        PerimeterEvaluation pr = new PerimeterEvaluation();
        pr.testPerimeter();
        pr.testMultipleFile();

    }
}
