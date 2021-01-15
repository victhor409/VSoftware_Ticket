package entities;


import java.io.Serializable;


public class Ticket implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private String cliente;
	private String cnpj;
	private String dataTicket;
	private String descricao;
	
	public Ticket() {
		
	}

	public Ticket(Integer id, String nome,String cliente, String cnpj, String dataTicket, String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.cliente = cliente;
		this.cnpj = cnpj;
		this.dataTicket = dataTicket;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getDataTicket() {
		return dataTicket;
	}

	public void setDataTicket(String dataTicket) {
		this.dataTicket = dataTicket;
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
		return "Ticket [id=" + id + ", nome=" + nome + ", cliente=" + cliente + ", cnpj=" + cnpj + ", dataTicket="
				+ dataTicket + ", descricao=" + descricao + "]";
	}

	
	
	
	
	
	
	
}
