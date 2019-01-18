public class Typewriter implements Runnable {
	private int x = 0;
	private String text;
	private boolean isTyping;
	private int speed;
	private boolean go;

	public void run() {
		System.out.println("Entered thread.");
		while (true) {
			while (!go) {
				try {
					Thread.sleep(speed);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			go = false;
			while (true) {
				if (x < text.length()) {
					++x;
					isTyping = true;
				} else {
					isTyping = false;
					break;
				}
				Hud.text = text.substring(0, x);
				try {
					Thread.sleep(this.speed);
				} catch (Exception e) {
				}
			}
		}
	}

	public void next() {
		go = true;
	}

	public Typewriter(int speed) {
		x = 0;
		this.speed = speed;
		isTyping = false;
		go = false;
	}

	public void set(String text) {
		this.text = text;
		this.x = 0;
	}

	public void end() {
		this.x = text.length() - 1;
	}

	public boolean isTyping() {
		return isTyping;
	}

}