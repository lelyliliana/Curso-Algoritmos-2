import java.util.Arrays;
public class Main {
    static void ordenar(int[] a) { dividir(a, 0, a.length - 1); }
    static void dividir(int[] a, int izq, int der) {
        if (izq >= der) return;
        int pivote = a[der], frontera = izq;
        for (int i = izq; i < der; i++) if (a[i] < pivote) {
            intercambiar(a, i, frontera++);
        }
        intercambiar(a, frontera, der);
        dividir(a, izq, frontera - 1); dividir(a, frontera + 1, der);
    }
    static void intercambiar(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
    public static void main(String[] args) {
        int[] a = {5, 2, 4, 2, -1};
        ordenar(a);
        System.out.println(Arrays.toString(a));
    }
}
