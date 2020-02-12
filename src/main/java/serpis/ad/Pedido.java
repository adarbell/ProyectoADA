package serpis.ad;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fecha = LocalDateTime.now();
    private BigDecimal importe = BigDecimal.ZERO;
    @ManyToOne
    @JoinColumn(name= "cliente")
    private Cliente cliente;
    @OneToMany (mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval =true)
    private List<PedidoLinea> pedidoLineas = new ArrayList<PedidoLinea>();

    private Pedido() {} //Hibernate necesita un ctor sin parámetros

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }

    public List<PedidoLinea> getPedidoLineas() {
        return pedidoLineas;
    }

    public void setPedidoLineas(List<PedidoLinea> pedidoLineas) {
        this.pedidoLineas = pedidoLineas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", fecha=" + fecha + ", importe=" + importe + ", cliente=" + cliente
				+ ", pedidoLineas=" + pedidoLineas + "]";
	}
    
    
}