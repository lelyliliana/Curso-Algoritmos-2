import java.util.Arrays;
public class Main {
    static void ordenar(int[] a) { dividir(a, new int[a.length], 0, a.length); }
    static void dividir(int[] a, int[] aux, int inicio, int fin) {
        if (fin - inicio <= 1) return;
        int medio = inicio + (fin - inicio) / 2;
        dividir(a, aux, inicio, medio); dividir(a, aux, medio, fin);
        int i = inicio, j = medio, k = inicio;
        while (i < medio && j < fin) aux[k++] = a[i] <= a[j] ? a[i++] : a[j++];
        while (i < medio) aux[k++] = a[i++];
        while (j < fin) aux[k++] = a[j++];
        System.arraycopy(aux, inicio, a, inicio, fin - inicio);
    }
    public static void main(String[] args) {
        int[] a = {5, 2, 4, 2, -1};
        ordenar(a);
        System.out.println(Arrays.toString(a));
    }
}
