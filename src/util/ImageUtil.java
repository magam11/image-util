package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtil {


    /**
     * երբ տրվում է նկարի path-ը և ըստ դրա վերդարձվում է նկարի BufferedImage-ը,
     */
    public static BufferedImage loadImage(String ref) {
        BufferedImage bimg = null;
        try {

            bimg = ImageIO.read(new File(ref));
            /**
             * կամ վերևի տողը կարող է փոխարինվել հետևյալով*/
            // Загружаем bimg
//            BufferedImage bimg = ImageUtil.loadImage ( "C: /Images/duke.gif" );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bimg;
    }

    /**
     * վերադարձնում է հայելային արտապատկերը, այսինքն  վերտիկալի նկատմամբ պտտված
     */
    public static BufferedImage verticalflip(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = dimg = new BufferedImage(w, h, img.getColorModel().getTransparency());
        Graphics2D g = dimg.createGraphics();
        g.drawString("barev", 10,10);
        g.drawImage(img, 0, 0, w, h, 0, h, w, 0, null);
        g.dispose();
        return dimg;
    }

    /**
     * վերադարձնում է հայելային արտապատկերը, այսինքը հորիզոնականի նկատմամբ պտտված
     */
    public static BufferedImage horizontalflip(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(w, h, img.getType());
        Graphics2D g = dimg.createGraphics();
        g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);
        g.dispose();
        return dimg;
    }

    /**
     * նկարիր պտտում ըստ տրված անկյան
    * */
    public static BufferedImage rotate(BufferedImage img, int angle) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = dimg = new BufferedImage(w, h, img.getType());
        Graphics2D g = dimg.createGraphics();
        g.rotate(Math.toRadians(angle), w/2, h/2);
        g.drawImage(img, null, 0, 0);
        return dimg;
    }

    /**
     * նկարի չասփսեի ֆիքսումը ըստ տրված երկարություն և լայնության
     * նկարի դեֆորմացիայի հաշվին է կատարվում նշված ձևափոխությունը
     */
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = dimg = new BufferedImage(newW, newH, img.getType());
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return dimg;
    }

    /**
     * Նկարի բաժանումը մասերի ըստ տրված սյուների և տողերի թվի
     */
    public static BufferedImage[] splitImage(BufferedImage img, int cols, int rows) {
        int w = img.getWidth()/cols;
        int h = img.getHeight()/rows;
        int num = 0;
        BufferedImage imgs[] = new BufferedImage[w*h];
        for(int y = 0; y < rows; y++) {
            for(int x = 0; x < cols; x++) {
                imgs[num] = new BufferedImage(w, h, img.getType());
                // Tell the graphics to draw only one block of the image
                Graphics2D g = imgs[num].createGraphics();
                g.drawImage(img, 0, 0, w, h, w*x, h*y, w*x+w, h*y+h, null);
                g.dispose();
                num++;
            }
        }
        return imgs;
    }


    /**
     *նկարի քառակուսի կտրելը կենտրոնի նկատմամբ
     */
    private static BufferedImage cropImageSquare(byte[] image) throws IOException {
        // Get a BufferedImage object from a byte array
        InputStream in = new ByteArrayInputStream(image);
        BufferedImage originalImage = ImageIO.read(in);

        // Get image dimensions
        int height = originalImage.getHeight();
        int width = originalImage.getWidth();

        // The image is already a square
        if (height == width) {
            return originalImage;
        }

        // Compute the size of the square
        int squareSize = (height > width ? width : height);

        // Coordinates of the image's middle
        int xc = width / 2;
        int yc = height / 2;

        // Crop
        BufferedImage croppedImage = originalImage.getSubimage(
                xc - (squareSize / 2), // x coordinate of the upper-left corner
                yc - (squareSize / 2), // y coordinate of the upper-left corner
                squareSize,            // width
                squareSize             // height
        );

        return croppedImage;
    }


 /**
  * օգտագործելով  scaleImage-ը և արդյունքը փոխանցելով resize-ին կարող ենք նկարի որակը քցել՝ լղոզելով, արդյունքում փոքրացնելով նկարի ծավալը
  */
    private static BufferedImage scaleImage(BufferedImage bufferedImage, int size) {
        double boundSize = size;
        int origWidth = bufferedImage.getWidth();
        int origHeight = bufferedImage.getHeight();
        double scale;
        if (origHeight > origWidth)
            scale = boundSize / origHeight;
        else
            scale = boundSize / origWidth;
        //* Don't scale up small images.
        if (scale > 1.0)
            return (bufferedImage);
        int scaledWidth = (int) (scale * origWidth);
        int scaledHeight = (int) (scale * origHeight);
        Image scaledImage = bufferedImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        // new ImageIcon(image); // load image
        // scaledWidth = scaledImage.getWidth(null);
        // scaledHeight = scaledImage.getHeight(null);
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = scaledBI.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(scaledImage, 0, 0, null);
        g.dispose();
        return (scaledBI);
    }

    public static void main(String[] args) throws IOException {
//        BufferedImage bufferedImage = ImageUtil.loadImage ( "C: /java/image/user_pictures/es.jpg" );
        BufferedImage bufferedImage = ImageUtil.loadImage("C:\\java\\image\\user_pictures\\es.jpg");
        BufferedImage scaleImage = scaleImage(bufferedImage,500);
        BufferedImage resize = resize(scaleImage, bufferedImage.getHeight(), bufferedImage.getWidth());
        String path = "C:\\java\\image\\user_pictures\\" + System.currentTimeMillis() + ".jpg";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        ImageIO.write(resize, "jpg", file);
    }


}
