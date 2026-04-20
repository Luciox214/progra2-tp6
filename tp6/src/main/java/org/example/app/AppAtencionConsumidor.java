package org.example.app;

import org.example.modelo.NivelUrgencia;
import org.example.modelo.Reclamo;
import org.example.tda.SimpleLinkedPriorityQueue;

import java.util.Scanner;

public class AppAtencionConsumidor {
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleLinkedPriorityQueue<Reclamo> colaReclamos = new SimpleLinkedPriorityQueue<>();

    public static void main(String[] args) {
        boolean ejecutando = true;

        while (ejecutando) {
            System.out.println("\n--- SISTEMA DE ATENCIÓN AL CONSUMIDOR ---");
            System.out.println("1. Redactar nuevo reclamo");
            System.out.println("2. Visualizar próximo reclamo");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = leerEnteroSeguro();

            switch (opcion) {
                case 1:
                    redactarReclamo();
                    break;
                case 2:
                    visualizarReclamo();
                    break;
                case 3:
                    ejecutando = false;
                    System.out.println("Cerrando sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Ingrese 1, 2 o 3.");
            }
        }
    }

    private static void redactarReclamo() {
        System.out.print("\nIngrese el título del reclamo: ");
        String titulo = leerTextoObligatorio();

        System.out.print("Ingrese la descripción: ");
        String descripcion = leerTextoObligatorio();

        System.out.println("Seleccione nivel de urgencia:");
        System.out.println("1. CRITICO | 2. ALTO | 3. MEDIO | 4. BAJO");
        NivelUrgencia urgencia = leerUrgenciaValida();

        Reclamo nuevoReclamo = new Reclamo(titulo, descripcion, urgencia);

        colaReclamos.enqueue(nuevoReclamo, urgencia.getPeso());
        System.out.println("Reclamo registrado exitosamente en la cola.");
    }

    private static void visualizarReclamo() {
        if (colaReclamos.isEmpty()) {
            System.out.println("\n¡Excelente! No hay reclamos pendientes en la cola.");
            return;
        }

        Reclamo proximo = colaReclamos.peek();

        System.out.println("\n--- PRÓXIMO RECLAMO A ATENDER ---");
        System.out.println(proximo.toString());
        System.out.println("---------------------------------");

        System.out.println("¿Qué desea hacer con este reporte?");
        System.out.println("1. Marcar como resuelto (Remover de la cola)");
        System.out.println("2. Dejar en la cola por ahora");

        int opcion = leerEnteroSeguro();

        if (opcion == 1) {
            colaReclamos.dequeue();
            System.out.println("Reclamo resuelto y eliminado del sistema.");
        } else {
            System.out.println("El reclamo fue conservado en la posición actual de la cola.");
        }
    }


    private static int leerEnteroSeguro() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Por favor, ingrese un número válido: ");
            }
        }
    }

    private static String leerTextoObligatorio() {
        while (true) {
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.print("Este campo no puede quedar vacío. Intente de nuevo: ");
        }
    }

    private static NivelUrgencia leerUrgenciaValida() {
        while (true) {
            int seleccion = leerEnteroSeguro();
            switch (seleccion) {
                case 1: return NivelUrgencia.CRITICO;
                case 2: return NivelUrgencia.ALTO;
                case 3: return NivelUrgencia.MEDIO;
                case 4: return NivelUrgencia.BAJO;
                default:
                    System.out.print("Nivel incorrecto. Ingrese un número del 1 al 4: ");
            }
        }
    }
}