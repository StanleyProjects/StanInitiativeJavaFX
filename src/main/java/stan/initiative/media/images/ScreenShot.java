package stan.initiative.media.images;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ScreenShot
{
    static private ScreenShot instance;
    static public ScreenShot getInstance()
    {
        if(instance == null)
        {
            instance = new ScreenShot();
        }
        return instance;
    }

    private String path;

    private ScreenShot()
    {
    }

    public void setPath(String p)
    {
        this.path = p;
    }

    public BufferedImage grabScreen(int x, int y, int w, int h)
    {
        try
        {
            return new Robot().createScreenCapture(new Rectangle(x, y, w, h));
        }
        catch(Exception e)
        {
        }
        return null;
    }
	
    public void saveScreen(BufferedImage bi)
    {
        saveScreen(bi, this.path, System.currentTimeMillis() + "");
    }
    public void saveScreen(BufferedImage bi, String path, String name)
    {
        try
        {
            ImageIO.write(bi, "png", new File(path + "/" + name + ".png"));
        }
        catch(Exception e)
        {
        }
    }
}