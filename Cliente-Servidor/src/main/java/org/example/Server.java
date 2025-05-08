package org.example;

import java.net.SocketException;
import java.util.Scanner;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) throws IOException {
        // Reservar el puerto y crear las colas correspondientes
        int port = 2000;
        DatagramSocket server_socket = new DatagramSocket(port);

        try{
            server_socket = new DatagramSocket();
        } catch (SocketException e){
            System.err.println(e.getLocalizedMessage());
            System.exit(-1);
        }

        String texto = null;

        // Un DatagramPacket vacío para la recepción
        int tam = 1000;
        byte [] buffer = new byte[tam];

        System.out.println("Esperando un datagrama del cliente...");
        DatagramPacket dp = new DatagramPacket(
                buffer, // zona de memoria
                tam // tamaño de esa zona de memoria
        );

        // Recibir
        server_socket.receive(dp);
        System.out.println("Mensaje recibido de " + dp.getAddress() + ":" + dp.getPort());
        texto = new String(
                dp.getData(),
                dp.getOffset(),
                dp.getLength(),
                StandardCharsets.UTF_8
        );
        System.out.println("El mensaje recibido es: " + texto);

        Scanner scanner = new Scanner(System.in);

        // Leer una cadena
        System.out.print("Introduce tu respuesta: ");
        String mensaje = scanner.nextLine();

        DatagramPacket dpr = new DatagramPacket(
                mensaje.getBytes(StandardCharsets.UTF_8), // la zona de memoria con los datos
                mensaje.getBytes(StandardCharsets.UTF_8).length, // Tamaño de esos datos
                InetAddress.getByAddress(dp.getAddress().getAddress()), // Dirección del servidor (InetAddress)
                dp.getPort() // puerto
        );

        System.out.println("Enviado el mensaje: " + mensaje);
        server_socket.send(dpr);
        System.out.println("El mensaje se ha enviado");

    }
}