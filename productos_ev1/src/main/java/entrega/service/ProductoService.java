package entrega.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entrega.model.Producto;
import entrega.repository.ProductoRepository;
import entrega.exception.ResourceNotFoundException; // O el paquete donde la tengas guardada

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id " + id));
    }

    public Producto crear(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto actualizar(Long id, Producto datos) {
        Producto existente = buscarPorId(id);
        existente.setNombre(datos.getNombre());
        existente.setDescripcion(datos.getDescripcion());
        existente.setPrecio(datos.getPrecio());
        existente.setStock(datos.getStock());
        return productoRepository.save(existente);
    }

    public void eliminar(Long id) {
        Producto existente = buscarPorId(id);
        productoRepository.delete(existente);
    }
}
