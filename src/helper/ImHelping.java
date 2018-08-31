package helper;

import java.awt.Graphics2D;
import java.awt.geom.CubicCurve2D;

import display.Draggable;
import display.NodeView;
import display.Window;
import graphics.Drawable;
import nodeSystem.Connector;

public class ImHelping {
	public static Window wind;
	public static NodeView root;

	public static void drawConnectionBezier(Graphics2D g, int startX, int startY, int endX, int endY) {
		int cuviness = Math.abs(startX -endX);
		cuviness /= 2;
		drawConnectionBezier(g, startX, startY, endX, endY, cuviness);
	}

	public static void drawConnectionBezier(Graphics2D g, int startX, int startY, int endX, int endY, int curveiness) {
		g.draw(new CubicCurve2D.Double(startX, startY, startX + curveiness, startY, endX - curveiness, endY, endX,
				endY));
	}

	public static void drawConnectionBezier(Graphics2D g, Drawable d1, Drawable d2) {
		drawConnectionBezier(g, d1.getX(), d1.getY(), d2.getX(), d2.getY());

	}
}
