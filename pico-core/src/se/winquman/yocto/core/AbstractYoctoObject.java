/**
 * 
 */
package se.winquman.yocto.core;

import java.util.logging.Logger;

import se.winquman.yocto.core.logging.LogSettings;
import se.winquman.yocto.error.ApplicationException;
import se.winquman.yocto.error.ApplicationRuntimeException;
import se.winquman.yocto.error.InitializationException;
import se.winquman.yocto.error.NotCreatedException;

/**
 * @author jpeter
 *
 */
public class AbstractYoctoObject implements YoctoObject {

	protected Context context;
	protected Configurator config;
	private LogSettings logSettings;
	protected Logger logger;
	
	// Is this object created?
	private boolean isCreated = false;
		
	/* (non-Javadoc)
	 * @see se.winquman.yocto.core.YoctoObject#create()
	 */
	@Override
	public void create(Context cont, Configurator conf) throws ApplicationException {
		config = conf;
		context = cont;
		logSettings = context.getLogSettings();
		logger = logSettings.getConfiguredLogger();
		isCreated = true;
	}
	
	protected void check() throws ApplicationRuntimeException {
		if(!isCreated) {
			throw new NotCreatedException("Object called before it was created: " + this.getClass());
		}
	}
	
}
