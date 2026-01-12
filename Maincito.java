package recuperacion;

public class Maincito {
    public static void main(String[] args) {
        final int NUM_OPERARIOS = 5; //Defino el total de puestos en la mesa, lo pongo como constante porque nunca cambia
        
        // Creo un objeto herramientas donde el array sera el total de operarios que en este caso son 5
        Herramientas[] herramientas = new Herramientas[NUM_OPERARIOS];
        for (int i = 0; i < NUM_OPERARIOS; i++) { //Bucle que crea el objeto segun su id
            herramientas[i] = new Herramientas(i);
        }

        // Array para guardar los operarios
        Operario[] operarios = new Operario[NUM_OPERARIOS];
        
        for (int i = 0; i < NUM_OPERARIOS; i++) { //Configura cada operario con cada vuelta

            Herramientas izquierda = herramientas[i]; //Asigna la herramienta izquierda segun el operario
            Herramientas derecha = herramientas[(i + 1) % NUM_OPERARIOS]; //Asigna la mesa derecha y El Operario 0 comparte con el vecino 1. Hasta que llega por su mismo numero que da 0 y vuelve a empezar

            operarios[i] = new Operario(i, izquierda, derecha); //Crea el objeto operario. le asigna el numero que es y asigna cual es la herramienta que tiene a su izquierda y a su derecha
            operarios[i].start(); // //Inicio el hilo
        }
        
        System.out.println("--- Estación de drones iniciada ---");
    }
}