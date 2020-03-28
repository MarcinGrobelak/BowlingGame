/**
 * 
 */
package pl.code_craft.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import pl.code_craft.frame.Frame;
import pl.code_craft.score.ScoreService;

/**
 * @author Marcin
 *
 */
@Named
@ViewScoped
public class ViewBean implements Serializable {

	private static final long serialVersionUID = -4273338410365761268L;

	private int roll;
	private int possiblePoints;
	private List<Frame> frames;
	private Frame currentFrame;

	@PostConstruct
	private void init() {
		frames = new ArrayList<>();
		currentFrame = new Frame();
		frames.add(currentFrame);
		possiblePoints = 10;
	}

	@PreDestroy
	private void destroy() {
		if (frames != null) {
			frames.clear();
			frames = null;
		}
		currentFrame = null;
	}

	public int getRoll() {
		return roll;
	}

	public void addRoll(int roll) {
		this.roll = roll;
		if (currentFrame.isClosed()) {
			currentFrame = new Frame();
			frames.add(currentFrame);
		}

		currentFrame.addRoll(roll);

		if (currentFrame.isClosed()) {
			ScoreService.setScore(frames);
			possiblePoints = 10;
			return;
		}

		possiblePoints = 10 - roll;
	}

	public int getPossiblePoints() {
		return possiblePoints;
	}

	public List<Frame> getFrames() {
		return frames;
	}
}
