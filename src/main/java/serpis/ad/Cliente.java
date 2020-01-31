package serpis.ad;
import javax.persistence.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre; 
    
    public Cliente() {	}
    
    public Cliente(String nombre) {
    	this.nombre = nombre;
	}
    
    public Cliente(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre=nombre;
    }
}
