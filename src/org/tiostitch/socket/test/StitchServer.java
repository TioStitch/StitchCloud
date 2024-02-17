package org.tiostitch.socket.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public final class StitchServer {

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(12347);
            Colors.srvPrintar("§b[StitchCloud] §fServidor iniciado na porta (§a12347§f)");

            while (true) {
                Socket cliente = server.accept();
                Colors.srvPrintar("§b[StitchCloud] §a" + cliente.getInetAddress() + " §fConectou-se ao serviço!");
                Scanner entrada = new Scanner(cliente.getInputStream());
                while(entrada.hasNextLine()){
                    final String nextLine = entrada.nextLine();
                    Colors.srvPrintar(nextLine);
                }
            }

        } catch (IOException e) {
            return;
        }
    }
}
