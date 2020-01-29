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

        Scanner tcl2 = new Scanner(System.in);
        int op2=tcl2.nextInt();

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
        Cliente cliente = entityManager.find(Cliente.class, 1L);
        Pedido pedido = new Pedido(cliente);
        PedidoLinea pedidoLinea1 = new PedidoLinea(pedido);
        Articulo articulo1 = entityManager.find(Articulo.class, 1L);
        pedidoLinea1.setArticulo(articulo1);
        pedidoLinea1.setUnidades(new BigDecimal(4));
        PedidoLinea pedidoLinea2 = new PedidoLinea(pedido);
        Articulo articulo2 = entityManager.find(Articulo.class, 2L);
        pedidoLinea2.setArticulo(articulo2);
        pedidoLinea2.setUnidades(new BigDecimal(5));
        entityManager.persist(pedido);
    }

    public static void modificarPedido(EntityManager entityManager) {    }
    public static void eliminarPedido(EntityManager entityManager) {    }

    private static void show(List<Pedido> pedidos) {
        for (Pedido pedido : pedidos)
            System.out.printf("%3d %s %s %s %n", pedido.getId(), pedido.getFecha(), pedido.getCliente().getNombre(), pedido.getImporte());
    }
}
