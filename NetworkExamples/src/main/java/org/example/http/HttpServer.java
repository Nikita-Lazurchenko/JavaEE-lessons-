package org.example.http;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private final int port;
    private final ExecutorService executor;

    public HttpServer(int port, int poolSize) {
            this.port = port;
            executor = Executors.newFixedThreadPool(poolSize);
    }

    public void run(){
        try{
            while(true){
                ServerSocket serverSocket = new ServerSocket(port);
                Socket socket = serverSocket.accept();
                executor.submit(() -> processSocket(socket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processSocket(Socket socket) {
        try(socket;
            var inputStream = new DataInputStream(socket.getInputStream());
            var outputStream = new DataOutputStream(socket.getOutputStream()))
        {
            System.out.println(inputStream.readByte());
            System.out.println(new String(inputStream.readNBytes(300)));

            byte[] body = Files.readAllBytes(Path.of("src/main/resources/text.html"));
            outputStream.write("""
                    HTTP/1.1 200 OK
                    Content-Type: text/html
                    Content-Length: %s
                    """.formatted(body.length).getBytes());
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write(body);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
