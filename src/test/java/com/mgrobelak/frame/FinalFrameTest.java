package com.mgrobelak.frame;

/**
 * @author Marcin Grobelak
 */

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FinalFrameTest {
	private FinalFrame finalFrame;

	@BeforeEach
	void initializeFrame() {
		finalFrame = new FinalFrame();
	}

	@Test
	void finalFrameShouldAddThirdRollWhenFirstRollWasStrike() {
		// when
		finalFrame.addRoll(10);
		finalFrame.addRoll(1);
		finalFrame.addRoll(5);
		// then
		assertEquals(5, finalFrame.getThirdRoll());
	}

	@Test
	void finalFrameShouldAddThirdRollWhenFirstTrwoWereSpare() {
		// when
		finalFrame.addRoll(6);
		finalFrame.addRoll(4);
		finalFrame.addRoll(1);
		// then
		assertEquals(1, finalFrame.getThirdRoll());
	}

	@Test
	void exceptionShouldBeThrownWhenAddingThirdRollToNonFulPointerLastframe() {
		// when
		finalFrame.addRoll(1);
		finalFrame.addRoll(2);
		// then
		assertThrows(IllegalStateException.class, () -> finalFrame.addRoll(3));
	}

	@Test
	void finalFrameShouldBeClosedWhenFirstRollWasStikeAndAnyThirdWasRolled() {
		// when
		finalFrame.addRoll(10);
		finalFrame.addRoll(1);
		finalFrame.addRoll(1);
		// then
		assertTrue(finalFrame.isClosed());
	}

	@Test
	void finalFrameShouldBeClosedAfterSpareAndAnyThirdWasRolled() {
		// when
		finalFrame.addRoll(7);
		finalFrame.addRoll(3);
		finalFrame.addRoll(1);
		// then
		assertTrue(finalFrame.isClosed());
	}

	@Test
	void finalFrameShouldBeClosedAfterFirstTwoNonFullPointerRolls() {
		// when
		finalFrame.addRoll(1);
		finalFrame.addRoll(1);
		// then
		assertTrue(finalFrame.isClosed());
	}

	@Test
	void finalFrameShouldNotBeClosedAfterFirstNonFullPointerRolls() {
		// when
		finalFrame.addRoll(1);
		// then
		assertFalse(finalFrame.isClosed());
	}

	@Test
	void finalFrameShouldNotBeClosedAfterStrikeWithoutThirdRoll() {
		// when
		finalFrame.addRoll(10);
		finalFrame.addRoll(1);
		// then
		assertFalse(finalFrame.isClosed());
	}

	@Test
	void finalFrameShouldNotBeClosedAfterSpareWithoutThirdRoll() {
		// when
		finalFrame.addRoll(5);
		finalFrame.addRoll(5);
		// then
		assertFalse(finalFrame.isClosed());
	}

	@Test
	void sumOfRollsShouldBeNullAfterFirstRoll() {
		// when
		finalFrame.addRoll(5);
		// then
		assertNull(finalFrame.getSumOfRolls());
	}

	@Test
	void sumOfRollsShouldBeSumOfFirstAndSecondIfThirdWasNotRolled() {
		// when
		finalFrame.addRoll(5);
		finalFrame.addRoll(5);
		// then
		assertEquals(10, finalFrame.getSumOfRolls());
	}

	@Test
	void sumOfRollsShouldBeSumOfAllRollsAfterThirdRoll() {
		// when
		finalFrame.addRoll(5);
		finalFrame.addRoll(5);
		finalFrame.addRoll(5);
		// then
		assertEquals(15, finalFrame.getSumOfRolls());
	}

	@Test
	void nullRollTranslationSholdBeEmptyString() {
		// then
		assertEquals("", finalFrame.getFirstRollAsString());
		assertEquals("", finalFrame.getSecondRollAsString());
		assertEquals("", finalFrame.getThirdRollAsString());
	}

	@Test
	void tenPointerRollTranslationShuldBeX() {
		// when
		finalFrame.addRoll(10);
		finalFrame.addRoll(10);
		finalFrame.addRoll(10);
		// then
		assertEquals("X", finalFrame.getFirstRollAsString());
		assertEquals("X", finalFrame.getSecondRollAsString());
		assertEquals("X", finalFrame.getSecondRollAsString());
	}

	@Test
	void otherThanTenRollTranslationShouldBeRollValue() {
		// when
		finalFrame.addRoll(1);
		finalFrame.addRoll(2);
		// then
		assertEquals("1", finalFrame.getFirstRollAsString());
		assertEquals("2", finalFrame.getSecondRollAsString());
	}

	@Test
	void secondRollSpareTranslationShouldBeBackSlash() {
		// when
		finalFrame.addRoll(5);
		finalFrame.addRoll(5);
		// then
		assertEquals("/", finalFrame.getSecondRollAsString());
	}

}
