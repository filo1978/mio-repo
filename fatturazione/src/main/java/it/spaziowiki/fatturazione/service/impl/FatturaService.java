package it.spaziowiki.fatturazione.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import it.spaziowiki.fatturazione.entities.Attivita;
import it.spaziowiki.fatturazione.entities.CMese;
import it.spaziowiki.fatturazione.entities.Cliente;
import it.spaziowiki.fatturazione.entities.Fattura;
import it.spaziowiki.fatturazione.entities.StatoFattura;
import it.spaziowiki.fatturazione.entities.TipoFattura;
import it.spaziowiki.fatturazione.enums.MeseEnum;
import it.spaziowiki.fatturazione.enums.StatoFatturaEnum;
import it.spaziowiki.fatturazione.enums.TipoFatturaEnum;
import it.spaziowiki.fatturazione.exception.AttivitaSaveException;
import it.spaziowiki.fatturazione.exception.FatturaDeleteException;
import it.spaziowiki.fatturazione.exception.FatturaException;
import it.spaziowiki.fatturazione.form.AttivitaForm;
import it.spaziowiki.fatturazione.form.BozzaForm;
import it.spaziowiki.fatturazione.form.ClienteFatturaAnnoForm;
import it.spaziowiki.fatturazione.form.FatturaAnnoForm;
import it.spaziowiki.fatturazione.form.FatturaAnnoFormWrapper;
import it.spaziowiki.fatturazione.form.FatturaForm;
import it.spaziowiki.fatturazione.form.ImportoMeseForm;
import it.spaziowiki.fatturazione.form.PairDto;
import it.spaziowiki.fatturazione.form.TotaleFattureForm;
import it.spaziowiki.fatturazione.form.factory.BozzaFormFactory;
import it.spaziowiki.fatturazione.form.factory.FatturaFormFactory;
import it.spaziowiki.fatturazione.repository.AttivitaRepository;
import it.spaziowiki.fatturazione.repository.CMeseRepository;
import it.spaziowiki.fatturazione.repository.ClienteRepository;
import it.spaziowiki.fatturazione.repository.FatturaRepository;
import it.spaziowiki.fatturazione.repository.StatoFatturaRepository;
import it.spaziowiki.fatturazione.repository.TipoFatturaRepository;
import it.spaziowiki.fatturazione.service.IAttivitaService;
import it.spaziowiki.fatturazione.service.ICMeseService;
import it.spaziowiki.fatturazione.service.IFatturaService;

@Service
@Transactional
public class FatturaService implements IFatturaService {

	@Autowired
	private FatturaRepository fatturaRepository;
	
	@Autowired
	private FatturaFormFactory fatturaFormFactory;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private TipoFatturaRepository tipoFatturaRepository;
	
	@Autowired
	private AttivitaRepository attivitaRepository;
	
	@Autowired
	private IAttivitaService attivitaService;
	
	@Autowired
	private StatoFatturaRepository statoFatturaRepository;
	
	@Autowired
	private CMeseRepository meseRepository;
	
	@Autowired
	private BozzaFormFactory bozzaFormFactory;
	
	@Value("${iva_default}")
	private BigDecimal ivaDefault;
	
	@Override
	public List<FatturaForm> getAllFattureCliente(Integer idCliente,String codTipoFattura){
		TipoFattura tipoFattura = new TipoFattura();
		tipoFattura.setCod(codTipoFattura);
		return fatturaFormFactory.getList(fatturaRepository.findByClienteIdClienteAndTipoFattura(idCliente,tipoFattura));
	}

	
	
