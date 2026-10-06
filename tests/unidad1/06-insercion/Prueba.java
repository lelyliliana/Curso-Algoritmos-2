import java.util.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
public class Prueba {
    static void verificar(boolean condicion) { if (!condicion) throw new AssertionError("Condición incumplida"); }
    interface Accion { void ejecutar() throws Exception; }
    static void error(Class<? extends Throwable> tipo, Accion accion) throws Exception {
        try { accion.ejecutar(); } catch (Throwable e) { if (tipo.isInstance(e)) return; throw new AssertionError("Error distinto",e); }
        throw new AssertionError("Faltó el error esperado");
    }
    public static void main(String[] args) throws Exception {
        List<int[]> casos = new ArrayList<>();
        casos.add(new int[]{}); casos.add(new int[]{1}); casos.add(new int[]{1,2,3}); casos.add(new int[]{3,2,1});
        casos.add(new int[]{2,2,2}); casos.add(new int[]{Integer.MAX_VALUE,0,Integer.MIN_VALUE});
        Random r=new Random(2026);
        for (int i=0;i<200;i++) { int[] a=new int[r.nextInt(41)]; for (int j=0;j<a.length;j++) a[j]=r.nextInt(21)-10; casos.add(a); }
        for (int[] original:casos) {
            int[] propio=original.clone(),esperado=original.clone(); Arrays.sort(esperado); Main.ordenar(propio);
            verificar(Arrays.equals(propio,esperado));
        }
        System.out.println("Pruebas correctas");
    }
}
