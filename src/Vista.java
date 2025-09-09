import java.util.Random;
import java.util.Scanner;

public class Vista {
    private final Scanner sc;
    private final Random rnd;
    private final JuegoController controller;
    private boolean jugarOtra;

    public Vista() {
        this(new Scanner(System.in), new Random(), new JuegoController());
    }

    public Vista(Scanner sc, Random rnd, JuegoController controller) {
        this.sc = sc;
        this.rnd = rnd;
        this.controller = controller;
        this.jugarOtra = true;
    }

    public void ejecutar() {
        System.out.println("Memoriaaaaa");
        while (jugarOtra) {
            jugarPartida();
            System.out.print("\n¿Jugar otra vez? (s/n): ");
            String resp = sc.nextLine().trim().toLowerCase();
            jugarOtra = resp.startsWith("s");
        }
        System.out.println("\nGracias por jugar :)");
        sc.close();
    }

    private void jugarPartida() {
        int filas = leerEntero("cuantas filas quieres (max 6): ");
        int cols  = leerEntero("cuantas columanas quieres (max 6): ");

        if (filas > 6 || cols > 6) {
            System.out.println("El tamaño máximo permitido es 6x6");
            return;
        }

        System.out.print("Nombre Jugador 1: ");
        String n1 = sc.nextLine().trim();
        System.out.print("Nombre Jugador 2: ");
        String n2 = sc.nextLine().trim();

        try {
            controller.nuevaPartida(filas, cols, simbolosBase(), rnd, n1, n2);
        } catch (Exception e) {
            System.out.println("No se pudo iniciar la partida: " + e.getMessage());
            return;
        }

        while (!controller.getJuego().terminado()) {
            String turnoDe = controller.getJuego().getJugadorActual().getNombre();
            System.out.println("\nTurno de: " + turnoDe);
            dibujar(controller.getJuego().getTablero().snapshotVisible());

            try {
                int r1 = leerEntero("Fila 1: ");
                int c1 = leerEntero("Col 1: ");
                controller.seleccionarPrimera(r1, c1);
                dibujar(controller.getJuego().getTablero().snapshotVisible());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                continue;
            }

            boolean pareja = false;
            boolean segundaHecha = false;
            while (!segundaHecha) {
                try {
                    int r2 = leerEntero("Fila 2: ");
                    int c2 = leerEntero("Col 2: ");
                    pareja = controller.seleccionarSegundaYResolver(r2, c2);
                    dibujar(controller.getJuego().getTablero().snapshotVisible());
                    segundaHecha = true;
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            if (pareja) {
                System.out.println("¡Pareja! Punto para " + turnoDe);
            } else {
                System.out.println("No coinciden. Presiona Enter para continuar...");
                sc.nextLine();
                controller.ocultarSiNoPareja();
                dibujar(controller.getJuego().getTablero().snapshotVisible());
            }

            controller.alternarTurnoSiNoPareja(pareja);
            controller.limpiarSeleccion();
        }

        var j0 = controller.getJuego().getJugador(0);
        var j1 = controller.getJuego().getJugador(1);
        System.out.println("\nMarcador final:");
        System.out.println(j0.getNombre() + ": " + j0.getPares());
        System.out.println(j1.getNombre() + ": " + j1.getPares());

        if (j0.getPares() > j1.getPares()) {
            System.out.println("Ganador: " + j0.getNombre());
        } else if (j1.getPares() > j0.getPares()) {
            System.out.println("Ganador: " + j1.getNombre());
        } else {
            System.out.println("¡Empate!");
        }
    }

    private int leerEntero(String tx) {
        boolean ok = false;
        int valor = 0;
        while (!ok) {
            System.out.print(tx);
            if (sc.hasNextInt()) {
                valor = sc.nextInt();
                sc.nextLine();
                ok = true;
            } else {
                sc.nextLine();
                System.out.println("Entrada inválida. Intenta de nuevo.");
            }
        }
        return valor;
    }

    private java.util.List<String> simbolosBase() {
        java.util.List<String> s = new java.util.ArrayList<>();
        s.add("🐶"); s.add("🐱"); s.add("🐭"); s.add("🐹");
        s.add("🐰"); s.add("🦊"); s.add("🐻"); s.add("🐼");
        s.add("🍎"); s.add("🍌"); s.add("🍇"); s.add("🍉");
        s.add("🍓"); s.add("🍒"); s.add("🍍"); s.add("🥑");
        s.add("⚽"); s.add("🏀"); s.add("🎲"); s.add("🎸");
        s.add("🚗"); s.add("✈️"); s.add("🚀"); s.add("🚲");
        return s;
    }

    private void dibujar(String[][] rep) {
        int filas = rep.length, cols = rep[0].length;
        System.out.print("    ");
        for (int c = 0; c < cols; c++) System.out.printf("%3d", c);
        System.out.println();
        for (int r = 0; r < filas; r++) {
            System.out.printf("%3d ", r);
            for (int c = 0; c < cols; c++) System.out.printf("%3s", rep[r][c]);
            System.out.println();
        }
    }
}
