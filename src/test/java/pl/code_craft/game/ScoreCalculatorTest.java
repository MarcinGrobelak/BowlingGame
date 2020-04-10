package pl.code_craft.game;

/**
 * @author Marcin Grobelak (code-craft.pl)
 */

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pl.code_craft.frame.FinalFrame;
import pl.code_craft.frame.Frame;
import pl.code_craft.frame.RegularFrame;

public class ScoreCalculatorTest {
	private List<Frame> frames;
	private Frame first;
	private Frame second;
	private Frame third;
	private Frame ninth;
	private Frame lastFrame;

	@BeforeEach
	void initializeTest() {
		frames = new ArrayList<>();
	}

	private void createThreeFramesList() {
		first = new RegularFrame();
		second = new RegularFrame();
		third = new RegularFrame();
		frames.add(first);
		frames.add(second);
		frames.add(third);
	}

	private void createTwoFramesList() {
		first = new RegularFrame();
		second = new RegularFrame();
		frames.add(first);
		frames.add(second);
	}

	private void crateTenFramesList() {
		for (int i = 0; i < 8; i++) {
			frames.add(new RegularFrame());
		}
		ninth = new RegularFrame();
		frames.add(ninth);
		lastFrame = new FinalFrame();
		frames.add(lastFrame);
	}

	@Test
	void allRollsAreStrikes() {
		// given
		createThreeFramesList();
		first.addRoll(10);
		second.addRoll(10);
		third.addRoll(10);
		// when
		ScoreCalculator.setScore(frames);
		// then
		assertEquals(30, first.getScore());
		assertNull(second.getScore());
		assertNull(third.getScore());
	}

	@Test
	void firstTwoStrikesThirdIsSpare() {
		// given
		createThreeFramesList();
		first.addRoll(10);
		second.addRoll(10);
		third.addRoll(5);
		third.addRoll(5);
		// when
		ScoreCalculator.setScore(frames);
		// then
		assertEquals(25, first.getScore());
		assertEquals(20, second.getScore());
		assertNull(third.getScore());
	}

	@Test
	void firstTwoStrikesThirdWithRegularFirstRoll() {
		// given
		createThreeFramesList();
		first.addRoll(10);
		second.addRoll(10);
		third.addRoll(3);
		// when
		ScoreCalculator.setScore(frames);
		// then
		assertEquals(23, first.getScore());
		assertNull(second.getScore());
		assertNull(third.getScore());
	}

	@Test
	void firstTwoStrikesThirdWithBothRegularRolls() {
		// given
		createThreeFramesList();
		first.addRoll(10);
		second.addRoll(10);
		third.addRoll(4);
		third.addRoll(2);
		// when
		ScoreCalculator.setScore(frames);
		// then
		assertEquals(24, first.getScore());
		assertEquals(16, second.getScore());
		assertEquals(6, third.getScore());
	}

	@Test
	void firstStrikeSecondSpare() {
		// given
		createTwoFramesList();
		first.addRoll(10);
		second.addRoll(5);
		second.addRoll(5);
		// when
		ScoreCalculator.setScore(frames);
		// then
		assertEquals(20, first.getScore());
		assertNull(second.getScore());
	}

	@Test
	void firstStrikeSecondRegularFirstRoll() {
		// given
		createTwoFramesList();
		first.addRoll(10);
		second.addRoll(1);
		// when
		ScoreCalculator.setScore(frames);
		// then
		assertNull(first.getScore());
		assertNull(second.getScore());
	}

	@Test
	void firstSpareThenRegularFirstRoll() {
		// given
		createTwoFramesList();
		first.addRoll(5);
		first.addRoll(5);
		second.addRoll(1);
		// when
		ScoreCalculator.setScore(frames);
		// then
		assertEquals(11, first.getScore());
	}

	@Test
	void firstSpareThenFullPointer() {
		// given
		createTwoFramesList();
		first.addRoll(5);
		first.addRoll(5);
		second.addRoll(10);
		// when
		ScoreCalculator.setScore(frames);
		// then
		assertEquals(20, first.getScore());
		assertNull(second.getScore());
	}

	@Test
	void nihthStrikeThenRegularLastFram() {
		// given
		crateTenFramesList();
		ninth.addRoll(10);
		lastFrame.addRoll(2);
		lastFrame.addRoll(3);
		// when
		ScoreCalculator.setScore(frames);
		// then
		assertEquals(15, ninth.getScore());
	}

	@Test
	void nihthStrikeThenLastFrameWithTenPointerAsFirstRoll() {
		// given
		crateTenFramesList();
		ninth.addRoll(10);
		lastFrame.addRoll(10);
		lastFrame.addRoll(2);
		// when
		ScoreCalculator.setScore(frames);
		lastFrame.addRoll(10);
		ScoreCalculator.setScore(frames);
		// then
		assertEquals(22, ninth.getScore());
	}

	@Test
	void lastFrameWithAllTenPointers() {
		// given
		crateTenFramesList();
		lastFrame.addRoll(10);
		lastFrame.addRoll(10);
		lastFrame.addRoll(10);
		// when
		ScoreCalculator.setScore(frames);
		// then
		assertEquals(30, lastFrame.getScore());
	}
}
