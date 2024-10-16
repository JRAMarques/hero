package com.JRAMarques.hero;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;
import java.io.PipedOutputStream;

public class Game {
    private final Screen screen;
//    private final Hero hero;
    private final Arena arena;

    public Game() throws IOException {
        TerminalSize terminalSize = new TerminalSize(40, 20);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();

        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        arena = new Arena(70, 40);
//        hero = new Hero(10, 10);
    }


    private void draw() throws IOException{
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(40, 20), ' ');
        arena.draw(graphics);
        screen.refresh();
//        screen.clear();
//        hero.draw(screen);
//        screen.refresh();
    }


    private void processKey(KeyStroke key) throws IOException
    {
//        System.out.println(key);
//        switch (key.getKeyType()) {
//            case ArrowUp:
//                moveHero(hero.moveUp());
//                break;
//            case ArrowDown:
//                moveHero(hero.moveDown());
//                break;
//            case ArrowLeft:
//                moveHero(hero.moveLeft());
//                break;
//            case ArrowRight:
//                moveHero(hero.moveRight());
//                break;
//            default:
//                break;
//        }
        arena.processKey(key);
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){
            screen.close();
        } if (key.getKeyType() == KeyType.EOF){
            screen.close();
            System.exit(0);
        }
    }

        public void run() throws IOException{
            while (true) {
                draw();
                KeyStroke key = screen.readInput();
                processKey(key);
            }
    }

    public static void main(String[] args){
        try{
            Game game = new Game();
            game.run();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}