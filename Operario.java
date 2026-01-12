package recuperacion;

import java.util.Random;

public class Operario extends Thread { //Puede ejecutarse a paralelo de lo demas al heredar hilo
    private final int id; //Este seria el numero de operario
    private final Herramientas herramientaIzquierda; //Guarda referencia del objeto que tiene a su izquierda
    private final Herramientas herramientaDerecha; // Guarda referencia del objeto que tiene a su derecha
    private final Random random = new Random(); //Me genera un numero aleatorio

    public Operario(int id, Herramientas izq, Herramientas der) { //Constructor que cuando en el main se crea un operario le pasan los datos aqui
        this.id = id;
        this.herramientaIzquierda = izq;
        this.herramientaDerecha = der;
    }

    @Override
    public void run() { //Todo lo que esta aqui dentro ocurre en paralelo
        try {
            while (true) { // Operario trabajara en bucle hasta que apague el programa

                Herramientas primera; //Aqui es donde se guardara la herramienta prioritaria
                Herramientas segunda; //Aqui es donde se guardara la herramienta secundaria

                if (herramientaIzquierda.getId() < herramientaDerecha.getId()) {
                    // Si la izquierda tiene un número menor (1 < 2), esa va primero
                    primera = herramientaIzquierda; //Si es menor, asigna la izquierda como la primera que escoge :)
                    segunda = herramientaDerecha; //Y esta seria la segunda en escoger
                } else {
                    // Aqui seria lo contrario a lo de arriba mano
                    primera = herramientaDerecha;
                    segunda = herramientaIzquierda;
                }

                // 
                System.out.println("[Operario " + id + "] - Intentando coger " + primera + "..."); //Imprimo cual es la primera herramienta que escojo segun el operario
                
                synchronized (primera) {
                     // Intento bloquear el objeto primera. Si otro ya lo tiene, se queda dormido en esta línea

                System.out.println("[Operario " + id + "] - Intentando coger " + primera + "..."); //imprimo la primera y voy a la segunda
                
                // Sin soltar la primera, intenta bloquear el objeto segunda. Si está ocupado, espera aqui
                synchronized (primera) {
                    System.out.println("[Operario " + id + "] - Tiene " + primera + ". Intentando coger " + segunda + "...");
                    
                    synchronized (segunda) {
                        // Aquí tiene AMBAS herramientas, muestro el numero de operario
                        System.out.println("[Operario " + id + "] - Herramientas adquiridas. Ensamblando dron...");
                        ensamblar(); // Llamo al metodo ensamblar que simula una pausa durmiendo por unos segundos al hilo :)
                    }
                }
                
                System.out.println("[Operario " + id + "] - Finalizado ensamblaje. Soltando herramientas.");
            }}
            } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); //Cierra el hilo
        }
    }
    
    // Métodos para simular tiempos

    private void ensamblar() throws InterruptedException {
        // Sleep aleatorio para simular trabajo
        Thread.sleep(random.nextInt(1000) + 500);
    }
}