package test.gcp.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }
    private static String webpage = "";
    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        App.init();
        System.out.println(webpage);
        App.listen();
    }

    private static void init() {
        try {
            File file = new File("src/resources/webapp/index.html");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String st;
            while ((st = br.readLine()) != null) {
                App.webpage += st;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void listen() {
        try {
            ServerSocket server = new ServerSocket(8080);
            while (true) {
                try (Socket socket = server.accept()) {
                    Date today = new Date();
                    String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + webpage;
                    socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
                    socket.close();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
