/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAEITechnique;

/**
 *
 * @author Noname
 */
public class ExtendedHillCipher {
    private int [][] involutory=new int[4][4];
    private int [][] cipherhill;
    private int [][] RGBmatriks;
    private int [][] RGBmatrikscipher;
    private int [][] matrikstemp;
    
    
// Stage hill cipher utama    
    public int [][] Encryption_Hill(int [][]cipher , int height, int width, String key,int sign)
       {   
        GeneratingUniqueCode obcode=new GeneratingUniqueCode();
        InvolutoryMatrix obinv=new InvolutoryMatrix();
        
        int [][] reverseOrder = null;
        double tiga=3;
        double empat=4;
        int temp = (int) Math.ceil(((Double.valueOf(height) * Double.valueOf(width))*tiga)/empat);
        
        cipherhill=new int[height][width];
        RGBmatriks=new int[4][temp];
        RGBmatrikscipher=new int[4][temp];
        matrikstemp=new int[4][temp];
        
        if(sign==0)
        {
        reverseOrder=reverse_orderEncryption(cipher, height, width);
        }
        else if(sign==1)
        {
        reverseOrder=reverse_orderEncryptionMod(cipher, height, width);
        }
        
        extraksi4x4(reverseOrder, height, width ,temp);
        
        obcode.generating4key(key);
        int bil1=obcode.getbil1();
        int bil2=obcode.getbil2();
        int bil3=obcode.getbil3();
        int bil4=obcode.getbil4();
        
        involutory=obinv.Generate_KeyMatriks(bil1,bil2,bil3,bil4);
               
        for(int i=0; i<4; i++)
                     {
                    for(int j=0; j<temp; j++)
                            {

                            for(int k=0; k<4; k++)
                                {
                                matrikstemp[i][j]+=involutory[i][k]*RGBmatriks[k][j];
                                }
                            }
       
                    }
            
          for(int i=0; i<4; i++)
                     {
                    for(int j=0; j<temp; j++)
                           {
                                RGBmatrikscipher[i][j]=matrikstemp[i][j]%256;    
                           }
       
                     }
              
        cipherhill=extraksi3x3(RGBmatrikscipher, height, width, temp);
            
        return cipherhill;
    }
    
 
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    
    
