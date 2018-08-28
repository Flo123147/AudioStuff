package unitGnerators;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.UnitGenerator;
import com.jsyn.unitgen.UnitSink;
import com.jsyn.unitgen.UnitSource;

public class Absolute extends UnitGenerator implements UnitSink, UnitSource {

	public UnitInputPort input;
	public UnitOutputPort output;

	public Absolute() {
		addPort(input = new UnitInputPort("Input"));
		addPort(output = new UnitOutputPort("Output"));
	}

	@Override
	public void generate(int start, int limit) {
		double[] ins = input.getValues();
		double[] outs = output.getValues();
		for (int i = start; i < limit; i++) {

			outs[i] = Math.abs(ins[i]);
		}
	}

	@Override
	public UnitOutputPort getOutput() {
		// TODO Auto-generated method stub
		return output;
	}

	@Override
	public UnitInputPort getInput() {
		// TODO Auto-generated method stub
		return input;
	}

}
