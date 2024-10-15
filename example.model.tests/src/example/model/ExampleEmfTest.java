package example.model;

import example.model.utils.EmfModelFileUtils;
import examplemetamodel.Bolt;
import examplemetamodel.Nut;
import examplemetamodel.Warehouse;
import examplemetamodel.impl.ExamplemetamodelFactoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExampleEmfTest {

	/**
	 * Temporary file path to write the XMI file to.
	 */
	protected final String tmpXmiPath = "./warehouse.xmi";

	/**
	 * Setup method that ensures no old XMI file is present before running any
	 * tests.
	 */
	@BeforeEach
	public void setup() {
		deleteTmpXmi();
	}

	/**
	 * Tear down method that ensures no old XMI file is present before running any
	 * tests.
	 */
	@AfterEach
	public void teardown() {
		deleteTmpXmi();
	}

	/**
	 * Tests the writing of an EMF model to a XMI file.
	 */
	@Test
	public void testFileWrite() {
		final Warehouse wh = createModel();
		EmfModelFileUtils.persistModel(wh, tmpXmiPath);
		assertTrue(new File(tmpXmiPath).isFile());
	}

	/**
	 * Tests the loading of an EMF model from a given XMI file.
	 */
	@Test
	public void testFileRead() {
		final Warehouse loaded = EmfModelFileUtils.loadModel("./resources/warehouse.xmi");
		assertNotNull(loaded);
		assertFalse(loaded.getParts().isEmpty());
		assertEquals(2, loaded.getParts().size());
	}

	//
	// Utilities
	//

	/**
	 * Creates an example warehouse model.
	 * 
	 * @return Warehouse model.
	 */
	protected Warehouse createModel() {
		final var factory = ExamplemetamodelFactoryImpl.init();
		final Warehouse wh = factory.createWarehouse();

		final Bolt b = factory.createBolt();
		b.setName("M4 12mm");
		wh.getParts().add(b);

		final Nut n = factory.createNut();
		n.setName("M4 normal");
		wh.getParts().add(n);

		return wh;
	}

	/**
	 * Deletes the temporary XMI file.
	 */
	protected void deleteTmpXmi() {
		try {
			Files.deleteIfExists(Path.of(getTmpXmiPath()));
		} catch (final IOException e) {
			e.printStackTrace();
			Assertions.fail("Clean up of warehouse.xmi failed in setup.");
		}
	}

	/**
	 * Returns the path of the temporary XMI file.
	 * 
	 * @return Path of the temporary XMI file.
	 */
	protected String getTmpXmiPath() {
		return tmpXmiPath;
	}

}
