/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAEITechnique;

/**
 *
 * @author Noname
 */
public class InvolutoryMatrix {
    
    public int [][] Generate_KeyMatriks(int bil1,int bil2, int bil3,int bil4)
    {
    int [][] identity={{1,0},{0,1}};
    int [][] A22={{bil1,bil2},{bil3,bil4}};
    int [][] temp=new int [2][2];
    int [][] A11=new int[2][2];
    int [][] A12=new int[2][2];
    int [][] A21=new int[2][2];
    
    
    
    // A11 process------------------------------------
    for(int i=0; i<2; i++)
        {
            for(int j=0; j<2; j++)
                {
                   A11[i][j]=256-A22[i][j]; 
                }
        }
    
    // A12 process-----------------------------------
    for(int i=0; i<2; i++)
        {
            for(int j=0; j<2; j++)
                {
                   temp[i][j]=A11[i][j]-identity[i][j]; 
                }
        }
    
    for(int i=0; i<2; i++)
        {
            for(int j=0; j<2; j++)
                {
                   A12[i][j]=256-temp[i][j]; 
                }
        }
    
    
    
    //A21 process---------------------------------------
    for(int i=0; i<2; i++)
        {
            for(int j=0; j<2; j++)
                {
                   temp[i][j]=identity[i][j]+A11[i][j]; 
                }
        }
    
    for(int i=0; i<2; i++)
        {
            for(int j=0; j<2; j++)
                {
                   A21[i][j]=temp[i][j]%256; 
                }
        }
    
    
   
    int [][] involutory={{A11[0][0],A11[0][1],A12[0][0],A12[0][1]},{A11[1][0],A11[1][1],A12[1][0],A12[1][1]}
            ,{A21[0][0],A21[0][1],A22[0][0],A22[0][1]},{A21[1][0],A21[1][1],A22[1][0],A22[1][1]}};
    
    
    return involutory;

    }
    
    
    
    
    
}
