package org.com.john.othello;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class OthelloBoard extends JFrame {
	private static final long serialVersionUID = -5239137243266124584L;
	private static final int GRID_START_X = 5;
	private static final int GRID_START_Y = 5;
	private static final int GRID_SIZE_X = 800;
	private static final int GRID_SIZE_Y = 800;
	private static final int PIECE_SIZE_X = 85;
	private static final int PIECE_SIZE_Y = 85;
	private GraphicsPanel othelloPanel;
	private OthelloListener othelloListener;
	

    public static BufferedImage image;

	public OthelloBoard(String title, int[][] grid, Socket socket) throws Exception {
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(765, 788));
		frame.setMaximumSize(new Dimension(765, 788));
		frame.setMinimumSize(new Dimension(765, 788));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		othelloPanel = new GraphicsPanel(grid);
		othelloListener = new OthelloListener(socket);
		othelloPanel.addMouseListener(othelloListener);
        frame.add(othelloPanel);
        frame.pack();
        frame.setVisible(true);
		
	}
	
	public GraphicsPanel getOthelloPanel() {
		return othelloPanel;
	}

	public OthelloListener getOthelloListener() {
		return othelloListener;
	}

	public void setOthelloListener(OthelloListener othelloListener) {
		this.othelloListener = othelloListener;
	}
	
	
}
