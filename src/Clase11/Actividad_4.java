import java.util.*;

public class Act4 {

    static class Usuario {
        private final int id;
        private final String nombre;

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
            return nombre;
        }
    }

    static class RedSocial {
        private final Map<Usuario, List<Usuario>> amistades = new HashMap<>();

        // Método para agregar un usuario a la red social
        public void agregarUsuario(Usuario usuario) {
            amistades.putIfAbsent(usuario, new ArrayList<>());
        }

        // Método para conectar dos usuarios
        public void agregarAmigo(Usuario usuario1, Usuario usuario2) {
            amistades.getOrDefault(usuario1, new ArrayList<>()).add(usuario2);
            amistades.getOrDefault(usuario2, new ArrayList<>()).add(usuario1);
        }

        // Método DFS
        public void DFS(Usuario inicio) {
            Set<Usuario> visitado = new HashSet<>();
            System.out.println("Recorrido DFS desde el usuario " + inicio.getNombre() + ":");
            DFSRecursivo(inicio, visitado);
            System.out.println();
        }

        // Método recursivo para DFS
        private void DFSRecursivo(Usuario usuario, Set<Usuario> visitado) {
            visitado.add(usuario);
            System.out.print(usuario + " -> ");

            for (Usuario amigo : amistades.getOrDefault(usuario, new ArrayList<>())) {
                if (!visitado.contains(amigo)) {
                    DFSRecursivo(amigo, visitado);
                }
            }
        }

        // Método BFS
        public void BFS(Usuario inicio) {
            Set<Usuario> visitado = new HashSet<>();
            Queue<Usuario> cola = new LinkedList<>();

            visitado.add(inicio);
            cola.add(inicio);

            System.out.println("Recorrido BFS desde el usuario " + inicio.getNombre() + ":");

            while (!cola.isEmpty()) {
                Usuario usuario = cola.poll();
                System.out.print(usuario + " -> ");

                for (Usuario amigo : amistades.getOrDefault(usuario, new ArrayList<>())) {
                    if (!visitado.contains(amigo)) {
                        visitado.add(amigo);
                        cola.add(amigo);
                    }
                }
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        RedSocial redSocial = new RedSocial();

        Usuario usuario1 = new Usuario(1, "Tiago");
        Usuario usuario2 = new Usuario(2, "Agustin");
        Usuario usuario3 = new Usuario(3, "Jorge");
        Usuario usuario4 = new Usuario(4, "Manuel");
        Usuario usuario5 = new Usuario(5, "Carlos");


        redSocial.agregarUsuario(usuario1);
        redSocial.agregarUsuario(usuario2);
        redSocial.agregarUsuario(usuario3);
        redSocial.agregarUsuario(usuario4);
        redSocial.agregarUsuario(usuario5);


        redSocial.agregarAmigo(usuario1, usuario2);
        redSocial.agregarAmigo(usuario1, usuario3);
        redSocial.agregarAmigo(usuario2, usuario4);
        redSocial.agregarAmigo(usuario3, usuario5);
        redSocial.agregarAmigo(usuario4, usuario5);


        redSocial.DFS(usuario1);
        redSocial.BFS(usuario1);
    }
}
