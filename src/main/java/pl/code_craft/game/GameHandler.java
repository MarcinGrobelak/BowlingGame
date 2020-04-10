package pl.code_craft.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pl.code_craft.frame.FinalFrame;
import pl.code_craft.frame.Frame;
import pl.code_craft.frame.RegularFrame;

/**
 * @author Marcin Grobelak (code-craft.pl)
 */

public class GameHandler implements Serializable {

	private static final long serialVersionUID = 1019600622476911947L;

	private List<Frame> frames;
	private Frame currentFrame;
	private int pointsToScore;
	boolean gameOver;

	public GameHandler() {
		frames = new ArrayList<>();
		currentFrame = new RegularFrame();
		frames.add(currentFrame);
		pointsToScore = 10;
		gameOver = false;
	}

	public void clear() {
		if (frames != null) {
			frames.clear();
			frames = null;
		}
		currentFrame = null;
	}

	public void roll(int roll) {
		if (currentFrame.isClosed()) {
			addFrame();
		}

		currentFrame.addRoll(roll);
		ScoreCalculator.setScore(frames);

		if (currentFrame.isFinal()) {
			if (currentFrame.isClosed()) {
				gameOver = true;
				pointsToScore = 10;
				return;
			}
			if (currentFrame.isFullPointer()) {
				pointsToScore = 10;
				return;
			}
		}

		if (currentFrame.isClosed()) {
			pointsToScore = 10;
			return;
		}
		pointsToScore = 10 - roll;
	}

	public int getPointsToScore() {
		return pointsToScore;
	}

	public Frame getCurrentFrame() {
		return currentFrame;
	}

	public List<Frame> getFrames() {
		return frames;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public int getOverallScore() {
		int sum = 0;
		for (Frame frame : frames) {
			if (frame.getScore() != null) {
				sum += frame.getScore();
			}
		}
		return sum;
	}

	private void addFrame() {
		if (!gameOver) {
			currentFrame = createFrame();
			frames.add(currentFrame);
		}
	}

	private Frame createFrame() {
		if (frames.size() == 9) {
			return new FinalFrame();
		}
		return new RegularFrame();
	}
}
