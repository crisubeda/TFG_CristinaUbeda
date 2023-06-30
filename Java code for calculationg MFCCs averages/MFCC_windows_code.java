/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tfg;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


/**
 *
 * @author Cristina
 */
public class MFCC_windows_code {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
                try {
                    String linea ;
                    BufferedReader br = null;
                    BufferedReader br2 = null;
                    File f = new File("C:\\Users\\Cristina\\Documents\\Ingenieria Biomedica\\4º\\TFG\\Basesdedatos_Features\\Comprobaciones\\JAVA\\mfccs.txt");
                    FileWriter fw2 = new FileWriter("C:\\Users\\Cristina\\Documents\\Ingenieria Biomedica\\4º\\TFG\\Basesdedatos_Features\\Comprobaciones\\JAVA\\mfccs_medias.txt");
                   
                    
                    br = new BufferedReader(new FileReader(f));
                    br2 = new BufferedReader(new FileReader(f));
                    long numFil = br.lines().count();
                    int numFilas = (int) numFil;
                    String [] tabla [] = new String[numFilas+1][1];
                    String [][] tabla_windows = new String[numFilas/4][1];
                    int contador = 0;
                    String texto_0 = "0:";
                    int i = 0;
                    int k = 0;
                    float [][] tabla_datos = new float[(numFilas/4)+1][40];
                    
                               while((linea = br2.readLine())!= null && (i <= numFilas)){
                                    if (linea.startsWith(texto_0)) {
                                        contador ++;
                                    }
                                    
                                    tabla[i][0] = linea;
                                    
                                    if(((i+1)%4) == 0){
                                        int j = 0;
                                        tabla_windows[k][j] = tabla[i-3][0].concat(tabla[i-2][0]).concat(tabla[i-1][0]).concat(tabla[i][0]);
                                        k++;
                                    }
                                    
                                    i++;
                                }
                         /*for(String matriz[]: tabla_windows){
                                System.out.println(Arrays.toString(matriz));
                            }*/
                         
                        //SPLIT WINDOWS EN MFCCs 
                        String st = "";
                        String puntos = ":";
                        int c;
                        for(int n=0; n<(numFilas/4); n++){
                            String[] datos = tabla_windows[n][0].split(" ");//when tabla_windows is full it starts to put null until the array is completely full
                            //System.out.println(numFilas);
                            int m=0;
                            for (String dato : datos) {
                                int value = dato.compareTo("");
                                if (value != 0) {
                                    if (dato.contains(puntos)){
                                        String h = dato.replace(puntos, st);
                                        dato = h;
                                    }
                                    //System.out.println("tabla_datos: " + tabla_datos[n][m]);
                                    tabla_datos[n][m] = Float.parseFloat(dato);
                                    m++;
                                    }
                                }
                            }
                        
                        
                            //we check that it starts with 0: line -> checks 397 elements
                            int variable = 0;
                            int valor = calcularFilas(numFilas);
                            System.out.println("valor: " + valor);
                            float[][] tabla_medias = new float[valor][40];
                            //float[][] tabla_sumas = new float[valor][40];
                            int filas = 0;
                            int h = 0;
                            int ant =0;
                            float suma_mfccs = 0;
                            
                            /*for(float matriz[]: tabla_sumas){
                                System.out.println(Arrays.toString(matriz));
                            }*/
                            
                            //checking that there is a 0 in the first element, i.e. that a new audio begins
                            while(filas<(numFilas/4)){
                                for(int n=filas+1; n<filas+398; n++){
                                    //System.out.println(tabla_datos[n][0]);
                                    if(tabla_datos[n][0] == 0){//checks if the first element of each line is 0
                                        //System.out.println("I find a 0 with n: " + n);
                                        variable = n; //variable is the row in which "0:" is located
                                        break;
                                    }//System.out.println("n: " + n);
                                } //if it is 0 then the first element is still 0 (as initialized)
                                if(variable == 0){
                                        for (int b=1; b<40; b++){
                                            suma_mfccs = 0;
                                            for(int m=filas; m<(filas + 398); m++){
                                                System.out.println("Sum1 before: " + suma_mfccs + " data: " + tabla_datos[m][b]);
                                                suma_mfccs = suma_mfccs + tabla_datos[m][b];
                                                System.out.println("suma_mfccs1 after: " + suma_mfccs);
                                                 //tabla_sumas[h][b] = tabla_sumas[h][b] + tabla_datos[m][b]; 
                                                //System.out.println("tabla_sumas[h][b]"+ tabla_sumas[h][b]);
                                                //System.out.println("m: " + m);
                                            }
                                            tabla_medias[h][b] = (float) (suma_mfccs / 398);
                                            //tabla_medias[h][b] = tabla_sumas[h][b] / 398; //mean of all the rows
                                            //System.out.println("1ra tabla_medias[h][b] " + tabla_medias[h][b]);
                                        }
                                    h++;
                                    filas = filas + 200;
                                    ant = filas;
                                    
                                }else{//variable is not 0 and the first element is not 0
                                        for (int b=1; b<40; b++){
                                             suma_mfccs = 0;
                                            for(int m=filas; m<variable; m++){//goes through integer file until it finds another 0:(found in line = variable)
                                                System.out.println("suma_mfccs2 before: " + suma_mfccs + "data: " + tabla_datos[m][b]);
                                                suma_mfccs = suma_mfccs + tabla_datos[m][b];
                                                System.out.println("suma_mfccs2  after: " + suma_mfccs);
                                                //tabla_sumas[h][b] = tabla_sumas[h][b] + tabla_datos[m][b];
                                            }
                                            tabla_medias[h][b] = (float) (suma_mfccs / (variable-filas));//ant
                                            //System.out.println("suma: " + suma_mfccs);
                                            //tabla_medias[h][b] = tabla_sumas[h][b] / (variable-ant);                                    
                                            //System.out.println("2daa tabla_medias[h][b] " + tabla_medias[h][b]);
                                            //System.out.println("variable-filas: " + (variable-filas));
                                        }
                                h++;
                                filas = variable;
                                ant = variable;
                                variable = 0; //variable is reset to 0 to see if the next first element is 0
                                }
                                
                            }
                            
                          /*for(float matriz[]: tabla_medias){
                                System.out.println(Arrays.toString(matriz));
                            }*/
                            
                            
                           fw2.write("nwindow,mfcc1,mfcc2,mfcc3,mfcc4,mfcc5,mfcc6,mfcc7,mfcc8,mfcc9,mfcc10,mfcc11,mfcc12,mfcc13,mfcc14,mfcc15,mfcc16,mfcc17,mfcc18,mfcc19,mfcc20,mfcc21,mfcc22,mfcc23,mfcc24,mfcc25,mfcc26,mfcc27,mfcc28,mfcc29,mfcc30,mfcc31,mfcc32,mfcc33,mfcc34,mfcc35,mfcc36,mfcc37,mfcc38,mfcc39\n");
                           String fila = "";
                           String ar[] = new String[valor];
                           
                            for(int y=0; y<(tabla_medias.length);y++){
                                StringBuilder sb = new StringBuilder();
                                for(int z=0; z<40; z++){
                                    fila = tabla_medias[y][z] + ",";
                                    sb.append(fila);
                                }
                                //System.out.println(sb.toString());
                                fw2.write(sb.toString() + "\n");
                            }
                            fw2.close();
                            
                    } catch (IOException ex) {
                        Logger.getLogger(MFCC_windows_code.class.getName()).log(Level.SEVERE, null, ex);
                    }
               
    }

    public static int calcularFilas(int numFilas){
        int j= 0;
        int k= 0;
        int count =0;
        while(k <= (numFilas/4)){
            j = k + 397;
            System.out.println("De: " + k + " a " + j);
            k = k + 200;
            //System.out.println("K: " + k);
            if(j>(numFilas/4)){
                break;
            } 
            count++;
        }
        return count;
    }
    
    
    public static void comprobarMedias(FileWriter fw3, float [][] tabla_datos){
        String fila2 = "";
        for(int p=0; p<(tabla_datos.length);p++){
            StringBuilder sb = new StringBuilder();
             for(int z=0; z<40; z++){
                    fila2 = tabla_datos[p][z] + ",";
                    //System.out.println("fila2: " + fila2);
                    sb.append(fila2);
             }
            try {
                fw3.write(sb.toString() + "\n");
            } catch (IOException ex) {
                Logger.getLogger(MFCC_windows_code.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
        try {
            fw3.close();
        } catch (IOException ex) {
            Logger.getLogger(MFCC_windows_code.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}