/*
 * Copyright (C) 2020  Pr. Olivier Gruber
 * Educational software for a basic game development
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Created on: March, 2020
 *      Author: Pr. Olivier Gruber
 */
package info3.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import info3.gameObjects.GameObject;
import info3.gameObjects.Transform;
import info3.managers.PaintManager;
import info3.managers.TickManager;

/**
 * A simple class that holds the images of a sprite for an animated cowbow.
 *
 */
public class Cowboy extends GameObject implements Paintable, Tickable {
	BufferedImage[] m_images;
	int m_imageIndex;
	long m_imageElapsed;
	long m_moveElapsed;
	long bulletElapsed;
	int m_width;
	boolean retour = false;
	float vitesseDeplacement = 2 * (float)Math.PI;
	float vitesseRotation = 1;

	Cowboy(int x, int y) throws IOException {
		super(x, y, 0, 48);
		m_images = loadSprite("resources/joueur.png", 1, 1);
		addBoxCollider(50, 50);
		addRigibody(10,1);
	}

	/*
	 * Simple animation here, the cowbow
	 */
	public void tick(long deltaTime) {
		rigibody.computeMovement(deltaTime);
		
		m_imageElapsed += deltaTime;
		bulletElapsed +=deltaTime;
		if (m_imageElapsed > 200) {
			m_imageElapsed = 0;
			m_imageIndex = (m_imageIndex + 1) % m_images.length;
		}
		
		move(Game.game.m_listener);
		
		if((Game.game.m_listener.key32 || Gamepad.instance.getLeftTrigger()) && bulletElapsed >50) {
			bulletElapsed = 0;
			createBullet();
		}
		
	}
	
	private void createBullet() {
		Bullet bullet = new Bullet((int)this.transform.getX(), (int)this.transform.getY(), this.transform.getRotation());
		PaintManager.instance.add(bullet);
		TickManager.instance.add(bullet);
	}
	
	public void move(CanvasListener canvasListener) {
		if(canvasListener.key37) {
			rigibody.setVelocityX(-200);
			transform.setRotation((float)Math.PI *0.5F);
		}
		if(canvasListener.key38) {
			rigibody.setVelocityY(-200);
			transform.setRotation((float)Math.PI );
		}
		if(canvasListener.key39) {
			rigibody.setVelocityX(200);
			transform.setRotation((float)Math.PI *1.5F);
		}
		if(canvasListener.key40) {
			rigibody.setVelocityY(200);
			transform.setRotation(0);
		}
		rigibody.setVelocityX(Gamepad.instance.getLeftStickPosition().x * 400);
		rigibody.setVelocityY(Gamepad.instance.getLeftStickPosition().y * 400);
		double angleInRadians = Math.atan2((double)Gamepad.instance.getRightStickPosition().y, (double)Gamepad.instance.getRightStickPosition().x);
		transform.setRotation((float)angleInRadians - (float)Math.PI/2);
		
	}

	public void paint(Graphics g, int width, int height, int cameraPositionX, int cameraPositionY) {
		m_width = width;
		BufferedImage img = m_images[m_imageIndex];
		int scale = 48;
		//g.drawImage(img, (int)(transform.getX() - cameraPositionX), (int)(transform.getY() - cameraPositionY), scale * img.getWidth(), scale * img.getHeight(),
		//		null);
		
		if(transform.getRotation()!=0) {
			Graphics2D g2 = (Graphics2D)g;
			AffineTransform old = g2.getTransform();
			g2.rotate(transform.getRotation(),transform.getX() - cameraPositionX + scale/2, transform.getY() - cameraPositionY + scale/2);
			g2.drawImage(img, (int)(transform.getX() - cameraPositionX), (int)(transform.getY()-cameraPositionY) ,img.getWidth(), img.getHeight(), null);
			g2.setTransform(old);
		}
		else {
		    g.drawImage(img, (int)(transform.getX() - cameraPositionX), (int)(transform.getY()-cameraPositionY) ,img.getWidth(), img.getHeight(), null);
		}
	}
	
	public String sauvegardeString() {
		return "C;"+(int)transform.getX()+";"+(int)transform.getY()+";";
	}

	public static BufferedImage[] loadSprite(String filename, int nrows, int ncols) throws IOException {
		File imageFile = new File(filename);
		if (imageFile.exists()) {
			BufferedImage image = ImageIO.read(imageFile);
			int width = image.getWidth(null) / ncols;
			int height = image.getHeight(null) / nrows;

			BufferedImage[] images = new BufferedImage[nrows * ncols];
			for (int i = 0; i < nrows; i++) {
				for (int j = 0; j < ncols; j++) {
					int x = j * width;
					int y = i * height;
					images[(i * ncols) + j] = image.getSubimage(x, y, width, height);
				}
			}
			return images;
		}
		return null;
	}

}
