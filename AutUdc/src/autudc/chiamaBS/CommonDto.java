package autudc.chiamaBS;

import java.lang.reflect.Method;

public class CommonDto {

	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		try {
			Class myClass = (Class) Class.forName(this.getClass().getName());

			Method myMethod[] = myClass.getMethods();
			for (int i = 0; i < myMethod.length; i++) {
				Method currentMethod = myMethod[i];
				try {
					if ((currentMethod.getName().startsWith("get") || currentMethod.getName().startsWith("is"))
							&& (!currentMethod.getName().startsWith("getClass")
									&& !currentMethod.getName().startsWith("toString") 
									&& !currentMethod.getName().startsWith("compare"))) {
						if (currentMethod.getReturnType().isArray()) {
							currentMethod.setAccessible(true);
							Object[] objArray = (Object[]) currentMethod.invoke(this, new Object[0]);
							stringBuffer.append(currentMethod.getName() + ": [");
							for (int j = 0; j < objArray.length; j++) {
								if (j > 0) {
									stringBuffer.append(",");
								}
								stringBuffer.append(objArray[j]);
							}
							stringBuffer.append("]");
						} else {
							stringBuffer.append(currentMethod.getName() + ": "+ currentMethod.invoke(this, new Object[0])+ "\n");
						}
					}
				} catch (Exception e) {
					System.err.println("Sono stati riscontrati problemi durante l'invoke del metodo: "+ currentMethod.getName());
				}
			}
		} catch (Exception e) {
			System.err.println("Errore su mappatura oggetto tramite reflection. "+ e.getMessage());
		}
		return stringBuffer.toString();
	}

	public int hashCode() {
		return super.hashCode();
	}

	public boolean equals(Object object) {
		return super.equals(object);
	}

}
