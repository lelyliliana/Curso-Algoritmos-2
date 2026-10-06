# Colisiones, encadenamiento y factor de carga

[Recurso anterior](../../unidad1/03-transformacion-de-claves/README.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../unidad1/05-burbuja/README.md)

## Objetivo

Guardar claves que comparten cubeta sin perder información.

## Conceptos y razonamiento

El encadenamiento conserva una colección por cubeta. Al insertar se busca la clave: si existe se actualiza; si no, se añade. El contrato aquí es un conjunto de enteros y rechaza duplicados mediante false. Una colisión entre claves distintas no es un duplicado.

El factor de carga α=n/m compara elementos y cubetas. Con encadenamiento puede superar 1. Aumentar cubetas y redistribuir claves puede reducir concentraciones, pero no garantiza una cubeta por clave. La complejidad esperada necesita supuestos sobre la distribución; en el peor caso todas las claves pueden compartir cubeta.

Otra política es direccionamiento abierto, que prueba posiciones alternativas dentro de un arreglo. Allí borrado y búsqueda requieren cuidado con marcas de eliminación; colocar null en cualquier lugar puede cortar una secuencia de búsqueda. Este ejemplo implementa encadenamiento para aprender la regla completa y deja la otra política como comparación conceptual.

## Caso resuelto y prueba de escritorio

Capacidad 5: añadir 7, 12, -3 conserva tres elementos en cubeta 2. Buscar 12 recorre esa cubeta y compara. Añadir 7 otra vez retorna false sin duplicar. α=3/5=0.6, aunque una cubeta tenga tres elementos.

## Código completo

Archivo: [Main.java](Main.java).

```java
import java.util.*;
public class Main {
    static class Tabla {
        final List<List<Integer>> cubetas = new ArrayList<>();
        int cantidad;
        Tabla(int capacidad) {
            if (capacidad <= 0) throw new IllegalArgumentException("Capacidad positiva");
            for (int i = 0; i < capacidad; i++) cubetas.add(new ArrayList<>());
        }
        boolean agregar(int clave) {
            List<Integer> cubeta = cubetas.get(Math.floorMod(clave, cubetas.size()));
            if (cubeta.contains(clave)) return false;
            cubeta.add(clave); cantidad++; return true;
        }
        boolean contiene(int clave) { return cubetas.get(Math.floorMod(clave, cubetas.size())).contains(clave); }
    }
    public static void main(String[] args) {
        Tabla t = new Tabla(5);
        t.agregar(7); t.agregar(12); t.agregar(-3);
        System.out.println(t.cubetas.get(2));
        System.out.println(t.agregar(7));
        System.out.println(t.contiene(12));
        System.out.println(t.contiene(99));
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
[7, 12, -3]
false
true
false
```

## Recorrido guiado del código

Identifica datos de entrada, condición de salida y estado que cambia. Sigue el caso resuelto conservando los valores antes y después de cada cambio. Comprueba que el código implementa el contrato descrito y anota el paso que trata la entrada vacía, ausente o inválida cuando corresponda.

## Eficiencia y límites

Búsqueda O(1+α) esperada bajo distribución apropiada y recorrido lineal de cubeta; peor O(n). Espacio O(n+m). Insertar en ArrayList puede requerir crecimiento.

## Errores frecuentes

- Confundir colisión con duplicado.
- Eliminar por índice cuando se quiere eliminar un Integer por valor.

## Ejercicio

Añade eliminar(clave). Actualiza cantidad solo cuando se elimine una coincidencia.

<details>
<summary>Solución y razonamiento (abre después de intentarlo)</summary>

Usa cubeta.remove(Integer.valueOf(clave)), no remove(clave), pues esa sobrecarga interpretaría un índice. Si retorna true, decrementa cantidad. Prueba ausente, primero y único de la cubeta.

</details>

## Preguntas de comprensión

1. ¿Qué precondición o invariante necesita esta solución?
2. ¿Qué caso puede revelar un error aunque el ejemplo normal funcione?
3. ¿Qué costo aparece al localizar un dato antes de modificarlo?
4. ¿Qué tendría que cambiar si se modifica el contrato del ejercicio?

## Evidencia de aprendizaje

Entrega tu solución, una prueba de escritorio y casos normal, límite e inválido o ausente. Explica resultado esperado y obtenido. Incluye el análisis de tiempo y memoria indicando el modelo utilizado. Una salida correcta sin explicación no permite comprobar cómo llegaste a ella.

[Recurso anterior](../../unidad1/03-transformacion-de-claves/README.md) · [Índice de unidad](../README.md) · [Siguiente recurso](../../unidad1/05-burbuja/README.md)
