package pe.edu.upeu.examen.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.examen.dto.PedidoDTO;
import pe.edu.upeu.examen.entities.Pedido;
import pe.edu.upeu.examen.service.PedidoService;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos") // O la ruta que prefieras
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> listar() {
        return ResponseEntity.ok(pedidoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        Pedido pedido = pedidoService.buscarPorId(id);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    public ResponseEntity<Pedido> guardar(@RequestBody PedidoDTO pedidoDto) {
        Pedido nuevoPedido = pedidoService.guardar(pedidoDto);
        return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pedidoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizar(@PathVariable Long id, @RequestBody PedidoDTO pedidoDto) {
        Pedido pedidoActualizado = pedidoService.actualizar(id, pedidoDto);
        if (pedidoActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidoActualizado);
    }
}