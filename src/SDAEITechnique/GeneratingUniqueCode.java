/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAEITechnique;

/**
 *
 * @author Noname
 */
public class GeneratingUniqueCode {
    private int bil1;
    private int bil2;
    private int bil3;
    private int bil4;
    
    public int getbil1()
        {
        return bil1;
        }

    public int getbil2()
        {
        return bil2;
        }
    
    public int getbil3()
        {
        return bil3;
        }
    
    public int getbil4()
        {
        return bil4;
        }    
    
    
     public int GenerateUniqueCode(String key)
             {
                int panjangkey=key.length();
                char[]huruf=new char[panjangkey];
                int[]angka=new int[panjangkey];
                int N=0,Code=0;
     
                for(int i=0; i<panjangkey; i++)
                    {
                      huruf[i]=key.charAt(i);
                    }
                
                for(int i=0; i<panjangkey; i++)
                    {
                      angka[i]=(int)huruf[i];
                  //    System.out.println(angka[i]);
                    }
                
                for(int i=0; i<panjangkey; i++)
                    {
                      N=(int)(N+angka[i]*(Math.pow(2, i+1)));
                    }
                
             //   System.out.println("N : "+N);
                String length= String.valueOf(N);
                
                for(int i=0; i<length.length(); i++)
                    {
                    Code=Code+Integer.parseInt(String.valueOf(length.charAt(i)));
                    }
             //   System.out.println("uniqueCode"+Code);
                return Code;
    }
    
    
   
     
    /* 
 
     public void generating4key(String key)
            {
            int panjangkey=key.length();
            char[]huruf=new char[4];
            int[]angka=new int[4];
     
     
     
             for(int i=0; i<4; i++)
                {
                  huruf[i]=key.charAt(i);
                }
                
            for(int i=0; i<4; i++)
                {
                  angka[i]=(int)huruf[i];       
                }
     
            bil1=(angka[0]*panjangkey)%256;
            bil2=(angka[1]*2)%256;
            bil3=(angka[2]*3)%256;
            bil4=(angka[3]*4)%256;
     }
   
     
     * 
     */
    
         public void generating4key(String key)
            {
            int panjangkey=key.length();
            char[]huruf=new char[panjangkey];
            int[]angka=new int[panjangkey];
     
     
     
             for(int i=0; i<panjangkey; i++)
                {
                  huruf[i]=key.charAt(i);
                }
                
            for(int i=0; i<panjangkey; i++)
                {
                  angka[i]=(int)huruf[i];       
                }
     
            bil1=angka[0];
            bil2=angka[1];
            bil3=angka[2];
            bil4=angka[3];
            
            
            for(int i=4; i<panjangkey; i++)
                {
                  bil1=bil1*angka[i]%256;       
                }
            for(int i=4; i<panjangkey; i++)
                {
                  bil2=bil2*angka[i]%256;       
                }
            for(int i=4; i<panjangkey; i++)
                {
                  bil3=bil3*angka[i]%256;       
                }
            for(int i=4; i<panjangkey; i++)
                {
                  bil4=bil4*angka[i]%256;       
                }
            
     }
    
}
