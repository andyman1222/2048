import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;

class KeyPress extends KeyAdapter implements KeyListener {
	// @Override
	public void keyTyped(KeyEvent e) {
	}

	// @Override
	public void keyPressed(KeyEvent e) {
		int keys = e.getKeyCode();
		if (keys == KeyEvent.VK_UP)
			main.changeDir("up");
		else if (keys == KeyEvent.VK_DOWN)
			main.changeDir("down");
		if (keys == KeyEvent.VK_W)
			main.changeDir("up");
		else if (keys == KeyEvent.VK_S)
			main.changeDir("down");
		if (keys == KeyEvent.VK_LEFT)
			main.changeDir("left");
		else if (keys == KeyEvent.VK_RIGHT)
			main.changeDir("right");
		if (keys == KeyEvent.VK_A)
			main.changeDir("left");
		else if (keys == KeyEvent.VK_D)
			main.changeDir("right");
	}

	// @Override
	public void keyReleased(KeyEvent e) {
		int keys = e.getKeyCode();
	}
}