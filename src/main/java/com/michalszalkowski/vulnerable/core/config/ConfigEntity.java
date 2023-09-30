package com.michalszalkowski.vulnerable.core.config;

import javax.persistence.*;

@Entity
@Table(name = "config")
public class ConfigEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String ckey;
	private String cvalue;

	public ConfigEntity() {
	}

	public ConfigEntity(String ckey, String cvalue) {
		this.ckey = ckey;
		this.cvalue = cvalue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCkey() {
		return ckey;
	}

	public void setCkey(String key) {
		this.ckey = key;
	}

	public String getCvalue() {
		return cvalue;
	}

	public void setCvalue(String value) {
		this.cvalue = value;
	}
}
