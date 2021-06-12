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
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import Class.Puzzle;
import Class.Squar;

import java.net.*;
import java.util.Scanner;
import java.util.Vector;
import java.io.*;

public class ClassicJigsawPuzzle extends JFrame implements KeyListener{
	Container cn;
	JPanel pn;
	Timer timer;
	int M = 22, N = 11;
	boolean die = false;
	JButton bt[][] = new JButton[M + 5][N + 5];
	boolean b[][] = new boolean[M + 5][N + 5];
	int preCl = 0;
	Color cl[] = {Color.black, Color.blue, Color.cyan, Color.green, Color.magenta, Color.orange, Color.yellow};
	Puzzle p = new Puzzle();
	public ClassicJigsawPuzzle() {
		super("HaiZuka");
		cn = init();
		timer = new Timer(300, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.down();
//				p.display();
				if (!p.check(b))
					newPuzz();
				update();
			}
		});
		timer.start();
	}
	
	public Container init() {
		Container cn = this.getContentPane();
		
		pn = new JPanel();
		pn.setLayout(new GridLayout(M, N));
		
		for (int i = 0; i < M + 5; i++)
			for (int j = 0; j < N + 5; j++) {
				b[i][j]  = false;
			}
		
		for (int i = 0; i <= M + 4; i++)
			for (int j = 0; j <= N + 4; j++) {
				bt[i][j] = new JButton();
				bt[i][j].addKeyListener(this);
				bt[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
			}
		for (int i = 0; i < 3; i++)
			for (int j = 3; j < N + 3; j++)
				b[i][j] = true;
		for (int i = 3; i < M + 3; i++)
			for (int j = 3; j < N + 3; j++) {
				b[i][j] = true;
				bt[i][j].setBackground(cl[0]);
				pn.add(bt[i][j]);
			}
		cn.add(pn);
		this.setVisible(true);
		this.setSize(395, 700);
		this.setLocationRelativeTo(null);

		setResizable(false);
//		this.setResizable(false);
		return cn;
	}
	
	
	public void update() {
		for (int i = M + 2; i >= 1; i--) {
			boolean kt = true;
			for (int j = 3; j < N + 3; j++)
				if (b[i][j] == true)
					kt = false;
			if (kt) {
				for (int h = i; h >= 1; h--)
					for (int j = 3; j < N + 3; j++) {
						b[h][j] = b[h - 1][j];
						bt[h][j].setBackground(bt[h - 1][j].getBackground());
					}
				for (int j = 3; j < N + 3; j++)
					b[0][j] = true;
			}
		}
		
		for (int i = 3; i < M + 3; i++)
			for (int j = 3; j < N + 3; j++)
				if (b[i][j])
					bt[i][j].setBackground(cl[0]);
		
		Vector<Squar> vP = p.getV();
		for (int i = 0; i < vP.size(); i++) {
			Squar sq = vP.elementAt(i);
			bt[sq.getX()][sq.getY()].setBackground(cl[p.getIc()]);
		}
		preCl = p.getIc();
	}
	
	public static void main(String[] args) {
		new ClassicJigsawPuzzle();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void newPuzz() {
		p.up();
		Vector<Squar> pp = p.getV();
		for (int i = 0; i < pp.size(); i++) {
			Squar sq = pp.elementAt(i);
			b[sq.getX()][sq.getY()] = false;
		}
		p = new Puzzle();
		p.setIc(p.initIc(preCl));
		preCl = p.getIc();
		if (!p.check(b)) {
			p.display();
			timer.stop();
			die = true;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (die)
			return;
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
				newPuzz();
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
