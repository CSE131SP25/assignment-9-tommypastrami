package assignment9;
import java.awt.Color;
import edu.princeton.cs.introcs.StdDraw;
public class Food {
   public static final double FOOD_SIZE = 0.02;
   private double x, y;
   private Color color;
   public Food() {
       this.x = Math.random();
       this.y = Math.random();
       this.color = ColorUtils.solidColor();
   }
   public double getX() { return x; }
   public double getY() { return y; }
   public void draw() {
       StdDraw.setPenColor(color);
       StdDraw.filledCircle(x, y, FOOD_SIZE / 2);
   }
}
