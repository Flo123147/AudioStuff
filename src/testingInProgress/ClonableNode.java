package testingInProgress;

import java.util.LinkedList;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.UnitGenerator;
import helper.Clonable;
import nodeSystem.Entry;
import nodeSystem.Node;

public class ClonableNode extends Node implements Clonable {

	protected UnitGenerator unitGen;

	private LinkedList<String[]> connections;
	private LinkedList<Entry[]> outConnections;

	public ClonableNode(int[] pos, String name) {
		super(pos, name);
		connections = new LinkedList<>();
		outConnections = new LinkedList<>();
	}

	protected void connect(UnitOutputPort out, UnitInputPort in) {
		out.connect(in);
		connections.add(new String[] { out.getName(), in.getName() });
	}

	protected void setUnitGen(UnitGenerator unitGen) {
		this.unitGen = unitGen;
	}

	@Override
	public UnitGenerator cloneUnitGen() {
		try {
			UnitGenerator uGen = unitGen.getClass().newInstance();

			return uGen;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void newConnection(Entry entry, Entry toAdd) {
		outConnections.add(new Entry[] { entry, toAdd });
		super.newConnection(entry, toAdd);
	}

	@Override
	public void removeConnection(Entry entry, Entry toRemove) {
		Entry[] removeThis = null;
		for (Entry[] entries : outConnections) {

			if (entries[0] == entry && entries[1] == toRemove) {
				removeThis = entries;
				break;				
			}
		}

		if (removeThis != null)
			outConnections.remove(removeThis);

		super.removeConnection(entry, toRemove);
	}

	@Override
	public LinkedList<String[]> getInnerConnections() {
		// TODO Auto-generated method stub
		return connections;
	}

	@Override
	public LinkedList<Entry[]> getOuterConnections() {
		// TODO Auto-generated method stub
		return outConnections;
	}

}