    public int [][] Decryption_Hill(int [][]cipher , int height, int width, String key,int sign)
       {   
        GeneratingUniqueCode obcode=new GeneratingUniqueCode();
        InvolutoryMatrix obinv=new InvolutoryMatrix();
        
        double tiga=3;
        double empat=4;
        int temp = (int) Math.ceil(((Double.valueOf(height) * Double.valueOf(width))*tiga)/empat);
        
        cipherhill=new int[height][width];
        RGBmatriks=new int[4][temp];
        RGBmatrikscipher=new int[4][temp];
        matrikstemp=new int[4][temp];
        
        extraksi4x4(cipher, height, width ,temp);
        obcode.generating4key(key);
        int bil1=obcode.getbil1();
        int bil2=obcode.getbil2();
        int bil3=obcode.getbil3();
        int bil4=obcode.getbil4();
        involutory=obinv.Generate_KeyMatriks(bil1,bil2,bil3,bil4);
        
        
        for(int i=0; i<4; i++)
                     {
                    for(int j=0; j<temp; j++)
                            {

                            for(int k=0; k<4; k++)
                                {
                                matrikstemp[i][j]+=involutory[i][k]*RGBmatriks[k][j];
                                }
                            }
       
                    }
     
        
        
          for(int i=0; i<4; i++)
                     {
                    for(int j=0; j<temp; j++)
                           {
                                RGBmatrikscipher[i][j]=matrikstemp[i][j]%256;    
                           }
       
                     }
        
          
       
        
        cipherhill=extraksi3x3(RGBmatrikscipher, height, width, temp);
        
        if(sign==0)
        {
        cipherhill=reverse_orderDecryption(cipherhill, height, width);
        }
        else if(sign==1)
        {
        cipherhill=reverse_orderDecryptionMod(cipherhill, height, width);
        }
        
        
        return cipherhill;
    }
    
    
  // Mengubah nilai pixel RGB menjadi empat baris-------------------------------
    
    
    private void extraksi4x4(int [][] cipher,int height, int width ,int temp)
    {
        int y=0;
        int z=0;
      
    for(int H=0; H<height; H++)
       {
        for(int W=0; W<width; W++)
            {                              
              int  red = (cipher[H][W] & 0x00ff0000) >> 16;   
              int  green = (cipher[H][W] & 0x0000ff00) >> 8; 
              int  blue = cipher[H][W] & 0x000000ff;  
                                                            
              if(z>=temp)
                {
                 y++;
                 z=0;
                }                               
                               
              RGBmatriks[y][z]=red;
              z++;
                               
              if(z>=temp)
                {
                  y++;
                  z=0;
                }
                               
              RGBmatriks[y][z]=green;
                  z++;
                               
              if(z>=temp)
                {
                  y++;
                  z=0;
                }
                               
              RGBmatriks[y][z]=blue;
                  z++;
                                                        
            }
        }            
    }
    
        
// Mengembalikan nilai RGB menjadi tiga baris-----------------------------------   
    
    
    private int [][] extraksi3x3(int [][] RGBmatrikscipher,int height,int width,int temp)
        {
                int panjangRGB=height*width*3;    
                int [] linear=new int[panjangRGB+1];
                int [] lineartemp= new int[height*width];
                int [][] result=new int[height][width];
                int x=0;

            for(int i=0; i<4; i++)
                {
                for(int j=0; j<temp; j++)
                    {
                    linear[x]=RGBmatrikscipher[i][j];   
                    x++;
                    }
                }

            int z=0;
            for(int i=0; i<height*width; i++)
                {
                lineartemp[i]=((linear[z] & 0xff)<<16)| ((linear[z+1] & 0xff)<<8) | linear[z+2] & 0xff;
                z=z+3;        
                }

            int count=0;
            for(int H=0; H<height; H++ )
                {
                for(int W=0; W<width; W++)
                    {
                    result[H][W]=lineartemp[count];
                    count++;
                    }
                }

            return result;

     }
    
    
 //-----------------------------------------------------------------------------   
    
       
    private int [][] reverse_orderEncryption(int [][] cipher, int height, int width)
        {    
            int count;
            int array;
    
            if(height>width)
               {
                array=height;
               }
            else
               {
                array=width;
               }
    
            char []tampung=new char[array*24];
    
  
            for(int i=0; i<height; i++)
               {
                count=0;
                for(int j=0; j<width; j++)
                    {
                    String biner= String.format("%24s", Integer.toBinaryString(cipher[i][j])).replace(" ", "0");

                        for(int x=0; x<24; x++)
                            {
                            tampung[count]=biner.charAt(x);
                            count++;
                            }               
                    }
                
         
               
               for(int j=0; j<width; j++)
                    {
                    String binerafter="";    
                    for(int x=0; x<24; x++)
                        {
                        binerafter=binerafter+tampung[count-1];
                        count--;
                        }
                
                     cipher[i][j]= Integer.parseInt(binerafter, 2);        
                    }  
                } 
    

            
            
 //--
                for(int j=0; j<width; j++)
                    {
                    count=0;
                    for(int i=0; i<height; i++)
                        {
                        String biner= String.format("%24s", Integer.toBinaryString(cipher[i][j])).replace(" ", "0");

                        for(int x=0; x<24; x++)
                            {
                            tampung[count]=biner.charAt(x);
                            count++;
                            }               
                        }
                
             
               
                    for(int i=0; i<height; i++)
                        {
                        String binerafter="";    
                        for(int x=0; x<24; x++)
                            {
                            binerafter=binerafter+tampung[count-1];
                            count--;
                            }
                
                        cipher[i][j]= Integer.parseInt(binerafter, 2);        
                        }  
                    }
    
    //--
    return cipher;
  }
    
    
    //--------------------------------------------------------------------------
    
    
    private int[][]reverse_orderDecryption(int [][] cipher, int height, int width)
    {
    int count;
    int array;
    if(height>width)
    {
    array=height;
    }
    else
    {
    array=width;
    }
    char []tampung=new char[array*24];

//--
     for(int j=0; j<width; j++)
    {
                count=0;
                for(int i=0; i<height; i++)
                    {
                    String biner= String.format("%24s", Integer.toBinaryString(cipher[i][j])).replace(" ", "0");

                        for(int x=0; x<24; x++)
                            {
                            tampung[count]=biner.charAt(x);
                            count++;
                            }               
                    }
                
             
               
               for(int i=0; i<height; i++)
                    {
                    String binerafter="";    
                    for(int x=0; x<24; x++)
                        {
                        binerafter=binerafter+tampung[count-1];
                        count--;
                        }
                
                     cipher[i][j]= Integer.parseInt(binerafter, 2);
                
         
                    }  
            }
    
  
 
 
//--
     
     
     
    for(int i=0; i<height; i++)
        {
                count=0;
                for(int j=0; j<width; j++)
                    {
                    String biner= String.format("%24s", Integer.toBinaryString(cipher[i][j])).replace(" ", "0");

                        for(int x=0; x<24; x++)
                            {
                            tampung[count]=biner.charAt(x);
                            count++;
                            }               
                    }
                
             
               
               for(int j=0; j<width; j++)
                    {
                    String binerafter="";    
                    for(int x=0; x<24; x++)
                        {
                        binerafter=binerafter+tampung[count-1];
                        count--;
                        }
                
                     cipher[i][j]= Integer.parseInt(binerafter, 2);
                
         
                    } 
    
  
        } 
    

         return cipher;
        }




//-----------------------------------------------------------------------------
//----------------------------Modifikasi---------------------------------------

private int [][] reverse_orderEncryptionMod(int [][] cipher, int height, int width)
        {    
            int count;
            int array;
    
            if(height>width)
               {
                array=height;
               }
            else
               {
                array=width;
               }
    
            char []tampung=new char[array*24];
    
  
            for(int i=0; i<height; i++)
               {
                count=0;
                for(int j=0; j<width; j++)
                    {
                    String biner= String.format("%24s", Integer.toBinaryString(cipher[i][j])).replace(" ", "0");

                        for(int x=0; x<24; x++)
                            {
                            tampung[count]=biner.charAt(x);
                            count++;
                            }               
                    }
                
         
               
               for(int j=0; j<width; j++)
                    {
                    String binerafter="";    
                    for(int x=0; x<24; x++)
                        {
                        binerafter=binerafter+tampung[count-1];
                        count--;
                        }
                
                     cipher[i][j]= Integer.parseInt(binerafter, 2);        
                    }  
                } 
    

            
            
 /*
                for(int j=0; j<width; j++)
                    {
                    count=0;
                    for(int i=0; i<height; i++)
                        {
                        String biner= String.format("%24s", Integer.toBinaryString(cipher[i][j])).replace(" ", "0");

                        for(int x=0; x<24; x++)
                            {
                            tampung[count]=biner.charAt(x);
                            count++;
                            }               
                        }
                
             
               
                    for(int i=0; i<height; i++)
                        {
                        String binerafter="";    
                        for(int x=0; x<24; x++)
                            {
                            binerafter=binerafter+tampung[count-1];
                            count--;
                            }
                
                        cipher[i][j]= Integer.parseInt(binerafter, 2);        
                        }  
                    }
    
    */
    return cipher;
  }
    
    
    //--------------------------------------------------------------------------
    
    
    private int[][]reverse_orderDecryptionMod(int [][] cipher, int height, int width)
    {
    int count;
    int array;
    if(height>width)
    {
    array=height;
    }
    else
    {
    array=width;
    }
    char []tampung=new char[array*24];

/*
     for(int j=0; j<width; j++)
    {
                count=0;
                for(int i=0; i<height; i++)
                    {
                    String biner= String.format("%24s", Integer.toBinaryString(cipher[i][j])).replace(" ", "0");

                        for(int x=0; x<24; x++)
                            {
                            tampung[count]=biner.charAt(x);
                            count++;
                            }               
                    }
                
             
               
               for(int i=0; i<height; i++)
                    {
                    String binerafter="";    
                    for(int x=0; x<24; x++)
                        {
                        binerafter=binerafter+tampung[count-1];
                        count--;
                        }
                
                     cipher[i][j]= Integer.parseInt(binerafter, 2);
                
         
                    }  
            }
    
  
 
 
*/
     
     
     
    for(int i=0; i<height; i++)
        {
                count=0;
                for(int j=0; j<width; j++)
                    {
                    String biner= String.format("%24s", Integer.toBinaryString(cipher[i][j])).replace(" ", "0");

                        for(int x=0; x<24; x++)
                            {
                            tampung[count]=biner.charAt(x);
                            count++;
                            }               
                    }
                
             
               
               for(int j=0; j<width; j++)
                    {
                    String binerafter="";    
                    for(int x=0; x<24; x++)
                        {
                        binerafter=binerafter+tampung[count-1];
                        count--;
                        }
                
                     cipher[i][j]= Integer.parseInt(binerafter, 2);
                
         
                    } 
    
  
        } 
    

         return cipher;
        }


}


