package org.example.modelo;

public enum NivelUrgencia {
    CRITICO(1),
    ALTO(2),
    MEDIO(3),
    BAJO(4);

    private final int peso;

    NivelUrgencia(int peso) {
        this.peso = peso;
    }

    public int getPeso() {
        return peso;
    }
}
