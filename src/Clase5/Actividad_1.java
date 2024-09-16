package Clase5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


class Usuario {
    private int id;
    private String nombre;

    public Usuario(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre + " (ID: " + id + ")";
    }
}


public class Actividad_1 {
    private HashMap<Integer, Usuario> usuarios; // Diccionario para almacenar los usuarios
    private HashMap<Integer, List<Usuario>> followers; // Diccionario para almacenar las listas de seguidores

    // Constructor
    public Actividad_1() {
        usuarios = new HashMap<>();
        followers = new HashMap<>();
    }

    // agregar un nuevo usuario
    public void agregarUsuario(int id, String nombre) {
        if (!usuarios.containsKey(id)) {
            Usuario nuevoUsuario = new Usuario(id, nombre);
            usuarios.put(id, nuevoUsuario);
            followers.put(id, new ArrayList<>()); // Inicializar la lista de seguidores
        }
    }
    // agregar un seguidor
    public void agregarSeguidor(int idSeguidor, int idSeguido) {
        if (usuarios.containsKey(idSeguidor) && usuarios.containsKey(idSeguido)) {
            List<Usuario> listaSeguidos = followers.get(idSeguidor);
            Usuario seguido = usuarios.get(idSeguido);
            if (!listaSeguidos.contains(seguido)) {
                listaSeguidos.add(seguido);
            }
        }
    }

    // listar los seguidores de un usuario dado
    public List<Usuario> listarSeguidores(int idUsuario) {
        if (followers.containsKey(idUsuario)) {
            return followers.get(idUsuario);
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        Actividad_1 redSocial = new Actividad_1();


        redSocial.agregarUsuario(1, "Alice");
        redSocial.agregarUsuario(2, "Bob");
        redSocial.agregarUsuario(3, "Charlie");


        redSocial.agregarSeguidor(1, 2); // Alice sigue a Bob
        redSocial.agregarSeguidor(1, 3); // Alice sigue a Charlie
        redSocial.agregarSeguidor(2, 3); // Bob sigue a Charlie

        //usuarios que sigue Alice
        System.out.println("Usuarios que sigue Alice: " + redSocial.listarSeguidores(1));

        // usuarios que sigue Bob
        System.out.println("Usuarios que sigue Bob: " + redSocial.listarSeguidores(2));
    }
}


/*
Caso base:
La inserción y búsqueda en un `HashMap` es O(1) en promedio.

1. *agregarUsuario(int id, String nombre)*:
   - Tiempo promedio: O(1) al agregar en el HashMap.

2. *agregarSeguidor(int idSeguidor, int idSeguido)*:
   - O(1) para acceder al `HashMap`, y O(m) para verificar si el seguidor ya existe, 
   donde m es el número de seguidores.

3. *listarSeguidores(int idUsuario)*:
   - O(1) para obtener la lista de seguidores.

Complejidad total:
Las operaciones principales (agregarUsuario, agregarSeguidor, listarSeguidores) son eficientes, 
con una complejidad general de O(1) para las búsquedas y O(m) para las listas de seguidores.
*/