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
		Frame last = frames.get(index);

		if (!last.isFullPointer()) {
			last.setScore(last.getSumOfRolles());
		}

		if (frames.size() >= 2) {
			Frame previous = frames.get(index - 1);

			if (frames.size() >= 3) {
				Frame antepenultimate = frames.get(index - 2);

				if (antepenultimate.isFullPointer() && previous.isFullPointer()) {
					if (last.isStrike()) {
						antepenultimate.setScore(30);
						return;
					}

					antepenultimate.setScore(20 + last.getFirstRoll());
					if (last.getSumOfRolles() != null) {
						previous.setScore(10 + last.getSumOfRolles());
					}
					return;
				}
			}

			if (previous.isStrike() && !last.isStrike()) {
				if (last.getSumOfRolles() != null) {
					previous.setScore(10 + last.getSumOfRolles());
				}
				return;
			}

			if (previous.isSpare() && !last.isFullPointer()) {
				previous.setScore(10 + last.getFirstRoll());
				return;
			}

			if (previous.isSpare() && last.isFullPointer()) {
				previous.setScore(20);
			}
		}
	}
}