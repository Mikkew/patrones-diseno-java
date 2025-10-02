/**
 * ! Patrón mediator
 * Es un patrón de diseño de comportamiento que ayuda a reducir
 * las dependencias desordenadas entre objetos.
 * Este patrón limita la comunicación directa entre ellos,
 * haciendo que solo interactúen a través de un objeto mediador.
 *
 * * Es útil reducir la complejidad de las relaciones entre objetos
 *
 * https://refactoring.guru/es/design-patterns/mediator
 */

package com.mms.patterns.desing.p03_comportamiento.c04_mediator;

import java.util.ArrayList;
import java.util.List;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

// Chatroom
class ChatRoom {
    private List<User> users;
    private String title;

    public ChatRoom(String title) {
        users = new ArrayList<>();
        this.title = title;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void sendMessage(User sender, String message) {
        List<User> usersToSend = this.users.stream().filter(user -> user != sender).toList();

        for (User user: usersToSend) {
            user.receiveMessage(sender, message);
        }
    }

    public String getTitle() {
        return title;
    }
}

class User {
    private String username;
    private ChatRoom chatRoom;

    public User(String username, ChatRoom chatRoom) {
        this.username = username;
        this.chatRoom = chatRoom;

        chatRoom.addUser(this);
    }

    public void sendMessage(String message) {
        System.out.println(String.format("%s%s envía: %s%s%s", BLUE, username, WHITE, message, RESET));
        this.chatRoom.sendMessage(this, message);
    }

    public void receiveMessage(User sender, String message) {
        System.out.println(String.format("%s%s  recibe de %s: %s%s%s", BLUE, username, username, WHITE, message, RESET));
    }
}

/**
 * Caso de uso: Chat grupal con mediador
 *
 * Este ejemplo simula un chat grupal donde los usuarios pueden enviar mensajes a todos los demás.
 * El objeto ChatRoom actúa como mediador, gestionando la comunicación entre los usuarios.
 * Los usuarios no se comunican directamente entre sí, sino a través del mediador.
 *
 * El patrón Mediator reduce el acoplamiento entre los objetos participantes y facilita la extensión del sistema.
 */
public class MediatorPattern_01 {
    public static void main(String[] args) {
        // Se crea el mediador (sala de chat)
        ChatRoom chatRoom = new ChatRoom("Grupo de trabajo");

        // Se crean los usuarios y se agregan al chat
        User user1 = new User("Fernando", chatRoom);
        User user2 = new User("Gastón", chatRoom);
        User user3 = new User("Mariangel", chatRoom);

        // Los usuarios envían mensajes a través del mediador
        user1.sendMessage("Hola a todos!");
        user2.sendMessage("Hola Fernando, ¿Cómo estás?");
        user3.sendMessage("Hola Fernando, Gastón, ¿Cómo están?");
    }
}
