package serpis.ad;
import javax.persistence.*;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    
    private Categoria() {}

    public Long getId() {
        return id;
    }
    public void setId (Long id) {
        this.id=id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre (String nombre) {
        this.nombre=nombre;
    }

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + "]";
	}
    
}
