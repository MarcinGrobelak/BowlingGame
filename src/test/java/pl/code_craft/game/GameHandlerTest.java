package pl.code_craft.game;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import pl.code_craft.frame.FinalFrame;
import pl.code_craft.frame.RegularFrame;

public class GameHandlerTest {

	private GameHandler game;

	@BeforeEach
	void initializeTest() {
		game = new GameHandler();
	}

	private void generateFrames() {
		game.roll(10); // 20
		game.roll(8);
		game.roll(2); // 13
		game.roll(3);
		game.roll(2); // 5
	}

	private void createNineframeList() {
		for (int i = 0; i < 9; i++) {
			game.roll(10);
		}
	}

	@Test
	void frameShouldBeCreatedWhenRollAfterClosedOne() {
		// given
		game.roll(10);
		game.roll(0);
		// then
		assertThat(game.getFrames(), hasSize(2));

	}

	@Test
	void newFrameShouldNotBeCreatedWhenRollAfterNotClosed() {
		// given
		game.roll(5);
		game.roll(1);
		// then
		assertThat(game.getFrames(), hasSize(1));
	}

	@Test
	void pointToScoreShouldBeTenAfterClosedFrame() {
		// given
		game.roll(10);
		// then
		assertThat(game.getPointsToScore(), equalTo(10));
	}

	@Test
	void pointsToScoreShouldBeTenMinusFirstRollAfterNonTenPointerFirstRoll() {
		// given
		game.roll(6);
		// then
		assertThat(game.getPointsToScore(), equalTo(4));
	}

	@Test
	void overallScoreShouldSummAllFramesScores() {
		// given
		generateFrames();
		// then
		assertEquals(38, game.getOverallScore());
	}

	@Test
	void framesShouldBeClearedAfterClearCall() {
		// given
		generateFrames();
		// when
		game.clear();
		// then
		assertThat(game.getFrames(), is(nullValue()));
		assertThat(game.getCurrentFrame(), is(nullValue()));
	}

	@RepeatedTest(9)
	void firstNineFramesShouldBeRegularFrames(RepetitionInfo repetition) {
		// given
		createNineframeList();
		// then
		assertThat(game.getFrames().get(repetition.getCurrentRepetition() - 1), instanceOf(RegularFrame.class));
	};

	@Test
	void tenthFrameShouldBeFinalFrame() {
		// given
		createNineframeList();
		// when
		game.roll(10);
		// then
		assertThat(game.getCurrentFrame(), instanceOf(FinalFrame.class));

	}

	@Test
	void closedTenthFrameShouldEndTheGame() {
		// given
		createNineframeList();
		// when
		game.roll(1);
		game.roll(1);
		// then
		assertThat(game.isGameOver(), is(true));
	}

}
