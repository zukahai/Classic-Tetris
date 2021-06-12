package Class;

import java.awt.Color;
import java.util.Vector;

public class Puzzle {
	int type = 0;
	Squar tt = new Squar();
	Vector<Squar> v = new Vector();

	public Puzzle() {
		this.tt = new Squar(2, 8);
		this.type = (int) (1000000 * Math.random() % 7 + 1);
//		this.type = 2;
		this.v = ininV();
	}

	public Vector<Squar> ininV() {
		Vector<Squar> vii = new Vector();
		if (this.type == 1) {
			vii.add(new Squar(tt.getX(), tt.getY()));
			vii.add(new Squar(tt.getX(), tt.getY() + 1));
			vii.add(new Squar(tt.getX() + 1, tt.getY()));
			vii.add(new Squar(tt.getX() + 1, tt.getY() + 1));
		} else if (this.type == 2) {
			vii.add(new Squar(tt.getX(), tt.getY()));
			vii.add(new Squar(tt.getX() - 1, tt.getY()));
			vii.add(new Squar(tt.getX() - 2, tt.getY()));
			vii.add(new Squar(tt.getX() + 1, tt.getY()));
		} else if (this.type == 3) {
			vii.add(new Squar(tt.getX(), tt.getY()));
			vii.add(new Squar(tt.getX() - 1, tt.getY()));
			vii.add(new Squar(tt.getX() + 1, tt.getY()));
			vii.add(new Squar(tt.getX() + 1, tt.getY() + 1));
		} else if (this.type == 4) {
			vii.add(new Squar(tt.getX(), tt.getY()));
			vii.add(new Squar(tt.getX() - 1, tt.getY()));
			vii.add(new Squar(tt.getX() + 1, tt.getY()));
			vii.add(new Squar(tt.getX() - 1, tt.getY() + 1));
		} else if (this.type == 5) {
			vii.add(new Squar(tt.getX(), tt.getY()));
			vii.add(new Squar(tt.getX() - 1, tt.getY()));
			vii.add(new Squar(tt.getX() + 1, tt.getY()));
			vii.add(new Squar(tt.getX(), tt.getY() - 1));
		} else if (this.type == 6) {
			vii.add(new Squar(tt.getX(), tt.getY()));
			vii.add(new Squar(tt.getX() + 1, tt.getY()));
			vii.add(new Squar(tt.getX(), tt.getY() + 1));
			vii.add(new Squar(tt.getX() - 1, tt.getY() + 1));
		} else if (this.type == 7) {
			vii.add(new Squar(tt.getX(), tt.getY()));
			vii.add(new Squar(tt.getX() + 1, tt.getY()));
			vii.add(new Squar(tt.getX(), tt.getY() - 1));
			vii.add(new Squar(tt.getX() - 1, tt.getY() - 1));
		}
		return vii;
	}

	public void turnRight() {
		if (type != 1) {
			Vector<Squar> tV = new Vector<>();
			for (int i = 0; i < this.v.size(); i++) {
				Squar sq = this.v.elementAt(i);
//				sq.display();
				sq = sq.sub(tt);
//				sq.display();
				sq = sq.turnR();
//				sq.display();
				sq = sq.add(tt);
//				sq.display();
//				System.out.println("-----------");
				tV.add(sq);
			}
			this.v = tV;
		}
	}
	
	public void turnLeft() {
		if (type != 1) {
			Vector<Squar> tV = new Vector<>();
			for (int i = 0; i < this.v.size(); i++) {
				Squar sq = this.v.elementAt(i);
//				sq.display();
				sq = sq.sub(tt);
//				sq.display();
				sq = sq.turnL();
//				sq.display();
				sq = sq.add(tt);
//				sq.display();
//				System.out.println("-----------");
				tV.add(sq);
			}
			this.v = tV;
		}
	}
	
	public void down() {
		this.tt = this.tt.add(new Squar(1, 0));
		Vector<Squar> tV = new Vector<>();
		for (int i = 0; i < this.v.size(); i++) {
			Squar sq = this.v.elementAt(i);
			sq = sq.add(new Squar(1, 0));
			tV.add(sq);
		}
		this.v = tV;
	}
	
	public void up() {
		this.tt = this.tt.sub(new Squar(1, 0));
		Vector<Squar> tV = new Vector<>();
		for (int i = 0; i < this.v.size(); i++) {
			Squar sq = this.v.elementAt(i);
			sq = sq.sub(new Squar(1, 0));
			tV.add(sq);
		}
		this.v = tV;
	}
	
	public void right() {
		this.tt = this.tt.add(new Squar(0, 1));
		Vector<Squar> tV = new Vector<>();
		for (int i = 0; i < this.v.size(); i++) {
			Squar sq = this.v.elementAt(i);
			sq = sq.add(new Squar(0, 1));
			tV.add(sq);
		}
		this.v = tV;
	}
	
	public void left() {
		this.tt = this.tt.sub(new Squar(0, 1));
		Vector<Squar> tV = new Vector<>();
		for (int i = 0; i < this.v.size(); i++) {
			Squar sq = this.v.elementAt(i);
			sq = sq.sub(new Squar(0, 1));
			tV.add(sq);
		}
		this.v = tV;
	}

	public boolean check(boolean b[][]) {
		for (int i = 0; i < this.v.size(); i++) {
			Squar sq = this.v.elementAt(i);
			if (b[sq.getX()][sq.getY()] == false) {
//				System.out.println("Day");
//				sq.display();
				return false;
			}
				
		}
		return true;
	}
	
	public void display() {
		for (int i = 0; i < this.v.size(); i++) {
			Squar sq = this.v.elementAt(i);
			System.out.print("(" + (sq.getX()) + ", " + (sq.getY()) + ") ");
		}
		System.out.println("(" + tt.getX() + ", " + tt.getY() + ") ");
	}
	
	public Puzzle(Squar TT) {
		this.tt = TT;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Squar getTt() {
		return tt;
	}

	public void setTt(Squar tt) {
		this.tt = tt;
	}

	public Vector<Squar> getV() {
		return v;
	}

	public void setV(Vector<Squar> v) {
		this.v = v;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Puzzle p = new Puzzle();
		p.turnLeft();
	}

}