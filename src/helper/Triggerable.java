package helper;


public interface Triggerable {
	void triggerOn(String triggerName);
	void triggerHold(String triggerName);
	void triggerOff(String triggerName);
}
