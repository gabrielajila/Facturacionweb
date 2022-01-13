package edu.ucacue.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
	@Table(name="productos")
public class Producto {
	
@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id; 
		
		@Column(length = 30)
		private String nombre;
		private String descripcion;
		private double precio;
		private String stock;
		
		public Producto(String nombre, String descripcion, double precio, String stock) {
			super();
			this.nombre = nombre;
			this.descripcion = descripcion;
			this.precio = precio;
			this.stock = stock;
		}

		public Producto() { //incluido y motivo de no mostrar la table
			super();
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public double getPrecio() {
			return precio;
		}

		public void setPrecio(double precio) {
			this.precio = precio;
		}

		public String getStock() {
			return stock;
		}

		public void setStock(String stock) {
			this.stock = stock;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
	}


