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
        for(int n=0;n<=8;n++) {
            Map<String,Deque<Integer>> torres=new HashMap<>();for(String t:List.of("A","B","C"))torres.put(t,new ArrayDeque<>());
            for(int d=n;d>=1;d--)torres.get("A").push(d);
            List<String> pasos=Main.resolver(n);verificar(pasos.size()==(1<<n)-1);
            for(String paso:pasos) {String[] p=paso.split(" -> ");int disco=torres.get(p[0]).pop();Deque<Integer> destino=torres.get(p[1]);verificar(destino.isEmpty() || destino.peek()>disco);destino.push(disco);}
            verificar(torres.get("A").isEmpty() && torres.get("B").isEmpty() && torres.get("C").size()==n);
        }
        error(IllegalArgumentException.class,()->Main.resolver(11));
        System.out.println("Pruebas correctas");
    }
}
