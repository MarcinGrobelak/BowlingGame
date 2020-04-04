package pl.code_craft.score;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pl.code_craft.frame.Frame;

public class ScoreServiceTest {
	private List<Frame> frames;
	private Frame first;
	private Frame second;
	private Frame third;

	@BeforeEach
	void initializeTest() {
		frames = new ArrayList<>();
		first = new Frame();
		second = new Frame();
		third = new Frame();
	}

	private void createThreeFramesList() {
		frames.add(first);
		frames.add(second);
		frames.add(third);
	}

	private void createTwoFramesList() {
		frames.add(first);
		frames.add(second);
	}

	@Test
	void allRollsAreStrikes() {
		// given
		createThreeFramesList();
		first.addRoll(10);
		second.addRoll(10);
		third.addRoll(10);
		// when
		ScoreService.setScore(frames);
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
		ScoreService.setScore(frames);
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
		ScoreService.setScore(frames);
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
		ScoreService.setScore(frames);
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
		ScoreService.setScore(frames);
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
		ScoreService.setScore(frames);
		// then
		assertNull(first.getScore());
		assertNull(second.getScore());
	}

	@Test
	void firstSpareSecondRegularFirstRoll() {
		// given
		createTwoFramesList();
		first.addRoll(5);
		first.addRoll(5);
		second.addRoll(1);
		// when
		ScoreService.setScore(frames);
		// then
		assertEquals(11, first.getScore());
	}

	@Test
	void firstSpareSecondFullPointer() {
		// given
		createTwoFramesList();
		first.addRoll(5);
		first.addRoll(5);
		second.addRoll(10);
		// when
		ScoreService.setScore(frames);
		// then
		assertEquals(20, first.getScore());
		assertNull(second.getScore());
	}

}
