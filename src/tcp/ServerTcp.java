package tcp;

import utils.ClientHandle;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerTcp {

    private static ServerSocket serverSocket = null;
    private static Map<String, Socket> clients = new HashMap<>();

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(5055);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //listenning to connect
        while (true) {
            try {
                Socket client = serverSocket.accept();
                new Thread() {
                    @Override
                    public void run() {
                        ClientHandle clientHandle = ClientHandle.getInstance();
                        clientHandle.clientHandle(client, clients);
                    }
                }.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
