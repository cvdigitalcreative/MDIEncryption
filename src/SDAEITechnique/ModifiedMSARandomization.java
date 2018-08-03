/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAEITechnique;

/**
 *
 * @author Noname
 */
public class ModifiedMSARandomization {
    int [][] temp=new int [100][100];
    int [][] tempori=new int [100][100];
    int t;
    
    
    public int [][] Encryption_MSA(int [][] plain, int height, int width,int uniquecode)
          {                                    
            int Wblock=(int) Math.floor(width/50);
            int Hblock=(int) Math.floor(height/50);
            int sisaW=width-(50*Wblock);
            int sisaH=height-(50*Hblock);

            int x=0;
            int xx=50;

            for(int ii=0; ii<Hblock; ii++)
                {
                int yy=50;
                int y=0;

                for(int jj=0; jj<Wblock; jj++)
                    {
                    int countm=0;
                    int countn=0;
                    int m=0;
                              
                for(int i=x; i<xx; i++)
                    {
                    int n=0;
                    for(int j=y; j<yy; j++)
                        {
                         tempori[m][n]=plain[i][j];
                         n++; 
                         countn++;
                        }
                        m++;
                        countm++;
                    }

                for (int u=0; u<uniquecode; u++)
                    {
                        Cycling(countm, countn);
                        UpShift(countm, countn);
                        RightShift(countm, countn);
                        LeftDiagonalRandomization(countm, countn);
                    }
                    DownShift(countm, countn);
                    LeftShift(countm, countn);
                    RightDiagonalRandomization(countm, countn);
          
                    m=0;
                    for(int i=x; i<xx; i++)
                        {
                        int n=0;

                        for(int j=y; j<yy; j++)
                            {
                            plain[i][j]=tempori[m][n];
                            n++;
                            }
                        m++;
                        }   

                        if(jj==Wblock-2)
                        {
                        yy=yy+50+sisaW;
                        }
                        else
                        {
                        yy=yy+50;            
                        }         
                        y=y+50;

                    }
        
            if(ii==Hblock-2)
            {
            xx=xx+50+sisaH;
            }
            else
            {
            xx=xx+50;
            }
            x=x+50;

            }

         return plain;
 }
    
    
//------------------------------------------------------------------------------   
    
    
    private void Cycling(int countm,int countn)
        {   
            int width=countn/countm;
            int height=countm;

            int Hawal=0;
            int Hakhir=height-1;
            int Wawal=0;
            int Wakhir=width-1;
            int batas;

            if((height/2)<(width/2))
                {    
                batas=(height/2)-1;
                }
            else
                {
                batas=(width/2)-1;
                }


            for(int i=0; i<batas; i++)
                {
                if(i%2==0)
                    {       
                    for(int x=Wakhir; x>=Wawal; x--)
                        {
                            if(x==Wakhir)
                                {
                                t=tempori[Hawal][x];
                                }
                            else
                                {
                                tempori[Hawal][x+1]=tempori[Hawal][x];
                                }
                        }

                //----------------------------------------------------------------

                    for(int x=Hawal; x<=Hakhir; x++)
                        {
                            if(x==Hawal)
                            {}
                            else
                            {
                            tempori[x-1][Wawal]=tempori[x][Wawal];
                            }
                        } 

                //---------------------------------------------------------------
                    for(int x=Wawal; x<=Wakhir; x++)
                        {                            
                            if(x==Wawal)
                            {}
                            else
                            {
                            tempori[Hakhir][x-1]=tempori[Hakhir][x];
                            }
                        }

                //---------------------------------------------------------------

                    for(int x=Hakhir; x>=Hawal+1; x-- )
                        {
                            if(x==Hawal+1)
                            {
                            tempori[x+1][Wakhir]=tempori[x][Wakhir];
                            tempori[x][Wakhir]=t;  
                            }
                            else if(x==Hakhir)
                            {
                            }
                            else
                            {
                            tempori[x+1][Wakhir]=tempori[x][Wakhir];
                            }
                        }
                    }
                else
                    {
                    for(int x=Wawal; x<=Wakhir; x++)
                    {
                        if(x==Wawal)
                        {
                            t=tempori[Hawal][x];
                        }
                        else
                        {
                            tempori[Hawal][x-1]=tempori[Hawal][x];
                        }
                    }

                //----------------------------------------------------------------

                    for(int x=Hawal; x<=Hakhir; x++)
                    {
                        if(x==Hawal)
                            {}
                        else
                            {
                            tempori[x-1][Wakhir]=tempori[x][Wakhir];
                            }
                    } 

                //---------------------------------------------------------------

                    for(int x=Wakhir; x>=Wawal; x--)
                    {
                        if(x==Wakhir)
                        {}
                        else
                        {
                            tempori[Hakhir][x+1]=tempori[Hakhir][x];
                        }
                    }

                //----------------------------------------------------------------

                    for(int x=Hakhir; x>=Hawal+1; x-- )
                    {
                        if(x==Hawal+1)
                        {
                        tempori[x+1][Wawal]=tempori[x][Wawal];
                        tempori[x][Wawal]=t;  
                        }
                        else if(x==Hakhir)
                        {}
                        else
                        {
                            tempori[x+1][Wawal]=tempori[x][Wawal];
                        }
                        }
                }

                    Hawal++;
                    Wawal++;
                    Wakhir--;
                    Hakhir--; 
          }
    }
  
    
//------------------------------------------------------------------------------    
    
    
    private void UpShift(int countm,int countn)
        {
         for(int i=0; i<(countm-1); i++)
            {
             for(int j=0; j<countn/countm; j++)
                {
                 temp[i][j]=tempori[i][j];
                 tempori[i][j]=tempori[i+1][j];
                 tempori[i+1][j]=temp[i][j];
                }
            }
        }
    
    
//------------------------------------------------------------------------------
    
    
    public void RightShift(int countm,int countn)
        {
         for(int i=0; i<countm; i++)
            {
             for(int j=(countn/countm)-1; j>0; j--)
                {
                 t=tempori[i][j];
                 tempori[i][j]=tempori[i][j-1];
                 tempori[i][j-1]=t;
                }
            }  
        }
    
    
//------------------------------------------------------------------------------
    
    
    private void LeftDiagonalRandomization(int countm,int countn)
        {   
         for(int i=0; i<countm-1; i++)
            {
             for(int j=0; j<(countn/countm)-1; j++)
                 {
                  if(i==j)
                         {
                         t=tempori[i][j];
                         tempori[i][j]=tempori[i+1][j+1];
                         tempori[i+1][j+1]=t;
                         }
                 }
            }
        }
    
    
//------------------------------------------------------------------------------    
   
    
    private void DownShift(int countm,int countn)
        {
         for(int i=countm-1; i>0; i--)
            {
             for(int j=(countn/countm)-1; j>=0; j--)
                 {
                   temp[i][j]=tempori[i][j];
                   tempori[i][j]=tempori[i-1][j];
                   tempori[i-1][j]=temp[i][j];
                 }  
            }
        }
     
   
 //-----------------------------------------------------------------------------
    
    
    private void LeftShift(int countm,int countn)
        {
         for(int i=0; i<countm; i++)
             {
              for(int j=0; j<countn/countm-1; j++)
                 {
                   t=tempori[i][j];
                   tempori[i][j]=tempori[i][j+1];
                   tempori[i][j+1]=t;
                 }
             }
        }
    
    
 //-----------------------------------------------------------------------------
    
    
  private void RightDiagonalRandomization(int countm,int countn)
        {
            int batas;   
            if((countn/countm)<countm)
                {
                batas=(countn/countm);
                }
            else
                {
                batas=countm;
                }

                int Q=0;
                int R=(countn/countm)-1;
                    for (int i=0; i<batas-1; i++)
                        {
                        t=tempori[Q][R];
                        tempori[Q][R]=tempori[Q+1][R-1];
                        tempori[Q+1][R-1]=t;

                        Q++;
                        R--;
                        }
        }    
  
  
  //----------------------------------------------------------------------------
  //----------------------------------------------------------------------------
  
  
   public int [][] Decryption_MSA(int [][] plain, int height, int width,int uniquecode)
        {                                
            int Wblock=(int) Math.floor(width/50);
            int Hblock=(int) Math.floor(height/50);
            int sisaW=width-(50*Wblock);
            int sisaH=height-(50*Hblock);

            int x=0;
            int xx=50;

            for(int ii=0; ii<Hblock; ii++)
                {
                int yy=50;
                int y=0;

                for(int jj=0; jj<Wblock; jj++)
                    {
                    int countm=0;
                    int countn=0;
                    int m=0;
                  
                        for(int i=x; i<xx; i++)
                        {
                            int n=0;
                            for(int j=y; j<yy; j++)
                            {
                                tempori[m][n]=plain[i][j];
                                n++; 
                                countn++;
                            }
                            m++;
                            countm++;
                        }

                        unRightDiagonalRandomization(countm, countn);
                        unLeftShift(countm, countn);
                        unDownShift(countm, countn);

                        for (int u=0; u<uniquecode; u++)
                            {
                                unLeftDiagonalRandomization(countm, countn);
                                unRightShift(countm, countn);
                                unUpShift(countm, countn);
                                unCycling(countm, countn);               
                            }
    
                        m=0;
                        for(int i=x; i<xx; i++)
                            {
                            int n=0;

                            for(int j=y; j<yy; j++)
                                {
                                plain[i][j]=tempori[m][n];
                                n++;
                                }
                            m++;
                            }   

                        if(jj==Wblock-2)
                            {
                            yy=yy+50+sisaW;
                            }
                        else
                            {
                            yy=yy+50;            
                            }         
                        y=y+50;
                    }

            if(ii==Hblock-2)
                {
                xx=xx+50+sisaH;
                }
            else
                {
                xx=xx+50;
                }
            x=x+50;

            }
             
          return plain;
 }
    

//------------------------------------------------------------------------------
   
   
    private void unCycling(int countm,int countn)
        {
        int width=countn/countm;
        int height=countm;

        int Hawal=0;
        int Hakhir=height-1;
        int Wawal=0;
        int Wakhir=width-1;
        int batas;

        if((height/2)<(width/2))
            {    
            batas=(height/2)-1;
            }
        else
            {
            batas=(width/2)-1;
            }
        
        for(int i=0; i<batas; i++)
            {
            if(i%2==1)
            {

    //---------------------------------------------------------------------------
            for(int x=Wakhir; x>=Wawal; x--)
                {

                    if(x==Wakhir)
                        {
                        t=tempori[Hawal][x];
                        }
                    else
                        {
                        tempori[Hawal][x+1]=tempori[Hawal][x];
                        }
                }

    //---------------------------------------------------------------------------       
            for(int x=Hawal; x<=Hakhir; x++)
                {

                    if(x==Hawal)
                    {}
                    else
                    {
                    tempori[x-1][Wawal]=tempori[x][Wawal];
                    }
                } 

    //---------------------------------------------------------------------------    
            for(int x=Wawal; x<=Wakhir; x++)
                {                            
                    if(x==Wawal)
                    {}
                    else
                    {
                    tempori[Hakhir][x-1]=tempori[Hakhir][x];
                    }
                }

    //---------------------------------------------------------------------------      
            for(int x=Hakhir; x>=Hawal+1; x-- )
                {

                    if(x==Hawal+1)
                    {
                    tempori[x+1][Wakhir]=tempori[x][Wakhir];
                    tempori[x][Wakhir]=t;  
                    }
                    else if(x==Hakhir)
                    {
                    }
                    else
                    {
                    tempori[x+1][Wakhir]=tempori[x][Wakhir];
                    }
                }


        }
        else
        {


    //----------------------------------------------------------------------------
        for(int x=Wawal; x<=Wakhir; x++)
                {

                    if(x==Wawal)
                        {
                        t=tempori[Hawal][x];
                        }
                    else
                        {
                        tempori[Hawal][x-1]=tempori[Hawal][x];
                        }
                }


    //----------------------------------------------------------------------------      
        for(int x=Hawal; x<=Hakhir; x++)
                {

                    if(x==Hawal)
                    {}
                    else
                    {
                    tempori[x-1][Wakhir]=tempori[x][Wakhir];
                    }
                } 


    //----------------------------------------------------------------------------      
        for(int x=Wakhir; x>=Wawal; x--)
                {

                    if(x==Wakhir)
                        {                   
                        }
                    else
                        {
                        tempori[Hakhir][x+1]=tempori[Hakhir][x];
                        }
                }

    //----------------------------------------------------------------------------     
        for(int x=Hakhir; x>=Hawal+1; x-- )
                {

                    if(x==Hawal+1)
                    {
                    tempori[x+1][Wawal]=tempori[x][Wawal];
                    tempori[x][Wawal]=t;  
                    }
                    else if(x==Hakhir)
                    {
                    }
                    else
                    {
                    tempori[x+1][Wawal]=tempori[x][Wawal];
                    }
                }


        }

            Hawal++;
            Wawal++;
            Wakhir--;
            Hakhir--; 



        }
    
    }
    
    
    
