package nodeSystem;

import com.jsyn.unitgen.PassThrough;

public class TaggedPassThrough extends PassThrough {
	private String tag;

	public TaggedPassThrough(String tag) {
		this.setTag(tag);
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
