package vista;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.annotations.JsonAdapter;

import modelo.entidad.Coche;
import modelo.negocio.GestorCoche;
import modelo.negocio.GestorUsuario;

public class MainCoche {

	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
		
		int contador = 1;
		boolean registrado;
		System.out.println("Bienvenido a la base de datos del Concesionario Pando");
		System.out.println("Posee de 3 intentos para acceder a nuestra aplicación con tu usuario y contraseña");
		System.out.println("En caso de agotar dichos intentos, por motivos de seguridad se bloqueará tu cuenta\n");

		do {		
			
			System.out.print("Introduce un usuario: ");
			String username = sc.next();
			System.out.print("Introduce una contraseña: ");
			String password = sc.next();
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI("http://localhost:8080/Ejercicio22_ClienteWeb/usuarios/login?fName=" + username
							+ "&password=" + password))
					.GET().build();

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			JSONObject json = new JSONObject(response.body());
			
			if (json.getBoolean("valido")) {
				registrado = true;
				while (registrado == true && contador < 4) {

					System.out.println("\nEnhorabuena, te has registrado exitosamente en el intento " + contador);
					boolean salir = false;
					GestorCoche gc = new GestorCoche();

					do {
						menu();
						Coche coche = new Coche();
						int opcion = sc.nextInt();
						switch (opcion) {
						case 1:
							System.out.println(
									"Para proceder a dar de alta un coche, debe cumplir tres requisitos mínimos, los cuáles son: ");
							System.out.println("a) La matrícula tendrá una longitud mínima de 7 caracteres");
							System.out.println("b) El kilometraje no puede ser menos de 0");
							System.out.println(
									"c) No puede haber dos matrículas iguales en la base de datos (no repetir la matrícula al introducirla)\n");
							System.out.println(
									"Introduzca los datos del nuevo coche (matrícula - marca - modelo - kilometraje): ");

							String matricula = sc.next();
							String marca = sc.next();
							String modelo = sc.next();
							int km = sc.nextInt();

							coche.setMatricula(matricula);
							coche.setMarca(marca);
							coche.setModelo(modelo);
							coche.setKilometros(km);

							int alta = gc.alta(coche);

							if (alta == 0) {
								System.out.println("Coche dado de alta en el Concesionario\n");
							} else if (alta == 1) {
								System.out.println("Error al conectarte a la Base de Datos\n");
							} else if (alta == 2) {
								System.out.println(
										"Error al introducir el coche. No cumple con los requisítos mínimos previamente establecidos\n");
							}
							break;

						case 2:
							System.out.println("Introduzca el ID del coche que desea eliminar del Concesionario");
							int id = sc.nextInt();
							coche.setId(id);
							boolean baja = gc.baja(coche.getId());

							if (baja) {
								System.out.println("Coche dado de baja en el Concesionario\n");
							} else {
								System.out.println("Error al eliminar el coche. No existe en la base de datos\n");
							}
							break;

						case 3:
							System.out.println(
									"Para proceder a la modificación de un coche existente, debe cumplir tres requisitos mínimos, los cuáles son: ");
							System.out.println("a) La matrícula nueva tendrá una longitud mínima de 7 caracteres");
							System.out.println("b) El nuevo kilometraje no puede ser menos de 0");
							System.out.println(
									"c) No puede haber dos matrículas iguales en la base de datos (no repetir la matrícula al modificarla)\n");
							System.out.println("Introduzca el ID del coche que deseas modificar: ");
							int idM = sc.nextInt();
							coche.setId(idM);

							System.out.println("Introduzca los nuevos datos del coche que deseas modificar: ");
							String matriculaM = sc.next();
							String marcaM = sc.next();
							String modeloM = sc.next();
							int kmM = sc.nextInt();

							coche.setMatricula(matriculaM);
							coche.setMarca(marcaM);
							coche.setModelo(modeloM);
							coche.setKilometros(kmM);

							int modificado = gc.modificar(coche);

							if (modificado == 0) {
								System.out.println("Coche actualizado en el Concesionario\n");
							} else if (modificado == 1) {
								System.out.println("Error al conectarte a la Base de Datos\n");
							} else if (modificado == 2) {
								System.out.println(
										"Error al modificar el coche. No cumple con los requisitos mínimos previamente establecidos\n");
							}
							break;

						case 4:
							System.out.println("Introduce el ID del coche que desea buscar: ");
							int id2 = sc.nextInt();
							coche.setId(id2);

							Coche cocheID = gc.buscarPorId(id2);
							if (cocheID == null) {
								System.out.println("Lo siento, no existe ese ID en la Base de Datos \n");
							} else {
								System.out.println(cocheID.toString() + "\n");
							}
							break;

						case 5:
							System.out.println("Introduce la matrícula del coche que desea buscar: ");
							String matricula2 = sc.next();
							coche.setMatricula(matricula2);

							Coche cocheMatricula = gc.buscarPorMatricula(matricula2);
							if (cocheMatricula != null) {
								System.out.println(cocheMatricula.toString() + "\n");
							} else {
								System.out.println("Lo siento, no existe esa matrícula en la Base de Datos \n");
							}
							break;

						case 6:
							System.out.println("Introduce la marca del coche que desea buscar: ");
							String marca2 = sc.next();
							coche.setMarca(marca2);

							List<Coche> listadoCochesMarca = gc.buscarPorMarca(marca2);
							if (listadoCochesMarca == null) {
								System.out.println("Lo siento, no existe esa marca en la Base de Datos \n");
							} else {
								System.out.println(listadoCochesMarca.toString() + "\n");
							}
							break;

						case 7:
							System.out.println("Introduce el modelo del coche que desea buscar: ");
							String modelo2 = sc.next();
							coche.setMarca(modelo2);

							List<Coche> listadoCochesModelo = gc.buscarPorModelo(modelo2);
							if (listadoCochesModelo != null) {
								System.out.println(listadoCochesModelo.toString() + "\n");
							} else {
								System.out.println("Lo siento, no existe ese modelo en la Base de Datos");
							}
							break;

						case 8:
							List<Coche> listadoCoches = gc.listarCoches();
							System.out.println(listadoCoches.toString() + "\n");
							break;

						case 9:
							try {
								List<Coche> lista_Coches = gc.listarCoches();

								Gson gson = new Gson();

								Writer writer = Files.newBufferedWriter(Paths.get("coches.json"));

								gson.toJson(lista_Coches, writer);

								System.out.println("Coches exportados correctamente\n");

								writer.close();
							} catch (Exception ex) {
								ex.printStackTrace();
							}

							break;
						case 10:
							try {
								gc.generarFicheroPdf();
								System.out.println("Se ha creado el fichero satisfactoriamente\n");
							} catch (Exception e) {
								System.out.println("Ha ocurrido un error generando el fichero");
							}

							break;
						case 0:
							salir = true;
							break;
						}
					} while (!salir);

					System.out.println("Saliendo del programa...");				
				} 				
			} else {
				System.out.println("Usuario o contraseña invalido. Vuelve a intentar registrarte\n");	
				contador++;
			}
		} while (contador < 4);
		System.out.println("Los intentos para registrarte en nuestro concesionario han sido agotados.");
		System.out.println("Se procede a bloquear tu cuenta");
	}

	public static void menu() {
		System.out.println("Elija una opción: ");
		System.out.println("1. Alta de coche");
		System.out.println("2. Baja de coche");
		System.out.println("3. Modificación de coche");
		System.out.println("4. Buscar coche por ID");
		System.out.println("5. Buscar coche por Matrícula");
		System.out.println("6. Buscar coche por Marca");
		System.out.println("7. Buscar coche por Modelo");
		System.out.println("8. Listado de todos los coches del concesionario");
		System.out.println("9. Exportar los coches a un fichero en formato JSON");
		System.out.println("10. Exportar los coches a un fichero en formato PDF");
		System.out.println("0. Salir de la aplicación");
	}
}
