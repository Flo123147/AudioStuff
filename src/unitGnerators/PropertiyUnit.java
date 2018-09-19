package unitGnerators;

import com.jsyn.unitgen.PassThrough;
import nodeComponents.PropertyComponent;

public class PropertiyUnit extends PassThrough {

	private PropertyComponent comp;

	public PropertiyUnit(PropertyComponent comp) {
		this.comp = comp;
	}

	@Override
	public void generate(int start, int limit) {
		if (input.isConnected()) {
			super.generate(start, limit);
		} else {
			output.setValueInternal(comp.getValue());
		}

	}

}