    private void unUpShift(int countm,int countn)
        {
            //System.out.println("masuk step upshift");
            for(int i=countm-1; i>0; i--)
                {
                for(int j=(countn/countm)-1; j>=0; j--)
                    {
                    temp[i][j]=tempori[i][j];
                    tempori[i][j]=tempori[i-1][j];
                    tempori[i-1][j]=temp[i][j];
                    }  
                }
        }
    
    
    
    private void unRightShift(int countm,int countn)
        {
            //System.out.println("masuk step rightshift");
            for(int i=0; i<countm; i++)
                {
                for(int j=0; j<countn/countm-1; j++)
                    {
                    t=tempori[i][j];
                    tempori[i][j]=tempori[i][j+1];
                    tempori[i][j+1]=t;
                    }
                }  
        }
    
    
    
    
    private void unLeftDiagonalRandomization(int countm,int countn)
        {
            for(int i=countm-1; i>0; i--)
            {
             for(int j=(countn/countm)-1; j>0; j--)
                 {
                  if(i==j)
                         {
                         t=tempori[i][j];
                         tempori[i][j]=tempori[i-1][j-1];
                         tempori[i-1][j-1]=t;
                         }
                 }
            }
            
        }
    
    
    
   
    private void unDownShift(int countm,int countn)
        {
            //System.out.println("masuk step downshift");
            for(int i=0; i<(countm-1); i++)
                {
                for(int j=0; j<countn/countm; j++)
                    {
                    temp[i][j]=tempori[i][j];
                    tempori[i][j]=tempori[i+1][j];
                    tempori[i+1][j]=temp[i][j];
                    }
                }
        }
     
    
    
    
    private void unLeftShift(int countm,int countn)
        {
            //System.out.println("masuk step leftshift");
            for(int i=0; i<countm; i++)
                {
                for(int j=(countn/countm)-1; j>0; j--)
                    {
                    t=tempori[i][j];
                    tempori[i][j]=tempori[i][j-1];
                    tempori[i][j-1]=t;
                    }
                }  
        }
    
    
    
    
    private void unRightDiagonalRandomization(int countm,int countn)
        {
            int batas;
            if((countn/countm)<countm)
                {
                batas=(countn/countm);
                }
            else
                {
                batas=countm;
                }
     
           int Q=0;
           int R=(countn/countm)-1;
              for (int i=0; i<batas-1; i++)
                  {
               
                    t=tempori[Q+1][R-1];
                    tempori[Q+1][R-1]=tempori[0][(countn/countm)-1];
                    tempori[0][(countn/countm)-1]=t;
                    
                   Q++;
                   R--;
                  } 
        }
    
   
}
