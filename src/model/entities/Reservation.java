package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		// programação defensiva: tratar os erros no começo do método
		if (!checkOut.after(checkIn)) {
			throw new DomainException("Check-out date must be after check-in date!");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	public Integer getRoomNumber() {
		return roomNumber;
	}
	
	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	public Date getCheckIn() {
		return checkIn;
	}
	
	public Date getCheckOut() {
		return checkOut;
	}
	
	public long duration() {
		// devolve a diferença entre as datas, em milisegundos
		long diff = checkOut.getTime() - checkIn.getTime();
		// converte e retorna a diferença em milisegundos para dias
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	// throws: progada a exceção DomainException
	public void updateDates(Date checkIn, Date checkOut) {
		Date now = new Date();	// recebe a data atual
		// verifica se as datas de check-in e check-out são anteriores a data atual
		if (checkIn.before(now) || checkOut.before(now)) {
			// lança uma exceção para ser tratada
			// IllegalArgumentException: quando os argumentos passados são inválidos
			throw new DomainException("Reservation dates for update must be future dates!");
		}
		if (!checkOut.after(checkIn)) {
			throw new DomainException("Check-out date must be after check-in date!");
		}
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	// toString para formatação
	@Override
	public String toString() {
		return "Room "
			+ roomNumber
			+ ", check-in: "
			+ sdf.format(checkIn)
			+ ", check-out: "
			+ sdf.format(checkOut)
			+ ", "
			+ duration()
			+ " nights";
	}
}
