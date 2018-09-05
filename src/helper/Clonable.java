package helper;

import java.util.LinkedList;
import com.jsyn.unitgen.UnitGenerator;
import nodeSystem.Entry;

public interface Clonable {
	public UnitGenerator cloneUnitGen();

	public LinkedList<String[]> getInnerConnections();
	
	public LinkedList<Entry[]> getOuterConnections();
}
