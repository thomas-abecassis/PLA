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
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import info3.game.graphics.GameCanvasListener;

public class CanvasListener implements GameCanvasListener {
  Game m_game;
  public boolean key16 = false;
  public boolean key32 = false;
  public boolean key37 = false;
  public boolean key38 = false;
  public boolean key39 = false;
  public boolean key40 = false;
  public int mouseX = 0;
  public int mouseY = 0;
  public boolean mouseClicked = false;

  CanvasListener(Game game) {
    m_game = game;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    System.out.println("Mouse clicked: ("+e.getX()+","+e.getY()+")");
    System.out.println("   modifiers="+e.getModifiersEx());
    System.out.println("   buttons="+e.getButton());
  }

  @Override
  public void mousePressed(MouseEvent e) {
    System.out.println("Mouse pressed: ("+e.getX()+","+e.getY()+")");
    System.out.println("   modifiers="+e.getModifiersEx());
    System.out.println("   buttons="+e.getButton());
    mouseClicked = true;
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    System.out.println("Mouse released: ("+e.getX()+","+e.getY()+")");
    System.out.println("   modifiers="+e.getModifiersEx());
    System.out.println("   buttons="+e.getButton());
    mouseClicked = false;
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    System.out.println("Mouse entered: ("+e.getX()+","+e.getY()+")");
    System.out.println("   modifiers="+e.getModifiersEx());
    System.out.println("   buttons="+e.getButton());
  }

  @Override
  public void mouseExited(MouseEvent e) {
    System.out.println("Mouse exited: ("+e.getX()+","+e.getY()+")");
    System.out.println("   modifiers="+e.getModifiersEx());
    System.out.println("   buttons="+e.getButton());
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    System.out.println("Mouse dragged: ("+e.getX()+","+e.getY()+")");
    System.out.println("   modifiers="+e.getModifiersEx());
    System.out.println("   buttons="+e.getButton());
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    System.out.println("Mouse moved: ("+e.getX()+","+e.getY()+")");
    System.out.println("   modifiers="+e.getModifiersEx());
    System.out.println("   buttons="+e.getButton());
    mouseX = e.getX();
    mouseY = e.getY();
  }

  @Override
  public void keyTyped(KeyEvent e) {
    System.out.println("Key typed: "+e.getKeyChar()+" code="+e.getKeyCode());
  }

  @Override
  public void keyPressed(KeyEvent e) {
	  if(e.getKeyCode()==16)
		  key16 = true;
	  if(e.getKeyCode()==32)
		  key32 = true;
	  if(e.getKeyCode()==37)
		  key37 = true;
	  if(e.getKeyCode()==38)
		  key38 = true;
	  else if(e.getKeyCode()==39)
		  key39 = true;
	  else if(e.getKeyCode()==40)
		  key40 = true;
	  else if(e.getKeyCode()==65)
		  m_game.m_camera.startShake();
    System.out.println("Key pressed: "+e.getKeyChar()+" code="+e.getKeyCode());
  }

  @Override
  public void keyReleased(KeyEvent e) {
	  if(e.getKeyCode()==16)
		  key16 = false;
	  if(e.getKeyCode()==32)
		  key32 = false;
	  if(e.getKeyCode()==37)
		  key37 = false;
	  if(e.getKeyCode()==38)
		  key38 = false;
	  else if(e.getKeyCode()==39)
		  key39 = false;
	  else if(e.getKeyCode()==40)
		  key40 = false;
    System.out.println("Key released: "+e.getKeyChar()+" code="+e.getKeyCode());
  }

  @Override
  public void tick(long elapsed) {
    m_game.tick(elapsed);
  }

  @Override
  public void paint(Graphics g) {
    m_game.paint(g);
  }

  @Override
  public void windowOpened() {
    m_game.loadMusic();
//    m_game.m_canvas.setTimer(6000);
  }

  @Override
  public void exit() {
  }

//  boolean m_expired;
  @Override
  public void endOfPlay(String name) {
//    if (!m_expired) // only reload if it was a forced reload by timer
      m_game.loadMusic();
//    m_expired = false;
  }

  @Override
  public void expired() { 
    // will force a change of music, after 6s of play
//    System.out.println("Forcing an ealy change of music");
//    m_expired = true;
//    m_game.loadMusic();    
  }

}
