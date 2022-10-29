package image_rgb;

import java.awt.*;
import java.awt.image.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import java.awt.event.*;


public class RGBImagePanel extends JPanel {
	
	int width, height;
	Image image = null;
	BufferedImage bufImage = null;
	
	public RGBImagePanel( Image image ){
		this.image = image;
		width = image.getWidth( this );
		height = image.getHeight( this );
		this.setSize( width , height );
		
		bufImage = createBufferedImage( image );
		
//		float min = 0.125f;
//		float max = 0.1667f;
		
		
		for ( int y = 0; y<bufImage.getHeight(); y++ ){
			for ( int x = 0; x<bufImage.getWidth(); x++ ){
				int color = bufImage.getRGB( x, y );
				int r = getRed( color );
				int g = getGreen( color );
				int b = getBlue( color );

				float[] hsb = Color.RGBtoHSB(r, g, b, null);
				
				if (hsb[0] < min || hsb[0] > max) {
					bufImage.setRGB(x, y, 0x000000);
				}
				
			}
		}
	}
	
	public BufferedImage createBufferedImage(Image img) {
		BufferedImage bimg = 
			new BufferedImage( img.getWidth(null),
			    		    img.getHeight(null), 
					     BufferedImage.TYPE_INT_RGB);

		Graphics g = bimg.getGraphics();
		g.drawImage(img, 0, 0, null);
		g.dispose();

		return bimg;
	}
	
	public int getRed( int color ){
		return color >> 16 & 0xff;
	}

	public int getGreen( int color ){
		return color >> 8 & 0xff;
	}

	public int getBlue( int color ){
		return color & 0xff;
	}
	
	public void paint( Graphics g ){
		g.drawImage( (Image)bufImage , 0 , 0 , this );
	}
	
//	public void saveHist(JFrame f) {
//		int[] hist = new int[256];
//		for ( int i = 0; i < 256; i++) hist[i] = 0;
//		for ( int h = 0; h < height; h++) {
//			for (int w = 0; w < width; w++) {
//				int index = getGreen(bufImage.getRGB(w, h));
//				hist[index]++;
//			}
//		}
//		
//		FileDialog l = new FileDialog(f, "save", FileDialog.SAVE);
//		l.setModal(true);
//		l.setVisible(true);
//		if(l.getFile() != null) {
//			try {
//				File file = new File(l.getFile());
//				BufferedWriter bufferedwriter = new BufferedWriter(new FileWriter(file));
//				String br = System.getProperty("line.separator");
//				for (int i = 0; i < 256; i++) {
//					String str = Integer.valueOf(i).toString() + ", " + Integer.valueOf(hist[i]).toString();
//					bufferedwriter.write(str + br);
//					System.out.println(str);
//				}
//				bufferedwriter.close();
//			} catch (IOException ev) {
//				ev.printStackTrace();
//			}
//		}
//	}
}
