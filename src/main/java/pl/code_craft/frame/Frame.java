package pl.code_craft.frame;

/**
 * @author Marcin
 *
 */
public class Frame {
	private Integer firstRoll;
	private Integer secondRoll;
	private Integer score;

	public Frame() {
		firstRoll = null;
		secondRoll = null;
		score = null;
	}

	public boolean isStrike() {
		return (firstRoll != null && firstRoll == 10);
	}

	public boolean isSpare() {
		return (firstRoll != null && secondRoll != null && firstRoll + secondRoll == 10);
	}

	public boolean isClosed() {
		return (isStrike() || secondRoll != null);
	}

	public boolean isFullPointer() {
		if (isStrike() || isSpare()) {
			return true;
		}
		return false;
	}

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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getFirstRoll() {
		return firstRoll;
	}

	public Integer getSecondRoll() {
		return secondRoll;
	}

	public Integer getSumOfRolles() {
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

}
