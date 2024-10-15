package example.model.utils.smartemf;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.emoflon.smartemf.persistence.SmartEMFResourceFactoryImpl;
import examplemetamodel.ExamplemetamodelPackage;
import examplemetamodel.Warehouse;

public final class SmartEmfModelFileUtils {

	private SmartEmfModelFileUtils() {
	}

	public static void persistModel(final Warehouse model, final String path) {
		if (path == null || path.isBlank()) {
			throw new IllegalArgumentException("Invalid path.");
		}

		if (model == null) {
			throw new IllegalArgumentException("Given model was null.");
		}

		// Workaround: Always use absolute path
		final URI absPath = URI.createFileURI(System.getProperty("user.dir") + "/" + path);

		// Create new model for saving
		final ResourceSet rs = new ResourceSetImpl();
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new SmartEMFResourceFactoryImpl(null));
		// ^null is okay if all paths are absolute
		final Resource r = rs.createResource(absPath);
		// Fetch model contents from eMoflon
		r.getContents().add(model);
		try {
			r.save(null);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public static Warehouse loadModel(final String path) {
		if (path == null || path.isBlank()) {
			throw new IllegalArgumentException("Invalid path.");
		}

		final URI absPath = URI.createFileURI(System.getProperty("user.dir") + "/" + path);
		final ResourceSet rs = new ResourceSetImpl();
		final Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		reg.getExtensionToFactoryMap().put("xmi", new SmartEMFResourceFactoryImpl(null));
		rs.getPackageRegistry().put(ExamplemetamodelPackage.eINSTANCE.getNsURI(), ExamplemetamodelPackage.eINSTANCE);
		rs.getResource(absPath, true);

		return (Warehouse) rs.getResources().get(0).getContents().get(0);
	}

}
