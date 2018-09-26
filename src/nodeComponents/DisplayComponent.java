package nodeComponents;

import nodeSupComponents.TextField;

public class DisplayComponent extends NodeComponent {

	private TextField tf;

	public DisplayComponent(String name, int maxWidth, int maxHeight) {
		super(name, maxWidth, maxHeight);
		addChild(tf = new TextField(new int[] { maxWidth / 2, 0 }, "Display", maxWidth, maxHeight, true));
		tf.setText("'Generic Message'");
	}

	public void setText(String string) {
		tf.setText(string);
	}

}
