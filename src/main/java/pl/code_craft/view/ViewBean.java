package pl.code_craft.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import pl.code_craft.frame.FinalFrame;
import pl.code_craft.frame.Frame;
import pl.code_craft.frame.RegularFrame;
import pl.code_craft.score.ScoreService;

/**
 * @author Marcin
 *
 */
@Named
@ViewScoped
public class ViewBean implements Serializable {

	private static final long serialVersionUID = -4273338410365761268L;

	private int pointsToScore;
	private List<Frame> frames;
	private Frame currentFrame;
	boolean gameOver;

	@PostConstruct
	private void init() {
		frames = new ArrayList<>();
		currentFrame = new RegularFrame();
		frames.add(currentFrame);
		pointsToScore = 10;
		gameOver = false;
	}

	@PreDestroy
	public void destroy() {
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
		ScoreService.setScore(frames);

		if (isFinalFrame()) {
			if (currentFrame.isClosed()) {
				gameOver = true;
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

	public int getPossiblePoints() {
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

	private boolean isFinalFrame() {
		return frames.size() == 10;
	}
}
