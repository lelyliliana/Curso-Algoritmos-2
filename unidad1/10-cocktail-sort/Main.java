import java.util.Arrays;
public class Main {
    static void ordenar(int[] a) {
        int inicio = 0, fin = a.length - 1;
        boolean cambio = true;
        while (cambio && inicio < fin) {
            cambio = false;
            for (int i = inicio; i < fin; i++) if (a[i] > a[i+1]) {
                int t = a[i]; a[i] = a[i+1]; a[i+1] = t; cambio = true;
            }
            if (!cambio) break;
            fin--; cambio = false;
            for (int i = fin; i > inicio; i--) if (a[i-1] > a[i]) {
                int t = a[i]; a[i] = a[i-1]; a[i-1] = t; cambio = true;
            }
            inicio++;
        }
    }
    public static void main(String[] args) {
        int[] a = {5, 2, 4, 2, -1};
        ordenar(a);
        System.out.println(Arrays.toString(a));
    }
}
