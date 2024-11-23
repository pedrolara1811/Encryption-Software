import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Encriptador e = new Encriptador();
        e.Iniciar();
    }

    public static class Encriptador {
        @SuppressWarnings("ConvertToTryWithResources")
        public void Iniciar() {
            int[] valores = new int[26]; // Solo 26 letras (A-Z)
            valores[0] = 5;    // A
            valores[1] = 8;    // B
            valores[2] = 3;    // C
            valores[3] = 10;   // D
            valores[4] = 7;    // E
            valores[5] = 9;    // F
            valores[6] = 4;    // G
            valores[7] = 2;    // H
            valores[8] = 11;   // I
            valores[9] = 17;   // J
            valores[10] = 6;   // K
            valores[11] = 15;  // L
            valores[12] = 1;   // M
            valores[13] = 12;  // N
            valores[14] = 25;  // O
            valores[15] = 13;  // P
            valores[16] = 22;  // Q
            valores[17] = 16;  // R
            valores[18] = 50;  // S
            valores[19] = 29;  // T
            valores[20] = 14;  // U
            valores[21] = 18;  // V
            valores[22] = 26;  // W
            valores[23] = 24;  // X
            valores[24] = 19;  // Y
            valores[25] = 21;  // Z

            Scanner scanner = new Scanner(System.in);
            String inputChar;
            List<Integer> seleccionados = new ArrayList<>(); // Para guardar los valores seleccionados
            StringBuilder resultadoFinal = new StringBuilder(); // Para guardar los resultados finales

            while (true) {
                System.out.print("Ingresa un carácter (A-Z) o 'terminar' para salir: ");
                inputChar = scanner.nextLine().toUpperCase();

                // Salir si el usuario ingresa "terminar"
                if (inputChar.equals("TERMINAR")) {
                    procesarEspacio(seleccionados, resultadoFinal);
                    break;
                }

                // Procesar la entrada del usuario
                for (char character : inputChar.toCharArray()) {
                    if (character >= 'A' && character <= 'Z') {
                        int valorActual = valores[character - 'A'];
                        seleccionados.add(valorActual); // Agregar valor actual a la lista
                        desplazarValores(valores, valorActual);
                    } else if (character == ' ') {
                        // Al presionar espacio, calcular la suma y el carácter correspondiente
                        procesarEspacio(seleccionados, resultadoFinal);
                    }
                }
            }

            scanner.close();
            System.out.println("\nResultados finales: " + resultadoFinal.toString().trim());
            System.out.println("Programa terminado.");
        }

        // Método para procesar el espacio y realizar la suma y el carácter correspondiente
        private void procesarEspacio(List<Integer> seleccionados, StringBuilder resultadoFinal) {
            if (!seleccionados.isEmpty()) {
                int sumaTotal = 0;
                StringBuilder valoresPalabra = new StringBuilder();

                for (int valor : seleccionados) {
                    valoresPalabra.append(valor);
                    sumaTotal += valor;
                }

                // Obtener el segundo dígito de la suma
                String sumaStr = String.valueOf(sumaTotal);
                int segundoDigito = sumaStr.length() > 1 ? Character.getNumericValue(sumaStr.charAt(sumaStr.length() - 2)) : Character.getNumericValue(sumaStr.charAt(0));

                // Obtener el carácter correspondiente
                char caracterCorrespondiente = obtenerCaracterPorValor(segundoDigito);

                // Concatenar resultados
                resultadoFinal.append(valoresPalabra).append(sumaTotal).append(caracterCorrespondiente);
                // resultadoFinal.append(" "); // Separar palabras con espacio
                seleccionados.clear(); // Limpiar la lista de seleccionados para la siguiente palabra
            }
        }

        // Método para desplazar los valores en el array
        private void desplazarValores(int[] valores, int desplazamiento) {
            int[] nuevosValores = new int[valores.length];
            for (int i = 0; i < valores.length; i++) {
                int nuevoIndex = (i + desplazamiento) % valores.length;
                nuevosValores[nuevoIndex] = valores[i];
            }
            System.arraycopy(nuevosValores, 0, valores, 0, valores.length);
        }

        private char obtenerCaracterPorValor(int valor) {
            switch (valor) {
                case 1:
                    return '@';
                case 2:
                    return '!';
                case 3:
                    return '<';
                case 4:
                    return '?';
                case 5:
                    return '%';
                case 6:
                    return '>';
                case 7:
                    return '¿';
                case 8:
                    return '¡';
                case 9:
                    return '-';
                case 0:
                    return ':';
                default:
                    return ' '; // En caso de un índice inválido
            }
        }

    }
}