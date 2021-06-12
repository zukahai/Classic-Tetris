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
	int M = 22, N = 11;
	JButton bt[][] = new JButton[M + 5][N + 5];
	boolean b[][] = new boolean[M + 5][N + 5];
	Puzzle p = new Puzzle();
	public ClassicJigsawPuzzle() {
		super("HaiZuka");
		cn = init();
	}
	
	public Container init() {
		Container cn = this.getContentPane();
		
		pn = new JPanel();
		pn.setLayout(new GridLayout(M, N));
		
		for (int i = 0; i < M + 5; i++)
			for (int j = 0; j < N + 5; j++)
				b[i][j] = false;
		
		for (int i = 0; i <= M + 4; i++)
			for (int j = 0; j <= N + 4; j++) {
				bt[i][j] = new JButton();
				bt[i][j].addKeyListener(this);
				bt[i][j].setActionCommand(String.valueOf(i * N + j));
			}
		for (int i = 3; i < M + 3; i++)
			for (int j = 3; j < N + 3; j++) {
				b[i][j] = true;
				pn.add(bt[i][j]);
			}
		update();
		cn.add(pn);
		
		this.setVisible(true);
		this.setSize(400, 700);
		this.setLocationRelativeTo(null);
//		this.setResizable(false);
		return cn;
	}
	
	public void update() {
		for (int i = 3; i < M + 3; i++)
			for (int j = 3; j < N + 3; j++)
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
			p.turnRight();
			if (!p.check(b)) {
//				System.out.println("Oh no 1");
				p.turnLeft();
			}
			update();
		} else if (e.getKeyCode() == e.VK_DOWN) {
			p.down();
			if (!p.check(b)) {
//				System.out.println("Oh no 2");
				p.up();
			}
			update();
		} else if (e.getKeyCode() == e.VK_LEFT) {
			p.left();
			if (!p.check(b)) {
//				System.out.println("Oh no 3");
				p.right();
			}
			update();
		} else if (e.getKeyCode() == e.VK_RIGHT) {
			p.right();
			if (!p.check(b)) {
//				System.out.println("Oh no 4");
				p.left();
			}
			update();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
