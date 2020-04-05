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
			last.setScore(last.getSumOfRolls());
		}

		if (frames.size() >= 2) {
			Frame previous = frames.get(index - 1);

			if (frames.size() >= 3) {
				Frame antepenultimate = frames.get(index - 2);

				if (antepenultimate.isStrike() && previous.isStrike()) {
					if (last.isStrike()) {
						antepenultimate.setScore(30);
						return;
					}

					antepenultimate.setScore(20 + last.getFirstRoll());
					if (last.getSumOfRolls() != null) {
						previous.setScore(10 + last.getSumOfRolls());
					}
					return;
				}
			}

			if (previous.isStrike() && !last.isStrike()) {
				if (last.getSumOfRolls() != null) {
					previous.setScore(10 + last.getSumOfRolls());
				}
				return;
			}

			if (previous.isSpare() && last.isStrike()) {
				previous.setScore(20);
				return;
			}

			if (previous.isSpare() && !last.isFullPointer()) {
				previous.setScore(10 + last.getFirstRoll());
				return;
			}

			if (index == 9) {
				if (previous.isStrike() && last.isClosed()) {
					previous.setScore(10 + last.getSumOfRolls());
				}

				if (last.isClosed()) {
					last.setScore(last.getSumOfRolls());
				}

			}
		}
	}
}