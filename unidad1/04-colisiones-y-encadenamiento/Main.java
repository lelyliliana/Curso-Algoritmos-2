import java.util.*;
public class Main {
    static class Tabla {
        final List<List<Integer>> cubetas = new ArrayList<>();
        int cantidad;
        Tabla(int capacidad) {
            if (capacidad <= 0) throw new IllegalArgumentException("Capacidad positiva");
            for (int i = 0; i < capacidad; i++) cubetas.add(new ArrayList<>());
        }
        boolean agregar(int clave) {
            List<Integer> cubeta = cubetas.get(Math.floorMod(clave, cubetas.size()));
            if (cubeta.contains(clave)) return false;
            cubeta.add(clave); cantidad++; return true;
        }
        boolean contiene(int clave) { return cubetas.get(Math.floorMod(clave, cubetas.size())).contains(clave); }
    }
    public static void main(String[] args) {
        Tabla t = new Tabla(5);
        t.agregar(7); t.agregar(12); t.agregar(-3);
        System.out.println(t.cubetas.get(2));
        System.out.println(t.agregar(7));
        System.out.println(t.contiene(12));
        System.out.println(t.contiene(99));
    }
}
