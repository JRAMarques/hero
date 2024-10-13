package com.JRAMarques.hero;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int width_;
    private int height_;
    private final Hero hero;
    private List<Wall> walls;

    public Arena(int height, int width){
        this.height_=height;
        this.width_=width;
        this.hero= new Hero(10, 10);
        this.walls = createWalls();
    }
    public void setHeight_(int height_) {
        this.height_ = height_;
    }
    public void setWidth_(int width_) {
        this.width_ = width_;
    }
    public int getHeight_() {
        return height_;
    }
    public int getWidth_() {
        return width_;
    }

    private List<Wall> createWalls(){
        List<Wall> walls = new ArrayList<>();
        for (int c=0; c<width_; c++){
            walls.add(new Wall(c, 0));
            walls.add(new Wall (c, height_-1));
        }
        for (int r=1; r<height_-1; r++){
            walls.add(new Wall(0, r));
            walls.add(new Wall(width_-1, r));
        }   return walls;
    }

    public void processKey(KeyStroke key) throws IOException {
//        System.out.println(key);
            Position newPosition = hero.getPosition();
            switch (key.getKeyType()) {
                case ArrowUp:
                    newPosition = hero.moveUp();
                    break;
                case ArrowDown:
                    newPosition = hero.moveDown();
                    break;
                case ArrowLeft:
                    newPosition = hero.moveLeft();
                    break;
                case ArrowRight:
                    newPosition = hero.moveRight();
                    break;
                default:
                    break;
            }
            if (canHeroMove(newPosition)){
                hero.setPosition(newPosition);
            }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Position p = (Position) o;
        return hero.getPosition().getX() == p.getX() && hero.getPosition().getY() == p.getY(); }

    private boolean canHeroMove(Position newPosition){
        if (newPosition.getX()>=0 && newPosition.getX() < width_ && newPosition.getY()>=0 && newPosition.getY()<height_){
            for (Wall wall:walls){
                if (wall.getPosition().equals(newPosition)){
                    return false;
                }
            }
            return true;        }
        return false;
    }


    public void moveHero(Position position){
        hero.setPosition(position);
    }

    public void draw(TextGraphics graphics) throws IOException{
//        TextGraphics graphics = screen.newTextGraphics();
//        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
//        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(width_, height_), ' ');
//        screen.clear();
        for (Wall wall : walls){
            wall.draw(graphics);
        }
        hero.draw(graphics);

        Position heroPosition = hero.getPosition();
//        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width_ * 2, height_ * 2), ' ');
//        graphics.putString(new TerminalPosition(heroPosition.getX() * 2, heroPosition.getY() * 2), "\\/");
//        graphics.putString(new TerminalPosition(heroPosition.getX() * 2, heroPosition.getY() * 2 + 1), "/\\");
//        screen.refresh();

    }

}
