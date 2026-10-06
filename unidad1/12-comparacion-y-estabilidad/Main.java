import java.util.Arrays;
public class Main {
    static void insercion(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int clave = a[i], j = i - 1;
            while (j >= 0 && a[j] > clave) { a[j+1] = a[j]; j--; }
            a[j+1] = clave;
        }
    }
    public static void main(String[] args) {
        int[] original = {4, 1, 4, -2};
        int[] referencia = original.clone(), propio = original.clone();
        Arrays.sort(referencia); insercion(propio);
        System.out.println(Arrays.equals(referencia, propio));
        System.out.println(Arrays.toString(original));
    }
}
