package pl.code_craft.frame;

public class FinalFrame extends RegularFrame {
	private Integer thirdRoll;

	@Override
	public void addRoll(Integer roll) throws IllegalStateException {
		if (firstRoll == null) {
			firstRoll = roll;
			return;
		}
		if (secondRoll == null) {
			secondRoll = roll;
			return;
		}
		if (isFullPointer()) {
			thirdRoll = roll;
			return;
		}

		throw new IllegalStateException("Can not add roll to a closed frame.");
	}

	@Override
	public boolean isClosed() {
		return (!isFullPointer() && super.isClosed() || isFullPointer() && thirdRoll != null);
	}

	@Override
	public Integer getSumOfRolls() {
		if (firstRoll != null && secondRoll != null && thirdRoll != null) {
			return firstRoll + secondRoll + thirdRoll;
		}
		if (firstRoll != null && secondRoll != null && !isFullPointer()) {
			return firstRoll + secondRoll;
		}
		return null;
	}

	public Integer getThirdRoll() {
		return thirdRoll;
	}

	@Override
	public boolean isFinal() {
		return true;
	}
}
