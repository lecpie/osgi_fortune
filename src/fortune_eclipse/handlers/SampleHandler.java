package fortune_eclipse.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import service.IFortuneService;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SampleHandler extends AbstractHandler {
	
	private IFortuneService service;
	private BundleContext ctx;
	
	/**
	 * The constructor.
	 */
	public SampleHandler() {
		this.ctx = FrameworkUtil.getBundle(fortune_eclipse.Activator.class).getBundleContext();
		this.service = ctx.getService(ctx.getServiceReference(IFortuneService.class));
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(
				window.getShell(),
				"Fortune_eclipse",
				service.getFortune());
		
		return null;
	}
}
