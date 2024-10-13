package com.JRAMarques.hero;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;

public class Arena {
    private int width_;
    private int height_;
    private Hero hero;

    public Arena(int height, int width){
        this.height_=height;
        this.width_=width;
        this.hero= new Hero(10, 10);
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
        if (newPosition.getX()>=0 && newPosition.getX() < width_ && newPosition.getY()>=0 && newPosition.getY()<height_){
            hero.setPosition(newPosition);
        }
    }


    public void moveHero(Position position){
        hero.setPosition(position);
    }

    public void draw(Screen screen) throws IOException{
        screen.clear();
        hero.draw(screen);
        screen.refresh();
    }

}
