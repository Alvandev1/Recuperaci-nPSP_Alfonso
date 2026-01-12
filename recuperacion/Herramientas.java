 package recuperacion;

 public class Herramientas {
	    private final int id; //Aqui declaro la variable que es la que guarda el identificador de cada herramienta <3

	    public Herramientas(int id) { //Este es mi amigo el constructor al ejecutar en el main este se ejecuta
	        this.id = id;
	    }

	    public int getId() { //Metodo GET, sirve para que el operario pueda acceder al ID de la herramienta :)
	        return id;
	    }
	    
	    // toString para facilitar la lectura
	    @Override
	    public String toString() {
	        return "Herramientas " + id;
	    }
	}
