package serpis.ad;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Articulo {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private BigDecimal precio;

    @ManyToOne
    @JoinColumn(name="categoria")
    private Categoria categoria;   
    
    private Articulo() {}

    public Articulo(Long id, String nombre, BigDecimal precio, Categoria categoria) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
	}   
    
	public Articulo(String nombre, BigDecimal precio, Categoria categoria) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
	}

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
    public BigDecimal getPrecio() {
        return precio;
    }
    public void setPrecio (BigDecimal precio) {
        this.precio=precio;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

	@Override
	public String toString() {
		return "Articulo [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", categoria=" + categoria + "]";
	}
    
    
}
