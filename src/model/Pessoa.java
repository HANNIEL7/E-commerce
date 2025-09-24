package model;

public class Pessoa {
    protected String login;
    protected String senha;
    protected String cpf;
    protected int idade;

    public Pessoa(String login, String senha, String cpf, int idade) {
        if (cpf == null) throw new IllegalArgumentException("CPF não pode ser nulo");
        if (!validarCPF(cpf)) {
            throw new IllegalArgumentException("Cpf inválido : " + cpf);
        }
        if (idade < 18) {
            throw new IllegalArgumentException("Acesso negado: menor de 18 anos.");
        }
        this.login = login;
        this.senha = senha;
        this.cpf = cpf.replaceAll("\\D", "");
        this.idade = idade;
    }

    public String getLogin() { return login; }
    public String getCpf() { return cpf; }
    public int getIdade() { return idade; }

    public void visualizarDados() {
        System.out.println("Login: " + login);
        System.out.println("CPF: " + cpf);
        System.out.println("Idade: " + idade);
    }

    // Validação do CPF (dígitos verificadores)
    public static boolean validarCPF(String cpf) {
        if (cpf == null) return false;
        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() != 11) return false;
        if (cpf.matches("(\\d)\\1{10}")) return false; // todos os dígitos iguais

        try {
            int soma1 = 0, soma2 = 0;
            for (int i = 0; i < 9; i++) {
                int digito = Character.getNumericValue(cpf.charAt(i));
                soma1 += digito * (10 - i);
                soma2 += digito * (11 - i);
            }

            int resto1 = (soma1 * 10) % 11;
            if (resto1 == 10) resto1 = 0;
            if (resto1 != Character.getNumericValue(cpf.charAt(9))) return false;

            soma2 += resto1 * 2;
            int resto2 = (soma2 * 10) % 11;
            if (resto2 == 10) resto2 = 0;
            if (resto2 != Character.getNumericValue(cpf.charAt(10))) return false;

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}