import java.util.Arrays;
public class Main {
    static void ordenar(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int clave = a[i], j = i - 1;
            while (j >= 0 && a[j] > clave) { a[j+1] = a[j]; j--; }
            a[j+1] = clave;
        }
    }
    public static void main(String[] args) {
        int[] a = {5, 2, 4, 2, -1};
        ordenar(a);
        System.out.println(Arrays.toString(a));
    }
}
