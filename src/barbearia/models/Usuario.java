package src.barbearia.models;

public class Usuario {
    private String nome;
    private String senha;
    private String email;

    public Usuario(String nome, String senha, String email) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
    }

    public Usuario() {
    }

    // Metodo de login, compara a senha digitada com a senha guardada
    public boolean usuarioLogin(String senha) {
        if (senha.equals(getSenha())) {
            System.out.println("\nLogin realizado com sucesso!");
            return true;
        } else {
            System.out.println("\nUsuário ou senha incorretos!");
            return false;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // To String
    @Override
    public String toString() {
        return "Usuario {nome=" + getNome() + ", senha=" + getSenha() + ", email=" + getEmail() + "}";
    }
}