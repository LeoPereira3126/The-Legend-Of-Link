package Main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // Screen settings
    final int originalTileSize = 16; // 16*16 tile (Tamaño base de los personajes dentro del juego)
    final int scale = 3; // Multiplica la escala del personaje por 3

    public final int tileSize = originalTileSize * scale; // define a los personajes en una escala de 48*48
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    int FPS = 60;

    KeyHandler keyHandler = new KeyHandler();

    Thread gameThread;
    Player player = new Player(this,keyHandler);


    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    // delta loop
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;

            }
            if(timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;

            }
            // con este loop vamos a actualizar la informacion dentro del juego como la posicion del personaje
            // ademas vamos a dibujar la pantalla con la informacion actualizada
        }

    }
    public void update() {
        Player.update();

    }
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        Player.draw(g2);

        g2.dispose();
    }

}
