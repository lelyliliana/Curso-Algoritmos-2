public class Main {
    static int buscar(int[] datos, int clave) {
        for (int i = 0; i < datos.length; i++) if (datos[i] == clave) return i;
        return -1;
    }
    public static void main(String[] args) {
        int[] datos = {8, 3, 8, 1};
        for (int clave : new int[]{8, 1, 7}) System.out.println(clave + " -> " + buscar(datos, clave));
    }
}
