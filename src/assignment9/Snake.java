package assignment9;

import java.util.LinkedList;
import edu.princeton.cs.introcs.StdDraw;

public class Snake {
    private static final double SEGMENT_SIZE = 0.02;
    private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;

    private LinkedList<BodySegment> segments;
    private double deltaX;
    private double deltaY;

    public Snake() {
        segments = new LinkedList<>();
        // start in center
        segments.add(new BodySegment(0.5, 0.5, SEGMENT_SIZE));
        deltaX = MOVEMENT_SIZE;
        deltaY = 0;
    }

    public void changeDirection(int direction) {
        if (direction == 1 && deltaY == 0) { // up
            deltaX = 0; deltaY = MOVEMENT_SIZE;
        } else if (direction == 2 && deltaY == 0) { // down
            deltaX = 0; deltaY = -MOVEMENT_SIZE;
        } else if (direction == 3 && deltaX == 0) { // left
            deltaX = -MOVEMENT_SIZE; deltaY = 0;
        } else if (direction == 4 && deltaX == 0) { // right
            deltaX = MOVEMENT_SIZE; deltaY = 0;
        }
    }

    public void move() {
        // compute new head position. effectively gliding along
        BodySegment head = segments.getFirst();
        double newX = head.getX() + deltaX;
        double newY = head.getY() + deltaY;
        // add new head
        segments.addFirst(new BodySegment(newX, newY, SEGMENT_SIZE));
        // remove tail
        segments.removeLast();
    }

    
    //where the growth actually happens. be like (if distance<size then grow). track the position of last segment
    public void grow() {
        // add new segment at tail position
        BodySegment tail = segments.getLast();
        segments.addLast(new BodySegment(tail.getX(), tail.getY(), SEGMENT_SIZE));
    }

    public void draw() {
        for (BodySegment seg : segments) {
            seg.draw();
        }
    }

    
    
    public boolean eatFood(Food f) {
        BodySegment head = segments.getFirst();
        double dx = head.getX() - f.getX();
        double dy = head.getY() - f.getY();
        double dist = Math.sqrt(dx*dx + dy*dy);
        if (dist < SEGMENT_SIZE) {
            //snake.grow
        	grow();
            return true;
        }
        return false;
    }

    
    //knowing when to stop (within [0,0] [1,1]. outputted as a boolean so we
    //can just be like while it is in bounds do stuff. didnt check for self collision
    public boolean isInbounds() {
        BodySegment head = segments.getFirst();
        double x = head.getX(), y = head.getY();
        return x >= 0 && x <= 1 && y >= 0 && y <= 1;
    }

    /**
     * @return current snake length (number of segments)
     */
    public int getLength() {
        return segments.size();
    }
}
