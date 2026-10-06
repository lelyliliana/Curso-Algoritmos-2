public class Main {
    static void hanoi(int n, String origen, String auxiliar, String destino, java.util.List<String> pasos) {
        if (n == 0) return;
        hanoi(n-1, origen, destino, auxiliar, pasos);
        pasos.add(origen + " -> " + destino);
        hanoi(n-1, auxiliar, origen, destino, pasos);
    }
    static java.util.List<String> resolver(int n) {
        if (n < 0 || n > 10) throw new IllegalArgumentException("Discos de 0 a 10");
        java.util.List<String> pasos = new java.util.ArrayList<>();
        hanoi(n,"A","B","C",pasos); return pasos;
    }
    public static void main(String[] args) {
        java.util.List<String> pasos = resolver(2);
        for (String p : pasos) System.out.println(p);
        System.out.println("Movimientos: " + pasos.size());
    }
}
