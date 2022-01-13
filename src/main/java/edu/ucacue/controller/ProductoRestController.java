package edu.ucacue.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import edu.ucacue.infraestructura.repository.ProductoRepositorio;
import edu.ucacue.modelo.Producto;
//@CrossOrigin(origins = { "http://localhost:4200" })
	
@RestController
@RequestMapping("/api")
public class ProductoRestController {

	

		@Autowired
		private ProductoRepositorio productoRepositorio;
		
		@GetMapping("/producto")
		public List<Producto> index() {
			return productoRepositorio.findAll();
		}

		@GetMapping("/producto/{id}")
		public Producto getById(@PathVariable int id) {

			Producto producto = productoRepositorio.findById(id).get();
			return producto;
		}

		@GetMapping("/producto/nombre")
		public List<Producto> getByNombre(@RequestParam(name = "nombre") String nombre) {

			List<Producto> productos = productoRepositorio.findAllByNombre(nombre);
			return productos;
		}

		@PostMapping("/producto")
		public ResponseEntity<?> saveProducto(@RequestBody Producto producto, BindingResult result) {
			Producto productoGrabar;
			Map<String, Object> response = new HashMap<>();

			if (result.hasErrors()) {
				List<String> errores = result.getFieldErrors().stream()
						.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
						.collect(Collectors.toList());
				response.put("Los errores son", errores);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}

			try {
				productoGrabar = productoRepositorio.save(producto);
			} catch (DataAccessException e) {
				response.put("mensaje", "Error al realizar el inserción en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			response.put("mensaje", "El Producto se ha insertado con éxito en la BD");
			response.put("Producto", productoGrabar);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}

		@PutMapping("/producto/{id}")
		public ResponseEntity<?> modificarProducto(@RequestBody Producto producto, BindingResult result,
				@PathVariable int id) {
			
			
			Map<String, Object> response = new HashMap<>();
			Producto producto1;
			try {
			producto1=productoRepositorio.getById(id);
			}catch(DataAccessException e) {
		
					response.put("mensaje", "Error: no se pudo editar, el producto ID: "
							.concat(id + " no existe en la base de datos!"));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			Producto productoModificado = new Producto();
			
			
			if (result.hasErrors()) {
				List<String> errores = result.getFieldErrors().stream()
						.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
						.collect(Collectors.toList());
				response.put("Los errores son", errores);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
			

			
			try {
				producto.setNombre(producto.getNombre());
				producto.setDescripcion(producto.getDescripcion());
				producto.setPrecio(producto.getPrecio());
				producto.setStock(producto.getStock());
				
				
				productoModificado= productoRepositorio.save(producto); 
				
			}catch (DataAccessException e) {
				response.put("mensaje", "Error al actualizar el producto en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}catch (Exception e) {
				response.put("mensaje", "Error al actualizar el producto en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			
			response.put("mensaje", "El Producto se ha modificado con éxito en la BD");
			response.put("Producto", productoModificado);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
		
		@DeleteMapping("/producto/{id}")
		public ResponseEntity<?> eliminarProducto(
				@PathVariable int id) {
			
			
			Map<String, Object> response = new HashMap<>();
			try {
				productoRepositorio.deleteById(id);
			}catch(DataAccessException e) {
		
					response.put("mensaje", "Error: no se pudo eliminar, el producto ID: "
							.concat(id + " no existe en la base de datos!"));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
				
			
			response.put("mensaje", "El Producto se ha eliminado con éxito en la BD");

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
		
		
}

