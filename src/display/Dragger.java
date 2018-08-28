package display;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import helper.ControlHelper;
import nodeSystem.Connector;
import nodeSystem.Root;
import nodeSystem.SliderKnob;

public class Dragger implements MouseMotionListener, MouseListener {

	private LinkedList<Draggable> dragos;
	private Draggable toDrag;

	private LinkedList<Clickable> clickos;

	private Root root;

	private boolean dragBackGround;

	private ControlHelper ch;
	private boolean connecting;
	private Connector connectFrom;

	public Dragger(Root root) {
		dragos = new LinkedList<>();
		clickos = new LinkedList<>();
		this.root = root;
		ch = new ControlHelper();
	}

	public void addDraggable(Draggable drago) {
		dragos.add(drago);
	}

	public void addClickable(Clickable clickable) {
		clickos.add(clickable);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (Clickable cl : clickos) {
			if (cl.getCollider() != null && cl.getCollider().contains(e.getPoint())) {
				cl.clicked();
				System.out.println(cl.getName());
				
				if (cl.getChildren().size() == 0) {
					break;
				}

			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		ch.update(e);
		ch.setStartXOnPanel(e.getX());
		ch.setStartYOnPanel(e.getY());

		System.out.println("click down");
		boolean backgroundClicked = true;
		for (Draggable d : dragos) {
			if (d.getCollider() != null && d.getCollider().contains(e.getPoint())) {
				toDrag = d;
				d.dragStart();
				System.out.println(d.getName());
				backgroundClicked = false;
				if (d.getChildren().size() == 0) {
					break;
				}

			}
		}

		if (backgroundClicked) {
			dragBackGround = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		ch.update(e);

		System.out.println("click up");

		if (connecting) {
			boolean foundPort = false;
			for (Draggable d : dragos) {
				if (d.getCollider() != null && d.getCollider().contains(e.getPoint())) {
					if (d instanceof Connector) {
						Connector connectTo = (Connector) d;
						if (connectTo.isInput() && !connectFrom.isSameNode(connectTo)) {
							connectTo.newInConnection(connectFrom);
							connectFrom.connentionSucc(connectTo);
							foundPort = true;
							break;
						}
					}

				}
			}
			if (!foundPort) {
				connectFrom.connentionFailed();

			}
		}
		connecting = false;

		if (toDrag != null) {
			toDrag.dragEnd();
		}

		toDrag = null;
		dragBackGround = false;

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		ch.update(e);

//		System.out.println(wind.getOffsetX() + "   " + wind.getOffsetY());
		if (toDrag != null) {

			toDrag.move(ch.getDeltaX(), ch.getDeltaY(), ch);

		} else if (dragBackGround) {
			root.moveRoot(ch.getDeltaX(), ch.getDeltaY());
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	public void makeConnection(Draggable temp, Connector connectFrom) {
		System.out.println("connecting...");

		toDrag = temp;
		connecting = true;
		this.connectFrom = connectFrom;
	}

}
