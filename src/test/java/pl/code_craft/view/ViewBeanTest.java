package pl.code_craft.view;

/**
 * @author Marcin Grobelak (code-craft.pl)
 */

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Test;

import pl.code_craft.frame.RegularFrame;

@EnableWeld
public class ViewBeanTest {

	@WeldSetup
	WeldInitiator weldInitiator = WeldInitiator.from(ViewBean.class).activate(ViewScoped.class).build();

	@Inject
	ViewBean view;

	@Test
	void varablesAreInitializedAfterCreation() {
		// then
		assertEquals(10, view.getPossiblePoints());
		assertFalse(view.isGameOver());
		assertNotNull(view.getCurrentFrame());
		assertThat(view.getFrames(), hasSize(1));
	}

	@Test
	void varablesAreCleanedAftedDestroy() {
		// given
		view.getFrames().add(new RegularFrame());
		// when
		view.destroy();
		// then
		assertNull(view.getCurrentFrame());
		assertNull(view.getFrames());
	}

	@Test
	void overallScoreShouldSummAllFramesScores() {
		// given
		generateFrames();
		// then
		assertEquals(38, view.getOverallScore());
		assertThat(view.getFrames(), hasSize(3));
	}

	private void generateFrames() {
		view.roll(10); // 20
		view.roll(8);
		view.roll(2); // 13
		view.roll(3);
		view.roll(2); // 5
	}

}
