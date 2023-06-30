/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tfg;

import java.io.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.shape.Line;
import org.apache.commons.csv.*;


/**
 *
 * @author Cristina
 */
public class TFG implements Serializable{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       
        File dir = new File("C:\\Users\\Cristina\\Documents\\Ingenieria Biomedica\\4º\\TFG\\Basesdedatos_Features");
         
          
        Appendable printWriter = new PrintWriter(dir+"/excel.csv","GBK");
                 CSVPrinter csvPrinter = CSVFormat.EXCEL.withHeader("Ruta", "Init", "End", "Duration", "Median Pitch", "Mean Pitch", "Standard deviation", 
                         "Minimum pitch", "Maximum pitch", " Number of pulses", "Number of periods", "Mean period", "Standard deviation of period", "Fraction of locally unvoiced frames", "Number of voice breaks", "Degree of voice breaks", "Jitter (local)",
                         "Jitter (local, absolute)", "Jitter (rap)", "Jitter (ppq5)", "Jitter (ddp)", "Shimmer (local)", "Shimmer (local, dB)", "Shimmer (apq3)", "Shimmer (apq5)",
                         "Shimmer (apq11)", "Shimmer (dda)", "Mean autocorrelation", "Mean noise-to-harmonics ratio", "Mean harmonics-to-noise ratio").print(printWriter);
        
