package entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket {
	
	private Integer id;
	private String Name;
	private String client;
	private Long cnpj;
	private Date date;
	
	private String descricao;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");

	public Ticket() {
		
	}

	
	public Ticket(Integer id, String name, String client, Long cnpj, Date date,String descricao) {
		super();
		this.id = id;
		Name = name;
		this.client = client;
		this.cnpj = cnpj;
		this.date = date;
		this.descricao=descricao;
	}
	
	


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public String getClient() {
		return client;
	}


	public void setClient(String client) {
		this.client = client;
	}


	public Long getCnpj() {
		return cnpj;
	}


	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public SimpleDateFormat getSdf() {
		return sdf;
	}


	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}
	
	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Ticket [id=" + id + ", Name=" + Name + ", client=" + client + ", cnpj=" + cnpj + ", date=" + date
				+ ", descricao=" + descricao + "]";
	}

	
	
	
	
}
