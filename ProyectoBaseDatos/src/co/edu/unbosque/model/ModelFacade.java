package co.edu.unbosque.model;

import co.edu.unbosque.model.persistence.ArlDAO;
import co.edu.unbosque.model.persistence.BonificacionDAO;
import co.edu.unbosque.model.persistence.BonificacionNominaDAO;
import co.edu.unbosque.model.persistence.CajaCompensacionDAO;
import co.edu.unbosque.model.persistence.ContratoDAO;
import co.edu.unbosque.model.persistence.DeduccionDAO;
import co.edu.unbosque.model.persistence.DeduccionNominaDAO;
import co.edu.unbosque.model.persistence.DepartamentoDAO;
import co.edu.unbosque.model.persistence.EpsDAO;
import co.edu.unbosque.model.persistence.LiquidacionDefinitivaDAO;
import co.edu.unbosque.model.persistence.NominaDAO;
import co.edu.unbosque.model.persistence.PensionDAO;
import co.edu.unbosque.model.persistence.PersonaDAO;
import co.edu.unbosque.model.persistence.PrestacionSocialDAO;
import co.edu.unbosque.model.persistence.ReporteDAO;
import co.edu.unbosque.model.persistence.SeguridadSocialDAO;

public class ModelFacade {

	private PersonaDAO personaDAO;
	private DepartamentoDAO departamentoDAO;
	private ArlDAO arlDAO;
	private BonificacionDAO bonificacionDAO;
	private BonificacionNominaDAO bonificacionNominaDAO;
	private CajaCompensacionDAO cajaCompensacionDAO;
	private ContratoDAO contratoDAO;
	private DeduccionDAO deduccionDAO;
	private DeduccionNominaDAO deduccionNominaDAO;
	private EpsDAO epsDAO;
	private LiquidacionDefinitivaDAO liquidacionDefinitivaDAO;
	private NominaDAO nominaDAO;
	private PensionDAO pensionDAO;
	private PrestacionSocialDAO prestacionDAO;
	private SeguridadSocialDAO seguridadSocialDAO;
	private ReporteDAO reporteDAO;

	public ModelFacade() {
		personaDAO = new PersonaDAO();
		departamentoDAO = new DepartamentoDAO();
		arlDAO = new ArlDAO();
		bonificacionDAO = new BonificacionDAO();
		bonificacionNominaDAO = new BonificacionNominaDAO();
		cajaCompensacionDAO = new CajaCompensacionDAO();
		contratoDAO = new ContratoDAO();
		deduccionDAO = new DeduccionDAO();
		deduccionNominaDAO = new DeduccionNominaDAO();
		epsDAO = new EpsDAO();
		liquidacionDefinitivaDAO = new LiquidacionDefinitivaDAO();
		nominaDAO = new NominaDAO();
		pensionDAO = new PensionDAO();
		prestacionDAO = new PrestacionSocialDAO();
		seguridadSocialDAO = new SeguridadSocialDAO();
		reporteDAO = new ReporteDAO();
	}

	public PersonaDAO getPersonaDAO() {
		return personaDAO;
	}

	public void setPersonaDAO(PersonaDAO personaDAO) {
		this.personaDAO = personaDAO;
	}

	public DepartamentoDAO getDepartamentoDAO() {
		return departamentoDAO;
	}

	public void setDepartamentoDAO(DepartamentoDAO departamentoDAO) {
		this.departamentoDAO = departamentoDAO;
	}

	public ArlDAO getArlDAO() {
		return arlDAO;
	}

	public void setArlDAO(ArlDAO arlDAO) {
		this.arlDAO = arlDAO;
	}

	public BonificacionDAO getBonificacionDAO() {
		return bonificacionDAO;
	}

	public void setBonificacionDAO(BonificacionDAO bonificacionDAO) {
		this.bonificacionDAO = bonificacionDAO;
	}

	public BonificacionNominaDAO getBonificacionNominaDAO() {
		return bonificacionNominaDAO;
	}

	public void setBonificacionNominaDAO(BonificacionNominaDAO bonificacionNominaDAO) {
		this.bonificacionNominaDAO = bonificacionNominaDAO;
	}

	public CajaCompensacionDAO getCajaCompensacionDAO() {
		return cajaCompensacionDAO;
	}

	public void setCajaCompensacionDAO(CajaCompensacionDAO cajaCompensacionDAO) {
		this.cajaCompensacionDAO = cajaCompensacionDAO;
	}

	public ContratoDAO getContratoDAO() {
		return contratoDAO;
	}

	public void setContratoDAO(ContratoDAO contratoDAO) {
		this.contratoDAO = contratoDAO;
	}

	public DeduccionDAO getDeduccionDAO() {
		return deduccionDAO;
	}

	public void setDeduccionDAO(DeduccionDAO deduccionDAO) {
		this.deduccionDAO = deduccionDAO;
	}

	public DeduccionNominaDAO getDeduccionNominaDAO() {
		return deduccionNominaDAO;
	}

	public void setDeduccionNominaDAO(DeduccionNominaDAO deduccionNominaDAO) {
		this.deduccionNominaDAO = deduccionNominaDAO;
	}

	public EpsDAO getEpsDAO() {
		return epsDAO;
	}

	public void setEpsDAO(EpsDAO epsDAO) {
		this.epsDAO = epsDAO;
	}

	public LiquidacionDefinitivaDAO getLiquidacionDefinitivaDAO() {
		return liquidacionDefinitivaDAO;
	}

	public void setLiquidacionDefinitivaDAO(LiquidacionDefinitivaDAO liquidacionDefinitivaDAO) {
		this.liquidacionDefinitivaDAO = liquidacionDefinitivaDAO;
	}

	public NominaDAO getNominaDAO() {
		return nominaDAO;
	}

	public void setNominaDAO(NominaDAO nominaDAO) {
		this.nominaDAO = nominaDAO;
	}

	public PensionDAO getPensionDAO() {
		return pensionDAO;
	}

	public void setPensionDAO(PensionDAO pensionDAO) {
		this.pensionDAO = pensionDAO;
	}

	public PrestacionSocialDAO getPrestacionDAO() {
		return prestacionDAO;
	}

	public void setPrestacionDAO(PrestacionSocialDAO prestacionDAO) {
		this.prestacionDAO = prestacionDAO;
	}

	public SeguridadSocialDAO getSeguridadSocialDAO() {
		return seguridadSocialDAO;
	}

	public void setSeguridadSocialDAO(SeguridadSocialDAO seguridadSocialDAO) {
		this.seguridadSocialDAO = seguridadSocialDAO;
	}

	public ReporteDAO getReporteDAO() {
		return reporteDAO;
	}

	public void setReporteDAO(ReporteDAO reporteDAO) {
		this.reporteDAO = reporteDAO;
	}

}
