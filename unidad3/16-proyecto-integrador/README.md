# Proyecto integrador: red de lugares y rutas

[Recurso anterior](../../unidad3/15-componentes-y-comprobacion/README.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../docs/cierre.md)

## Objetivo

Integrar colecciones, búsqueda, orden, cola y persistencia.

## Conceptos y razonamiento

El proyecto representa lugares ficticios como un grafo simple no dirigido sin pesos. Permite agregar lugares, conectar dos existentes, listar vecinos y consultar una ruta con el menor número de conexiones. Un lugar aislado permanece en la red. Los códigos distinguen mayúsculas, admiten letras Unicode, números, `_` y `-`, y tienen entre 1 y 30 caracteres bajo la validación del ejemplo.

TreeMap organiza códigos ordenados y TreeSet evita vecinos repetidos con orden determinista. BFS usa ArrayDeque como cola, HashSet para marcas y HashMap para predecesores. Estos papeles conectan búsqueda y estructuras de las unidades anteriores. Orden lexicográfico de códigos no es distancia geográfica ni clasificación cultural de nombres.

La vista pública copia vecinos y protege colecciones, por lo que el llamador no rompe enlaces internos. Conectar valida ambos extremos y el lazo antes de modificar. Lugar/arista duplicados retornan false y conservan estado. Rutas con origen=destino contienen un solo lugar; ausencia de conexión produce lista vacía; un código desconocido es error.

El archivo UTF-8 tiene la cabecera `ALG2-1`, registros `V;codigo` y después registros `A;origen;destino`. Se guarda una sola dirección por arista, pero carga reconstruye ambas. La carga construye un candidato y solo lo retorna después de validar todas las líneas. El menú sustituye la red tras éxito. La escritura directa puede interrumpirse y no es una transacción; conserva copias antes de experimentar. Se trabaja con archivos pequeños de confianza y no se impone límite de tamaño.

## Caso resuelto y prueba de escritorio

Red A–B, A–C, B–D, C–D, E aislado. BFS elige [A, B, D] porque los vecinos de A se recorren ordenados. [A, C, D] también sería mínima. Hacia E retorna []. Guardar y cargar deben conservar enlaces simétricos y E.

| Regla | Evidencia |
|---|---|
| Sin lazos | A–A se rechaza |
| Sin duplicados | Repetir A–B no aumenta vecinos |
| Extremos existentes | A–Z no modifica A |
| Mínimo de conexiones | A–D usa 2 aristas |
| Carga completa | Un registro inválido no sustituye la red activa |

## Código completo

Archivo: [Main.java](Main.java).

```java
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
```

## Ejecución paso a paso

1. Prepara el JDK según la [guía de ambiente](../../docs/ambiente-y-herramientas.md).
2. Abre una terminal en la carpeta de esta lección. Cada Main.java es independiente: no compiles todos juntos.
3. Ejecuta este comando en PowerShell (Windows), Terminal (Ubuntu) o Terminal (macOS):

```bash
java Main.java
```

4. Compara con [esperado.txt](esperado.txt). Antes de modificar, explica qué instrucción produce cada resultado.

### Salida esperada

```text
{A=[B, C], B=[A, D], C=[A, D], D=[B, C], E=[]}
A a D: [A, B, D]
A a E: []
Recuperación igual: true
```

## Modo interactivo

Desde esta carpeta ejecuta:

```bash
java Main.java --menu
```

El menú empieza con red vacía. Usa 1 para crear A, B, C, Dy E; 2 para conectar; 3 para consultar ruta; 4 para listar; 5 para guardar; 6 para cargar y 0 para salir. Cerrar sin guardar pierde los cambios. Guardar sustituye salida/red.txt en la carpeta actual. Al reiniciar, elige Cargar para recuperar. Los datos se crean localmente, no se envían a otro servicio.

Antes de editar red.txt, conserva una copia. Cambia una línea a A; A; Z y comprueba que Cargar informa el error y que Listar conserva la red que estaba activa. Después restaura el archivo válido.

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

BFS en esta representación realiza O(Vlog V+E) operaciones con códigos de longitud acotada y accesos hash esperados constantes; TreeMap agrega log V al acceder vecinos. Espacio O(V) de búsqueda más estructura O(V+E). Guardar recorre V+E, con memoria de líneas proporcional al texto generado.

## Errores frecuentes

- Afirmar O(V+E) sin considerar TreeMap de esta implementación.
- Sustituir red durante una carga parcial.

## Ejercicio

Añade desconectar sin perder simetría. Prueba rutas después de retirar una conexión y después de aislar el destino.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Valida extremos; si existe a–b, retira bdeady [a] y adeady [b]. Devuelve true solo si había conexión. Desconectar A–Bconserva ruta [A, C, D]; desconectar C–Ddespués puede dejar D conectado solo B, pero aún hay camino A–Csin conexión a D si también no hay acceso a B. Traza el estado completo antes de afirmar ausencia.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.

[Recurso anterior](../../unidad3/15-componentes-y-comprobacion/README.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../docs/cierre.md)
