package example.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;

import example.model.utils.smartemf.SmartEmfModelFileUtils;
import examplemetamodel.Warehouse;

public class ExampleSmartEmfTest extends ExampleEmfTest {

	/**
	 * Temporary file path to write the XMI file to.
	 */
	protected final String tmpXmiPath = "./warehouse-smartemf.xmi";

	/**
	 * Tests the writing of a SmartEMF model to a XMI file.
	 */
	@Test
	public void testFileWrite() {
		final Warehouse wh = createModel();
		SmartEmfModelFileUtils.persistModel(wh, tmpXmiPath);
		assertTrue(new File(tmpXmiPath).isFile());
	}

	/**
	 * Tests the loading of a SmartEMF model from a given XMI file.
	 */
	@Test
	public void testFileRead() {
		final Warehouse loaded = SmartEmfModelFileUtils.loadModel("./resources/warehouse.xmi");
		assertNotNull(loaded);
		assertFalse(loaded.getParts().isEmpty());
		assertEquals(2, loaded.getParts().size());
	}

	//
	// Utilities
	//

	/**
	 * Returns the path of the temporary XMI file.
	 * 
	 * @return Path of the temporary XMI file.
	 */
	@Override
	protected String getTmpXmiPath() {
		return tmpXmiPath;
	}
}
