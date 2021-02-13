package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.helpers.AssetManager;
import com.mygdx.game.screens.GameScreen;

//clase que controla pantallas del juego y carga recursos
public class SpaceRace extends Game {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		//cargamos recursos
		AssetManager.load();
		//seteamos la pantalla
		setScreen(new GameScreen());
	}

	
	@Override
	//se llama cuando se acaba el juego
	public void dispose () {
		super.dispose();
		//borrar los recursos utilizados
		AssetManager.dispose();
	}
}
