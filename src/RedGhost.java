import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class RedGhost extends Ghost {

    BFSFinder bfs;

    public RedGhost(int x, int y,PacBoard pb){
        super(x,y,pb,12);
    }

    @Override
    public void loadImages(){
        ghostR = new Image[2];
        ghostL = new Image[2];
        ghostU = new Image[2];
        ghostD = new Image[2];
        try {
            ghostR[0] = ImageIO.read(new FileInputStream("red1.png"));
            ghostR[1] = ImageIO.read(new FileInputStream("red3.png"));
            ghostL[0] = ImageHelper.flipHor(ImageIO.read(new FileInputStream("red1.png")));
            ghostL[1] = ImageHelper.flipHor(ImageIO.read(new FileInputStream("red3.png")));
            ghostU[0] = ImageIO.read(new FileInputStream("red4.png"));
            ghostU[1] = ImageIO.read(new FileInputStream("red5.png"));
            ghostD[0] = ImageIO.read(new FileInputStream("red6.png"));
            ghostD[1] = ImageIO.read(new FileInputStream("red7.png"));
        }catch(IOException e){
            System.err.println("Cannot Read Images !");
        }
    }

    moveType pendMove = moveType.UP;

    @Override
    public moveType getMoveAI(){
        if(isPending){
            if(isStuck){
                if(pendMove == moveType.UP)
                {
                    pendMove = moveType.DOWN;
                }
                else if(pendMove == moveType.DOWN)
                {
                    pendMove = moveType.UP;
                }
                return pendMove;
            }
            else
            {
                return pendMove;
            }
        }
        if(bfs==null)
            bfs = new BFSFinder(parentBoard);
        if(isDead) {
            return baseReturner.getMove(logicalPosition.x,logicalPosition.y, parentBoard.ghostBase.x,parentBoard.ghostBase.y);
        }else{
            return bfs.getMove(logicalPosition.x,logicalPosition.y,parentBoard.pacman.logicalPosition.x,parentBoard.pacman.logicalPosition.y);
        }
    }


}