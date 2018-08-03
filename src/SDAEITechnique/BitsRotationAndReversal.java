/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAEITechnique;

/**
 *
 * @author Noname
 */
public class BitsRotationAndReversal 
    {
  
    
     public int Encryption_Bits(int pixel,int pjgPsw)
              {       
               int  red = (pixel & 0x00ff0000) >> 16;    // Mengambil warna merah dari pixel
               int  green = (pixel & 0x0000ff00) >> 8;   // Mengambil warna hijau dari pixel
               int  blue = pixel & 0x000000ff;           // Mengambil warna biru dari pixel
    
               String binerred= String.format("%8s", Integer.toBinaryString(red)).replace(" ", "0");
               String binergreen= String.format("%8s", Integer.toBinaryString(green)).replace(" ", "0");
               String binerblue= String.format("%8s", Integer.toBinaryString(blue)).replace(" ", "0");
            
               int redint= Integer.parseInt(Process_Rotation(binerred,pjgPsw), 2);
               int greenint=Integer.parseInt(Process_Rotation(binergreen,pjgPsw), 2);
               int blueint=Integer.parseInt(Process_Rotation(binerblue,pjgPsw), 2);
                      
               int rotpixel= ((redint & 0xff)<<16) | ((greenint & 0xff)<<8) | blueint & 0xff;
                  
               return rotpixel;
              }
    
    
     //-------------------------------------------------------------------------          
    
     
     private String Process_Rotation(String biner,int pjgPsw)
              {
                 char [] originalbiner = new char[biner.length()];
                 char [] temp=new char[biner.length()];
                 char [] tempbalik=new char[biner.length()];
                 int count=0,count2=0;
                 String binerrot ="";
    
                 for(int i=0; i<biner.length(); i++)
                    {
                      originalbiner[i]=biner.charAt(i);
                    }
    
                 
            if(pjgPsw==0)
              {
                for(int i=7; i>=0; i--)
                    {
                     temp[count]=originalbiner[i]; //Memisah kearray lain sesuai jumlah x
                     count++;
                    }
                
                for(int i=0; i<8; i++)
                    {
                    originalbiner[i]=temp[i]; //Memisah kearray lain sesuai jumlah x
                    }                
               }                      
            else
            {
                 for(int i=0; i<8; i++)
                    {
                        if(i<pjgPsw)
                          {
                           temp[i]=originalbiner[i]; //Memisah kearray lain sesuai jumlah x
                          }
                        else
                          {
                           originalbiner[i-pjgPsw]=originalbiner[i]; //indeks setelah x di masukan ke indeks awal dan seterusnya
                          }
                     }
    
                  for(int i=pjgPsw-1; i>=0; i--)
                     {
                         tempbalik[count2]=temp[i]; // indeks yang dipisah tadi di balik
                         count2++;
                     }
    
                  for(int i=8-pjgPsw; i<8; i++)
                     {
                          originalbiner[i]=tempbalik[count]; // indeks yang sudah di balik ..di satukan lagi dengan array awal 
                          count++;
                     }
            }
            
            
                  for(int i=0; i<8; i++)
                     {
                          binerrot=binerrot+originalbiner[i];
                     }
    
                  return binerrot;
                 
          }
     
     
     //-------------------------------------------------------------------------
     //-------------------------------------------------------------------------
     
     
     public int Decryption_Bits(int pixel,int pjgPsw)
            {
            int  red = (pixel & 0x00ff0000) >> 16;    // Mengambil warna merah dari pixel
            int  green = (pixel & 0x0000ff00) >> 8;   // Mengambil warna hijau dari pixel
            int  blue = pixel & 0x000000ff;           // Mengambil warna biru dari pixel

            String binerred= String.format("%8s", Integer.toBinaryString(red)).replace(" ", "0");
            String binergreen= String.format("%8s", Integer.toBinaryString(green)).replace(" ", "0");
            String binerblue= String.format("%8s", Integer.toBinaryString(blue)).replace(" ", "0");

            int redint= Integer.parseInt(Process_unRotation(binerred,pjgPsw), 2);
            int greenint=Integer.parseInt(Process_unRotation(binergreen,pjgPsw), 2);
            int blueint=Integer.parseInt(Process_unRotation(binerblue,pjgPsw), 2);

            int rotpixel= ((redint & 0xff)<<16) | ((greenint & 0xff)<<8) | blueint & 0xff;

            return rotpixel;
            }
    
    
    //--------------------------------------------------------------------------
     
     
    private String Process_unRotation(String biner,int pjgPsw)
            {
            char [] originalbiner = new char[biner.length()];
            char [] temp=new char[biner.length()];
            char [] tempbalik=new char[biner.length()];
            int count=0,count2=0;
            String binerrot ="";

            for(int i=0; i<biner.length(); i++)
                {
                    originalbiner[i]=biner.charAt(i);
                }


            if(pjgPsw==0)
                {

                for(int i=7; i>=pjgPsw; i--)
                {
                        temp[count]=originalbiner[i]; //Memisah kearray lain sesuai jumlah x
                        count++;
                }
                }
            else
                {

                for(int i=7; i>=8-pjgPsw; i--)
                    {
                            temp[count]=originalbiner[i]; //Memisah kearray lain sesuai jumlah x
                            count++;
                    }

                for(int i=pjgPsw; i<8; i++)
                    {
                        temp[i]=originalbiner[count2]; // indeks yang dipisah tadi di balik
                        count2++;
                    }

                }

            for(int i=0; i<8; i++)
                {
                    binerrot=binerrot+temp[i];
                }
            return binerrot;

            }
     
}
