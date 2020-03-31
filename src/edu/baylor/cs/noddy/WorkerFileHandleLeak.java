package edu.baylor.cs.noddy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;;

public class WorkerFileHandleLeak extends Worker {

	public WorkerFileHandleLeak() {
		super();
	}

	public WorkerFileHandleLeak(String name, Set<Worker> finishSet) {
		super(name, finishSet);
	}

	@Override
	public void run() {

		String name = getName() + "resource.txt";
		// Switch to try-with-resources to prevent zos from file-handle leaking.
		// Specifies an encoding.
		try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File(getName() + "big.jar")),
				StandardCharsets.UTF_8)) {
			zos.putNextEntry(new ZipEntry(name));
			zos.write("not too much in here".getBytes(StandardCharsets.UTF_8)); // Specify an encoding
			zos.closeEntry();
			zos.putNextEntry(new ZipEntry(getName() + "largeFile.out"));
			for (int i = 0; i < 10000000; i++) {
				zos.write((int) (Math.round(Math.random() * 100) + 20));
			}
			zos.closeEntry();
			zos.close();

			int ITERATIONS = 2000;
			for (int i = 0; i < ITERATIONS; i++) {
				// Try-with-resources would keep the work, if it threw an exception, from
				// leaking a file handle
				try (InputStream istr = WorkerFileHandleLeak.class.getClassLoader()
						.getResourceAsStream("test.properties")) {
					// Work that needs done goes here
					istr.close();
				} catch (RuntimeException e) {
					throw e;
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				// These seem are pointless because they create URLs which nothing is done with
				// WorkerFileHandleLeak.class.getClassLoader().getResource("test.properties");
				// WorkerFileHandleLeak.class.getClassLoader().getResource("/cz/cvut/fel/cs/ass/test.properties");
				// ^ really?

				// Same as earlier try-with-resources
				try (FileInputStream fis = new FileInputStream(new File(getName() + "big.jar"))) {
					// Do desired work here
				} catch (RuntimeException e) {
					// If a runtime exception is caught, then re-throw it instead of stopping it
					throw e;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			Thread.sleep(Long.MAX_VALUE);

			getFinishSet().add(this);
		} catch (RuntimeException e) {
			// If a runtime exception is caught, then re-throw it instead of stopping it
			throw e;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public Worker clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
