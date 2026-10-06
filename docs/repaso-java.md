# Repaso de Java esencial

[Inicio](../README.md) · [Siguiente recurso](../unidad1/01-busqueda-secuencial/README.md)

## Elementos necesarios

| Elemento | Uso |
|---|---|
| int/long | Enteros con rangos limitados |
| int [] | Arreglo de longitud fija e índices 0..length-1 |
| if/while/for | Decisiones y repetición |
| Método | Parámetros, resultado y contrato |
| Clase/objeto | Datos y operaciones sobre una instancia |
| null | Ausencia de referencia |
| List/Map/Deque | Interfaces de colecciones |

Java pasa argumentos por valor. Si el valor es referencia, el método puede modificar el objeto compartido; reasignar el parámetro no reasigna la variable externa. Para texto utiliza equals cuando buscas igualdad de contenido y==cuando comparas identidad o valores primitivos.

```java
import java.util.Arrays;
public class Main {
    static int[] copiaOrdenada(int[] entrada) {
        int[] copia = entrada.clone();
        Arrays.sort(copia);
        return copia;
    }
    public static void main(String[] args) {
        int[] original = {4,1,4};
        System.out.println(Arrays.toString(copiaOrdenada(original)));
        System.out.println(Arrays.toString(original));
    }
}
```

La salida es [1, 4, 4] y luego [4, 1, 4]. La copia exterior permite ordenar esosint sin modificar original. Si los elementos fueran objetos mutables, se compartirían referencias a ellos.

## Validación y excepciones

IllegalArgumentException informa un argumento fuera del contrato. NoSuchElementException puede expresar una operación sobre estructura vacía. El contrato explica qué excepciones se esperan. No se capturan todos los errores para continuar como si los datos fueran válidos.

Los ejemplos no aceptan null como arreglo o texto salvo que lo indiquen. Recibir vacío y recibir null son situaciones diferentes. Para cálculos que deben detectar desbordamiento pueden usarse Math.addExact o multiplyExact.

## Ejecución

Con JDK 21, en la carpeta de un ejemplo:

```bash
java Main.java
```

Para separar compilación y ejecución:

```bash
javac -encoding UTF-8 -d out Main.java
java -cp out Main
```

Los comandos sirven en las terminales de los tres sistemas. Consulta la guía de ambiente si Java o javac no se reconocen.
