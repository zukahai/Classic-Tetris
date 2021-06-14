## <p align="center"> Làm Game Xếp Hình Bằng Java </p>
<p align="center"> <img src="https://github.com/zukahai/HaiZuka/blob/master/Images/ClassicJigsawPuzzle/Bg.png" alt="Tieude" /> </p>

## Làm Game Xếp Hình Bằng Java

### Giới thiệu trò chơi xếp hình

Trò chơi xếp hình cổ điển là trò chơi quá quen thuộc với thế hệ 8x 9x.

Bạn sẽ được cho một cái hộp kích thước M x N, lúc bắt đầu trò chơi thì trạng thái của hộp đang rỗng.

Mỗi lượt bạn sẽ được cho 1 khối hình ngẫu nhiên trong 7 hình phía dưới:

<p align="center"> <img src="https://github.com/zukahai/HaiZuka/blob/master/Images/ClassicJigsawPuzzle/1.png" alt="Tieude" /> </p>

Khối hình này sẽ có xu hướng dịch chuyển xuống phía dưới, nó sẽ dừng lại khi rơi xuống đáy hộp, hoặc gặp phải một khối hình đã rơi xuống trước đó, ngay sau khi nó dừng lại thì một khối hình khác sẽ rơi xuống, nếu các hình vuông trong các khối hình gộp được thành một hàng ngang thì hàng ngang đó sẽ biến mất và các khối hình ở phía trên nó sẽ rơi xuống, thế chỗ cho hàng ngang vừa mất.

Bạn có thể di chuyển khối hình sang trái, sang phải hoặc xuống dưới, đặc biệt bạn cũng có thể xoay khối hình một góc 90o theo chiều kim đồng hồ (bạn có thể xoay nhiều lần). Nhiệm vụ của bạn là phá vỡ nhiều hàng ngang càng nhiều càng tốt, bạn sẽ bị thua nếu như không còn chỗ xuất hình cho khối hình tiếp theo.

### Lập trình game xếp hình

Để dễ dàng xử lý các khối hình ta sẽ tạo ra class cubes gồm các thuộc tính:

- Loại khối hình (1 trong 7 khối hình đã nêu ở trên)
- Màu sắc của khối hình
- Hình vuông trung tâm (dùng để xoay khối hình)
- Tất cả tạo độ của các hình vuông có trong khối hình

Và các phương thức:

- Khởi tạo tọa độ các ô vuông của khối hình
- Xoay trái 90 độ
- Xoay phải 90 độ
- Dịch khối hình lên trên, xuống dưới, sang trái, sang phải
- Kiểm tra khôi hình và chạm
- Và các phương thức getters và setters của các thuộc tính
Sử dụng class Squar gồm 2 thuộc tính x và y để biểu diễn đối tượng từng ô vuông trong trò chơi

Đầu tiên chúng ta tìm hiểu phương thước khơi tạo các ô vuông trong 1 khối hình, chúng ta sẽ khởi tạo 1 ô vuông trong một khối hình sau đó sinh ra các ô vuông khác tùy vào từng loại khối hình (có 7 loại khối hình):

```Java
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
			vii.add(new Squar(tt.getX() + 1, tt.getY() - 1));
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
```

Phương thức xoay phải 90 độ

<p align="center"> <img src="https://github.com/zukahai/HaiZuka/blob/master/Images/ClassicJigsawPuzzle/2.png" alt="Tieude" /> </p>
<p align="center"> <img src="https://github.com/zukahai/HaiZuka/blob/master/Images/ClassicJigsawPuzzle/4.png" alt="Tieude" /> </p>

Như đã nói ở trên, khi xoay phải 90 độ ta sẽ xoay tất cả các ô vuông trong khối hình 1 góc 90 độ theo chiều kim đồng hồ với gốc tạo độ là o vuông trung tâm trong khối hình, Giả sử tạo độ của ô vuông trung tâm có tạo độ (a, b) ta sẽ thức hiện phép quay phải 90 độ như sau:

- Tính tiến khối hình theo Vector t(-a, -b), lúc này ô vuông trung tâm có tạo độ là (0, 0)
- Xoay khối hình 90 độ tại gốc tạo độ, lúc đó điểm (x, y) sẽ nằm ở vị trí (y, -x)
- Tính tiến khối hình theo Vector t(a, b)
Sau 3 bước trên ta đã có tạo độ của khối hình sau khi xoay phải 90 độ

Phương thức xoay trái 90 độ cũng tương tự, chỉ khác là tạo độ (x, y) sau khi xoay sẽ thành (-y, x)

Các phương thức, dịch chuyển lên, xuống, sang trái, qua phải:
```Java
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
```
Phương thức kiểm tra va chạm:

Để kiểm tra va chạm ta sẽ sử dụng boolean[][]b biết những ô nào chứa vật cản, bạn đầu khơi tạo những ô trống trong hộp mang giá trị true, nhưng ô ô viền trái, viền phải và đáy hộp mạng giá trị false. Nếu như một trong các ô vuông của khối hình trùng với vật cản thì phương thức trả về giá trị false:

```Java
	public boolean check(boolean b[][]) {
		for (int i = 0; i < this.v.size(); i++) {
			Squar sq = this.v.elementAt(i);
			if (b[sq.getX()][sq.getY()] == false) {
				return false;
			}
				
		}
		return true;
	}
```

Xử lý sự kiểm khi gõ phím:

```Java
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (die) 
			return;
		if (e.getKeyCode() == e.VK_UP) {
			p.turnRight();
			if (!p.check(b)) {
				p.turnLeft();
			}
			update();
		} else if (e.getKeyCode() == e.VK_DOWN) {
			p.down();
			if (!p.check(b)) {
				newPuzz();
			}
			update();
		} else if (e.getKeyCode() == e.VK_LEFT) {
			p.left();
			if (!p.check(b)) {
				p.right();
			}
			update();
		} else if (e.getKeyCode() == e.VK_RIGHT) {
			p.right();
			if (!p.check(b)) {
				p.left();
			}
			update();
		}
	}
```

Có nghĩa là khi gặp vật cản, khối hộp sẽ giữ nguyên trang thái trước khi va chạm.

Xóa bỏ 1 hàng ngang nếu nó đều là các ô vuông trong các khối hình:

```Java
		for (int i = M + 2; i >= 1; i--) {
			boolean kt = true;
			for (int j = 3; j < N + 3; j++)
				if (b[i][j] == true)
					kt = false;
			if (kt) {
				score++;
				updateScore();
				for (int h = i; h >= 1; h--)
					for (int j = 3; j < N + 3; j++) {
						b[h][j] = b[h - 1][j];
						bt[h][j].setBackground(bt[h - 1][j].getBackground());
					}
				for (int j = 3; j < N + 3; j++)
					b[0][j] = true;
			}
		}
```

### Kết

Trên đây là chia sẻ của mình về cách mình đã lập trình game xếp hình, mọi đóng góp xin được ghi nhận ở phần bình luận, các bạn có thể tham khảo source code của mình [Tại đây](https://github.com/zukahai/Classic-Jigsaw-Puzzle).

Video Demo:

[<p align="center"> <img src="https://github.com/zukahai/HaiZuka/blob/master/Images/ClassicJigsawPuzzle/6.png" alt="xephinh" /> </p>](https://www.youtube.com/watch?v=qKyZKUxDZh8)

## <p align="center">  :tv: Thanks for whatching :earth_africa: </p>
