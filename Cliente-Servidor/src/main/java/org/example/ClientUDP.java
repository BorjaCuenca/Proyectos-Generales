package org.example;

import java.net.SocketException;
import java.util.Scanner;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class ClientUDP {
    public static void main(String[] args) throws IOException {
        // Reservar un puerto efímero (cualquier libre) y crear las colas:
        int server_port = 2000;
        DatagramSocket client_socket = null;

        try{
            client_socket = new DatagramSocket();
        } catch (SocketException e){
            System.err.println(e.getLocalizedMessage());
            System.exit(-1);
        }

        System.out.println(client_socket.getLocalPort());
        Scanner scanner = new Scanner(System.in);

        boolean bucle = true;

        while (bucle){
            // Leer una cadena
            System.out.print("Introduce tu mensaje: ");
            String mensaje = scanner.nextLine();

            DatagramPacket dp = new DatagramPacket(
                    mensaje.getBytes(StandardCharsets.UTF_8), // la zona de memoria con los datos
                    mensaje.getBytes(StandardCharsets.UTF_8).length, // Tamaño de esos datos
                    InetAddress.getByName("127.0.0.1"), // Dirección del servidor (InetAddress)
                    server_port // puerto
            );

            System.out.println("Enviando el mensaje: " + mensaje);
            client_socket.send(dp);
            System.out.println("El mensaje se ha enviado");

            if (Character.isDigit(mensaje.charAt(0))){
                //Recibir una respuesta
                // Un DatagramPacket vacío para la recepción
                int tam = 1000;
                byte [] buffer = new byte[tam];

                System.out.println("Esperando un datagrama del servidor...");
                DatagramPacket dpr = new DatagramPacket(
                        buffer, // zona de memoria
                        tam // tamaño de esa zona de memoria
                );

                // Recibir
                client_socket.receive(dpr);
                System.out.println("Mensaje recibido de " + dpr.getAddress() + ":" + dpr.getPort());
                String respuesta = new String(
                        dpr.getData(),
                        dpr.getOffset(),
                        dpr.getLength(),
                        StandardCharsets.UTF_8
                );
                System.out.println("El mensaje recibido es: " + respuesta);
            }else {
                bucle = false;
            }
        }

        System.out.println("Liberando los recursos");
        client_socket.close();
    }
}