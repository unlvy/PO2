import java.util.Random;
import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.io.*;
import javax.imageio.*;

public class CalkaOznaczona {

    private Fun fun;

    CalkaOznaczona(Funkcja funkcja) {   
        fun = funkcja; 
    }
    
    CalkaOznaczona(Fun fun) {
        this.fun = fun;
    }
    
    public double wartoscProstokaty(double a, double b, int n) {
    
        double result = 0.0;
        
        if (b < a || n < 1) { throw new BladCalki(); }
        try {
            
            double step = (b - a) / n;
            for (int i = 0; i < n; i++) {
                result += fun.wartosc(a + i * step) * step;
            }
        
        } catch (BladFunkcji e) {
            throw new BladCalki();
        }
        return result;    
    }
    
    public double wartoscMonteCarlo(double a, double b, int n) {
        double result = 0.0;
        Random random = new Random();
            
        if (b < a || n < 1) { throw new BladCalki(); }
        try {
              
            for (int i = 0; i < n; i++) {
                result += fun.wartosc(random.nextDouble() * (b - a) + a);
            }
            
        } catch (BladFunkcji e) {
            throw new BladCalki();
        }
        return result / n * (b - a);    
    }
    
    public boolean plot(double a, double b) {
    
        int w = 500;
        int h = 500;
        int n = 50;
        String filename = "graph.png";
        
        BufferedImage myPicture = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = myPicture.createGraphics();
                
        int middleX = w / 2;
        int middleY = h / 2; 
        
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLACK);
        g.draw(new Line2D.Double(0, middleY, w, middleY));
        g.draw(new Line2D.Double(middleX, 0, middleX, h));
        
                 
        double step = (b-a) / n;
            
        for (double x = a; x < b; x += step) {
            g.setColor(Color.BLUE);
            Point2D p1 = new Point2D.Double(w - (x + a) * w / (a - b), (fun.wartosc(x) + a) * h / (a - b));
            Point2D p2 = new Point2D.Double(w - ((x + step) + a)* w / (a - b), (fun.wartosc(x + step) + a)* h / (a - b));
            
            g.draw(new Line2D.Double(p1, p2));
            g.setColor(Color.RED);

            Point2D p3 = new Point2D.Double(w - (x + step / 2 + a) * w / (a - b), (fun.wartosc(x + step / 2) + a) * h / (a - b));
            Point2D p4 = new Point2D.Double(w - (x + step / 2 + a) * w / (a - b), h / 2);
            g.draw(new Line2D.Double(p3, p4));
        }
        

        
        try {
            File outputfile = new File(filename);
            ImageIO.write(myPicture, "png", outputfile);
        } catch (IOException e) {
            System.out.println("I/O error while saving " + filename);
            return false;
        }    
        return true;
    }
    
}
