package org.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class BasicClient {
    public static void main(String[] args) throws IOException {
        // Reservar un puerto efímero (cualquier libre) y crear las colas:
        int server_port = 2000;
        DatagramSocket client_socket = new DatagramSocket();
        System.out.println(client_socket.getLocalPort());

        String mensaje = "Mensaje para Juanmi";

        DatagramPacket dp = new DatagramPacket(
                mensaje.getBytes(StandardCharsets.UTF_8), // la zona de memoria con los datos
                mensaje.getBytes(StandardCharsets.UTF_8).length, // Tamaño de esos datos
                InetAddress.getByName("172.16.143.187"), // Dirección del servidor (InetAddress)
                server_port // puerto
        );

        System.out.println("Enviado el mensaje: " + mensaje);
        client_socket.send(dp);
        System.out.println("El mensaje se ha enviado");

        System.out.println("Liberando los recursos");
        client_socket.close();
    }
}