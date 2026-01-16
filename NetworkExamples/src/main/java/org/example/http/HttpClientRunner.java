package org.example.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

import static java.net.http.HttpRequest.BodyPublishers.ofFile;

public class HttpClientRunner {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080"))
                .headers("Content-Type", "application/json")
                .POST(ofFile(Path.of("src/main/resources/example.json")))
                .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.headers());
        System.out.println(response.body());
    }
}
