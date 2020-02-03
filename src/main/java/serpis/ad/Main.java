package serpis.ad;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Inicio
    	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("serpis.ad.hpedidos");
    	EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        //Menú
        System.out.println("Bienvenido");
        System.out.println("Vamos a gestionar nuestros Pedidos");
        System.out.println("Que necesitas realizar:");
        System.out.println("1-Añadir");
        System.out.println("2-Modificar");
        System.out.println("3-Eliminar");

        Scanner tcl = new Scanner(System.in);
        int op2=tcl.nextInt();

        switch (op2) {
            case 1:
                System.out.println("Vamos a Insertar");
                nuevoPedido(entityManager);
                break;
            case 2:
                System.out.println("Vamos a Modificar");
                modificarPedido(entityManager);
                break;
            case 3:
                System.out.println("Vamos a Borrar");
                eliminarPedido(entityManager);
                break;
        }

        List<Pedido> pedidos = entityManager.createQuery("from Pedido order by Id", Pedido.class).getResultList();
        show(pedidos);

        entityManager.getTransaction().commit();
        entityManager.close();

        entityManagerFactory.close();
    }

    public static void nuevoPedido(EntityManager entityManager) { 
    	System.out.println("¿Qué cliente desea hacer el pedido?");
    	Cliente cliente = new Cliente(new Scanner(System.in).nextLine());
    	System.out.println(cliente.toString());
    	Pedido pedido = new Pedido(cliente);
    	Articulo articulo = null;
    	System.out.println("¿Qué artículos forman parte del pedido?");
    	Boolean stop = true;
    	while (stop == true) {
    		System.out.println("> Nombre: ");
    		String nombre = new Scanner(System.in).nextLine();
    		System.out.println("> Precio: ");
    		BigDecimal precio = new Scanner(System.in).nextBigDecimal();
    		System.out.println("> Categoría (ID): ");
    		Long categoria = new Scanner(System.in).nextLong();
    		PedidoLinea pedidoLinea = new PedidoLinea(pedido);
    		articulo = new Articulo(nombre, precio, entityManager.find(Categoria.class, categoria));
    		pedidoLinea.setArticulo(articulo);
    		System.out.println("¿Desea añadir un artículo más? (Y/N)");
    		switch (new Scanner(System.in).nextLine()) {
				case "Y":
					stop = true;
					break;
				case "N":
					stop = false;
					break;
				default:
					stop = false;
					break;
			}
    	}
    	entityManager.persist(cliente);
    	entityManager.persist(articulo);
    	entityManager.persist(pedido);
    }

    public static void modificarPedido(EntityManager entityManager) {    }
    public static void eliminarPedido(EntityManager entityManager) {    }

    private static void show(List<Pedido> pedidos) {
        for (Pedido pedido : pedidos)
            System.out.printf("%3d %s %s %s %n", pedido.getId(), pedido.getFecha(), pedido.getCliente().getNombre(), pedido.getImporte());
    }
}
