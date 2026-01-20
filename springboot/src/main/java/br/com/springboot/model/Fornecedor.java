	package br.com.springboot.model;
	
	import org.hibernate.validator.constraints.br.CNPJ;
	
	import jakarta.persistence.Column;
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.Id;
	import jakarta.persistence.Table;
	import jakarta.validation.constraints.Email;
	import jakarta.validation.constraints.NotBlank;
	import jakarta.validation.constraints.Size;
	
	
	@Entity
	@Table(name="fornecedores")
	public class Fornecedor {
		@Id
		@GeneratedValue
		private Long id;
		@Column(nullable = false, length = 50)
		@NotBlank(message = "Informe o Nome Fantasia")
		@Size(min = 3, max = 50)
		private String nomeFantasia;
		@Column(nullable = false, length = 50)
		@NotBlank(message = "Informe o Razão Social")
		@Size(min = 3, max = 50)
		private String razaoSocial;
		@Column (nullable = false, length = 18, unique = true)
		@CNPJ(message = "CNPJ Inválido ou duplicado!")
		private String cnpj;
		@Column(length = 10)
		private String telefone;
		@Column(length = 11)
		private String celular;
		@Column(length = 50)
		@Email
		private String email;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getNomeFantasia() {
			return nomeFantasia;
		}
		public void setNomeFantasia(String nomeFantasia) {
			this.nomeFantasia = nomeFantasia;
		}
		public String getRazaoSocial() {
			return razaoSocial;
		}
		public void setRazaoSocial(String razaoSocial) {
			this.razaoSocial = razaoSocial;
		}
		public String getCnpj() {
			return cnpj;
		}
		public void setCnpj(String cnpj) {
			this.cnpj = cnpj;
		}
		public String getTelefone() {
			return telefone;
		}
		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}
		public String getCelular() {
			return celular;
		}
		public void setCelular(String celular) {
			this.celular = celular;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public boolean isAtivo() {
			return ativo;
		}
		public void setAtivo(boolean ativo) {
			this.ativo = ativo;
		}
		private boolean ativo;
	}
		