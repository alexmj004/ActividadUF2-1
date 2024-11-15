package cliente;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {


        // SOCKET.
        try {
            Socket servidorSocket = new Socket("localhost",8080);
            System.out.println("CONECTADO.");

            System.out.println("""
                MENÚ:
                    1. Consultar libro por ISBN.
                    2. Consultar libro por titulo.
                    3. Salir de la aplicación.
                
                Elige una opción.                          
                """);
            int opcion = scan.nextInt();

            // Enviamos la opcion elegida al servidor.
            PrintWriter pw = new PrintWriter(servidorSocket.getOutputStream(),true);
            pw.println(opcion);

            // Leemos la respuesta del servidor.
            BufferedReader br = new BufferedReader(new InputStreamReader(servidorSocket.getInputStream()));
            String respuestaServidor = br.readLine();
            System.out.println(respuestaServidor);

            // Mandamos mensaje al servidor.
            switch (opcion){
                case 1:
                    String isbnCliente = scan.next();
                    pw.println(isbnCliente);

                    break;
                case 2:

                    String tituloCliente = scan.next();
                    pw.println(tituloCliente);
                    break;
                default:

            }

            // Leemos el libro que nos envía el servidor.
            System.out.println(br.readLine());


            // Cerramos socket.
            servidorSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
