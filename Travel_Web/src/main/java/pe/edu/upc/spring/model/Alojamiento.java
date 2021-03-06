package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="alojamiento")
public class Alojamiento implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAlojamiento;
	
	@Column(length = 30,name="descripcion_Alojamiento",nullable=false)
	private String descripcionAlojamiento;
	
	@ManyToOne
	@JoinColumn(name="idHotel", nullable=false)
	private Hotel hotel;
	
	@ManyToOne
	@JoinColumn(name="idRestaurante", nullable=false)
	private Restaurante restaurante;
	
	@ManyToOne
	@JoinColumn(name="idProvincia", nullable=false)
	private Provincia provincia;

	public Alojamiento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Alojamiento(int idAlojamiento, String descripcionAlojamiento, Hotel hotel, Restaurante restaurante,
			Provincia provincia) {
		super();
		this.idAlojamiento = idAlojamiento;
		this.descripcionAlojamiento = descripcionAlojamiento;
		this.hotel = hotel;
		this.restaurante = restaurante;
		this.provincia = provincia;
	}

	public int getIdAlojamiento() {
		return idAlojamiento;
	}

	public void setIdAlojamiento(int idAlojamiento) {
		this.idAlojamiento = idAlojamiento;
	}

	public String getDescripcionAlojamiento() {
		return descripcionAlojamiento;
	}

	public void setDescripcionAlojamiento(String descripcionAlojamiento) {
		this.descripcionAlojamiento = descripcionAlojamiento;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	
}