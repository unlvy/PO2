import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.io.*;
import javax.imageio.*;

public class Task01 {

    /**
     * fields
     */
    private double a;
    private double b;
    private double c;


    /**
     * constructor
     */
    Task01(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * methods
     */

    public void print() {
        System.out.println("f(x) = " + a + "x^2 + " + b + "x + " + c);
    }

    public double delta() {
        return b * b - 4.0 * a * c;
    }

    public double fun(double x) {
        return a * x * x + b * x + c;
    }

    public double[] solutions() {
        double[] result = new double[2];
        double delta = delta();
        if (a == 0) {
            result[0] = result[1] = -c / b;
            return result;
        } else if (delta >= 0) {
            result[0] = (-b - Math.sqrt(delta)) / (2.0 * a);
            result[1] = (-b + Math.sqrt(delta)) / (2.0 * a);
            return result;
        } else {
            return null;
        }
    }

    public void printFactoredForm() {
        double[] solutions = solutions();
        if (solutions == null || a == 0) {
            System.out.println("Impossible to print factored form");
        } else {
            System.out.println("Factored form: f(x) = " + a + "(x - " + solutions[0] + ")(x - " + solutions[1] + ")");
        }
    }

    public void printVertexForm() {
        if (a == 0) {
            System.out.println("Impossible to print vertex form");
        } else {
            double p = -b / (2 * a);
            double q = -delta() / (4 * a);
            System.out.println("Vertex form: f(x) = " + a + "(x - " + p + ")^2 + " + q);
        }
    }

    public void makePlot(String filename, int w, int h) {

        BufferedImage plot = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = plot.createGraphics();

        int middleX = w / 2;
        int middleY = h / 2;

        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLACK);
        g.draw(new Line2D.Double(0, middleY, w, middleY));
        g.draw(new Line2D.Double(middleX, 0, middleX, h));

        g.setStroke(new BasicStroke(2));
        g.setColor(Color.RED);

        double range = 50;
        double stepX = w / (2 * range); double stepY = h / (2 * range);
        double y;
        for (double x = -range; x <= range;) {
            y = fun(x);
            Point2D p1 = new Point2D.Double(middleX + x * stepX, middleY - y * stepY);
            x++; y = fun(x);
            Point2D p2 = new Point2D.Double(middleX + x * stepX, middleY - y * stepY);
            g.draw(new Line2D.Double(p1, p2));
        }

        try {
            File outputFile = new File(filename);
            ImageIO.write(plot, "png", outputFile);
        } catch (IOException e) {
            System.out.println("I/O error while saving " + filename);
        }
    }

}


class Task01Demo {

    public static void main(String[] args) {

        Task01 test = new Task01(2, 2, -4);
        test.print();
        System.out.println("Delta = " + test.delta());
        double[] results = test.solutions();

        if (results != null) {
            System.out.println("x1 = " + results[0] + ", x2 = " + results[1]);
        } else {
            System.out.println("Delta < 0!");
        }

        test.printFactoredForm();

        test.printVertexForm();

        test.makePlot("plot.png", 500, 500);

    }

}
