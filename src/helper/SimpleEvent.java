package helper;

public class SimpleEvent implements Comparable<SimpleEvent> {
	public int pitch;
	public boolean isOn;
	public double time;

	public SimpleEvent(int pitch, boolean isOn, double time) {
		this.pitch = pitch;
		this.isOn = isOn;
		this.time = time;
	}

	@Override
	public int compareTo(SimpleEvent o) {
		return (int) ((this.time - o.time )*1000);
	}

	@Override
	public String toString() {
		return "(Pitch: " + pitch + " Time: " + time + " isOne: " + isOn + ")";
	}
}
