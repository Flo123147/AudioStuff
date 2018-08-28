package audioShit;


import nodeSystem.Entry;
import nodeSystem.Node;

public class AudioInEntry extends Entry{

	public AudioInEntry( Node node, String name) {
		super(node, name, 00);
		addInConnector();
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
