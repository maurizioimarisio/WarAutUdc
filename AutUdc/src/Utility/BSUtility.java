package Utility;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import autudc.logger.Logger;
import autudc.logger.LoggerFactory;


public class BSUtility {
	
	 private Logger logger = null;
	
	protected static final String INHEADER = "INHEADER";
	protected static final String OUTBST = "OUTBST";
	protected static final String INPBST = "INPBST";
	protected static final String OUTESI = "OUTESI";
	protected static final String OUTHEADER = "OUTHEADER";
	protected static final String OUTSEG = "OUTSEG";
		
//	protected static final String BS_CODICE_TIPO_CANALE = "52";
//	protected static final String BS_CODICE_SOCIETA = "01";
//	protected static final String BS_CODICE_SPORTELLO = "00700";
//	protected static final String BS_CODICE_UO_RICH = "00700";
//	protected static final String BS_DATA_CONT = "24052006";
//	protected static final String BS_IND_MQ_SINCRONO_S = "S";
	
	public BSUtility() {
		super();
		try {
            if (logger == null) {
                String pkgName = this.getClass().getPackage().getName();
                logger = LoggerFactory.getLogger(pkgName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.debug("Costruttore BSUtility()");
	}

			
	/**
	 * Metodo per la stampa dei log di un singolo tag di un BS 
	 * 
	 * @param objectBS (oggettoBS.tag)
	 * @param idServizio
	 * @param printAllFields (settare a true se si vogliono stampare anche i field/tag vuoti altrimenti a false)
	 */
	public void writeLogBS(Object[] object, String idServizio, boolean printAllFields) {
		logger.debug("BSUtility.writeLogBS INIZIO.");
		try {
			if (object != null && object[0] != null){
				Object objectBS = object[0];
				Class classe = objectBS.getClass();
				String classeName = classe.getName().substring(classe.getName().lastIndexOf(".")+1);
				Field[] fields = classe.getFields();
				for (int i = 0; i<fields.length;i++){
					Field field = fields[i];
					String fieldName = field.getName();
					Object fieldValue = null;
					
					try {
						fieldValue = field.get(objectBS);
						if (fieldValue !=null && field.getType().isArray() ){
							Object[] elements = (Object[])fieldValue;
								for (int j = 0; j<elements.length;j++){
									Object element = (Object) elements[j];
									Class classeField = element.getClass();
									String classeFieldName = classeField.getName().substring(classeField.getName().lastIndexOf(".")+1);
									Field[] fieldsFields = (Field[])classeField.getFields();
									getRowForDebug(classeField.getName(), idServizio, "", "", printAllFields);
									if(fieldsFields!=null || new Vector().equals(fieldsFields)){
										for (int k = 0; k<fieldsFields.length;k++){
											Field fieldField = fieldsFields[k];
											String fieldNameField = fieldField.getName();
											Object fieldValueField  = null;
											try {
												fieldValueField = fieldField.get(element);
												getRowForDebug(classeFieldName, idServizio, fieldNameField, fieldValueField, printAllFields);
											} catch (Exception e) {
												getRowForDebug("ERRORE IN" +classeFieldName+" "+ fieldName , idServizio, fieldNameField, fieldValueField, printAllFields);
											}
										}
									}
								}
						} else {
							getRowForDebug(classeName, idServizio, fieldName, fieldValue, printAllFields);
						}
					} catch (Exception e) {
						getRowForDebug("ERRORE IN" +classeName, idServizio, fieldName, fieldValue, printAllFields);
					}
				}
			}
		} catch (Exception e) {
			logger.error("ERRORE GENERICO IN BSUtility.writeLogBS("+object+","+idServizio+")");
		}
		logger.debug("BSUtility.writeLogBS FINE.");
	}
	
		
  	
	/**
	 * setta il singolo field
	 * 
	 * @param fatherElement
	 * @param idServizio
	 * @param fieldValue
	 * @param classeFieldName
	 * @param toSet
	 * @throws IllegalAccessException
	 */
	
	
	private void getRowForDebug(String className, String idServizio, String name, Object val){
		getRowForDebug(className, idServizio, name, val, false);
	}
	
	private void getRowForDebug(String className, String idServizio, String name, Object val, boolean printAllFields){
		String retValue = "[AUTUDC] | ";
		String value = val == null ? "" : val instanceof String ? val.toString() : new String(val.toString());
		try {
			if (printAllFields || value.trim().length()>0 ){

				retValue = retValue + className+"  ("+idServizio+") ";
				retValue = retValue +" "+name+" : >>>"+ value +"<<<";
				logger.error(retValue);
			}
		} catch (Exception e) {
			logger.error("getRowForDebug("+className +","+ idServizio +","+ name +","+ val+")");
		}
	}
	
	protected Map getBSIntoMap(Object object){
		Map bsMap = new HashMap();
		Class classe = object.getClass();
		String classeName = classe.getName().substring(classe.getName().lastIndexOf(".")+1);
		Field[] externalTags = classe.getFields();
		for (int i = 0; i<externalTags.length;i++){
			Field externalTag = externalTags[i];
			String externalTagName = externalTag.getName();
			Object[] externalTagValue = new Object[0];
			try {
				externalTagValue = (Object[])externalTag.get(object);
				if (INHEADER.equals(externalTagName)){
					Map INHEADER_obj = new HashMap();
					INHEADER_obj = getFieldsMap(externalTagValue[0]);
					bsMap.put(INHEADER, INHEADER_obj);
				} else if (INPBST.equals(externalTagName)){
					Map INPBST_obj = new HashMap();
					INPBST_obj = getFieldsMap(externalTagValue[0]);
					bsMap.put(INPBST, INPBST_obj);
				} else if (OUTHEADER.equals(externalTagName)){
					Map OUTHEADER_obj = new HashMap();
					OUTHEADER_obj = getFieldsMap(externalTagValue[0]);
					bsMap.put(OUTHEADER, OUTHEADER_obj);
				} else if (OUTBST.equals(externalTagName)){
					Map OUTBST_obj = new HashMap();
					OUTBST_obj = getFieldsMap(externalTagValue[0]);
					bsMap.put(OUTBST, OUTBST_obj);
				} else if (OUTSEG.equals(externalTagName)){
					Map OUTSEG_obj = new HashMap();
					OUTSEG_obj = getFieldsMap(externalTagValue[0]);
					bsMap.put(OUTSEG, OUTSEG_obj);
				} else if (OUTESI.equals(externalTagName)){
					Map OUTESI_obj = new HashMap();
					OUTESI_obj = getFieldsMap(externalTagValue[0]);
					bsMap.put(OUTESI, OUTESI_obj);
				}
			} catch (NullPointerException e) {
				logger.error("Il tag "+externalTagName+" non è presente nel BS "+classeName);
			} catch (Exception e) {
				getRowForDebug("ERRORE IN", classeName , externalTagName, externalTagValue);
			}
		}
		return bsMap;
	}
	
	private Map getFieldsMap(Object obj){
		Map returnValue = new HashMap();
		Class classe = obj.getClass();
		String classeName = classe.getName().substring(classe.getName().lastIndexOf(".")+1);
		Field[] fields = classe.getFields();
		for (int i = 0; i<fields.length;i++){
			Field field = fields[i];
			String fieldName = field.getName();
			Object fieldValue = null;
			try {
				fieldValue = field.get(obj);
			} catch (Exception e) {
				logger.error("ERRORE IN" +classeName+" "+ fieldName+ " " +fieldValue);
			}
			if (field.getType().isArray()){
				Object[] element = (Object[]) fieldValue;
				Map tagValue = new HashMap();
				for (int j=0;j<element.length;j++){
					tagValue.put("00"+Integer.toString(j) ,getFieldsMap(element[j]));
				}
				fieldValue = tagValue;
			}
			returnValue.put(fieldName, fieldValue);	
		} 
		return returnValue;
	}
	
}
