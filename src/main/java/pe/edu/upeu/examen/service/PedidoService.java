package pe.edu.upeu.examen.service;

import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.examen.dto.PedidoDTO;
import pe.edu.upeu.examen.entities.Pedido;
import java.util.List;

public interface PedidoService {
    List<Pedido> listar();
    Pedido buscarPorId(Long id);
    Pedido guardar(PedidoDTO pedido);
    @Transactional
    Pedido actualizar(Long id, PedidoDTO pedidoDto);
    void eliminar(Long id);
}
