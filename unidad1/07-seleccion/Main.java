import java.util.Arrays;
public class Main {
    static void ordenar(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < a.length; j++) if (a[j] < a[menor]) menor = j;
            int t = a[i]; a[i] = a[menor]; a[menor] = t;
        }
    }
    public static void main(String[] args) {
        int[] a = {5, 2, 4, 2, -1};
        ordenar(a);
        System.out.println(Arrays.toString(a));
    }
}
