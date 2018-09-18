package uiShit;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.sql.Time;
import java.util.LinkedList;

import audioShit.TimeLinePlayer;
import display.NodeView;
import display.Window;
import helper.NoteEvent;

public class BasicTimeLine extends UiSubElement implements ClickReciever {
	public NodeView nodeView;
	private Button butt, butt2, butt3, butt4, butt5, butt6;
	private int divider, redLine;
	private boolean isNodeView;
	private TimeLinesBox timeLineBox;
	private TimeLinePlayer timeLinePlayer;

	public BasicTimeLine(int[] pos, String name, TimeLinesBox timeLinesBox) {
		super(pos, name);
		setColor(Color.WHITE);
		setHeight(100);
		divider = 40;

		redLine = 200;

		addChild(butt = new Button(new int[] { 0, 0 }, "Switch", this));
		addChild(butt2 = new Button(new int[] { 0, 20 }, "Play", this));
		addChild(butt3 = new Button(new int[] { 0, 40 }, "Pause", this));
		addChild(butt4 = new Button(new int[] { 0, 60 }, "Stop", this));
		addChild(butt5 = new Button(new int[] { 0, 80 }, "Test1", this));
		addChild(butt6 = new Button(new int[] { 0, 100 }, "Tetris", this));
		nodeView = new NodeView(getName() + "NodeView", wind, this);

		this.timeLineBox = timeLinesBox;
		Thread t = new Thread(
				timeLinePlayer = new TimeLinePlayer(new int[] { redLine, 0 }, getName() + "Player", nodeView));
		addChild(timeLinePlayer);
		t.start();

	}

	@Override
	public void init() {
		setHeight(timeLinePlayer.getOctaveHeight());
		super.init();
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {

		super.draw(g, x, y);
		g.setColor(getBaseColor());
		g.fillRect(x, y, getWidth(), getHeight());

		timeLinePlayer.draw(g, x + divider, y);

		g.setColor(Color.RED);
		g.drawLine(x + redLine, y, x + redLine, y + getHeight());
	}

	public void switchToNodes() {
		nodeView.switchTo();
	}

	public void switchToMain() {
		nodeView.switchBack();
	}

	@Override
	public void click(String button) {
		System.out.println("WUUUUUUUUUUUUUUUUUUUUUUUUUUU");
		switch (button) {
		case "Switch":
			if (!isNodeView) {
				switchToNodes();
				isNodeView = true;
				setPos(new int[] { 0, 400 });
				System.out.println(getLocalY() + "   " + getY());
				nodeView.migrateClicksAndDragos(this);
				nodeView.addComponent(this);
			} else {
				switchToMain();
				isNodeView = false;
				timeLineBox.reAddTimeLine(this);
			}
			break;
		case "Play":
			nodeView.unmute();
			if (nodeView.hasChanged()) {
				nodeView.startThisShit();
			}
			timeLinePlayer.unPause();
			break;
		case "Pause":
			nodeView.mute();
			timeLinePlayer.pause();
			break;
		case "Stop":
			timeLinePlayer.stop();
			break;
		case "Test1":
			timeLinePlayer.test1();
			break;
		case "Tetris":
			timeLinePlayer.tetris();
			break;
		default:
			break;
		}

	}

}
