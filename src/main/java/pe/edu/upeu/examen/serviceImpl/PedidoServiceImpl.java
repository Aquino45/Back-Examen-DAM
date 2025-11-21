package pe.edu.upeu.examen.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.examen.dto.PedidoDTO;
import pe.edu.upeu.examen.entities.Cliente;
import pe.edu.upeu.examen.entities.Pedido;
import pe.edu.upeu.examen.entities.Plato;
import pe.edu.upeu.examen.repository.ClienteRepository;
import pe.edu.upeu.examen.repository.PedidoRepository;
import pe.edu.upeu.examen.repository.PlatoRepository;
import pe.edu.upeu.examen.service.PedidoService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repo;
    private final ClienteRepository clienteRepo;
    private final PlatoRepository platoRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> listar() {
        return repo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Pedido buscarPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Pedido guardar(PedidoDTO pedidoDto) {
        Pedido pedido = new Pedido();
        pedido.setNumeroMesa(pedidoDto.getNumeroMesa());

        Cliente cliente = clienteRepo.findById(pedidoDto.getCliente_id())
                .orElseThrow(() -> new RuntimeException("Error: Cliente no encontrado"));
        Plato plato = platoRepo.findById(pedidoDto.getPlato_id())
                .orElseThrow(() -> new RuntimeException("Error: Plato no encontrado"));

        pedido.setCliente(cliente);
        pedido.setPlato(plato);

        return repo.save(pedido);
    }

    @Transactional
    @Override
    public Pedido actualizar(Long id, PedidoDTO pedidoDto) {
        Pedido pedidoExistente = repo.findById(id).orElse(null);
        if (pedidoExistente == null) {
            return null;
        }

        pedidoExistente.setNumeroMesa(pedidoDto.getNumeroMesa());

        Cliente cliente = clienteRepo.findById(pedidoDto.getCliente_id())
                .orElseThrow(() -> new RuntimeException("Error al actualizar: Cliente no encontrado"));

        Plato plato = platoRepo.findById(pedidoDto.getPlato_id())
                .orElseThrow(() -> new RuntimeException("Error al actualizar: Plato no encontrado"));

        pedidoExistente.setCliente(cliente);
        pedidoExistente.setPlato(plato);

        return repo.save(pedidoExistente);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}