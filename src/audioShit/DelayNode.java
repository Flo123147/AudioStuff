package audioShit;

import com.jsyn.unitgen.InterpolatingDelay;

import nodeSystem.Node;
import nodeSystem.Slider;

public class DelayNode extends Node{
 
	private AudioInOutEntry inOut;
	private InterpolatingDelay delay;
	private float delaySec;
	private Slider slide;
	
	public DelayNode(int[] pos, String name) {
		super(pos, name);
		
		delay = new InterpolatingDelay();
		OutputNode.getSynth().add(delay);
		delay.start();
		
		addEntry(inOut = new AudioInOutEntry(this, ""));
		inOut.getLeftPorts().output.connect(delay.input);
		delay.output.connect(inOut.getRightPorts().input);
		
		delay.allocate(10*OutputNode.getSynth().getFrameRate());
		
		
		addEntry(slide = new Slider(this, "Delay", 0, 10));
		slide.getRightPorts().output.connect(delay.delay);
	}
	
	public void setDelay(float delay) {
		if(this.delaySec == delay) {
			
		}else {
			delaySec = delay;
			this.delay.allocate((int) (delaySec*OutputNode.getSynth().getFrameRate()));
		}
	}

}
