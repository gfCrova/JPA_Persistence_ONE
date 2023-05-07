package com.alura.tienda.Pruebas;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import com.alura.tienda.DAO.CategoriaDAO;
import com.alura.tienda.DAO.ClienteDAO;
import com.alura.tienda.DAO.PedidoDAO;
import com.alura.tienda.DAO.ProductoDAO;
import com.alura.tienda.Entities.Categoria;
import com.alura.tienda.Entities.Cliente;
import com.alura.tienda.Entities.ItemsPedido;
import com.alura.tienda.Entities.Pedido;
import com.alura.tienda.Entities.Producto;
import com.alura.tienda.Utils.JPAUtils;


public class LoadRecords {
    public static void cargarRegistros() throws FileNotFoundException {
        EntityManager em = JPAUtils.getEntityManager();
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        ProductoDAO productoDAO = new ProductoDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);
        PedidoDAO pedidoDAO = new PedidoDAO(em);
        em.getTransaction().begin();

        loadCategoria("categoria",categoriaDAO,em);

        loadProducto("producto",productoDAO,categoriaDAO,em);

        loadCliente("cliente",clienteDAO,em);

        List<Cliente> clientesList = clienteDAO.consultarTodos();
        List<Pedido> pedidoList= new ArrayList<>();

        for(Cliente cl:clientesList) {
            pedidoList.add(new Pedido(cl));
        }

        for(int i=0;i<pedidoList.size();i++) {
            pedidoList.get(i).agregarItems(new ItemsPedido(i+1,productoDAO.consultarPorId((long) (i+1)),pedidoList.get(i)));
            pedidoDAO.guardar(pedidoList.get(i));
        }

        em.getTransaction().commit();
        em.close();

    }

    private static void loadProducto(String type, ProductoDAO productoDao,CategoriaDAO categoriaDAO, EntityManager em) throws FileNotFoundException {
        List<String> productosTxt =readFile(type);
        for(int i=0;i<productosTxt.size();i++) {
            String[] line = productosTxt.get(i).split(";");
            if(line.length>1) {
                Categoria categoria = categoriaDAO.consultaPorNombre(line[3]);
                Producto producto = new Producto(line[4],line[0],new BigDecimal(line[1]),categoria);
                productoDao.guardar(producto);
                em.flush();
            }
        }
    }

    private static void loadCategoria(String type, CategoriaDAO categoriaDao,EntityManager em) throws FileNotFoundException {
        List<String> categoriasTxt =readFile(type);
        for(int i=0;i<categoriasTxt.size();i++) {
            String[] line = categoriasTxt.get(i).split(";");
            if(line.length==1) {
                Categoria categoria = new Categoria(categoriasTxt.get(i));
                categoriaDao.guardar(categoria);
                em.flush();
            }
        }
    }

    private static void loadCliente(String type, ClienteDAO clienteDao,EntityManager em) throws FileNotFoundException {
        List<String> clientesTxt =readFile(type);
        for(int i=0;i<clientesTxt.size();i++) {
            String[] line = clientesTxt.get(i).split("~");
            System.out.println(line[0]+line[1]);
            Cliente cliente= new Cliente(line[0],line[1]);
            clienteDao.guardar(cliente);
            em.flush();
        }
    }

    private static List<String> readFile(String type) throws FileNotFoundException {
        File file = new File("C:\\Users\\crova\\OneDrive\\Documentos\\jpa-alura\\"+type+".txt");
        Scanner scan = new Scanner(file);
        List<String> pedido= new ArrayList<>();
        while(scan.hasNextLine()){
            pedido.add(scan.nextLine());
        }
        scan.close();
        return pedido;
    }


}
