package pl.code_craft.score;

import java.util.List;

import pl.code_craft.frame.Frame;

/**
 * @author Marcin
 *
 */
public class ScoreService {

	public static void setScore(List<Frame> frames) {

		int index = frames.size() - 1;
		Frame current = frames.get(index);

		if (!current.isFullPointer()) {
			current.setScore(current.getSumOfRolles());
		}

		if (frames.size() >= 2) {
			Frame previous = frames.get(index - 1);
			Frame first = null;

			if (frames.size() >= 3) {
				first = frames.get(index - 2);

				if (first.isStrike() && previous.isStrike()) {
					if (current.isStrike()) {
						first.setScore(30);
						return;
					}

					first.setScore(20 + current.getFirstRoll());
					previous.setScore(20 + current.getFirstRoll());
					return;
				}
			}

			if ((first != null && !first.isStrike() && !current.isFullPointer()) || frames.size() == 2) {
				if (previous.isStrike()) {
					previous.setScore(10 + current.getScore());
					return;
				}

				if (previous.isSpare()) {
					previous.setScore(10 + current.getFirstRoll());
					return;
				}
			}
		}

	}
}