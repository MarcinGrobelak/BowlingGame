package pl.code_craft.frame;

/**
 * @author Marcin
 *
 */
public class RegularFrame implements Frame {
	protected Integer firstRoll;
	protected Integer secondRoll;
	private Integer score;

	@Override
	public boolean isStrike() {
		return (firstRoll != null && firstRoll == 10);
	}

	@Override
	public boolean isSpare() {
		return (firstRoll != null && secondRoll != null && firstRoll + secondRoll == 10);
	}

	@Override
	public boolean isClosed() {
		return (isStrike() || secondRoll != null);
	}

	@Override
	public boolean isFullPointer() {
		if (isStrike() || isSpare()) {
			return true;
		}
		return false;
	}

	@Override
	public void addRoll(Integer roll) throws IllegalStateException {
		if (firstRoll == null) {
			firstRoll = roll;
			return;
		}
		if (!isClosed()) {
			secondRoll = roll;
			return;
		}
		throw new IllegalStateException("Can not add roll to a closed frame.");
	}

	@Override
	public Integer getScore() {
		return score;
	}

	@Override
	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public Integer getFirstRoll() {
		return firstRoll;
	}

	public Integer getSecondRoll() {
		return secondRoll;
	}

	@Override
	public Integer getSumOfRolls() {
		if (firstRoll != null && secondRoll != null) {
			return firstRoll + secondRoll;
		}
		return null;
	}

	public String getSecondRollLabel() {
		if (isStrike()) {
			return "X";
		}
		if (isSpare()) {
			return "/";
		}
		if (secondRoll != null) {
			return String.valueOf(secondRoll);
		}
		return "";
	}

	@Override
	public boolean isFinal() {
		return false;
	}

}
