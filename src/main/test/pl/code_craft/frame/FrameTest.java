package pl.code_craft.frame;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FrameTest {

	private Frame frame;

	@BeforeEach
	void initializeFrame() {
		frame = new Frame();
	}

	@Test
	void frameIsStrikeWhenFirstRollEqualsTen() {
		// when
		frame.addRoll(10);
		// then
		assertTrue(frame.isStrike());
	}

	@RepeatedTest(9)
	void frameIsNotStrikeWhenFirstRollIsLessThanTen(RepetitionInfo repetitionInfo) {
		// when
		frame.addRoll(repetitionInfo.getCurrentRepetition());
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

}
