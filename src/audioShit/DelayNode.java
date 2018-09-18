package audioShit;

import com.jsyn.unitgen.InterpolatingDelay;

import oldNodeSystem.OldNode;
import oldNodeSystem.Slider;

public class DelayNode extends OldNode{
 
	private AudioInOutEntry inOut;
	private InterpolatingDelay delay;
	private float delaySec;
	private Slider slide;
	
	public DelayNode(int[] pos, String name) {
		super(pos, name);
		
		delay = new InterpolatingDelay();
		wind.addToSynth(delay);
		delay.start();
		
		addEntry(inOut = new AudioInOutEntry(this, ""));
		inOut.getLeftPorts().output.connect(delay.input);
		delay.output.connect(inOut.getRightPorts().input);
		
		delay.allocate(10*wind.getSynth().getFrameRate());
		
		
		addEntry(slide = new Slider(this, "Delay", 0, 10));
		slide.getRightPorts().output.connect(delay.delay);
	}
	
	public void setDelay(float delay) {
		if(this.delaySec == delay) {
			
		}else {
			delaySec = delay;
			this.delay.allocate((int) (delaySec*wind.getSynth().getFrameRate()));
		}
	}

}
