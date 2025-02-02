import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Scanner

class Tarea(val id: Int, val descripcion: String) {
    var estado: Boolean = false  // false -> Pendiente, true -> Realizada
    var fechaRealizada: String? = null

    fun marcarComoRealizada() {
        if (!estado) {
            estado = true
            fechaRealizada = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
            println("Tarea \"$descripcion\" marcada como realizada el $fechaRealizada")
        } else {
            println("La tarea \"$descripcion\" ya estaba realizada.")
        }
    }

    override fun toString(): String {
        return if (estado) {
            "ID: $id | $descripcion | Estado: Realizada | Fecha: $fechaRealizada"
        } else {
            "ID: $id | $descripcion | Estado: Pendiente"
        }
    }
}

class ListaTareas {
    private val tareas = mutableListOf<Tarea>()
    private var contadorId = 1

    fun agregarTarea(descripcion: String) {
        val nuevaTarea = Tarea(contadorId++, descripcion)
        tareas.add(nuevaTarea)
        println("Tarea añadida: $nuevaTarea")
    }

    fun eliminarTarea(id: Int) {
        val tarea = tareas.find { it.id == id }
        if (tarea != null) {
            tareas.remove(tarea)
            println("Tarea eliminada: ${tarea.descripcion}")
        } else {
            println("No se encontró una tarea con ID $id")
        }
    }

    fun cambiarEstadoTarea(id: Int) {
        val tarea = tareas.find { it.id == id }
        if (tarea != null) {
            tarea.marcarComoRealizada()
        } else {
            println("No se encontró una tarea con ID $id")
        }
    }

    fun mostrarTareas() {
        if (tareas.isEmpty()) {
            println("No hay tareas registradas.")
        } else {
            println("Lista de todas las tareas:")
            tareas.forEach { println(it) }
        }
    }

    fun mostrarTareasPendientes() {
        val pendientes = tareas.filter { !it.estado }
        if (pendientes.isEmpty()) {
            println("No hay tareas pendientes.")
        } else {
            println("Tareas pendientes:")
            pendientes.forEach { println(it) }
        }
    }

    fun mostrarTareasRealizadas() {
        val realizadas = tareas.filter { it.estado }
        if (realizadas.isEmpty()) {
            println("No hay tareas realizadas.")
        } else {
            println("Tareas realizadas:")
            realizadas.forEach { println(it) }
        }
    }
}

fun main() {
    val listaTareas = ListaTareas()
    val scanner = Scanner(System.`in`)

    while (true) {
        println("\n--- Menú de Lista de Tareas ---")
        println("1. Agregar tarea")
        println("2. Eliminar tarea")
        println("3. Cambiar estado de tarea")
        println("4. Mostrar todas las tareas")
        println("5. Mostrar tareas pendientes")
        println("6. Mostrar tareas realizadas")
        println("0. Salir")
        print("Seleccione una opción: ")

        when (scanner.nextInt()) {
            1 -> {
                print("Ingrese la descripción de la tarea: ")
                scanner.nextLine()  // Limpiar buffer
                val descripcion = scanner.nextLine()
                listaTareas.agregarTarea(descripcion)
            }
            2 -> {
                print("Ingrese el ID de la tarea a eliminar: ")
                val id = scanner.nextInt()
                listaTareas.eliminarTarea(id)
            }
            3 -> {
                print("Ingrese el ID de la tarea a marcar como realizada: ")
                val id = scanner.nextInt()
                listaTareas.cambiarEstadoTarea(id)
            }
            4 -> listaTareas.mostrarTareas()
            5 -> listaTareas.mostrarTareasPendientes()
            6 -> listaTareas.mostrarTareasRealizadas()
            0 -> {
                println("Saliendo del programa...")
                return
            }
            else -> println("Opción inválida, intente de nuevo.")
        }
    }
}
