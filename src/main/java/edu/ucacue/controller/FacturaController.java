package edu.ucacue.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ucacue.infraestructura.repository.DetalleFacturaRepositorio;
import edu.ucacue.infraestructura.repository.FacturaCabeceraRepositorio;
import edu.ucacue.infraestructura.repository.PersonaRepositorio;
import edu.ucacue.infraestructura.repository.ProductoRepositorio;
import edu.ucacue.modelo.DetalleFactura;
import edu.ucacue.modelo.FacturaCabecera;
import edu.ucacue.modelo.Persona;
import edu.ucacue.modelo.Producto;

@RestController
@RequestMapping("/api")
public class FacturaController {


	@Autowired
	private DetalleFacturaRepositorio dfr;
	
	@Autowired
	private FacturaCabeceraRepositorio fcr;
	
	@Autowired
	private PersonaRepositorio pr;
	
	@Autowired
	private ProductoRepositorio ppr;
	
	FacturaCabecera fc= new FacturaCabecera();
	

	@GetMapping("/factura1")
	public List<DetalleFactura> index() {
		
		return dfr.findAll();
	}	
	
	

	@GetMapping("/factura1/totalventas")
	public List<DetalleFactura> index1() {
		
		return dfr.findAll();
	}	
	
	/*@GetMapping("producto")
	public List<Producto> index3() {
		return ppr.findAll();
	}
	
	@GetMapping("/factura2")
	public List<FacturaCabecera> index1() {
		return fcr.findAll();
	}	
	*/
	
	@PostMapping("/guardar")
	public ResponseEntity<?> saveFactura(@RequestBody DetalleFactura factura , FacturaCabecera factura2, BindingResult result) {
		DetalleFactura facturaGrabar;
		FacturaCabecera facturaGrabar2;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errores = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("Los errores son", errores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			facturaGrabar = dfr.save(factura);
			facturaGrabar2 = fcr.save(factura2);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el inserción en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El Cliente se ha insertado con éxito en la BD");
		response.put("Factura", facturaGrabar);
		response.put("Factura", facturaGrabar2);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	
	
	
	}
	
	
	/*@GetMapping("/suma")
	
	public void setProducto(List<Producto> productos) {
		
		this.producto=productos;
			double total1=0;
			for (Producto producto1 : productos) {
				total1=total1+producto1.getPrecio();
				
			}
			this.detalle=detalles;
			for (DetalleFactura detalle : detalle) {
				
			double total2=0;
			total2=total1*detalle.getValorVenta();
				
					
			
			
	}
			*/
	
	
	}
	
