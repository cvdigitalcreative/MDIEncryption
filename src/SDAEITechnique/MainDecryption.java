/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAEITechnique;


import java.awt.image.BufferedImage;

/**
 *
 * @author Noname
 */
public class MainDecryption 
{
        private BufferedImage image;
        private String key;
        private int width;
        private int height;
        private int panjangkey;
        private int plain[][];
        private int plainx[][];
        private int sign;
    
        public void set_key(String key)
            {
            this.key=key;
            }
    
        public void set_image(BufferedImage image,int sign)
            {
            this.image=image;
            this.sign=sign;
            }
        
        
        public void decrypt()
            {
                GeneratingUniqueCode UC=new GeneratingUniqueCode();
                ModifiedMSARandomization obMSA=new ModifiedMSARandomization();
                ExtendedHillCipher obHill= new ExtendedHillCipher();
                BitsRotationAndReversal obBits=new BitsRotationAndReversal();
                             
                 width=image.getWidth();
                 height=image.getHeight();
                              
                 panjangkey=key.length()%7;
            //     System.out.println("Panjang key : "+panjangkey);
                 plain=new int[height][width];
                 for(int H=0; H<height; H++)
                    {
                        for(int W=0; W<width; W++)
                            {
                   
                              int pixel=image.getRGB(W, H);    
                              int  red = (pixel & 0x00ff0000) >> 16;    // Mengambil warna merah dari pixel
                              int  green = (pixel & 0x0000ff00) >> 8;   // Mengambil warna hijau dari pixel
                              int  blue = pixel & 0x000000ff;  
                                                             
                              
                        plain[H][W]= ((red & 0xff)<<16) | ((green & 0xff)<<8) | blue & 0xff;
                        
                        }
                    }
                int uniquecode=UC.GenerateUniqueCode(key);
                plain=obMSA.Decryption_MSA(plain, height, width,uniquecode ); 
                plain=obHill.Decryption_Hill(plain, height, width,key,sign);
               
              
                 for(int H=0; H<height; H++)
                    {
                    for(int W=0; W<width; W++)
                        {     
                        plain[H][W]=obBits.Decryption_Bits(plain[H][W], panjangkey);   
                        }
                    }                  
            }
           
        
        
        
       
        
        
         public BufferedImage get_plain_image()
              {
    
                BufferedImage plainimage=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                for(int H=0; H<height; H++)
                    {
                        for(int W=0; W<width; W++)
                            {
                                plainimage.setRGB(W, H, plain[H][W]);
                            }
                    }
    
                 return plainimage;
               }
}
