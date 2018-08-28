package audioShit;

import nodeSystem.Entry;
import nodeSystem.Node;

public class AUdioInOutEntry extends Entry {

	public AUdioInOutEntry(Node node, String name) {
		super(node, name, 0);
		addInConnector();
		addOutCOnnector();
	}

	@Override
	protected void updateMetrics() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setDissconceted() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setConnected() {
		// TODO Auto-generated method stub

	}

}
