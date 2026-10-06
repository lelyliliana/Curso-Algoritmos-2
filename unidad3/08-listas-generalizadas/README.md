# Listas generalizadas: átomos y sublistas

[Recurso anterior](../../unidad3/07-buscar-y-eliminar-en-bst/README.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../unidad3/09-conceptos-de-grafos/README.md)

## Objetivo

Leer una estructura anidada con una gramática explícita.

## Conceptos y razonamiento

Una lista generalizada contiene átomos o sublistas. Una lista lineal simple de enteros no expresa por sí sola esa distinción. Aquí un tipo sellado Elemento permite Atomo y Grupo; el grupo conserva una lista exterior inmutable para no introducir ciclos mediante ediciones posteriores.

La gramática de práctica es lista=(elementos separados por comas); cada elemento es una lista o un átomo de letras/dígitos. Se acepta() como vacía y se ignoran blancos alrededor de tokens. No admite cadenas entre comillas, signos ni operadores de un lenguaje completo. El método público exige una lista como entrada total, no solo un prefijo válido.

El lector avanza pos al consumir caracteres y usa recursión al abrir una sublista. Se detectan coma faltante, cierre faltante, elemento inválido y texto sobrante. No hay límite explícito de anidamiento en esta versión:se usa con entradas pequeñas del ejercicio, porque una profundidad grande puede agotar la pila.

## Caso resuelto y prueba de escritorio

Entrada(a, (b, c), ()) contiene 3 átomos y dos sublistas, una vacía. Representar devuelve la forma canónica sin blancos. Entrada(a, ) falla porque después de coma se exige otro elemento.

## Código completo

Archivo: [Main.java](Main.java).

```java
public class Main {
    sealed interface Elemento permits Atomo, Grupo {}
    record Atomo(String texto) implements Elemento {}
    record Grupo(java.util.List<Elemento> elementos) implements Elemento {
        Grupo { elementos = java.util.List.copyOf(elementos); }
    }
    static class Lector {
        final String texto; int pos;
        Lector(String texto) { this.texto = texto; }
        void blancos() { while (pos < texto.length() && Character.isWhitespace(texto.charAt(pos))) pos++; }
        Elemento elemento() {
            blancos();
            if (pos >= texto.length()) throw new IllegalArgumentException("Elemento esperado");
            if (texto.charAt(pos) == '(') {
                pos++; blancos(); java.util.List<Elemento> r = new java.util.ArrayList<>();
                if (pos < texto.length() && texto.charAt(pos) == ')') { pos++; return new Grupo(r); }
                while (true) {
                    r.add(elemento()); blancos();
                    if (pos >= texto.length()) throw new IllegalArgumentException("Falta cierre");
                    char c = texto.charAt(pos++);
                    if (c == ')') return new Grupo(r);
                    if (c != ',') throw new IllegalArgumentException("Coma esperada");
                }
            }
            int inicio = pos;
            while (pos < texto.length() && Character.isLetterOrDigit(texto.charAt(pos))) pos++;
            if (inicio == pos) throw new IllegalArgumentException("Átomo inválido");
            return new Atomo(texto.substring(inicio,pos));
        }
    }
    static Grupo leer(String texto) {
        Lector lector = new Lector(texto); Elemento e = lector.elemento(); lector.blancos();
        if (!(e instanceof Grupo g) || lector.pos != texto.length()) throw new IllegalArgumentException("Lista completa requerida");
        return g;
    }
    static int atomos(Elemento e) {
        if (e instanceof Atomo) return 1;
        int total = 0; for (Elemento hijo : ((Grupo)e).elementos()) total += atomos(hijo); return total;
    }
    static String representar(Elemento e) {
        if (e instanceof Atomo a) return a.texto();
        java.util.StringJoiner j = new java.util.StringJoiner(",","(",")");
        for (Elemento hijo : ((Grupo)e).elementos()) j.add(representar(hijo));
        return j.toString();
    }
    public static void main(String[] args) {
        Grupo g = leer("(a, (b,c), ())");
        System.out.println(representar(g)); System.out.println("Átomos: " + atomos(g));
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
(a,(b,c),())
Átomos: 3
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Leer Θ(n) en caracteres; espacio Θ(n) para tokens y grupos, pila O(profundidad). Representar con concatenaciones por nivel puede copiar texto y llegar a O(n×profundidad), no se presume lineal siempre.

## Errores frecuentes

- Tratar una sublista como átomo sin etiqueta.
- Aceptar un prefijo y omitir texto sobrante.

## Ejercicio

Representa el polinomio 3 x²+2 x+1 como lista de términos y define el significado de cada posición.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Una opción es((3, 2), (2, 1), (1, 0)):cada término contiene coeficiente y exponente. Esta gramática maneja los valores como átomos textuales; interpretarlos numéricamente requiere validación adicional. Coeficientes negativos exigen ampliar la gramática y las pruebas.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.

[Recurso anterior](../../unidad3/07-buscar-y-eliminar-en-bst/README.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../unidad3/09-conceptos-de-grafos/README.md)
