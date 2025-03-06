
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Fecha inicio:
 * Fecha fin:
 *
 * @author Ignasi Paredes
 */

public class ScriptObtainMeanFromTxt {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Ignasi\\Downloads\\DocumentosUIB\\2n - 2n - ACSI\\Practica Tema 2\\cpuMonitorTOP.txt";
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            float[] valoresMonitorizacionTop = new float [1080];

            // Leer y procesar el archivo
            for (int i = 0; (line = br.readLine()) != null; i++) {
                valoresMonitorizacionTop[i] = Float.parseFloat(line.replace(",", "."));
            }
            
            Map<Float, Integer> frecuencia = new HashMap<>();

            for (float num : valoresMonitorizacionTop) {
                frecuencia.put(num, frecuencia.getOrDefault(num, 0) + 1);
            }

            // Imprimir resultados
            for (Map.Entry<Float, Integer> entry : frecuencia.entrySet()) {
                System.out.println("Valor: " + entry.getKey() + " - Frecuencia: " + entry.getValue());
            }
            
            float suma = 0;
            int totalValores = valoresMonitorizacionTop.length;

            for (float num : valoresMonitorizacionTop) {
                suma += num;
            }

            float media = suma / totalValores;
            System.out.println("Media de los valores: " + media);

            System.out.println("✅ Archivo CSV generado correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
