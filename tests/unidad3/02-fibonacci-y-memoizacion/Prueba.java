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
        long a=0,b=1;for(int n=0;n<=92;n++) {verificar(Main.fibonacci(n)==a);if(n<91){long c=Math.addExact(a,b);a=b;b=c;}else if(n==91){a=b;}}
        error(IllegalArgumentException.class,()->Main.fibonacci(-1));error(IllegalArgumentException.class,()->Main.fibonacci(93));
        System.out.println("Pruebas correctas");
    }
}
