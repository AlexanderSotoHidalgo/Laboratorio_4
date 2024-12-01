package Proyecto_Renacer;

import java.util.*;
public class Proyecto_Renacer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FilaAtencionPacientes filaAtencion = new FilaAtencionPacientes();
        RegistroPaciente registroPaciente = new RegistroPaciente();
        AgendaCitasMedicas agendaCitas = new AgendaCitasMedicas();
        RegistroMedicos registroMedicos = new RegistroMedicos();
        InventarioMedicamentos inventario = new InventarioMedicamentos();
        ExpedienteMedico expediente = new ExpedienteMedico();
        new Recepcionista("admin", "1234");
        new Recepcionista("recep1", "password1");
        new Recepcionista("recep2", "password2");
        System.out.println("Ingrese el nombre de usuario: ");
        String usuarioIngresado = scanner.nextLine();
        System.out.println("Ingrese la contraseña: ");
        String contrasenaIngresada = scanner.nextLine();
        // Validar login
        if (Recepcionista.validarLogin(usuarioIngresado, contrasenaIngresada)) {
            System.out.println("¡Login exitoso! Accediendo a las opciones...");
            while (true) {
            System.out.println("\nBienvenido al Sistema de Gestión Médica");
            System.out.println("1. Registrar paciente");
            System.out.println("2. Consultar información de paciente");
            System.out.println("3. Programar cita médica");
            System.out.println("4. Consultar historial médico");
            System.out.println("5. Registrar médico");
            System.out.println("6. Consultar Lista de Pacientes");
            System.out.println("7. Control de inventario de medicamentos");
            System.out.println("8. Generar reportes de actividad");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    // Registrar paciente
                    System.out.print("Ingrese el DNI");
                    String DNIPaciente = scanner.nextLine();
                    System.out.print("Ingrese nombre del paciente: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese apellido del paciente: ");
                    String apellido = scanner.nextLine();
                    System.out.print("Ingrese edad del paciente: ");
                    int edad = scanner.nextInt();
                    scanner.nextLine();  // Limpiar buffer
                    System.out.print("Ingrese historial médico del paciente: ");
                    String historial = scanner.nextLine();

                    System.out.print("Ingrese prioridad del paciente (0 para leve, 1 para urgente): ");
                    int prioridad = scanner.nextInt();
                    scanner.nextLine();  // Limpiar buffer
                    Paciente paciente = new Paciente(DNIPaciente, nombre, apellido, edad, historial, prioridad);
                    registroPaciente.registrarPaciente(paciente);  // Guardar en la lista y archivo
                    filaAtencion.agregarPaciente(paciente);
                    
                    break;

                case 2:
                    // Consultar paciente por DNI
                    System.out.print("Ingrese el DNI del paciente a consultar: ");
                    String DNIBuscar = scanner.nextLine();
                    Paciente pacienteEncontrado = registroPaciente.buscarPacientePorDNI(DNIBuscar);
                    if (pacienteEncontrado != null) {
                        System.out.println("Paciente encontrado: " + pacienteEncontrado);
                    } else {
                        System.out.println("Paciente no encontrado.");
                    }
                    break;

                case 3:
                    // Programar cita médica
                    System.out.print("Ingrese el nombre del paciente: ");
                    String nombreCita = scanner.nextLine();
                    System.out.print("Ingrese fecha de la cita (YYYY-MM-DD): ");
                    String fechaCita = scanner.nextLine();
                    System.out.print("Ingrese hora de la cita (HH:MM): ");
                    String horaCita = scanner.nextLine();
                    System.out.print("Ingrese el nombre del médico: ");
                    String nombreMedico = scanner.nextLine();
                    System.out.print("Ingrese especialidad médica: ");
                    String especialidad = scanner.nextLine();
                    Medico medico = new Medico(nombreMedico, especialidad, "9:00 - 18:00");
                    Cita cita = new Cita(nombreCita, fechaCita, horaCita, medico);
                    agendaCitas.agregarCita(cita);
                    break;

                case 4:
                    // Consultar historial médico
                    System.out.print("Ingrese el DNI del paciente a consultar el historial: ");
                    String DNIHistorial = scanner.nextLine();
                    Paciente pacienteHistorial = registroPaciente.buscarPacientePorDNI(DNIHistorial);
                    if (pacienteHistorial != null) {
                        expediente.mostrarHistorial();
                    } else {
                        System.out.println("Paciente no encontrado.");
                    }
                    break;

                case 5:
                    // Registrar médicos
                    System.out.print("Ingrese nombre del médico: ");
                    String nombreMedicoRegistro = scanner.nextLine();
                    System.out.print("Ingrese especialidad del médico: ");
                    String especialidadMedico = scanner.nextLine();
                    Medico medicoRegistro = new Medico(nombreMedicoRegistro, especialidadMedico, "9:00 - 18:00");
                    registroMedicos.agregarMedico(medicoRegistro);
                    break;
                    case 6:
                    registroPaciente.mostrarPacientesOrdenados();
                    break;


                case 7:
                    // Control de inventario de medicamentos
                    System.out.print("Ingrese el nombre del medicamento: ");
                    String nombreMedicamento = scanner.nextLine();
                    System.out.print("Ingrese cantidad disponible: ");
                    int cantidad = scanner.nextInt();
                    System.out.print("Ingrese la fecha de expiración: ");
                    String fechaExpiracion = scanner.next();
                    Medicamento medicamento = new Medicamento(nombreMedicamento, cantidad, fechaExpiracion);
                    inventario.agregarMedicamento(medicamento);
                    break;

                case 8:
                    // Generar reportes de actividad
                    System.out.println("Generando reporte de actividad...");
                    // Aquí puedes agregar lógica para generar reportes (no implementado)
                    break;

                case 9:
                    System.out.println("¡Hasta luego!");
                    scanner.close();
                    return; // Salir del programa

                default:
                    System.out.println("Opción inválida. Por favor, elija nuevamente.");
            }
        }
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }

        scanner.close();
    }
}
