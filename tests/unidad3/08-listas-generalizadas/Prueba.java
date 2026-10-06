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
        for(String s:List.of("()","(a)","(a,(b,c),())","((()))","(abc,123)")) {
            Main.Grupo g=Main.leer(s);verificar(Main.representar(g).equals(s));verificar(Main.representar(Main.leer(Main.representar(g))).equals(s));
        }
        verificar(Main.atomos(Main.leer("(a,(b,c),())"))==3);
        for(String s:List.of("","a","(","(a,)","(a,,b)","(a b)","(a))","(a)extra","(-1)"))error(IllegalArgumentException.class,()->Main.leer(s));
        System.out.println("Pruebas correctas");
    }
}
