package imageviewer.ui.swing;

import static imageviewer.ui.ImageScaler.*;

import imageviewer.model.Image;
import imageviewer.ui.ImageDisplay;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private Image image;

    @Override
    public void paint(Graphics g) {
        BufferedImage bitmap = bitmap();
        Scale scale = scale(dimension(bitmap.getWidth(), bitmap.getHeight()), dimension(this.getWidth(), this.getHeight()));
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(bitmap, scale.getX(), scale.getY(), scale.getWidth(), scale.getHeight(), null);
    }

    @Override
    public void display(Image image) {
        this.image = image;
        this.repaint();
    }

    private BufferedImage bitmap() {
        try {
            return ImageIO.read(new ByteArrayInputStream(image.bitmap()));
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public Image currentImage() {
        return image;
    }
    
}