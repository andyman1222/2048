import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class main extends JPanel {
	static int x = 4;
	static int y = 4;
	static int increment = 2;
	static int answer = 2048;
	static int[][] array = new int[x][y];
	static boolean alreadyWon = false;
	// second array is across
	static int randomX, randomY;
	public static String direction = "null";
	protected static Graphics i;

	public void paint(Graphics g) {
		i = g;
		g.setColor(Color.white);
		g.fillRect(25, 25, y * 50, x * 50);
		g.setColor(Color.black);
		int line = 1;
		for (int[] col : array) {
			int colum = 1;
			for (int item : col) {
				g.drawRect((colum * 50) - 25, (line * 50) - 25, 50, 50);
				if (item != 0) {

					Color numberColor;
					if (item * 3 < 255) {
						numberColor = new Color(255, 255 - (item * 3), 255 - (item * 3));
					} else {
						numberColor = new Color(240, 240, 0);
					}
					g.setColor(numberColor);
					g.fillRect((colum * 50) - 25, (line * 50) - 25, 50, 50);
					g.setColor(Color.black);
					g.drawRect((colum * 50) - 25, (line * 50) - 25, 50, 50);
					g.drawString("" + item, colum * 50, line * 50);
				}
				colum++;
			}
			line++;
		}
		repaint();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("2048");
		frame.add(new main());
		frame.setSize(y * 100, x * 100);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		KeyPress keys = new KeyPress();
		frame.addKeyListener(keys);
		randomX = (int) (Math.random() * x);
		randomY = (int) (Math.random() * y);
		array[randomX][randomY] = increment;
		changeDir("null");
	}

	private static void shiftElements(boolean upDown, boolean oppositeDir, int i, int ii, int oneOrNegOne) {
		for (int iii = ii; (oppositeDir ? -1 : iii) < (upDown ? x : y); iii -= oneOrNegOne) {
			int firstTemp = upDown ? iii : i;
			int secTemp = upDown ? i : iii;
			int firstTempMod = firstTemp + (upDown ? oneOrNegOne : 0);
			int secTempMod = secTemp + (upDown ? 0 : oneOrNegOne);
			if ((upDown ? firstTemp : secTemp) == (oppositeDir ? ((upDown ? x : y) - 1) : 0))
				array[firstTemp][secTemp] = 0;
			else
				array[firstTempMod][secTempMod] = array[firstTemp][secTemp];
		}
	}

	public static void changeDir(String direction) {

		System.out.println(direction);
		boolean changed = false;
		boolean alreadyCombined = false;
		boolean upDown = false, oppositeDir = false;
		switch (direction) {

		case "up":
			upDown = true;
			oppositeDir = false;

			for (int i = 0; i < x; i++) {
				alreadyCombined = false;
				for (int ii = 1; ii < x; ii++) {
					if (ii > 0) {
						if (array[ii][i] != 0) {
							if (array[ii - 1][i] == 0) {
								array[ii - 1][i] = array[ii][i];
								for (int iii = ii; iii < x; iii++) {
									if (iii == x - 1)
										array[iii][i] = 0;
									else
										array[iii][i] = array[iii + 1][i];
								}
								ii -= 2;
								changed = true;
							} else if (array[ii - 1][i] == array[ii][i] && !alreadyCombined) {
								array[ii - 1][i] = array[ii][i] * 2;
								array[ii][i] = 0;
								for (int iii = ii; iii < x; iii++) {
									if (iii == x - 1)
										array[iii][i] = 0;
									else
										array[iii][i] = array[iii + 1][i];
								}
								ii -= 2;
								alreadyCombined = true;
								changed = true;
							} else
								alreadyCombined = false;
						}
					}
				}
			}
			break;
		case "down":
			upDown = true;
			oppositeDir = true;

			for (int i = 0; i < y; i++) {
				alreadyCombined = false;
				for (int ii = x - 2; ii > -1; ii--) {
					if (ii < x - 1) {
						if (array[ii][i] != 0) {
							if (array[ii + 1][i] == 0) {
								array[ii + 1][i] = array[ii][i];
								array[ii][i] = 0;
								for (int iii = ii; iii > -1; iii--) {
									if (iii == 0)
										array[iii][i] = 0;
									else
										array[iii][i] = array[iii - 1][i];
								}
								ii += 2;
								changed = true;
							} else if (array[ii + 1][i] == array[ii][i] && !alreadyCombined) {
								array[ii + 1][i] = array[ii][i] * 2;
								array[ii][i] = 0;
								for (int iii = ii; iii > -1; iii--) {
									if (iii == 0)
										array[iii][i] = 0;
									else
										array[iii][i] = array[iii - 1][i];
								}
								ii += 2;
								alreadyCombined = true;
								changed = true;
							} else
								alreadyCombined = false;
						}
					}
				}
			}
			break;
		case "left":
			upDown = false;
			oppositeDir = false;

			for (int i = 0; i < x; i++) {
				alreadyCombined = false;
				for (int ii = 1; ii < y; ii++) {
					if (ii > 0) {
						if (array[i][ii] != 0) {
							if (array[i][ii - 1] == 0) {
								array[i][ii - 1] = array[i][ii];
								array[i][ii] = 0;
								for (int iii = ii; iii < y; iii++) {
									if (iii == y - 1)
										array[i][iii] = 0;
									else
										array[i][iii] = array[i][iii + 1];
								}
								ii -= 2;
								changed = true;
							} else if (array[i][ii] == array[i][ii - 1] && !alreadyCombined) {
								array[i][ii - 1] = array[i][ii] * 2;
								array[i][ii] = 0;
								for (int iii = ii; iii < y; iii++) {
									if (iii == y - 1)
										array[i][iii] = 0;
									else
										array[i][iii] = array[i][iii + 1];
								}
								ii -= 2;
								alreadyCombined = true;
								changed = true;
							} else
								alreadyCombined = false;
						}
					}
				}
			}

			break;
		case "right":
			upDown = false;
			oppositeDir = true;

			for (int i = 0; i < x; i++) {
				alreadyCombined = false;
				for (int ii = y - 2; ii > -1; ii--) {
					if (ii < y - 1) {
						if (array[i][ii] != 0) {
							if (array[i][ii + 1] == 0) {
								array[i][ii + 1] = array[i][ii];
								array[i][ii] = 0;
								for (int iii = ii; iii > -1; iii--) {
									if (iii == 0)
										array[i][iii] = 0;
									else
										array[i][iii] = array[i][iii - 1];
								}
								ii += 2;
								changed = true;
							} else if (array[i][ii] == array[i][ii + 1] && !alreadyCombined) {
								array[i][ii + 1] = array[i][ii] * 2;
								array[i][ii] = 0;
								for (int iii = ii; iii > -1; iii--) {
									if (iii == 0)
										array[i][iii] = 0;
									else
										array[i][iii] = array[i][iii - 1];
								}
								ii += 2;
								alreadyCombined = true;
								changed = true;
							} else
								alreadyCombined = false;
						}
					}
				}
			}

			break;
		case "null":
			changed = true;
			break;
		}
		/*
		 * if (direction != "null") { int xOrY = upDown ? y : x; for (int i = 0;
		 * i < (upDown ? x : y); i++) { //System.out.println("" + i + "<" +
		 * (upDown ? x : y)); alreadyCombined = false; int oneOrNegOne =
		 * oppositeDir ? 1 : -1; for (int ii = oppositeDir ? xOrY - 2 : 1;
		 * (oppositeDir ? - 1 : ii) < (oppositeDir ? ii : xOrY); ii -=
		 * oneOrNegOne) { System.out.println("" + (oppositeDir ? -1 : ii) + "<"
		 * + (oppositeDir ? ii : xOrY)); int first = upDown ? ii : i, sec =
		 * upDown ? i : ii; if ((oppositeDir ? ii : 0) < (oppositeDir ? xOrY - 1
		 * : ii)) { if (array[first][sec] != 0) {
		 * 
		 * int firstMod = first + (upDown?oneOrNegOne:0); int secMod = sec +
		 * (upDown?0:oneOrNegOne);
		 * 
		 * if (array[firstMod][secMod] == 0) { shiftElements(upDown,
		 * oppositeDir, i, ii, oneOrNegOne); ii += oppositeDir ? 2 : -2; changed
		 * = true; } else if (array[firstMod][secMod] == array[first][sec] &&
		 * !alreadyCombined) { array[firstMod][secMod] = array[first][sec] * 2;
		 * array[first][sec] = 0; shiftElements(upDown, oppositeDir, i, ii,
		 * oneOrNegOne); ii += oppositeDir ? 2 : -2; alreadyCombined = true;
		 * changed = true; } else alreadyCombined = false; } } // else
		 * alreadyCombined = false; } } }
		 */
		direction = "";
		update(changed);
	}

	public static void update(boolean changed) {
		boolean emptySpace = hasBlankSpace();
		for (int[] col : array) {
			for (int item : col) {
				if (item >= answer && !alreadyWon) {
					JOptionPane.showMessageDialog(null, "" + answer + " reached! You win!");
					alreadyWon = true;
				}
			}
		}
		if (changed) {

			if (emptySpace) {
				randomX = (int) (Math.random() * x);
				randomY = (int) (Math.random() * y);
				while (array[randomX][randomY] != 0) {
					randomX = (int) (Math.random() * x);
					randomY = (int) (Math.random() * y);
				}
				array[randomX][randomY] = (Math.random() > .3) ? increment : increment * 2;
			}

			int line = 1;
			for (int[] col : array) {
				int colum = 1;
				for (int item : col) {
					if (item != 0)
						System.out.print("" + item + "\t");
					else
						System.out.print("\t");
					colum++;
				}
				System.out.println();
				line++;
			}
			System.out.println();
		}
		if (hasLost())
			JOptionPane.showMessageDialog(null, "You lost!");
	}

	public static boolean hasLost() {
		for (int i = 0; i < x; i++)
			for (int ii = 1; ii < y; ii++)
				if (array[i][ii] == array[i][ii - 1])
					return false;
		for (int i = 0; i < y; i++)
			for (int ii = 1; ii < x; ii++)
				if (array[ii][i] == array[ii - 1][i])
					return false;
		return !hasBlankSpace();
	}

	public static boolean hasBlankSpace() {
		for (int[] col : array)
			for (int item : col)
				if (item == 0)
					return true;
		return false;
	}
}
