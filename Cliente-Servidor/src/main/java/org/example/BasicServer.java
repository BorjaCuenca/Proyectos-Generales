package org.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class BasicServer {
    public static void main(String[] args) throws IOException {
        // Reservar el puerto y crear las colas correspondientes
        int port = 2000;
        DatagramSocket server_socket = new DatagramSocket(port);

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
        String texto = new String(
                dp.getData(),
                dp.getOffset(),
                dp.getLength(),
                StandardCharsets.UTF_8
        );
        System.out.println("El mensaje recibido es: " + texto);

    }
}