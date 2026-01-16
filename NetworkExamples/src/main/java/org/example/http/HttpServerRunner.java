package org.example.http;

public class HttpServerRunner {
    public static void main(String[] args) {
        HttpServer server =  new HttpServer(8080,100);
        server.run();
    }
}
