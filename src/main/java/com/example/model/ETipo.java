package com.example.model;

public enum ETipo {
    DEBITO(1, "Débito", ENatureza.E),
    BOLETO(2, "Boleto", ENatureza.S),
    FINANCIAMENTO(3, "Financiamento", ENatureza.S),
    CREDITO(4, "Credito", ENatureza.E),
    RECEBIMENTO_EMP(5, "Recibemento Emprestimo", ENatureza.E),
    VENDAS(6, "Vendas", ENatureza.E),
    RECEBIMENTO_TED(7, "Recibemento TED", ENatureza.E),
    RECEBIMENTO_DOC(8, "Recibemento DOC", ENatureza.E),
    ALUGUEL(9, "Aluguel", ENatureza.S);

    private final Integer id;
    private final String descricao;
    private final ENatureza natureza;

    ETipo(Integer id, String descricao, ENatureza natureza) {
        this.id = id;
        this.descricao = descricao;
        this.natureza = natureza;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public ENatureza getNatureza() {
        return natureza;
    }

    public enum ENatureza {
        E("+"), S("-");

        String sinal;

        ENatureza(String sinal) {
            this.sinal = sinal;
        }
    }

    public static ETipo getETipoById(int id) {
        for (ETipo value : values()) {
            if (id == value.getId()) {
                return value;
            }
        }
        return null;
    }
}
