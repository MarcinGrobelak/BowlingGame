package com.mgrobelak.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.mgrobelak.game.GameHandler;

/**
 * @author Marcin Grobelak
 */

@Named
@ViewScoped
public class ViewBean implements Serializable {

	private static final long serialVersionUID = -4273338410365761268L;

	private GameHandler game;

	@PostConstruct
	private void init() {
		game = new GameHandler();
	}

	@PreDestroy
	public void destroy() {
		game.clear();
		game = null;
	}

	public void roll(int roll) {
		game.roll(roll);
	}

	public GameHandler getGame() {
		return game;
	}
}
