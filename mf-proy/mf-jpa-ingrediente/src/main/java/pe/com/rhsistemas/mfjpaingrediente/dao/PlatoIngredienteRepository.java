/**
 * 
 */
package pe.com.rhsistemas.mfjpaingrediente.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.com.rhsistemas.mfjpaingrediente.entity.PlatoIngrediente;
import pe.com.rhsistemas.mfjpaingrediente.entity.PlatoIngredienteInter;
import pe.com.rhsistemas.mfjpaingrediente.entity.PlatoIngredientePK;


/**
 * @author Edwin
 *
 */
public interface PlatoIngredienteRepository extends JpaRepository<PlatoIngrediente, PlatoIngredientePK> {

	@Query(value = "Select pi from PlatoIngrediente pi where pi.id.idPlato = ?1")
	public List<PlatoIngrediente> findAllByPlato(Integer idPlato);
	
	@Query(value = "Select pi from PlatoIngrediente pi inner join MenuDetalle md on md.idPlato = pi.id.idPlato where md.id.idGenerado = ?1")
	public List<PlatoIngrediente> listaIngredientesMenu(Integer idMenu);
	
	@Query(value = "select pi2.id.idPlato as idPlato, pi2.id.idIngrediente as idIngrediente, i.deIngrediente as deIngrediente, SUM(case when pi2.nuCantidad is null then 0 else pi2.nuCantidad end) as totalIngrediente, um.idUnidMedi as idUnidadMedida, um.deUnidMedi as deUnidadMedida"
			+ "  from PlatoIngrediente pi2 "
			+ "inner join MenuDetalle md on md.idPlato = pi2.id.idPlato "
			+ "inner join Ingrediente i on i.idIngrediente = pi2.id.idIngrediente "
			+ "inner join UnidadMedida um on um.idUnidMedi = pi2.unidadMedida.idUnidMedi "
			+ "where md.id.idGenerado = ?1 "
			+ " GROUP BY pi2.id.idPlato, pi2.id.idIngrediente, i.deIngrediente, um.idUnidMedi, um.deUnidMedi")
	public List<PlatoIngredienteInter> cantidadIngredientePlatoIngrediente(Long idMenu);
}
