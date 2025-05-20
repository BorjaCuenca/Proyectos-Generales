package org.example;

import java.net.SocketException;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class ServerUDP {
    public static void main(String[] args) throws IOException {
        // Reservar el puerto y crear las colas correspondientes
        int port = 2000;
        DatagramSocket server_socket = null;

        try{
            server_socket = new DatagramSocket(port);
        } catch (SocketException e){
            System.err.println(e.getLocalizedMessage());
            System.exit(-1);
        }

        boolean bucle = true;

        while (bucle){
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

            if (Character.isDigit(texto.charAt(0))){
                int desplazamiento = Character.getNumericValue(texto.charAt(0));
                StringBuilder resultado = new StringBuilder();
                for (int i=0; i<texto.length(); i++){
                    char c = texto.charAt(i);
                    if (Character.isLetter(c)) {
                        char base = Character.isUpperCase(c) ? 'A' : 'a';
                        char letraCifrada = (char) ((c - base + desplazamiento) % 26 + base);
                        resultado.append(letraCifrada);
                    } else {
                        resultado.append(c);
                    }
                }
                String mensaje = resultado.toString();

                DatagramPacket dpr = new DatagramPacket(
                        mensaje.getBytes(StandardCharsets.UTF_8), // la zona de memoria con los datos
                        mensaje.getBytes(StandardCharsets.UTF_8).length, // Tamaño de esos datos
                        InetAddress.getByAddress(dp.getAddress().getAddress()), // Dirección del servidor (InetAddress)
                        dp.getPort() // puerto
                );

                System.out.println("Enviando el mensaje: " + mensaje);
                server_socket.send(dpr);
                System.out.println("El mensaje se ha enviado");
            }else {
                //Terminamos la conversación
                bucle = false;
            }
        }

        System.out.println("Liberando los recursos");
        server_socket.close();
    }
}