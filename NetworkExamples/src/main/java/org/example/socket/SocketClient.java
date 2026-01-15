package org.example.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) {
        try(var socket = new Socket("localhost",8080);
            var inputStream = new DataInputStream(socket.getInputStream());
            var outputStream = new DataOutputStream(socket.getOutputStream());
            var scanner = new Scanner(System.in);)
        {
            while (scanner.hasNextLine()) {
                outputStream.writeUTF(scanner.nextLine());
                System.out.println("Сервер : " + inputStream.readUTF());

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
