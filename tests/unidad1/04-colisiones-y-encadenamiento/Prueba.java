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
        Main.Tabla t=new Main.Tabla(5); Set<Integer> esperado=new HashSet<>();
        for(int n=-50;n<=50;n++) { verificar(t.agregar(n)==esperado.add(n)); verificar(t.agregar(n)==esperado.add(n)); }
        verificar(t.cantidad==esperado.size()); for(int n=-100;n<=100;n++) verificar(t.contiene(n)==esperado.contains(n));
        error(IllegalArgumentException.class,()->new Main.Tabla(0));
        System.out.println("Pruebas correctas");
    }
}
