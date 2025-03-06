/**
 * Fecha inicio:
 * Fecha fin:
 *
 * @author Ignasi Paredes
 */

package script.from.txt.to.excel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScriptFromTxtToExcel {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Ignasi\\Downloads\\DocumentosUIB\\2n - 2n - ACSI\\Practica Tema 2\\monitorizacionCPU.txt";
        String csvPath = "C:\\Users\\Ignasi\\Downloads\\DocumentosUIB\\2n - 2n - ACSI\\Practica Tema 2\\monitorizacionCPU.csv";
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(csvPath))) {

            // Escribir encabezados de columna una vez
            bw.write("Timestamp,% CPU (global),% CPU (user),%CPU (system)\n");
            //bw.write("Timestamp,Capacidad disponible,Capacidad utilizada,% Memoria utilizada\n");

            String line;
            Pattern pattern = Pattern.compile("Timestamp:\\s+(\\S+)\\s+\\| %CPU idle:\\s+([0-9,]+)\\s+\\| %CPU user:\\s+([0-9,]+)\\s+\\| %CPU system:\\s+([0-9,]+)");
            //Pattern pattern = Pattern.compile("Timestamp:\\s+(\\S+)\\s+\\- Capacidad disponible:\\s+([0-9,]+)\\s+\\- Capacidad utilizada:\\s+([0-9,]+)\\s+\\- % Memoria utilizada:\\s+([0-9.0-9]+)");
            
            // Leer y procesar el archivo
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String timestamp = matcher.group(1);
                    String cpuIdle = matcher.group(2).replace(',', '.');   // Reemplazamos la coma por punto
                    String cpuUser = matcher.group(3).replace(',', '.');
                    String cpuSystem = matcher.group(4).replace(',', '.');

                    DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
                    DecimalFormat df = new DecimalFormat("0.0", symbols);

                    // Convertir a valores numéricos
                    double idle = Double.parseDouble(cpuIdle);
                    double global = 100.00 - idle;
                    double user = Double.parseDouble(cpuUser);
                    double system = Double.parseDouble(cpuSystem);
                    //int cD = Integer.parseInt(capacidadDisponible);
                    //int cU = Integer.parseInt(capacidadUtilizada);
                    //double mU = Double.parseDouble(memoriaUtilizada);

                    // Escribir los valores en formato CSV
                    bw.write(timestamp + "," + df.format(global) + "," + df.format(user) + "," + df.format(system) + "\n");
                    //bw.write(timestamp + "," + cD + "," + cU + "," + df.format(mU) + "\n");
                }
            }
            System.out.println("✅ Archivo CSV generado correctamente: " + csvPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
