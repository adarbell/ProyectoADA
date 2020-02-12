package serpis.ad;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {
	static List<Pedido> pedidos;
	static List<Articulo> articulos;
	static List<Cliente> clientes;
	static List<PedidoLinea> pedidosL;
	static List<Categoria> categorias;
	
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
        System.out.println("2-Eliminar");
        System.out.println("3-Mostrar");

        Scanner tcl = new Scanner(System.in);
        int op2=tcl.nextInt();

        switch (op2) {
            case 1:
                System.out.println("Vamos a Insertar");
                nuevoPedido(entityManager);
                break;
            case 2:
                System.out.println("Vamos a Borrar");
                eliminarPedido(entityManager);
                break;
            case 3:
            	System.out.println("Elige que mostrar:");
                System.out.println("1-Clientes");
                System.out.println("2-Articulos");
                System.out.println("3-Pedidos");
                System.out.println("4-Lineas de pedido");
                System.out.println("5-Categorias");                
                switch (tcl.nextInt()) {
                    case 1:
                    	clientes = entityManager.createQuery("from Cliente order by Id", Cliente.class).getResultList();
                    	for (Cliente cliente : clientes)
                    		System.out.println(cliente.toString());
                    case 2:
                    	articulos = entityManager.createQuery("from Articulo order by Id", Articulo.class).getResultList();
                    	for (Articulo articulo : articulos)
                    		System.out.println(articulo.toString());
                    case 3:
                    	pedidos = entityManager.createQuery("from Pedido order by Id", Pedido.class).getResultList();
                    	for (Pedido pedido : pedidos)
                    		System.out.println(pedido.toString());
                    case 4:
                    	pedidosL = entityManager.createQuery("from PedidoLinea order by Id", PedidoLinea.class).getResultList();
                    	for (PedidoLinea pedidoL : pedidosL)
                    		System.out.println(pedidoL.toString());
                    case 5:
                    	categorias = entityManager.createQuery("from Categoria order by Id", Categoria.class).getResultList();
                    	for (Categoria categoria : categorias)
                    		System.out.println(categoria.toString());
                }            	
                break;
        }        

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

    public static void eliminarPedido(EntityManager entityManager) {
    	clientes = entityManager.createQuery("from Cliente order by Id", Cliente.class).getResultList();
    	articulos = entityManager.createQuery("from Articulo order by Id", Articulo.class).getResultList();
    	pedidos = entityManager.createQuery("from Pedido order by Id", Pedido.class).getResultList();
    	pedidosL = entityManager.createQuery("from PedidoLinea order by Id", PedidoLinea.class).getResultList();
    	categorias = entityManager.createQuery("from Categoria order by Id", Categoria.class).getResultList();
    	System.out.println("Elige que eliminar:");
        System.out.println("1-Clientes");
        System.out.println("2-Articulos");
        System.out.println("3-Pedidos");
        System.out.println("4-Lineas de pedido");
        System.out.println("5-Categorias");
        Scanner tcl = new Scanner(System.in);
        int op2=tcl.nextInt();
        switch (op2) {
            case 1:
                System.out.println("Di el cliente a eliminar:");
                Cliente cliente = clientes.get(tcl.nextInt());
                if(cliente != null){
                   clientes.remove(cliente);
                   System.out.println(cliente.getNombre() + " se ha eliminado");
                }
            	entityManager.persist(cliente);
                break;
            case 2:
                System.out.println("Di el articulo a eliminar:");
                Articulo articulo = articulos.get(tcl.nextInt());
                if(articulo != null){
                   articulos.remove(articulo);
                   System.out.println(articulo.getNombre() + " se ha eliminado");
                }
            	entityManager.persist(articulo);
                break;
            case 3:
                System.out.println("Di el pedido a eliminar:");
                Pedido pedido = pedidos.get(tcl.nextInt());
                if(pedido != null){
                   pedidos.remove(pedido);
                   System.out.println("Pedido " + pedido.getId() + " se ha eliminado");
                }
            	entityManager.persist(pedido);
                break;
            case 4:
                System.out.println("Di la linea de pedido a eliminar:");
                PedidoLinea pedidoL = pedidosL.get(tcl.nextInt());
                if(pedidoL != null){
                	pedidosL.remove(pedidoL);
                   System.out.println("Linea " + pedidoL.getId() + " se ha eliminado");
                }
            	entityManager.persist(pedidoL);
                break;
            case 5:
                System.out.println("Di la categoría a eliminar:");
                Categoria categoria = categorias.get(tcl.nextInt());
                if(categoria != null){
                	categorias.remove(categoria);
                   System.out.println("Categoria " + categoria.getId() + " se ha eliminado");
                }
            	entityManager.persist(categoria);
                break;
        }
    }
}
