/**
 * ! Patrón Proxy
 * Este patrón se utiliza para controlar el acceso a un objeto, es decir,
 * se crea un objeto que actúa como intermediario entre el cliente y el objeto real.
 *
 * * Es útil cuando necesitamos controlar el acceso a un objeto,
 * * por ejemplo, para verificar si el cliente tiene permiso
 * * para acceder a ciertos métodos o propiedades.
 *
 * https://refactoring.guru/es/design-patterns/proxy
 *
 */

package com.mms.patterns.desing.p02_estructurales.c07_proxy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.mms.patterns.desing.utils.ConsoleColors.*;

// 1. Interfaz Document
interface Document {
    void displayContent(User user);
}

// 2. Clase que representa el Documento Confidencial - ConfidentialDocument
class ConfidentialDocument implements Document {
    private String content;

    public ConfidentialDocument(String content) {
        this.content = content;
    }

    @Override
    public void displayContent(User user) {
        final String fmt = String.format("%sContenido del documento: \n%s\n%s",BLUE, content, RESET);
        System.out.println(fmt);
    }
}

// 3. Clase Proxy - DocumentProxy
class DocumentProxy implements Document {
    private Document document;
    private List<User.Role> mustHaveRoles;

    public DocumentProxy(Document document) {
        this(document, Collections.emptyList());
    }

    public DocumentProxy(Document document, List<User.Role> mustHaveRoles) {
        this.document = document;
        this.mustHaveRoles = mustHaveRoles;
    }

    @Override
    public void displayContent(User user) {
        if (this.mustHaveRoles.contains(user.getRole())) {
            this.document.displayContent(user);
            return;
        }
        final String fmt = String.format("%sAcceso denegado. %s, no tienes permisos suficientes para ver este documento.%s \n",
                RED, user.getName(), RESET);
        System.out.println(fmt);
    }
}

// 4. Clase que representa al Usuario - User
class User {
    private final String name;
    private final Role role;

    public enum Role {
        ADMIN,
        USER
    }

    public User(String name, Role role) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (role == null) {
            throw new IllegalArgumentException("El rol no puede ser nulo");
        }

        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', role=" + role + "}";
    }
}

/**
 * Caso de uso:
 * ------------------------------------------------------
 * Este ejemplo simula un sistema de documentos confidenciales donde solo ciertos usuarios
 * pueden acceder al contenido. El patrón Proxy se utiliza para controlar el acceso: el proxy
 * verifica si el usuario tiene el rol adecuado antes de mostrar el documento real.
 *
 * En el main, se crean dos usuarios con diferentes roles y se muestra cómo el proxy permite
 * o deniega el acceso según el rol, demostrando el control de acceso sin modificar la lógica
 * del documento confidencial.
 */
public class ProxyPattern_02 {
    public static void main(String[] args) {
        // Crear el documento confidencial real
        Document confidentialDoc = new ConfidentialDocument("" +
                "Este es el contenido confidencial del documento.");

        // Crear el proxy que solo permite acceso a usuarios con rol ADMIN
        Document proxy = new DocumentProxy(confidentialDoc, Arrays.asList(User.Role.ADMIN));

        // Crear dos usuarios con diferentes roles
        User user1 = new User("Juan", User.Role.USER);
        User user2 = new User("Ana", User.Role.ADMIN);

        // Usuario 1 intenta acceder (debería ser denegado)
        System.out.println("Intento de acceso del usuario 1:");
        proxy.displayContent(user1);

        // Usuario 2 intenta acceder (debería ser permitido)
        System.out.println("Intento de acceso del usuario 2:");
        proxy.displayContent(user2);
    }
}
