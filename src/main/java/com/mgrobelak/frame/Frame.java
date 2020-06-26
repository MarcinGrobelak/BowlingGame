package com.mgrobelak.frame;

/**
 * @author Marcin Grobelak
 */

public interface Frame {
	public void addRoll(Integer roll) throws IllegalStateException;

	public boolean isStrike();

	public boolean isSpare();

	public boolean isFullPointer();

	public boolean isClosed();

	public Integer getScore();

	public void setScore(Integer score);

	public Integer getFirstRoll();

	public Integer getSecondRoll();

	public Integer getSumOfRolls();

	public boolean isFinal();
}
