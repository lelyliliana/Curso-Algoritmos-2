public class Main {
    static int indice(int clave, int capacidad) {
        if (capacidad <= 0) throw new IllegalArgumentException("Capacidad positiva requerida");
        return Math.floorMod(clave, capacidad);
    }
    public static void main(String[] args) {
        for (int clave : new int[]{7, 12, -3}) System.out.println(clave + " -> " + indice(clave, 5));
    }
}
