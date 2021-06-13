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
	Timer timer = new Timer(100, null);
	int M = 22, N = 11;
	int delay = 100;
	int index = delay;
	int score = 0;
	boolean die = false;
	JButton bt[][] = new JButton[M + 5][N + 7];
	boolean b[][] = new boolean[M + 5][N + 7];
	int preCl = 0;
	Color cl[] = {Color.black, Color.blue, Color.cyan, Color.green, Color.magenta, Color.orange, Color.yellow};
	Puzzle p = new Puzzle();
	Puzzle [] Que = new Puzzle[4];
	public ClassicJigsawPuzzle() {
		super("HaiZuka");
		cn = init();
		timer = new Timer(1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (index == 0) {
					p.down();
//					p.display();
					if (!p.check(b))
						newPuzz();
					update();
					index = delay;
				} else 
					index --;
				System.out.println(index);
			}
		});
	}
	
	public Container init() {
		Container cn = this.getContentPane();
		
		pn = new JPanel();
		pn.setLayout(new GridLayout(M, N + 4));
		
		for (int i = 0; i < Que.length; i++) {
			Que[i] = new Puzzle();
			Que[i].setIc(Que[i].initIc(preCl));
			preCl = Que[i].getIc();
		}
		
		for (int i = 0; i < M + 5; i++)
			for (int j = 0; j < N + 5; j++) {
				b[i][j]  = false;
			}
		
		for (int i = 0; i <= M + 3; i++)
			for (int j = 0; j <= N + 6; j++) {
				bt[i][j] = new JButton();
				bt[i][j].addKeyListener(this);
				bt[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
			}
		for (int i = 0; i <= M + 3; i++)
			for (int j = N + 3; j <= N + 6; j++) {
				bt[i][j].setBorder(null);
			}
		
		for (int i = 0; i < M + 3; i++)
			for (int j = 3; j < N + 3; j++) {
				b[i][j] = true;
			}
		
		for (int i = 3; i < M + 3; i++)
			for (int j = 3; j < N + 7; j++) {
				bt[i][j].setBackground(cl[0]);
				pn.add(bt[i][j]);
			}
		cn.add(pn);
		updateQue();
		this.setVisible(true);
		this.setSize(500, 700);
		this.setLocationRelativeTo(null);
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		return cn;
	}
	
	public void updateQue() {
		delay -= 1;
		if (delay > 50)
			delay -= 1;
		if (delay > 20)
			delay -= 1;
		if (delay < 10)
			delay = 10;
		for (int i = 0; i <= M; i++)
			for (int j = N + 3; j <= N + 6; j++) {
				bt[i][j].setBackground(Color.black);
				bt[i][j].setBorder(null);
			}
				
		p = Que[0];
		p.setTt(new Squar(2, 8));
		p.setV(p.ininV());
		if (!p.check(b)) {
			timer.stop();
			die = true;
		}
		for (int i = 0; i < Que.length - 1; i++)
			Que[i] = Que[i + 1];
		Que[Que.length - 1] = new Puzzle();
		Que[Que.length - 1].setIc(Que[Que.length - 1].initIc(preCl));
		preCl = Que[Que.length - 1].getIc();
		int H = 2;
		for (int i = 0; i < Que.length; i++) {
			Que[i].setTt(new Squar(H + Que[i].getTop() + 1, N + 4));
			if (Que[i].getType() == 4 || Que[i].getType() == 5 || Que[i].getType() == 7)
				Que[i].setTt(new Squar(H + Que[i].getTop() + 1, N + 5));
			Que[i].setV(Que[i].ininV());
			for (int J = 0; J < Que[i].getV().size(); J++) {
				Squar sq = Que[i].getV().elementAt(J);
				bt[sq.getX()][sq.getY()].setBackground(cl[Que[i].getIc()]);
				bt[sq.getX()][sq.getY()].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
			}
			H += Que[i].getBot() + Que[i].getTop() + 3;
		}
	}
	
	public void update() {
		if (die){
			JOptionPane.showMessageDialog(null, "Your Score: " + score);
			System.exit(0);
		}
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
		new ClassicJigsawPuzzle().timer.start();
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
		updateQue();
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