	@Override
	public Integer insert(FatturaForm fatturaForm) throws FatturaException{
		checkInsert(fatturaForm);
		Fattura fattura= new Fattura();
		Cliente cliente=clienteRepository.findById(fatturaForm.getIdCliente()).get();
		fattura.setCliente(cliente);
		fattura.setIdFattura(getIdFattura());
		if(StringUtils.hasText(fatturaForm.getDtFattura())){
		
			try {
				fattura.setDtFattura(new SimpleDateFormat("dd/MM/yyyy").parse(fatturaForm.getDtFattura()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		fattura.setTipoFattura(tipoFatturaRepository.findById(fatturaForm.getCodTipo()).get());
		fattura.setIdBollo(fatturaForm.getIdBollo());
		fattura.setImportoNetto(fatturaForm.getImportoNetto());
		fattura.setIva(fatturaForm.getIva());
		fattura.setOggetto(fatturaForm.getOggetto());
		fattura.setStatoFattura(getStatoFattura(fatturaForm));
		if(fatturaForm.isTipoFattura())
			fattura.setNumeroFattura(getNumeroFattura(cliente,fattura));
		if(StringUtils.hasText(fatturaForm.getCodMese())) {
			CMese mese= meseRepository.findById(fatturaForm.getCodMese()).get();
			fattura.setMese(mese);
		}
		if(fatturaForm.getCodTipo().equals(TipoFatturaEnum.BOZZA.getCod())) {
			fattura.setIva(ivaDefault);
			fattura.setImportoNetto(new BigDecimal(0));
		}
		fatturaRepository.save(fattura);
		return fattura.getIdFattura();
	}
	
	private StatoFattura getStatoFattura(FatturaForm fatturaForm) {
		if(!StringUtils.hasLength(fatturaForm.getCodStato()))
			return null;
		 return statoFatturaRepository.findById(fatturaForm.getCodStato().trim()).get();	
	}

	private void checkInsert(FatturaForm fatturaForm) throws FatturaException{
		if(!fatturaForm.isTipoFattura())
			return;
		if(!StringUtils.hasLength(fatturaForm.getDtFattura()))
			throw new FatturaException("Data fattura obbligatoria");
		if(!StringUtils.hasLength(fatturaForm.getIdBollo()))
			throw new FatturaException("Bollo obbligatorio");
		Fattura fattura=fatturaRepository.findByIdBollo(fatturaForm.getIdBollo());
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		if(fattura!=null&&(fatturaForm.getIdFattura()==null||fatturaForm.getIdFattura().compareTo(fattura.getIdFattura())!=0))
			throw new FatturaException("Numero bollo già utilizzato");
		try {
			Date dataFattura=new SimpleDateFormat("dd/MM/yyyy").parse(fatturaForm.getDtFattura());
			
			Date dataFatturaMax=fatturaRepository.getMaxDataFattura();
			if(dataFatturaMax!=null){
				
				if(dataFattura.before(dataFatturaMax))
					throw new FatturaException("La data fattura dev'essere maggiore di "+formatter.format(dataFatturaMax));
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void checkUpdate(FatturaForm fatturaForm) throws FatturaException{
		if(!fatturaForm.isTipoFattura())
			return;
		if(!StringUtils.hasLength(fatturaForm.getDtFattura()))
			throw new FatturaException("Data fattura obbligatoria");
		if(!StringUtils.hasLength(fatturaForm.getIdBollo()))
			throw new FatturaException("Bollo obbligatorio");
		Fattura fattura=fatturaRepository.findByIdBollo(fatturaForm.getIdBollo());
		if(fattura!=null&&fattura.getIdFattura().intValue()!=fatturaForm.getIdFattura().intValue())
			throw new FatturaException("Numero bollo già utilizzato");
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date dataFattura=new SimpleDateFormat("dd/MM/yyyy").parse(fatturaForm.getDtFattura());
			Date dataMinima=fatturaRepository.getMinDataFatturaUpdate(fatturaForm.getIdFattura());
			Date dataMassima=fatturaRepository.getMaxDataFatturaUpdate(fatturaForm.getIdFattura());
			if(dataMinima!=null&&dataFattura.before(dataMinima))
				throw new FatturaException("La data fattura dev'essere maggiore o uguale a "+formatter.format(dataMinima));
			if(dataMassima!=null&&dataFattura.after(dataMassima))
				throw new FatturaException("La data fattura dev'essere minore o uguale a "+formatter.format(dataMassima));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private Integer getIdFattura() {
		Integer idFattura=fatturaRepository.getMaxIdFattura();
		if(idFattura==null){
			idFattura=1;
		}else{
			idFattura++;
		}
		return idFattura;
	}



	private String getNumeroFattura(Cliente cliente, Fattura fattura) {
		Calendar c= Calendar.getInstance();
		int anno=c.get(Calendar.YEAR);
		
		String numeroFattura=fatturaRepository.getMaxNumFatturaAnno(anno);
		if(numeroFattura==null){
			numeroFattura="001"+"/"+cliente.getNickname();
		}else{
			String numeroString=numeroFattura.substring(0, 3);
			Integer numero=new Integer(numeroString);
			numero=numero+1;
			if(numero<10){
				numeroFattura="00"+numero+"/"+cliente.getNickname();
			}else if(numero<100){
				numeroFattura="0"+numero+"/"+cliente.getNickname();
			}else{
				numeroFattura=numero+"/"+cliente.getNickname();
			}
		}
		return numeroFattura;
	}



	@Override
	public void update(FatturaForm fatturaForm) throws FatturaException{
		
		Fattura fattura=fatturaRepository.findById(fatturaForm.getIdFattura()).get();
		Cliente cliente=clienteRepository.findById(fatturaForm.getIdCliente()).get();
		fattura.setCliente(cliente);
		
		if(isFromPreventivoToFattura(fattura,fatturaForm)){
			checkInsert(fatturaForm);
		}else{
			checkUpdate(fatturaForm); 
		}
		
		if(StringUtils.hasText(fatturaForm.getDtFattura())){
			try {
				fattura.setDtFattura(new SimpleDateFormat("dd/MM/yyyy").parse(fatturaForm.getDtFattura()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		fattura.setIdBollo(fatturaForm.getIdBollo());
		fattura.setImportoNetto(fatturaForm.getImportoNetto());
		fattura.setIva(fatturaForm.getIva());
		fattura.setOggetto(fatturaForm.getOggetto());
		fattura.setStatoFattura(getStatoFattura(fatturaForm));
		if(StringUtils.hasText(fatturaForm.getCodMese())) {
			CMese mese= meseRepository.findById(fatturaForm.getCodMese()).get();
			fattura.setMese(mese);
		}
		if(isFromPreventivoToFattura(fattura,fatturaForm)){
			fattura.setStatoFattura(getStatoFattura(fatturaForm));
			fattura.setTipoFattura(tipoFatturaRepository.findById(fatturaForm.getCodTipo()).get());
			fattura.setNumeroFattura(getNumeroFattura(cliente,fattura));
			
		}
		fatturaRepository.save(fattura);
	}
	
	private boolean isFromPreventivoToFattura(Fattura fattura,FatturaForm fatturaForm){
		return fattura.getTipoFattura().getCod().equals(TipoFatturaEnum.BOZZA.getCod())&&fatturaForm.getCodTipo().equals(TipoFatturaEnum.FATTURA.getCod());
	}

	@Override
	public FatturaForm select(Integer idFattura) {
		Fattura fattura=fatturaRepository.findById(idFattura).get();
		return fatturaFormFactory.getForm(fattura);
	}



	@Override
	public void delete(FatturaForm fatturaForm) throws FatturaDeleteException {
		Fattura fattura=fatturaRepository.findById(fatturaForm.getIdFattura()).get();
		for(Attivita attivita:fattura.getAttivitas()){
			Attivita attivitaDb=attivitaRepository.findById(attivita.getIdAttivita()).get();
			attivitaRepository.delete(attivitaDb);
		}
		fatturaRepository.delete(fattura);
	}

	@Override
	public List<BozzaForm> getAllBozzeEntity() {
		TipoFattura tipoFattura = new TipoFattura();
		tipoFattura.setCod(TipoFatturaEnum.BOZZA.getCod());
		List<Fattura>l=fatturaRepository.findByTipoFattura(tipoFattura);
		return bozzaFormFactory.getList(l);
	}
	
	@Override
	public List<FatturaForm> getAllBozze() {
		return getAllFattureByTipo(TipoFatturaEnum.BOZZA.getCod());
	}

	@Override
	public List<FatturaForm> getAllFatture() {
		return getAllFattureByTipo(TipoFatturaEnum.FATTURA.getCod());
	}
	
	@Override
	public FatturaAnnoFormWrapper getFatturaAnno(){
		List<FatturaAnnoForm> toRet = new ArrayList<FatturaAnnoForm>();
		List<Object[]>l= fatturaRepository.getTotaleFattureAnnoProjection(TipoFatturaEnum.FATTURA.getCod());
		for(Object[] o: l){
			FatturaAnnoForm fatturaAnnoForm = new FatturaAnnoForm((BigDecimal) o[0], (Integer) o[1]);
			toRet.add(fatturaAnnoForm);
		}
	    FatturaAnnoForm maxFatturaAnnoForm = toRet.stream().max(Comparator.comparing(FatturaAnnoForm::getImporto)).orElseThrow(NoSuchElementException::new);
	    BigDecimal modulo=maxFatturaAnnoForm.getImporto().remainder(new BigDecimal(5000));
	    BigDecimal maxImportoGrafico=new BigDecimal(0);
	    if(modulo.compareTo(new BigDecimal(0))==0) {
	    	maxImportoGrafico=maxFatturaAnnoForm.getImporto();
	    }else {
	    	maxImportoGrafico=(maxFatturaAnnoForm.getImporto().subtract(modulo)).add(new BigDecimal(5000));
	    }
	    
		return new FatturaAnnoFormWrapper(toRet, maxImportoGrafico);
	}
	
	@Override
	public List<TotaleFattureForm> getTotaleFattoreProjection(){
		List<Object[]>l= fatturaRepository.getTotaleFattoreProjection();
		List<TotaleFattureForm> toRet = new ArrayList<TotaleFattureForm>();
		for(Object[] o: l){
			TotaleFattureForm fattureForm = new TotaleFattureForm();
			fattureForm.setAnnoFattura((Integer) o[0]);
			fattureForm.setImportoNettoFatturato((BigDecimal) o[1]);
			fattureForm.setImportoLordoFatturato((BigDecimal) o[2]);
			fattureForm.setImportoNettoNonPagato((BigDecimal) o[3]);
			fattureForm.setImportoLordoNonPagato((BigDecimal) o[4]);
			fattureForm.setImportoNettoAnnullato((BigDecimal) o[5]);
			fattureForm.setImportoLordoAnnullato((BigDecimal) o[6]);
			if(fattureForm.getImportoLordoFatturato()!=null&&fattureForm.getImportoLordoNonPagato()!=null)
				fattureForm.setImportoLordoPagato(fattureForm.getImportoLordoFatturato().subtract(fattureForm.getImportoLordoNonPagato()));
			if(fattureForm.getImportoNettoFatturato()!=null&&fattureForm.getImportoNettoNonPagato()!=null)
				fattureForm.setImportoNettoPagato(fattureForm.getImportoNettoFatturato().subtract(fattureForm.getImportoNettoNonPagato()));
			toRet.add(fattureForm);
		}
		return toRet;
	}
	
	@Override
	public List<ImportoMeseForm> getImportoMese(Integer anno){
		List<Object[]>l= fatturaRepository.getFattureAnnoMeseProjection(anno,TipoFatturaEnum.FATTURA.getCod());
		List<ImportoMeseForm> toRet = new ArrayList<ImportoMeseForm>();
		for(Object[] o: l){
			ImportoMeseForm importoMeseForm = new ImportoMeseForm();
			importoMeseForm.setImporto((BigDecimal) o[0]);
			importoMeseForm.setMese(MeseEnum.getMeseEnum((Integer)o[1]).getDescrizione());
			toRet.add(importoMeseForm);
		}
		for(MeseEnum mese: MeseEnum.values()) {
			
			ImportoMeseForm i=toRet.stream().filter(v -> v.getMese().equals(mese.getDescrizione())).findAny().orElse(null);
			if(i==null) {
				ImportoMeseForm importoMeseForm = new ImportoMeseForm();
				importoMeseForm.setImporto(new BigDecimal(0));
				importoMeseForm.setMese(mese.getDescrizione());
				toRet.add(importoMeseForm);
			}
			
		}
		Collections.sort(toRet, new Comparator<ImportoMeseForm>() {
		    @Override
		    public int compare(ImportoMeseForm i1, ImportoMeseForm i2) {
		        		    	
		    	int onrdine1=MeseEnum.getMeseEnum(i1.getMese()).getOrdine();
		    	int onrdine2=MeseEnum.getMeseEnum(i2.getMese()).getOrdine();
		    	
		        return  onrdine1 > onrdine2 ? 1 : (onrdine1 < onrdine2) ? -1 : 0;
		    }
		});
		
		return toRet;
	}
	
	@Override
	public List<ClienteFatturaAnnoForm> getClienteFatturaAnno(Integer anno){
		List<Object[]>l= fatturaRepository.getFattureAnnoClienteProjection(anno,TipoFatturaEnum.FATTURA.getCod());
		List<ClienteFatturaAnnoForm> toRet = new ArrayList<ClienteFatturaAnnoForm>();
		for(Object[] o: l){
			ClienteFatturaAnnoForm clienteFatturaAnnoForm = new ClienteFatturaAnnoForm();
			clienteFatturaAnnoForm.setFatturatoCliente((BigDecimal) o[0]);
			clienteFatturaAnnoForm.setDenominazioneCliente((String) o[1]);
			toRet.add(clienteFatturaAnnoForm);
		}
		return toRet;
	}
	
	
	
	private List<FatturaForm> getAllFattureByTipo(String codTipo){
		TipoFattura tipoFattura = new TipoFattura();
		tipoFattura.setCod(codTipo);
		List<Fattura>l=fatturaRepository.findByTipoFattura(tipoFattura);
		return fatturaFormFactory.getList(l);
	}



	@Override
	public FatturaForm duplica(Integer idFattura) throws FatturaException {
		Fattura fattura=fatturaRepository.findById(idFattura).get();
		Fattura fatturaClone=fattura.clone();
		fatturaClone.setIdFattura(getIdFattura());
		fatturaClone.setAttivitas(null);
		fatturaClone.setStatoFattura(getStatoFatturaDefault());
		fatturaClone.setDtFattura(new Date());
		fatturaClone.setIdBollo("da inserire");
		fatturaClone.setNumeroFattura(getNumeroFattura(fatturaClone.getCliente(),fattura));
		fatturaRepository.save(fatturaClone);
		for(Attivita attivita:fattura.getAttivitas()){
			AttivitaForm attivitaNew = new AttivitaForm();
			attivitaNew.setDescrizione(attivita.getDescrizione());
			attivitaNew.setIdFattura(fatturaClone.getIdFattura());
			try {
				attivitaService.insert(attivitaNew);
			} catch (AttivitaSaveException e) {
				throw new FatturaException(e.getMessage());
			}
		}
		return fatturaFormFactory.getForm(fatturaClone);
	}



	private StatoFattura getStatoFatturaDefault() {
		return statoFatturaRepository.findById(StatoFatturaEnum.NON_PAGATA.getCod()).get();
	}



	@Override
	public List<PairDto> getAllAnnoFatture() {
		List<PairDto> l = new ArrayList<PairDto>();
		List<Integer> listAnni=fatturaRepository.getAllAnnoFatture(TipoFatturaEnum.FATTURA.getCod());
		for(Integer anno:listAnni) {
			PairDto pairDto = new PairDto(""+anno.intValue(), ""+anno.intValue());
			l.add(pairDto);
		}
		return l;
	}



	@Override
	public List<FatturaForm> getAllFattureBlack() {
		return getAllFattureByTipo(TipoFatturaEnum.BLACK.getCod());
	}



	@Override
	public List<BozzaForm> getAllBozzeCliente(Integer idCliente) {
		TipoFattura tipoFattura = new TipoFattura();
		tipoFattura.setCod(TipoFatturaEnum.BOZZA.getCod());
		List<Fattura> l= fatturaRepository.findByClienteIdClienteAndTipoFattura(idCliente,tipoFattura);
		return bozzaFormFactory.getList(l);
	}

}
