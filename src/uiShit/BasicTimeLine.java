package uiShit;

import java.awt.Color;
import java.awt.Graphics2D;
import java.sql.Time;

import audioShit.TimeLinePlayer;
import display.NodeView;
import display.Window;

public class BasicTimeLine extends UiSubElement implements ClickReciever {
	public NodeView nodeView;
	private Button butt, butt2, butt3,butt4;
	private int divider;
	private boolean isNodeView;
	private TimeLinesBox timeLineBox;
	private TimeLinePlayer timeLinePlayer;

	public BasicTimeLine(int[] pos, String name, TimeLinesBox timeLinesBox) {
		super(pos, name);
		setColor(Color.WHITE);
		setHeight(100);
		divider = 100;

		addChild(butt = new Button(new int[] { 0, 0 }, "Switch", this));
		addChild(butt2 = new Button(new int[] { 0, 20 }, "Play", this));
		addChild(butt3 = new Button(new int[] { 0, 40 }, "Pause", this));
		addChild(butt4 = new Button(new int[] { 0, 60 }, "TEST", this));
		
		nodeView = new NodeView(getName() + "NodeView", wind, this);

		this.timeLineBox = timeLinesBox;
		Thread t = new Thread(timeLinePlayer = new TimeLinePlayer(nodeView));
		t.start();
	}

	public void addAudioTrigger(double time, int pitch) {
		timeLinePlayer.add(time, pitch);
	}

	@Override
	protected void draw(Graphics2D g, int x, int y) {
		g.setColor(getColor());
		g.fillRect(x, y, getWidth(), getHeight());
		super.draw(g, x, y);
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
				nodeView.migrateClicksAndDragos( this);
				nodeView.addComponent(this);
			} else {
				switchToMain();
				isNodeView = false;
				timeLineBox.reAddTimeLine(this);
			}
			break;
		case "Play":
			if(nodeView.hasChanged()) {
				nodeView.startThisShit();
			}
			timeLinePlayer.unPause();
			break;
		case "Pause":
			timeLinePlayer.pause();
			break;
		case "TEST":
			//nodeView.gc.gatherUnits();
			break;
		default:
			break;
		}
		
	}

}
