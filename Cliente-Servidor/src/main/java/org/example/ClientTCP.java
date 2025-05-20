package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientTCP {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 12000);

        Scanner scanner = new Scanner(System.in);

        boolean bucle = true;

        while (bucle){
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8)
            );
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8);

            String mensaje = null;
            do {
                System.out.print("Introduce tu mensaje (el primer caracter debe ser un dígito): ");
                mensaje = scanner.nextLine();
            } while (!Character.isDigit(mensaje.charAt(0)) && !mensaje.equals("FINAL_MESSAGE"));

            System.out.println("Enviando el mensaje: " + mensaje);
            out.println(mensaje);
            System.out.println("El mensaje se ha enviado");

            String respuesta = in.readLine();
            System.out.println("El mensaje recibido es: " + respuesta);

            if (respuesta.equals("FINAL_MESSAGE_OK")){
                bucle = false;
            }
        }

        System.out.println("Liberando los recursos");
        socket.close();
    }
}