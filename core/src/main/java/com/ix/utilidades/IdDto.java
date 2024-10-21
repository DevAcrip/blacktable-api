package com.ix.utilidades;

import java.util.Objects;

public class IdDto {	
	
	private int id;
	
	public IdDto() {
		super();
	}

	public IdDto(int oid) {
		this.id=id;
	}
		
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdDto other = (IdDto) obj;
		return id == other.id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}  

}
