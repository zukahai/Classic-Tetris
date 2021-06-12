package Class;

import java.awt.Color;
import java.util.Vector;

public class Puzzle {
	int type = 0;
	Squar tt = new Squar();
	Vector<Squar> v = new Vector();
	
	public Puzzle() {
		this.tt = new Squar(5, 5);
//		this.type = (int) (1000000 * Math.random() % 2 + 1);
		this.type = 7;
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
	
	public void turn() {
		if (type != 1)
			this.v = turnV();
	}
	
	public Vector<Squar> turnV() {
		Vector<Squar> tV = new Vector<>();
		for (int i = 0; i < this.v.size(); i++) {
			Squar sq = this.v.elementAt(i);
			Squar temp = sq.sub(tt);
			sq = temp.turn();
			sq = sq.add(tt);
			tV.add(sq);
		}
		return tV;
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
		p.turnV();
		
	}

}
