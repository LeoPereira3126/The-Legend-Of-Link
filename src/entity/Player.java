package entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    static GamePanel gamePanel;
    static KeyHandler keyHandler;

    public Player (GamePanel gamePanel, KeyHandler keyHandler) {
        Player.gamePanel = gamePanel;
        Player.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();

        }
    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";

    }
    public void getPlayerImage () {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));

        }
        catch (IOException e) {
            e.printStackTrace();

        }
    }
    public static void update() {
        if (keyHandler.upPressed == true) {
            direction = "up";
            y -= speed;
        }
        else if (keyHandler.downPressed == true) {
            direction = "down";
            y += speed;
        }
        else if (keyHandler.leftPressed == true) {
            direction = "left";
            x -= speed;
        }
        else if (keyHandler.rightPressed == true) {
            direction = "right";
            x += speed;
        }

    }

    public static void draw(Graphics2D g2) {
       // g2.setColor(Color.WHITE);

       // g2.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
        BufferedImage image = null;

        switch (direction){
        case "up":
            image = up1;
            break;
        case "down":
            image = down1;
            break;
        case "left":
            image = left1;
            break;
        case "right":
            image = right1;
            break;
        }
        g2.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null );

    }
}
