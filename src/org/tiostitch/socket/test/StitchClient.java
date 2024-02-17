package org.tiostitch.socket.test;

import com.sun.security.ntlm.Client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public final class StitchClient {

    public static void main(String[] args) {
        try {
            Socket cliente = new Socket();
            cliente.connect(new InetSocketAddress("localhost", 12347));

            PrintStream saida = new PrintStream(cliente.getOutputStream());
            Scanner teclado = new Scanner(System.in);
            while (teclado.hasNextLine()) {
                final String linha = teclado.nextLine();

                if (linha.startsWith("/") || linha.equals(commandCheck(linha, cliente, saida))) {
                    Colors.srvPrintar(saida, "§b[StitchCloud] §fUm comando foi executado!");
                } else {
                    saida.println(cliente.getInetAddress().getHostName() + ": " + linha);
                }
            }
        }
        catch(IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static String commandCheck(final String linha, final Socket cliente, final PrintStream saida) throws SocketException {
        switch (linha) {
            case "/help":
                System.out.println(" ");
                System.out.println("Comandos: (3)");
                System.out.println("- /help - Comandos do Servidor");
                System.out.println("- /sair - Desconectar do Servidor");
                System.out.println("- /info - Informações do Servidor");
                System.out.println(" ");

                Colors.srvPrintar(saida, "§b[StitchCloud] §7- /help");
                break;
            case "/sair":
                Colors.srvPrintar("§5[StitchCloud] §fA §cconexão §ffoi fechada.");
                Colors.srvPrintar("§5[StitchCloud] §fO programa já pode ser fechado!");

                Colors.srvPrintar(saida, "§b[StitchCloud] §a" + cliente.getInetAddress() + " §fdesconectou-se do servidor.");
                Colors.srvPrintar(saida, "§b[StitchCloud] §7- /help");
                break;
            case "/info":
                System.out.println("");
                System.out.println("Informações do Servidor:");
                System.out.println(" IP: " + cliente.getLocalAddress());
                System.out.println(" Porta: " + cliente.getPort());
                System.out.println(" Canal: " + cliente.getChannel());
                System.out.println(" Vivo: " + cliente.getKeepAlive());
                System.out.println("");

                Colors.srvPrintar(saida, "§b[StitchCloud] §7- /info");
                break;
        }
        return "";
    }
}