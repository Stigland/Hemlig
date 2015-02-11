package com.sandklef.edu.MoveMe;

public class Point {
    
    private int xPos;
    private int yPos;

    public Point(int x, int y) {
	xPos=x;
	yPos=y;
    }

    public void setX(int x) {
	xPos=x;
    }
			     
    public void setY(int y) {
	yPos=y;
    }
			     
    public int getCol() {
	return xPos;
    }
			     
    public int getRow() {
	return yPos;
    }

    public boolean equals(Point p) {
	return ( (yPos==p.getRow()) && 
		 (xPos==p.getCol()) );
    }



    public String toString() {
	return "Point[" + xPos + "," + yPos + "]";
    }

}