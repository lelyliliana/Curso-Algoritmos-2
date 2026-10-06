import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.IOException;
import java.util.*;

public class Main {
    static String validarCodigo(String codigo) {
        if (codigo == null || !codigo.matches("[\\p{L}\\p{N}_-]{1,30}"))
            throw new IllegalArgumentException("Código de 1 a 30 letras, números, _ o -");
        return codigo;
    }
    static class Red {
        private final NavigableMap<String, NavigableSet<String>> ady = new TreeMap<>();
        boolean lugar(String codigo) {
            validarCodigo(codigo);
            if (ady.containsKey(codigo)) return false;
            ady.put(codigo,new TreeSet<>()); return true;
        }
        void exigir(String codigo) {
            validarCodigo(codigo);
            if (!ady.containsKey(codigo)) throw new IllegalArgumentException("Lugar inexistente: " + codigo);
        }
        boolean conectar(String a,String b) {
            exigir(a); exigir(b);
            if (a.equals(b)) throw new IllegalArgumentException("No se admiten lazos");
            if (ady.get(a).contains(b)) return false;
            ady.get(a).add(b); ady.get(b).add(a); return true;
        }
        List<String> ruta(String origen,String destino) {
            exigir(origen); exigir(destino);
            Map<String,String> previo = new HashMap<>();
            Set<String> visto = new HashSet<>();
            Deque<String> cola = new ArrayDeque<>();
            visto.add(origen); cola.addLast(origen);
            while (!cola.isEmpty()) {
                String v=cola.removeFirst();
                if (v.equals(destino)) break;
                for (String w:ady.get(v)) if (visto.add(w)) {
                    previo.put(w,v); cola.addLast(w);
                }
            }
            if (!visto.contains(destino)) return List.of();
            List<String> resultado = new ArrayList<>();
            for (String v=destino;v!=null;v=previo.get(v)) resultado.add(v);
            Collections.reverse(resultado); return List.copyOf(resultado);
        }
        SortedMap<String,List<String>> vista() {
            SortedMap<String,List<String>> copia = new TreeMap<>();
            for (var e:ady.entrySet()) copia.put(e.getKey(),List.copyOf(e.getValue()));
            return Collections.unmodifiableSortedMap(copia);
        }
        void guardar(Path ruta) throws IOException {
            List<String> lineas = new ArrayList<>(); lineas.add("ALG2-1");
            for (String v:ady.keySet()) lineas.add("V;"+v);
            for (var e:ady.entrySet()) for (String w:e.getValue())
                if (e.getKey().compareTo(w)<0) lineas.add("A;"+e.getKey()+";"+w);
            Path padre=ruta.toAbsolutePath().getParent(); if (padre!=null) Files.createDirectories(padre);
            Files.write(ruta,lineas,StandardCharsets.UTF_8);
        }
        static Red cargar(Path ruta) throws IOException {
            List<String> lineas=Files.readAllLines(ruta,StandardCharsets.UTF_8);
            if (lineas.isEmpty() || !lineas.get(0).equals("ALG2-1")) throw new IllegalArgumentException("Cabecera inválida");
            Red candidato=new Red(); boolean aristas=false;
            for (int i=1;i<lineas.size();i++) {
                String[] campos=lineas.get(i).split(";",-1);
                try {
                    if (campos.length==2 && campos[0].equals("V") && !aristas) {
                        if (!candidato.lugar(campos[1])) throw new IllegalArgumentException("Lugar duplicado");
                    } else if (campos.length==3 && campos[0].equals("A")) {
                        aristas=true;
                        if (!candidato.conectar(campos[1],campos[2])) throw new IllegalArgumentException("Arista duplicada");
                    } else throw new IllegalArgumentException("Registro inválido u orden incorrecto");
                } catch (IllegalArgumentException error) {
                    throw new IllegalArgumentException("Línea " + (i+1) + ": " + error.getMessage(),error);
                }
            }
            return candidato;
        }
    }
    static void demostracion() throws IOException {
        Red red=new Red(); for (String v:List.of("A","B","C","D","E")) red.lugar(v);
        red.conectar("A","B"); red.conectar("A","C"); red.conectar("B","D"); red.conectar("C","D");
        System.out.println(red.vista());
        System.out.println("A a D: " + red.ruta("A","D"));
        System.out.println("A a E: " + red.ruta("A","E"));
        Path ruta=Path.of("salida","red.txt"); red.guardar(ruta);
        System.out.println("Recuperación igual: " + red.vista().equals(Red.cargar(ruta).vista()));
    }
    static void menu() {
        Red red=new Red(); Path ruta=Path.of("salida","red.txt");
        Scanner teclado=new Scanner(System.in,StandardCharsets.UTF_8);
        while (true) {
            System.out.println("1 Lugar | 2 Conectar | 3 Ruta | 4 Listar | 5 Guardar | 6 Cargar | 0 Salir");
            if (!teclado.hasNextLine()) { System.out.println("Fin de entrada"); return; }
            try {
                String opcion=teclado.nextLine();
                switch (opcion) {
                    case "0" -> { System.out.println("Fin"); return; }
                    case "1" -> { System.out.println("Código:"); System.out.println(red.lugar(teclado.nextLine()) ? "Lugar agregado" : "Ya existe"); }
                    case "2" -> {
                        System.out.println("Origen:"); String a=teclado.nextLine(); System.out.println("Destino:"); String b=teclado.nextLine();
                        System.out.println(red.conectar(a,b) ? "Conexión agregada" : "Ya existe");
                    }
                    case "3" -> {
                        System.out.println("Origen:"); String a=teclado.nextLine(); System.out.println("Destino:"); String b=teclado.nextLine();
                        List<String> camino=red.ruta(a,b);
                        System.out.println(camino.isEmpty() ? "Sin ruta" : camino + " (" + (camino.size()-1) + " conexiones)");
                    }
                    case "4" -> System.out.println(red.vista());
                    case "5" -> { red.guardar(ruta); System.out.println("Red guardada"); }
                    case "6" -> { Red candidato=Red.cargar(ruta); red=candidato; System.out.println("Red cargada"); }
                    default -> System.out.println("Opción inválida");
                }
            } catch (IllegalArgumentException | IOException error) { System.out.println("Error: " + error.getMessage()); }
            catch (NoSuchElementException error) { System.out.println("Fin de entrada"); return; }
        }
    }
    public static void main(String[] args) throws IOException {
        if (args.length==0) demostracion();
        else if (Arrays.equals(args,new String[]{"--menu"})) menu();
        else throw new IllegalArgumentException("Uso: java Main.java [--menu]");
    }
}
