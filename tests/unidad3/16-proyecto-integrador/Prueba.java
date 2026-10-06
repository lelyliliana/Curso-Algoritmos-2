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
        Main.Red red=new Main.Red();for(String v:List.of("A","B","C","D","E","Salón"))verificar(red.lugar(v));
        red.conectar("A","B");red.conectar("A","C");red.conectar("B","D");red.conectar("C","D");
        verificar(red.ruta("A","D").equals(List.of("A","B","D")));verificar(red.ruta("A","E").isEmpty());verificar(red.ruta("A","A").equals(List.of("A")));
        var previo=red.vista();verificar(!red.lugar("A"));verificar(!red.conectar("B","A"));
        error(IllegalArgumentException.class,()->red.conectar("A","A"));error(IllegalArgumentException.class,()->red.conectar("A","Z"));
        error(IllegalArgumentException.class,()->red.lugar("A;B"));verificar(red.vista().equals(previo));
        error(UnsupportedOperationException.class,()->red.vista().put("Z",List.of()));error(UnsupportedOperationException.class,()->red.vista().get("A").add("Z"));
        Path carpeta=Files.createTempDirectory("alg2-");Path archivo=carpeta.resolve("red.txt");
        try {
            red.guardar(archivo);verificar(red.vista().equals(Main.Red.cargar(archivo).vista()));
            verificar(Files.readString(archivo,StandardCharsets.UTF_8).contains("Salón"));
            for(String contenido:List.of("", "OTRO\n", "ALG2-1\nV;A\nV;A\n", "ALG2-1\nV;A\nA;A;Z\n", "ALG2-1\nV;A\nA;A;A\n", "ALG2-1\nV;A\nV;B\nA;A;B\nA;B;A\n", "ALG2-1\nV;A\nV;B\nA;A;B\nV;C\n", "ALG2-1\nX;A\n")) {
                Files.writeString(archivo,contenido,StandardCharsets.UTF_8);error(IllegalArgumentException.class,()->Main.Red.cargar(archivo));verificar(red.vista().equals(previo));
            }
            Files.writeString(archivo,"ALG2-1\n",StandardCharsets.UTF_8);verificar(Main.Red.cargar(archivo).vista().isEmpty());
        } finally {Files.deleteIfExists(archivo);Files.deleteIfExists(carpeta);}

        System.out.println("Pruebas correctas");
    }
}
