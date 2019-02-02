package main;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageTest {

    public static void main(String[] args) throws IOException {
        BufferedImage bufferedImage = cropeImage();
//        BufferedImage verticalflip = verticalflip(bufferedImage);
        String path = "C:\\Users\\muraz\\Desktop\\" + System.currentTimeMillis() + ".jpg";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        ImageIO.write(bufferedImage, "jpg", file);

    }

    private static BufferedImage cropeImage() throws IOException {
        BufferedImage originalImage = loadImage("C:\\Users\\muraz\\Desktop\\image.jpg");



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
        System.out.println("x coordinate of upper-left corner "+xc);
        System.out.println("y coordinate of upper-left corner "+yc);
        System.out.println("squarSize "+squareSize);

        // Crop
//        BufferedImage croppedImage = originalImage.getSubimage(
//                xc - (squareSize / 2), // x coordinate of the upper-left corner
//                yc - (squareSize / 2), // y coordinate of the upper-left corner
//                squareSize,            // width
//                squareSize             // height
//        );
//        Graphics2D graphics = croppedImage.createGraphics();
//        graphics.setFont(new Font("Arial",Font.BOLD,14));
//        graphics.drawString("fdasdfsdfdsfdfdfffsdfdsffsdfsdfds",xc - (squareSize / 2)+100,yc - (squareSize / 2)+100);
//        BufferedImage bufferedImageWiteImage = loadImage("C:\\Users\\muraz\\Desktop\\witeImage.jpg");
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ImageIO.write( bufferedImageWiteImage, "jpg", baos );
//        baos.flush();
//        byte[] WiteImageInByte = baos.toByteArray();
//        graphics.drawBytes(WiteImageInByte,0,WiteImageInByte.length,0,0);
//
//        graphics.setColor(Color.CYAN);
//        Graphics2D graphics = originalImage.createGraphics();
//        graphics.setColor(Color.BLUE);
//        graphics.setBackground(Color.YELLOW);
//        graphics.setFont(new Font("Arial",Font.BOLD,20));
//        graphics.fill(new Rectangle(500,740,1000,2000));
//        graphics.fill3DRect(500,740,1000,2000,false);
//        Graphics g = newImage.getGraphics();
        BufferedImage newImage = new BufferedImage(originalImage.getWidth()+2*25, originalImage.getHeight(), originalImage.getType());

        Graphics g = newImage.getGraphics();

        g.setColor(Color.red);
        g.fillRect(0,0,originalImage.getWidth()+2*25,originalImage.getHeight());
        g.drawImage(originalImage, 25, 0, null);
        g.dispose();

        return newImage;

    }

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
    public static BufferedImage verticalflip(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = dimg = new BufferedImage(w, h, img.getColorModel().getTransparency());
        Graphics2D g = dimg.createGraphics();
        g.drawString("barefdgdfffdgfdgffdgfddgfdgdgfgdfgfdgdfgdfgdfgdfgdfgdfgdfdddddddddddddddddddddddv", 370,350);
        g.drawImage(img, 0, 0, w, h, 0, h, w, 0, null);
        g.dispose();
        return dimg;
    }
}
