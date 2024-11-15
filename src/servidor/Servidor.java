package servidor;

import model.Libro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Servidor {
    private static Scanner scan = new Scanner(System.in);
    private static List<Libro> libros;

    public static void main(String[] args) {
        libros = new ArrayList<>();
        libros.add(new Libro("1234","Odisea","Homero",30.00));
        libros.add(new Libro("5678","Don Quijo de la Mancha","Cervantes",30.00));
        libros.add(new Libro("9101","Diario","Ana Frank",40.00));
        libros.add(new Libro("1121","La Biblia","Desconocido",15.00));
        libros.add(new Libro("3141","La teoría de la evolución","Darwin",55.00));

        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("ESCUCHANDO...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("CONECTADO.");

            // // Leemos la opción del cliente.
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String opcionCliente = br.readLine();
            System.out.println("OPCIÓN CLIENTE:"+opcionCliente);

            // Enviamos respuesta al cliente.
            PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(),true);

            if(opcionCliente.equals("1")){
                // Le pedimos el ISBN:
                pw.println("Indica el ISBN a consultar.");

                // Leemos el isbn del cliente.
                String isbnCliente = br.readLine();
                System.out.println("ISBN DEL CLIENTE:"+isbnCliente);

                // Enviamos el Libro al cliente.
                pw.println(consultaLibroPorISBN(isbnCliente));
            } if(opcionCliente.equals("2")){
                // Le pedimos el ISBN:
                pw.println("Indica el titulo a consultar.");

                // Leemos el isbn del cliente.
                String tituloCliente = br.readLine();
                System.out.println("TITULO DEL CLIENTE:"+tituloCliente);

                // Enviamos el Libro al cliente.
                pw.println(consultaLibroPorTitulo(tituloCliente));
            }



            System.out.println("FIN PROGRAMA.");



            // Cerramos los socket.
            serverSocket.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Métodos:
    public static String consultaLibroPorISBN(String isbn){
        for(Libro libro:libros){
            if(libro.getIsbn().equals(isbn)){
                return libro.toString();
            }

        }
        return null;

    }
    public static String consultaLibroPorTitulo(String titulo){
        for(Libro libro:libros){
            if(libro.getTitulo().equals(titulo)){
                return libro.toString();
            }

        }
        return null;

    }

}
