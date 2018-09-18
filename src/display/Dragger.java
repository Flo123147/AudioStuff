package display;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import helper.ControlHelper;
import helper.ValueContainer;
import oldNodeSystem.Connector;
import oldNodeSystem.SliderKnob;

public class Dragger implements MouseMotionListener, MouseListener {

	private Draggable toDrag;
	private boolean dragBackGround;

	private ControlHelper ch;
	private boolean connecting;
	private Connector connectFrom;

	private Window wind;

	private ValueContainer<View> viewCont;

	public Dragger(ValueContainer<View> viewCont) {
		this.wind = wind;
		ch = new ControlHelper();
		this.viewCont = viewCont;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		for (Clickable cl : viewCont.x.getClickos()) {
			System.out.print(cl.getName() + "  ");
			if (cl.getCollider() != null && cl.getCollider().contains(e.getPoint())) {
				cl.clicked();
				System.out.println(cl.getName() + "  Clicked");

				if (cl.getChildren().size() == 0) {
					break;
				}

			}
		}
		System.out.println();

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

		for (Draggable d : viewCont.x.getDragos()) {
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
			for (Draggable d : viewCont.x.getDragos()) {
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

		} else if (dragBackGround && viewCont.x.isDraggable()) {
			viewCont.x.moveRoot(ch.getDeltaX(), ch.getDeltaY());
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
