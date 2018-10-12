package testingInProgress;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.Circuit;
import com.jsyn.unitgen.PassThrough;
import com.jsyn.util.MultiChannelSynthesizer;

public class MultiSynthUnitTEST extends Circuit {
	private PassThrough psa;
	private PassThrough psb;

	public UnitOutputPort outputA, outputB;

	public MultiSynthUnitTEST(UnitOutputPort out) {
		psa = new PassThrough();
		psb = new PassThrough();
		
		add(psa);
		add(psb);

		psa.output.setName("Output A");
		psb.output.setName("Output B");

		addPort(outputA = psa.output);
		addPort(outputB = psb.output);

		out.connect(0, psa.input, 0);
		out.connect(1, psb.input, 0);
	}

}
