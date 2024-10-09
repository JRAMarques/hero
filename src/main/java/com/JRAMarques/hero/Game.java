package com.JRAMarques.hero;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class Game {
    private final Screen screen;

    public Game() throws IOException {
        TerminalSize terminalSize = new TerminalSize(40, 20);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();

        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
    }

    private int x=10;
    private int y=10;

    private void draw() throws IOException{
        screen.clear();
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
        screen.refresh();
    }

    private void processKey(KeyStroke key) throws IOException
    {
        //System.out.println(key);
        switch (key.getKeyType()) {
            case ArrowUp:
                y--;
                break;
            case ArrowDown:
                y++;
                break;
            case ArrowLeft:
                x--;
                break;
            case ArrowRight:
                x++;
                break;
            default:
                break;
        }
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