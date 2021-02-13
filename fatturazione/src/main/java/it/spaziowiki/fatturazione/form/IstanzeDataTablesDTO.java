package it.spaziowiki.fatturazione.form;

import java.util.Collection;

public class IstanzeDataTablesDTO<T> implements java.io.Serializable{

	 private static final long serialVersionUID = -8220588043068200705L;
	 private Collection<? extends AbstractForm>  aaData;
	 private int sEcho;
	 private Integer iTotalRecords;
	 private Integer iTotalDisplayRecords;
	 
	 
	public Collection<? extends AbstractForm>  getAaData() {
		return aaData;
	}
	public void setAaData(Collection<? extends AbstractForm>  aaData) {
		this.aaData = aaData;
	}
	public int getsEcho() {
		return sEcho;
	}
	public void setsEcho(int sEcho) {
		this.sEcho = sEcho;
	}
	public Integer getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(Integer iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public Integer getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(Integer iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
