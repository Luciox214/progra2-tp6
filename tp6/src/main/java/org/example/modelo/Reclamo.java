package org.example.modelo;


public class Reclamo {
    private final String titulo;
    private final String descripcion;
    private final NivelUrgencia urgencia;

    public Reclamo(String titulo, String descripcion, NivelUrgencia urgencia) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.urgencia = urgencia;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s", urgencia.name(), titulo, descripcion);
    }
}