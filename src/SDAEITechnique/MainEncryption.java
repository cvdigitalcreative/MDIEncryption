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
public class MainEncryption 
    {
        private BufferedImage image;
        private String key;
        private int width;
        private int height;
        private int panjangkey;
        private int cipher[][];
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
        
        
        
 // Proses Utama dari semua tahap       
        public void encrypt()
            {
                // System.out.println("Masuk proses encrypt");
                 
                 BitsRotationAndReversal obBits=new BitsRotationAndReversal();
                 ExtendedHillCipher obHill=new ExtendedHillCipher();
                 ModifiedMSARandomization obMsa=new ModifiedMSARandomization();
                 GeneratingUniqueCode UC=new GeneratingUniqueCode();
                 
                 width=image.getWidth();
                 height=image.getHeight();
                 
                 panjangkey=key.length()%7;
                 System.out.println("Panjang key : "+panjangkey);
                 cipher=new int[height][width];
    
                 for(int H=0; H<height; H++)
                    {
                        for(int W=0; W<width; W++)
                            {
                            //  cipher[H][W]=image.getRGB(W, H);
                              int pixel=image.getRGB(W, H);    
                              int  red = (pixel & 0x00ff0000) >> 16;    // Mengambil warna merah dari pixel
                              int  green = (pixel & 0x0000ff00) >> 8;   // Mengambil warna hijau dari pixel
                              int  blue = pixel & 0x000000ff;           // Mengambil warna biru dari pixel
                                                            
                              cipher[H][W]=obBits.Encryption_Bits(pixel, panjangkey); // Step 1
                              
                            //    System.out.println(cipher[H][W]);
                            }
            
                    }
              //  System.out.println("step 1 slesai");
                cipher=obHill.Encryption_Hill(cipher, height, width,key,sign); // Step 2
              //  System.out.println("step 2 slesai");
                int uniquecode=UC.GenerateUniqueCode(key);
                cipher=obMsa.Encryption_MSA(cipher, height, width,uniquecode ); // Step 3                
              //  System.out.println("step 3 slesai");
              //  System.out.println("Proses encrypt selesai");
              }
    
    
    
        
        
        
// Mengeset gambar cipher
        
         public BufferedImage get_cipher_image()
              {
    
                BufferedImage cipherimage=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                for(int H=0; H<height; H++)
                    {
                        for(int W=0; W<width; W++)
                            {
                                cipherimage.setRGB(W, H, cipher[H][W]);
                            }
                    }
    
                 return cipherimage;
               }
    
        
    }
