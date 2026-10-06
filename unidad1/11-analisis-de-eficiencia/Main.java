public class Main {
    static long comparacionesSeleccion(int n) {
        if (n < 0) throw new IllegalArgumentException("Tamaño no negativo");
        return (long) n * (n - 1) / 2;
    }
    public static void main(String[] args) {
        for (int n : new int[]{0, 4, 8, 16}) System.out.println(n + " -> " + comparacionesSeleccion(n));
    }
}
