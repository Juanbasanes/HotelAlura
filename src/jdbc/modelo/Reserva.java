package jdbc.modelo;

import java.sql.Date;

public class Reserva {
	
	private Integer id;
	private Date fechaEntrada;
	private Date fechaSalida;
	private String valor;
	private String formaPago;
	
	
	public Reserva(Date fechaEntrada, Date fechaSalida, String valor, String formaPago) {		
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}
		
	public Reserva(Integer id, Date fechaEntrada, Date fechaSalida, String valor, String formaPago) {
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Date getfechaEntrada() {
		return fechaEntrada;
	}

	public Date getfechaSalida() {
		return fechaSalida;
	}

	public String getvalor() {
		return valor;
	}

	public String getformaPago() {
		return formaPago;
	}

	
//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		return String.format("La reserva generada fue: %d, %s, %s, %s, %s", this.id, this.fechaE, this.fechaS, this.valor, this.formaPago);
//	}
//	
	
	
}

