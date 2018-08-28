package nodeSystem;

public class KeyTestNode extends KeyNode {

	public KeyTestNode(int[] pos, String name) {
		super(pos, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void keyOn(char key) {
		System.out.println(key  + "   ON" );
	}

	@Override
	public void keyOff(char key) {
		System.out.println(key  + "   OFF" );
	}

}
