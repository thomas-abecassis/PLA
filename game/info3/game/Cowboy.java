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

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import info3.gameObjects.GameObject;
import info3.gameObjects.Transform;

/**
 * A simple class that holds the images of a sprite for an animated cowbow.
 *
 */
public class Cowboy extends GameObject implements Paintable {
	BufferedImage[] m_images;
	int m_imageIndex;
	long m_imageElapsed;
	long m_moveElapsed;
	int m_width;
	boolean retour = false;
	float vitesseDeplacement = 300;

	Cowboy() throws IOException {
		super(200, 200, 0, 1);
		m_images = loadSprite("resources/winchester-4x6.png", 4, 6);
	}

	/*
	 * Simple animation here, the cowbow
	 */
	public void tick(long deltaTime) {
		m_imageElapsed += deltaTime;
		if (m_imageElapsed > 200) {
			m_imageElapsed = 0;
			m_imageIndex = (m_imageIndex + 1) % m_images.length;
		}
		
		if (transform.getX() > m_width-50)
			retour = true;
		else if (transform.getX() < 0)
			retour = false;
		if (!retour)
			transform.translate(vitesseDeplacement*(deltaTime/(float)1000), 0); //divise par 1000 pour avoir le deltaTime en seconde, on a facilement le deplacement en pixel/seconde de cette manière
		else
			transform.translate(-vitesseDeplacement*(deltaTime/(float)1000), 0);
	}

	public void paint(Graphics g, int width, int height, int cameraPositionX, int cameraPositionY) {
		m_width = width;
		BufferedImage img = m_images[m_imageIndex];
		int scale = 2;
		g.drawImage(img, (int)(transform.getX() - cameraPositionX), (int)(transform.getY() - cameraPositionY), scale * img.getWidth(), scale * img.getHeight(),
				null);
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
