package com.ibrace.police.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class UsuarioPolice implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String login;

	@ManyToMany(mappedBy="usuarios", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<ModuloSistema> modulos = new ArrayList<>();

	@ManyToMany(mappedBy="usuarios", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Perfil> pefis = new HashSet<>();

	public UsuarioPolice() {
	}

	public UsuarioPolice(Integer id, String login) {
		super();
		this.id = id;
		this.login = login;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List<ModuloSistema> getModulos() {
		return modulos;
	}

	public void setModulos(List<ModuloSistema> modulos) {
		this.modulos = modulos;
	}

	public Set<Perfil> getPefis() {
		return pefis;
	}

	public void setPefis(Set<Perfil> pefis) {
		this.pefis = pefis;
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
		UsuarioPolice other = (UsuarioPolice) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
