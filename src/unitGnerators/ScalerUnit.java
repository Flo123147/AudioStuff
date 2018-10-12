package unitGnerators;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.unitgen.UnitFilter;

public class ScalerUnit extends UnitFilter {

	public UnitInputPort scale;

	public ScalerUnit() {
		addPort(scale = new UnitInputPort("Scaler"));
		scale.setDefault(0.1);
		scale.set(scale.getDefault());
	}

	@Override
	public void generate(int start, int limit) {
		double[] ins = input.getValues();
		double[] outs = output.getValues();
		double[] skalars = scale.getValues();
		for (int i = 0; i < limit; i++) {
			outs[i] = ins[i] * skalars[i];			
			
		}
//		System.out.println(ins[start] + "   " + skalars[start]);
	}

}
