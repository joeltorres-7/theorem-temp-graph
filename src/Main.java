import java.util.*;

class GrafoRutas {
    private Map<String, List<String>> grafo = new HashMap<>();

    // Agregar ciudad (vértice)
    public void agregarCiudad(String ciudad) {
        grafo.putIfAbsent(ciudad, new ArrayList<>());
    }

    // Agregar carretera (arista)
    public void agregarCarretera(String ciudad1, String ciudad2) {
        grafo.get(ciudad1).add(ciudad2);
        grafo.get(ciudad2).add(ciudad1);
    }

    // Mostrar rutas
    public void mostrarRutas() {
        for (String ciudad : grafo.keySet()) {
            System.out.println(ciudad + " -> " + grafo.get(ciudad));
        }
    }

    // Verificar si hay camino entre dos ciudades
    public boolean hayCamino(String origen, String destino) {
        Set<String> visitado = new HashSet<>();
        Queue<String> cola = new LinkedList<>();
        cola.add(origen);

        while (!cola.isEmpty()) {
            String actual = cola.poll();
            if (actual.equals(destino)) return true;

            for (String vecina : grafo.get(actual)) {
                if (visitado.add(vecina)) {
                    cola.add(vecina);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        GrafoRutas grafo = new GrafoRutas();
        grafo.agregarCiudad("Ciudad A");
        grafo.agregarCiudad("Ciudad B");
        grafo.agregarCiudad("Ciudad C");
        grafo.agregarCiudad("Ciudad D");

        grafo.agregarCarretera("Ciudad A", "Ciudad B");
        grafo.agregarCarretera("Ciudad A", "Ciudad C");
        grafo.agregarCarretera("Ciudad B", "Ciudad D");

        grafo.mostrarRutas();
        System.out.println("¿Hay camino entre A y D? " + grafo.hayCamino("Ciudad A", "Ciudad D"));
    }
}
