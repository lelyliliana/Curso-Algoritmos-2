import java.util.Arrays;
public class Main {
    static void ordenar(int[] a) {
        for (int fin = a.length - 1; fin > 0; fin--) {
            boolean cambio = false;
            for (int i = 0; i < fin; i++) if (a[i] > a[i+1]) {
                int t = a[i]; a[i] = a[i+1]; a[i+1] = t; cambio = true;
            }
            if (!cambio) break;
        }
    }
    public static void main(String[] args) {
        int[] a = {5, 2, 4, 2, -1};
        ordenar(a);
        System.out.println(Arrays.toString(a));
    }
}
