package pl.code_craft.view;

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

@EnableWeld
public class ViewBeanTest {

	@WeldSetup
	WeldInitiator weldInitiator = WeldInitiator.from(ViewBean.class).activate(ViewScoped.class).build();

	@Inject
	ViewBean view;

	@Test
	void gameShouldBeInitializedAfterCreation() {
		// then
		assertEquals(10, view.getGame().getPointsToScore());
		assertFalse(view.getGame().isGameOver());
		assertNotNull(view.getGame().getCurrentFrame());
		assertEquals(1, view.getGame().getFrames().size());
	}

	@Test
	void gameShouldBeNullAftedDestroy() {
		// when
		view.destroy();
		// then
		assertNull(view.getGame());
	}

	@Test
	void rollSouldPassRollValueToGameHandler() {
		// when
		view.roll(5);
		// then
		assertEquals(5, view.getGame().getCurrentFrame().getFirstRoll());
	}

}
