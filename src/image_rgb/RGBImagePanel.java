package image_rgb;

import java.awt.*;
import java.awt.image.*;
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
		
		for ( int y = 0; y<bufImage.getHeight(); y++ ){
			for ( int x = 0; x<bufImage.getWidth(); x++ ){
				int color = bufImage.getRGB( x, y );
				int r = 255 - getRed( color );
				int g = 255 - getGreen( color );
				int b = 255 - getBlue( color );
				int greenColor = color & 0x00FF00;
				bufImage.setRGB(x,y, greenColor);
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
}