        File[] files = dir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".txt");
                }
            });
        try {
            int count = 0;
            System.out.println("Número de ficheros en directorio: " + files.length);
            for (int i = 0; i < files.length; i++) {
                    File file = files[i];
                    //System.out.println(file);
            String path = file.getAbsolutePath();
            BufferedReader br = null;

                br = new BufferedReader(new FileReader(path));
                String file_data = null;
                file_data = br.readLine();
               
                List<String> lines = Files.readAllLines(Paths.get(path));
                ArrayList<String> arr = (ArrayList<String>) lines;
    //Line 4: 4 , 6 and 9
                String[] elements4 = arr.get(4).split("[ %]");
                String init = elements4.length > 0 ? elements4[4] : "";
                String end =elements4.length > 0 ? elements4[6] : "";
                String duration =elements4.length > 0 ? elements4[9] : "";
                //System.out.println(init + " " + end +" "+ duration);
    //Line 6: 5            
                String[] elements6 = arr.get(6).split("[ %]");
                String median_pitch = elements6.length > 0 ? elements6[5] : "";
                //System.out.println(median_pitch);
    //Line 7: 5            
                String[] elements7 = arr.get(7).split("[ %]");
                String mean_pitch = elements7.length > 0 ? elements7[5] : "";
                //System.out.println(mean_pitch);           
    //Line 8: 5            
                String[] elements8 = arr.get(8).split("[ %]");
                String standard_deviation = elements8.length > 0 ? elements8[5] : "";
                //System.out.println(standard_deviation);        
    //Line 9: 5            
                String[] elements9 = arr.get(9).split("[ %]");
                String minimum_pitch = elements9.length > 0 ? elements9[5] : "";
                //System.out.println(minimum_pitch);
    //Line 10: 5            
                String[] elements10 = arr.get(10).split("[ %]");
                String maximum_pitch = elements10.length > 0 ? elements10[5] : "";
                //System.out.println(maximum_pitch);            
    //Line 12: 6           
                String[] elements12 = arr.get(12).split("[ %]");
                String number_pulses = elements12.length > 0 ? elements12[6] : "";
                //System.out.println(number_pulses); 
    //Line 13: 6           
                String[] elements13 = arr.get(13).split("[ %]");
                String number_periods = elements13.length > 0 ? elements13[6] : "";
                //System.out.println(number_periods);            
    //Line 14: 5            
                String[] elements14 = arr.get(14).split("[ %]");
                String mean_period = elements14.length > 0 ? elements14[5] : "";
                //System.out.println(mean_period);            
    //Line 15: 7            
                String[] elements15 = arr.get(15).split("[ %]");
                String standard_deviation_period = elements15.length > 0 ? elements15[7] : "";
                //System.out.println(standard_deviation_period);    
    //Line 17: 8            
                String[] elements17 = arr.get(17).split("[ %]");
                String fraction_locally_unvoiced_frames = elements17.length > 0 ? elements17[8] : "";
                //System.out.println(fraction_locally_unvoiced_frames);   
    //Line 18: 7           
                String[] elements18 = arr.get(18).split("[ %]");
                String number_voice_breaks = elements18.length > 0 ? elements18[7] : "";
                //System.out.println(number_voice_breaks);              
    //Line 19: 7           
                String[] elements19 = arr.get(19).split("[ %]");
                String degree_voice_breaks = elements19.length > 0 ? elements19[7] : "";
                //System.out.println(degree_voice_breaks);            
    //Line 21: 5           
                String[] elements21 = arr.get(21).split("[ %]");
                String jitter_local = elements21.length > 0 ? elements21[5] : "";
                //System.out.println(jitter_local);            
    //Line 22: 6           
                String[] elements22 = arr.get(22).split("[ %]");
                String jitter_local_absolute = elements22.length > 0 ? elements22[6] : "";
                //System.out.println(jitter_local_absolute);
    //Line 23: 5           
                String[] elements23 = arr.get(23).split("[ %]");
                String jitter_rap = elements23.length > 0 ? elements23[5] : "";
                //System.out.println(jitter_rap);            
    //Line 24: 5           
                String[] elements24 = arr.get(24).split("[ %]");
                String jitter_ppq5 = elements24.length > 0 ? elements24[5] : "";
                //System.out.println(jitter_ppq5);
    //Line 25: 5           
                String[] elements25 = arr.get(25).split("[ %]");
                String jitter_ddp = elements25.length > 0 ? elements25[5] : "";
                //System.out.println(jitter_ddp);            
    //Line 27: 5           
                String[] elements27 = arr.get(27).split("[ %]");
                String shimmer_local = elements27.length > 0 ? elements27[5] : "";
                //System.out.println(shimmer_local);
    //Line 28: 6           
                String[] elements28 = arr.get(28).split("[ %]");
                String shimmer_local_dB = elements28.length > 0 ? elements28[6] : "";
                //System.out.println(shimmer_local_dB);            
    //Line 29: 5           
                String[] elements29 = arr.get(29).split("[ %]");
                String shimmer_apq3 = elements29.length > 0 ? elements29[5] : "";
                //System.out.println(shimmer_apq3); 
    //Line 30: 5           
                String[] elements30 = arr.get(30).split("[ %]");
                String shimmer_apq5 = elements30.length > 0 ? elements30[5] : "";
                //System.out.println(shimmer_apq5);             
    //Line 31: 5           
                String[] elements31 = arr.get(31).split("[ %]");
                String shimmer_apq11 = elements31.length > 0 ? elements31[5] : "";
                //System.out.println(shimmer_apq11);
    //Line 32: 5           
                String[] elements32 = arr.get(32).split("[ %]");
                String shimmer_dda = elements32.length > 0 ? elements32[5] : "";
                //System.out.println(shimmer_dda);
    //Line 34: 5           
                String[] elements34 = arr.get(34).split("[ %]");
                String mean_autocorrelation = elements34.length > 0 ? elements34[5] : "";
                //System.out.println(mean_autocorrelation);
    //Line 35: 6           
                String[] elements35 = arr.get(35).split("[ %]");
                String noise_harmonics_ratio = elements35.length > 0 ? elements35[6] : "";
                //System.out.println(noise_harmonics_ratio);
    //Line 36: 6           
                String[] elements36 = arr.get(36).split("[ %]");
                String harmonics_noise_ratio = elements36.length > 0 ? elements36[6] : "";
                //System.out.println(harmonics_noise_ratio);

            csvPrinter.printRecord(path, init, end, duration, median_pitch, mean_pitch, standard_deviation, minimum_pitch, maximum_pitch,
                    number_pulses, number_periods, mean_period, standard_deviation_period, fraction_locally_unvoiced_frames, number_voice_breaks,
                    degree_voice_breaks, jitter_local, jitter_local_absolute, jitter_rap, jitter_ppq5, jitter_ddp, shimmer_local, shimmer_local_dB, 
                    shimmer_apq3, shimmer_apq5, shimmer_apq11, shimmer_dda, mean_autocorrelation, noise_harmonics_ratio, harmonics_noise_ratio);        
            count ++;
            }
            csvPrinter.flush();
            csvPrinter.close(); 
            System.out.println("Archivos en excel: " + count);
            
        }catch (FileNotFoundException ex) {
            Logger.getLogger(TFG.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
    }

