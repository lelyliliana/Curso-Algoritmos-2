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
        verificar(Main.buscar(new int[]{},1)==-1); verificar(Main.buscar(new int[]{4,4},4)==0);
        verificar(Main.buscar(new int[]{4,8,2},2)==2); verificar(Main.buscar(new int[]{4,8},99)==-1);
        System.out.println("Pruebas correctas");
    }
}
