package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Class.Puzzle;
import Class.Squar;

public class ClassicJigsawPuzzle extends JFrame implements KeyListener{
	Container cn;
	JPanel pn;
	int M = 10, N = 10;
	JButton bt[][] = new JButton[M + 1][N + 1];
	Puzzle p = new Puzzle();
	public ClassicJigsawPuzzle() {
		super("HaiZuka");
		cn = init();
	}
	
	public Container init() {
		Container cn = this.getContentPane();
		
		pn = new JPanel();
		pn.setLayout(new GridLayout(M, N));
		
		for (int i = 0; i < M; i++)
			for (int j = 0; j < N; j++) {
				bt[i][j] = new JButton();
				bt[i][j].addKeyListener(this);
				bt[i][j].setActionCommand(String.valueOf(i * N + j));
				pn.add(bt[i][j]);
			}
		update();
		cn.add(pn);
		
		this.setVisible(true);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
//		this.setResizable(false);
		return cn;
	}
	
	public void update() {
		for (int i = 0; i < M; i++)
			for (int j = 0; j < N; j++)
				bt[i][j].setBackground(Color.white);
		Vector<Squar> vP = p.getV();
		for (int i = 0; i < vP.size(); i++) {
			Squar sq = vP.elementAt(i);
			bt[sq.getX()][sq.getY()].setBackground(Color.GREEN);
		}
	}
	
	public static void main(String[] args) {
		new ClassicJigsawPuzzle();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == e.VK_UP) {
			p.turn();
			update();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
