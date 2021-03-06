package com.mgrobelak.frame;

/**
 * @author Marcin Grobelak
 */

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class RegularFrameTest {

	private RegularFrame frame;

	@BeforeEach
	void initializeFrame() {
		frame = new RegularFrame();
	}

	@Test
	void frameIsStrikeWhenFirstRollEqualsTen() {
		// when
		frame.addRoll(10);
		// then
		assertTrue(frame.isStrike());
	}

	@RepeatedTest(9)
	void frameIsNotStrikeWhenFirstRollIsLessThanTen(RepetitionInfo repetition) {
		// when
		frame.addRoll(repetition.getCurrentRepetition());
		// then
		assertFalse(frame.isStrike());
	}

	@ParameterizedTest
	@CsvSource({ "1,9", "2,8", "3,7", "4,6", "5,5", "6,4", "7,3", "8,2", "9,1" })
	void frameIsSprareWhenSumOfRolesEqualsTen(int first, int second) {
		// when
		frame.addRoll(first);
		frame.addRoll(second);
		// then
		assertTrue(frame.isSpare());
	}

	@Test
	void frameCannotBeSpareWhenItIsStrike() {
		// when
		frame.addRoll(10);
		// then
		assertFalse(frame.isSpare());
	}

	@ParameterizedTest
	@MethodSource("possiblePairsWithSumLessThanTen")
	void frameIsNotSpareWhenSumOfRolesNotEqualsTen(int first, int second) {
		// when
		frame.addRoll(first);
		frame.addRoll(second);
		// then
		assertFalse(frame.isSpare());
	}

	@Test
	void firstRollIsNotNullAfterFirstAddRollCall() {
		// when
		frame.addRoll(1);
		// then
		assertNotNull(frame.getFirstRoll());
	}

	@Test
	void secondRollIsNotNullAfterSecondAddRollCallOnNonStrike() {
		// when
		frame.addRoll(1);
		frame.addRoll(2);
		// then
		assertNotNull(frame.getSecondRoll());
	}

	@Test
	void exceptionShouldBeThrownIfAddRollToStrike() {
		// when
		frame.addRoll(10);
		// then
		assertThrows(IllegalStateException.class, () -> frame.addRoll(1));
	}

	@Test
	void exceptionShouldBeThrownIfAddRollToClosedFrame() {
		// when
		frame.addRoll(1);
		frame.addRoll(2);
		// then
		assertThrows(IllegalStateException.class, () -> frame.addRoll(3));
	}

	@Test
	void strikeIsFullPointer() {
		// when
		frame.addRoll(10);
		// then
		assertTrue(frame.isFullPointer());
	}

	@Test
	void spareIsFullPointer() {
		// when
		frame.addRoll(2);
		frame.addRoll(8);
		// then
		assertTrue(frame.isFullPointer());
	}

	@ParameterizedTest
	@MethodSource("possiblePairsWithSumLessThanTen")
	void frameWithSumOfRollsLestThanTenIsNotFullPointer(int first, int second) {
		// when
		frame.addRoll(first);
		frame.addRoll(second);
		// then
		assertFalse(frame.isFullPointer());
	}

	private static List<Arguments> possiblePairsWithSumLessThanTen() {
		List<Arguments> args = new ArrayList<>();
		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j <= 9; j++) {
				if (i + j < 10) {
					args.add(Arguments.of(i, j));
				}
			}
		}
		return args;
	}

	@Test
	void sumeOfRollsShouldBeNullIfFirstRollIsNull() {
		// then
		assertNull(frame.getFirstRoll());
		assertNull(frame.getSumOfRolls());
	}

	@Test
	void sumeOfRollsShouldBeNullIfSecondRollIsNull() {
		// when
		frame.addRoll(1);
		// then
		assertNull(frame.getSecondRoll());
		assertNull(frame.getSumOfRolls());
	}

	@Test
	void sumeOfRollsShouldBeSumValueIfBothRollsAreNotNull() {
		// when
		frame.addRoll(1);
		frame.addRoll(2);
		// then
		assertNotNull(frame.getSumOfRolls());
		assertEquals(3, frame.getSumOfRolls());
	}

	@Test
	void secondRollAsStringShouldBeXWhenStrike() {
		// when
		frame.addRoll(10);
		// then
		assertEquals("X", frame.getSecondRollAsString());
	}

	@Test
	void secondRollAsStringShouldBeSlashWhenSpare() {
		// when
		frame.addRoll(5);
		frame.addRoll(5);
		// then
		assertEquals("/", frame.getSecondRollAsString());
	}

	@Test
	void secondRollAsStringShouldBeSecondRollValueIfNotFullPointer() {
		// when
		frame.addRoll(1);
		frame.addRoll(2);
		// then
		assertEquals("2", frame.getSecondRollAsString());
	}

	@Test
	void secondRollAsStrinShouldBeEmptyStringWhenSecondRollIsNull() {
		// then
		assertEquals("", frame.getSecondRollAsString());
	}

	@Test
	void firstRollAsStrinShouldBeEmptyStringWhenFirstRollIsNull() {
		// then
		assertEquals("", frame.getFirstRollAsString());
	}

	@Test
	void firstRollAsStrinShouldBeFirstRollValueAfterFirstRoll() {
		// when
		frame.addRoll(1);
		// then
		assertEquals("1", frame.getFirstRollAsString());
	}

}
