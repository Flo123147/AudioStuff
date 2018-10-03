package display;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import helper.Clickable;
import helper.ControlHelper;
import helper.ValueContainer;
import nodeSystem.NodeAudioInPort;
import nodeSystem.NodeAudioOutPort;
import nodeSystem.NodeControlInPort;
import nodeSystem.NodeControlOutPort;
import oldNodeSystem.Connector;
import oldNodeSystem.SliderKnob;

public class Dragger implements MouseMotionListener, MouseListener {

	private Draggable toDrag;
	private boolean dragBackGround;

	private ControlHelper ch;
	private boolean connecting;
	private NodeAudioOutPort connectAudioFrom;
	private NodeControlOutPort connectControlFrom;

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
			if (cl.getCollider() != null && cl.getCollider().contains(e.getPoint())) {
				cl.clicked(ch);

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

		boolean backgroundClicked = true;

		for (Draggable d : viewCont.x.getDragos()) {
			if (d.getCollider() != null && d.getCollider().contains(e.getPoint())) {
				toDrag = d;
				d.dragStart();
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

		if (connecting) {
			boolean foundPort = false;

			for (Draggable d : viewCont.x.getDragos()) {
				if (d.getCollider() != null && d.getCollider().contains(e.getPoint())) {
					if (connectAudioFrom != null && d instanceof NodeAudioInPort) {
						NodeAudioInPort naip = (NodeAudioInPort) d;
						naip.addInConnection(connectAudioFrom);
						connectAudioFrom.connectingEnd(true, naip);
						connectAudioFrom = null;
						foundPort = true;
					} else if (connectControlFrom != null && d instanceof NodeControlInPort) {
						NodeControlInPort ncip = (NodeControlInPort) d;
						ncip.addInConnection(connectControlFrom);
						connectControlFrom.connectingEnd(true, ncip);
						connectControlFrom = null;
						foundPort = true;
					}

				}
			}
			if (!foundPort) {
				if (connectAudioFrom != null)
					connectAudioFrom.connectingEnd(false, null);
				if (connectControlFrom != null)
					connectControlFrom.connectingEnd(false, null);
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

		if (toDrag != null) {

			toDrag.move(ch.getDeltaX(), ch.getDeltaY(), ch);

		} else if (dragBackGround && viewCont.x.isDraggable()) {
			viewCont.x.moveRoot(ch.getDeltaX(), ch.getDeltaY());
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	public void startControlConnecting(NodeControlOutPort startControl, Draggable temp) {
		connecting = true;
		connectControlFrom = startControl;
		toDrag = temp;
	}

	public void startAudioConnecting(NodeAudioOutPort startAudio, Draggable temp) {

		connecting = true;
		connectAudioFrom = startAudio;
		toDrag = temp;

	}

}
