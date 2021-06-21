package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        if (str.contains("/?msg=")) {
                            String[] line = str.split(" ");
                            String[] msg = line[1].split("=");
                            if (msg[1].equals("Exit")) {
                                out.write("Server is closed".getBytes());
                                server.close();
                                break;
                        }
                            if (msg[1].equals("Hello")) {
                                out.write("Hello".getBytes());
                                continue;
                            }
                            out.write(msg[1].getBytes());
                        }
                        System.out.println(str);
                    }
                } catch (Exception e) {
                    LOG.error("Exception in out / in", e);
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in server", e);
        }
    }
}
