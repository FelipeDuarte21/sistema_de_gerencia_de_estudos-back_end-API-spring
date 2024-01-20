package br.com.felipeduarte.gerenciadordeestudo.entity.enums;

public enum TipoUsuario {
	
	ADMIN(0,"admin"),
	USER(1,"user");
	
	private int codigo;
	private String descricao;
	
	private TipoUsuario(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public boolean isAdmin() {
		return this == ADMIN;
	}
	
	public boolean isUser() {
		return this == USER;
	}
	
	public static TipoUsuario toEnum(Integer codigo) {
		
		for(TipoUsuario u: TipoUsuario.values()) {
			if(codigo == u.codigo) {
				return u;
			}
		}
		
		return TipoUsuario.USER;
		
	}

}
