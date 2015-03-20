package com.sandklef.edu.MoveMe;

import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Random;
import java.util.Date;


import com.sandklef.edu.MoveMe.Box;

public class Field extends JPanel {
    
    private Random rand ;

    private Box boxes[][];
    private int xSize;
    private int ySize;

    private int rows ;
    private int cols ;

    int startRow;
    int stopRow;
    int startCol;
    int stopCol;


    Point currentPoint;

    /**
     *
     * Creates a Field 
     *
     * @param xSize Size of the field - horizontal
     * @param ySize Size of the field - vertical
     *
     */
    public Field(int xSize, int ySize, int cols, int rows) {
	System.out.println("Create Field: " + xSize + " " + ySize + 
			   " " + cols + " " + rows);

	rand = new Random((new Date()).getTime());

	this.rows  = rows;
	this.cols  = cols;
	this.xSize = xSize ;
	this.ySize = ySize ;

	boxes = new Box[cols][rows];
	setLayout(new GridLayout(cols,rows));

	for (int i=0;i<rows;i++) {
	    for (int j=0;j<cols;j++) {
		Box tmp = new Box(xSize/cols,
				  ySize/rows, 
				  j, i);
		add(tmp);

		// j=cols, i=rows
		boxes[j][i]=tmp;
		
		System.out.println("Create Box: " + i + " " + j);
	    } 
	}
	startCol =  1 + rand.nextInt(cols-2) ;
	stopCol  =  1 + rand.nextInt(cols-2) ;


	startRow =  rows-1;
	stopRow  =  0;
	
	//	pack();
	setVisible(true);
	//	setMinimumSize(new Dimension(ySize, xSize));

	//	createRandomBarriers((cols+rows)/2);

	System.out.println("Start: " + startCol + " - " + startRow);

	currentPoint = new Point(startCol, startRow);

	boxes[startCol][startRow].setStart();
	boxes[stopCol][stopRow].setStop();
    }

    public void pleaseRedraw() {
	System.out.println("Redrawing....");
	repaint();
	updateUI();
    }

    public int getCols() {
	return cols;
    }

    public int getRows() {
	return rows;
    }

    public void cleanBarriers() {
	for (int i=0;i<getCols();i++) {
	    for (int j=0;j<getCols();j++) {
		if (boxes[i][j].isBarrier()) {
		    boxes[i][j].unSetBarrier();
		} else if (boxes[i][j].isPath()) {
		    boxes[i][j].unSetPath();
		}
	    }
	}
    }

    public void cleanFoot() {
	for (int i=0;i<getCols();i++) {
	    for (int j=0;j<getCols();j++) {
		if (boxes[i][j].isPath()) {
		    boxes[i][j].unSetPath();
		}
	    }
	}
    }

    public void createRandomBarriers(int nr) {

	for (int i=0;i<nr/2;i++) {
	    createRandomBarrierCol(cols/2);
	}

	for (int i=0;i<nr/2;i++) {
	    createRandomBarrierRow(cols/2);
	}
    }

    public void createRandomBarrier() {
	int row = rand.nextInt(rows);
	int col = rand.nextInt(cols);

	boxes[row][col].setBarrier();
    }

    public void createRandomBarrierCol(int size) {
	// int col = 1 + rand.nextInt(cols-1);
	// int row = 1 + rand.nextInt(rows-size-2);
	int col = rand.nextInt(cols-1); 
	int row = rand.nextInt(rows-size-1);

	for (int i=row;i<size;i++) {
	    if (boxes[col][i].isStop() ||
		boxes[col][i].isStart()) {
		;
	    } else {
		System.out.println("boxes[" + col + "," + i + "]= true");
		boxes[col][i].setBarrier();
	    }
	}
    }

    public void createRandomBarrierRow(int size) {
	// int col = 1 + rand.nextInt(cols-size-1);
	// int row = 1 + rand.nextInt(rows-1);
	int col = rand.nextInt(cols-size-1);
	int row = rand.nextInt(rows);

	for (int i=col;i<size;i++) {
	    System.out.println("boxes[" + i + "," + row + "]= true");
	    boxes[i][row].setBarrier();
	}
    }

    private Point moveImpl(int dir) {
	Point p;
	if (dir==0) 
	    p = getUp(currentPoint);
	else if (dir==1) 
	    p = getDown(currentPoint);
	else if (dir==2) 
	    p = getRight(currentPoint);
	else 
	    p = getLeft(currentPoint);

	if ( (p==null) ||  (isBarrier(p) ) ) {
	    System.out.println("Crash!!!");
	    return currentPoint;
	}
	markPointPath(p, "");
	currentPoint = p;
	return currentPoint;
    }

    public Point moveUp() {
	return moveImpl(0);
    }

    public Point moveDown() {
	return moveImpl(1);
    }

    public Point moveRight() {
	return moveImpl(2);
    }

    public Point moveLeft() {
	return moveImpl(3);
    }

    public Point getStart() {
 	return new Point(startCol, startRow);
    }

    public Point getStop() {
 	return new Point(stopCol, stopRow);
    }


    public boolean isStop(Point p) {
	int col = p.getCol();
	int row = p.getRow();
	return ( (col==stopCol) && (row==stopRow) );
    }

    public boolean isStop() {
	int col = currentPoint.getCol();
	int row = currentPoint.getRow();
	return ( (col==stopCol) && (row==stopRow) );
    }

    public boolean isBarrier(Point p) {
	if (p==null) return true;
	int col = p.getCol();
	int row = p.getRow();
	if ( (col>=cols) || (row>=rows) || (col<0) || (row<0) ) return true;

 	Box b = boxes[col][row];
	boolean ret = b.isBarrier();

	//System.out.println(" isBarrier( ) " + col + " " + row + " => " + ret);
	return ret;
    }

    public boolean isWall(Point p) {
	boolean ret ;
	int col = p.getCol();
	int row = p.getRow();
	// System.out.println(" isWall() " + 
	// 		   col + " >=" + cols + " " +
	// 		   rows + ">=" + rows);
	
	if ( (col<0) || (row<0) || (col >= cols) || (row>=rows) ) {
	    return true;
	}
	return false;
    }

    public Point getLeft(Point p) {
	int newX = p.getCol()-1; 
	if ( newX < 0 ) {
	    return null;
	} else {
	    return new Point (newX, p.getRow());
	}
    }
    
    public Point getRight(Point p) {
	int newX = p.getCol()+1; 
	if ( newX > cols ) {
	    return null;
	} else {
	    return new Point (newX, p.getRow());
	}
    }
    

    public Point getUp(Point p) {
	int newY = p.getRow()-1; 
	if ( newY > rows ) {
	    return null;
	} else {
	    return new Point (p.getCol(), newY);
	}
    }
    
    public Point getDown(Point p) {
	int newY = p.getRow()+1; 
	if ( newY < 0 ) {
	    return null;
	} else {
	    return new Point (p.getCol(), newY);
	}
    }
    
    public void markPointPath(Point p, String s) {
	if (p==null) return;
	int col = p.getCol();
	int row = p.getRow();
	if ( (col>=cols) || (row>=rows) ) return;
 	Box b = boxes[col][row];
	if (b!=null) {
	    if (!p.equals(getStart())) {
		b.setPath(s);
	    }
	}
    }

    public void markPointBeenHere(Point p, String s) {
	if (p==null) return;
	int col = p.getCol();
	int row = p.getRow();
	if ( (col>=cols) || (row>=rows) ) return;
 	Box b = boxes[col][row];
	if (b!=null) {
	    if (!p.equals(getStart())) {
		b.setBeenHere(s);
	    }
	}
    }



}
