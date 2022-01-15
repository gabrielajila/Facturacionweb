package edu.ucacue.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="detalles_facturas")

public class DetalleFactura implements Serializable{
	private static final long serialVersionUID = 1607830177598686701L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFactura;
	
	private int cantidad;
	private double valorVenta;
	
	@ManyToOne
	@JoinColumn(name = "producto_fk")
	private Producto producto;
	
	@ManyToOne
	@JoinColumn(name = "factura_cabecera_fk")
	private FacturaCabecera facturaCabecera;
	
	public DetalleFactura(int cantidad, Producto producto, FacturaCabecera facturaCabecera) {
		super();
		this.cantidad = cantidad;
		this.producto = producto;
		this.facturaCabecera = facturaCabecera;
		this.valorVenta= this.cantidad * producto.getPrecio();
	}
	
	public DetalleFactura() {
		
	}
	
	public int getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getValorVenta() {
		return valorVenta;
	}
	public void setValorVenta(double valorVenta) {
		this.valorVenta = valorVenta;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public FacturaCabecera getFacturaCabecera() {
		return facturaCabecera;
	}
	public void setFacturaCabecera(FacturaCabecera facturaCabecera) {
		this.facturaCabecera = facturaCabecera;
		}
	
	public void setProducto(List<Producto> productos, Producto producto) {
		
	/*this.producto=producto;
		double total1=0;
		for (Producto producto1 : productos) {
			total1=total1+producto1.getPrecio();
			
		}
		double total=0;
		total= total1* getCantidad();
		this.valorVenta=total;
		
}*/
}}

