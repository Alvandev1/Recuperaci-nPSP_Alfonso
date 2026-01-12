# Práctica de Recuperación: Estación de Montaje de Drones

Este proyecto implementa una solución en Java para el problema clásico de la sincronización de procesos conocido como "La Cena de los Filósofos", adaptado aquí como una **Estación de Montaje de Drones**.

El objetivo es demostrar el manejo de hilos (**Threads**), la gestión de recursos compartidos y la implementación de mecanismos para evitar el interbloqueo (*Deadlock*) e inanición (*Starvation*).

## 📋 Descripción del Problema
Una empresa dispone de una mesa circular con **5 Operarios**. Entre cada par de operarios hay una herramienta compartida. Para ensamblar un dron, un operario necesita poseer simultáneamente **dos herramientas**: la de su izquierda y la de su derecha.

* **Recursos Compartidos:** 5 Herramientas (Clase `Herramientas.java`).
* **Hilos:** 5 Operarios (Clase `Operario.java`).
* **Restricción:** Dos operarios adyacentes no pueden trabajar a la vez porque comparten una herramienta.

## 🛠️ Tecnologías
* **Lenguaje:** Java (JDK 17 o superior).
* **Conceptos aplicados:** Programación Multihilo, Monitores (`synchronized`), Exclusión Mutua.

## 🚀 Ejecución del Proyecto

1.  Clonar este repositorio.
2.  Compilar los archivos Java:
    ```bash
    javac recuperacion/*.java
    ```
3.  Ejecutar la clase principal:
    ```bash
    java recuperacion.Maincito
    ```

## ⚙️ Estrategia de Sincronización y Anti-Bloqueo (Deadlock)

Para cumplir con los requisitos de la práctica y garantizar la estabilidad del sistema, se han implementado las siguientes estrategias técnicas:

### 1. Uso de Monitores (Exclusión Mutua)
Se utiliza la palabra clave `synchronized` de Java para proteger el acceso a los objetos `Herramientas`. Esto garantiza la **exclusión mutua**: solo un hilo puede poseer una herramienta específica en un momento dado.

### 2. Estrategia para evitar el Deadlock (Jerarquía de Recursos)
El problema principal en este tipo de sistemas es la **espera circular** (interbloqueo), que ocurriría si todos los operarios tomaran su herramienta izquierda al mismo tiempo y esperaran indefinidamente por la derecha.

**Solución Implementada:**
Se ha aplicado una solución basada en la **Jerarquía de Recursos** (Resource Hierarchy Solution).
* Cada herramienta tiene un `ID` único.
* En la clase `Operario.java`, independientemente de si una herramienta está a la "izquierda" o "derecha" física del operario, el código obliga al hilo a solicitar los bloqueos **siempre en orden ascendente de ID**.

**Lógica del código (`Operario.java`):**
```java
// Lógica para romper la espera circular
if (herramientaIzq.getId() < herramientaDer.getId()) {
    primera = herramientaIzq;  // Coger la menor primero
    segunda = herramientaDer;
} else {
    primera = herramientaDer;  // Coger la menor primero (Caso del último operario)
    segunda = herramientaIzq;
}

synchronized (primera) {
    synchronized (segunda) {
        // Sección crítica
    }
}
