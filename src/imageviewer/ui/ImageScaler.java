package imageviewer.ui;

public class ImageScaler {
    
    public static Scale scale(Dimension image, Dimension screen) {
        if (isImageShorter(image,screen)) return centerImage(image,screen);
        if (requireScaleHeight(image,screen)) return scaleHeight(image,screen);
        if (requireScaleWidth(image,screen)) return scaleWidth(image,screen);
        return new Scale(0, 0, screen.width, screen.height);
    }
    
    public static Dimension dimension(int width, int height) {
        return new Dimension(width, height);
    }

    private static boolean isImageShorter(Dimension image, Dimension screen) {
        return image.width < screen.width && image.height < screen.height;
    }

    private static boolean requireScaleHeight(Dimension image, Dimension screen) {
        return image.width * 1.0 / screen.width > image.height * 1.0 / screen.height;
    }

    private static boolean requireScaleWidth(Dimension image, Dimension screen) {
        return image.width * 1.0 / screen.width < image.height * 1.0 / screen.height;
    }

    private static Scale centerImage(Dimension image, Dimension screen) {
        return new Scale((screen.width-image.width)/2, (screen.height-image.height)/2, image.width, image.height);
    }

    private static Scale scaleHeight(Dimension image, Dimension screen) {
        int h = (int) (screen.width * image.height * 1.0 / image.width);
        int y = (screen.height - h) / 2;
        return new Scale(0, y, screen.width, h);
    }

    private static Scale scaleWidth(Dimension image, Dimension screen) {
        int w = (int) (screen.height * image.width * 1.0 / image.height);
        int x = (screen.width - w) / 2;
        return new Scale(x, 0, w, screen.height);
    }
    
    public static class Scale {
        private final int x;
        private final int y;
        private final int width;
        private final int height;

        public Scale(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
        
    }
    
    public static class Dimension {
        private final int width;
        private final int height;

        public Dimension(int width, int height) {
            this.width = width;
            this.height = height;
        }
        
    }
    
}
