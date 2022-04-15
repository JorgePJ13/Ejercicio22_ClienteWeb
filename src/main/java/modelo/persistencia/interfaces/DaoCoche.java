package modelo.persistencia.interfaces;

import java.util.List;
import modelo.entidad.Coche;

public interface DaoCoche {

	/**
	 * Metodo que da de alta un coche en la BBDD. Se generar� el ID de manera
	 * autom�tica.
	 * @param c el coche a dar de alta
	 * @return true en caso de que se haya dado de alta. false en caso de error
	 * con la BBDD.
	 */
	boolean alta(Coche c);
	
	/**
	 * Metodo que da de baja un coche en la BBDD si el ID introducido coincide con alguna de la BBDD.
	 * @param id el identificador del coche a dar de baja
	 * @return true en caso de que se haya dado de baja. false en caso de error
	 * con la BBDD.
	 */
	boolean eliminar(int id);
	
	/**
	 * M�todo que modifica los datos de un coche previamente introducido en la BBDD, seg�n el ID del coche.
	 * @param c el coche a modificar 
	 * @return true en caso de que se haya modificado. false en caso de error con la BBDD.
	 */
	boolean modificar(Coche c);
	
	/**
	 * M�todo que muestra un listado todos los coches que hay en la BBDD
	 * @return la lista de coches habidos en la BBDD
	 */
	List<Coche> listar();
	
	/**
	 * M�todo que busca un determinado coche seg�n el identificador que posea
	 * @param id el identificador del coche a buscar
	 * @return el coche a buscar
	 */
	Coche buscarPorId(int id);
	
	/**
	 * M�todo que busca un determinado coche seg�n la matr�cula que posea
	 * @param matricula la matricula que posee el coche
	 * @return el coche a buscar
	 */
	Coche buscarPorMatricula(String matricula);
	
	/**
	 * M�todo que busca un coche seg�n su marca
	 * @param marca la marca perteneciente al coche
	 * @return el coche a buscar
	 */
	List<Coche> buscarPorMarca(String marca);
	
	/**
	 * M�todo que busca un coche seg�n el tipo de modelo
	 * @param modelo el modelo del coche (que tipo es)
	 * @return el coche a buscar
	 */
	List<Coche> buscarPorModelo(String modelo);
}
