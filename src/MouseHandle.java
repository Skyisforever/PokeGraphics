import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandle implements MouseListener {
	private int x, y;
	private boolean clickable = false;
	private Hud hud;
	
	public void setHud(Hud hud) {
		this.hud = hud;
	}
	
	@Override
	public void mousePressed(MouseEvent arg0) {
		// System.out.println(Hud.tw.isTyping());
		if (hud.tw.isTyping()) {
			hud.tw.end();
			clickable = false;
			return;
		}

		clickable = true;

		x = arg0.getX();
		y = arg0.getY();

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		int x1, y1;
		x1 = arg0.getX();
		y1 = arg0.getY();

		int x2 = (x + x1) / 2;
		int y2 = (y + y1) / 2;

		if (Math.abs(x - x1) <= 50 && Math.abs(y - y1) <= 25 && clickable) {
			hud.clicked = true;
			hud.setCoord(x2, y2);
			//System.out.printf("X: %d Y: %d\n", x2, y2);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}
}