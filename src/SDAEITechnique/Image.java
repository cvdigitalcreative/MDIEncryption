/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAEITechnique;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Noname
 */
public class Image {
    BufferedImage image;
    public JLabel lblGambar;
    
    
    public void set_image(BufferedImage image)
    {
    this.image=image;
    }
    
    public void tampilkanImage() { 
       
        ImageIcon imageIcon = new ImageIcon(resize(image, 
        lblGambar.getWidth(), lblGambar.getHeight()));  
        lblGambar.setIcon(imageIcon); 
      } 
    
    
    public static BufferedImage resize(BufferedImage img, int newW, int newH) 
    { 


            int w = img.getWidth(); 
            int h = img.getHeight(); 
            BufferedImage dimg = dimg = new BufferedImage(newW, newH, img.getType()); 
            Graphics2D g = dimg.createGraphics(); 
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
            RenderingHints.VALUE_INTERPOLATION_BILINEAR); 
            g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null); 
            g.dispose(); 
            return dimg; 
        } 
    
    
    
    
    
}
