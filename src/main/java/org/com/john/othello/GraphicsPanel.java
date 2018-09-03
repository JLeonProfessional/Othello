package org.com.john.othello;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class GraphicsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int NEXT_SQUARE_LENGTH = 93;
	private int[][] grid;

    public GraphicsPanel(int[][] grid){
        setPreferredSize(new Dimension(800, 800));
        this.grid = grid;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        try {
//        	drawGrid(g);
//        	drawPieceFromGrid(g, grid);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }
    
    public void paintComponent(int[][] grid) {
    	Graphics g = getGraphics();
    	super.paintComponent(g);
    	try {
    		drawGrid(g);
    		drawPieceFromGrid(g, grid);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    protected static void drawGrid(Graphics g) throws IOException {
		BufferedImage gridImage = ImageIO.read(GraphicsPanel.class.getClassLoader().getResource("gridwithlines.jpg"));
        g.drawImage(gridImage, 0, 0, null);
    }
    
    public static void drawPieceFromGrid(Graphics g, int[][] grid) throws IOException{
    	for(int row = 0; row < 8; row++) {
    		for(int column = 0; column < 8; column++) {
    			if(grid[row][column] != BoardSpecs.EMPTY_SQUARE) {
    				drawPiece(g, grid[row][column], 5 + (column * NEXT_SQUARE_LENGTH), 5 + (row * NEXT_SQUARE_LENGTH));
    			}
    		}
    	}
    }
    
    private static void drawPiece(Graphics g, int color, int x, int y) throws IOException {
    	String colorName;
    	if(color == BoardSpecs.WHITE_COLOR) {
    		colorName = "white.png";
    	} else {
    		colorName = "black.png";
    	}
    	g.drawImage(ImageIO.read(GraphicsPanel.class.getClassLoader().getResource(colorName)), x, y, null);
    	
    }
    
    
}