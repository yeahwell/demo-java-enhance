package com.yeahwell.demo.crack.me;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.yeahwell.demo.crack.core.JarReplacer;

public class SignatureVerifierReplacer extends JarReplacer {

	private List<String> legalFileList = null;
	private String defaultClassName = "SignatureVerifier.class";

	public SignatureVerifierReplacer() {
		this.legalFileList = new ArrayList();
		this.legalFileList.add("/SignatureVerifier.class");
		this.legalFileList.add("/SignatureVerifier$1.class");
		this.legalFileList.add("/SignatureVerifier$1$1.class");
	}

	public boolean isInLegalFile(String fileName) {
		for (String cn : this.legalFileList) {
			if (fileName.endsWith(cn)) {
				return true;
			}
		}
		return false;
	}

	public boolean replace(File f) {
		if (f == null) {
			return false;
		}
		System.out.println("\treplacing file [" + f.getAbsolutePath() + "]");
		File bakFile = createBakFile(f);
		JarOutputStream out = null;
		try {
			if (bakFile != null) {
				String defaultPackage = "";
				JarFile bakJar = new JarFile(bakFile);
				out = new JarOutputStream(new FileOutputStream(f));
				Enumeration jes = bakJar.entries();
				while (jes.hasMoreElements()) {
					JarEntry j = (JarEntry) jes.nextElement();
					String name = j.getName();
					if (isInLegalFile(name)) {
						defaultPackage = name.substring(0,
								name.lastIndexOf('/'));
					} else {
						out.putNextEntry(j);
						InputStream in = bakJar.getInputStream(j);
						writeJarEntry(in, out);
					}
				}
				addJarFile(defaultPackage + "/" + this.defaultClassName, out);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return true;
	}

	public boolean isFileLegal(File f) {
		boolean isFoundSV = false;
		boolean isFoundSV$1 = false;
		boolean isFoundSV$1$1 = false;
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(f);
			Enumeration zes = zipFile.entries();
			while (zes.hasMoreElements()) {
				ZipEntry ze = (ZipEntry) zes.nextElement();
				String name = ze.getName();
				if (name.endsWith("/SignatureVerifier.class")) {
					isFoundSV = true;
				}
				if (name.endsWith("/SignatureVerifier$1.class")) {
					isFoundSV$1 = true;
				}
				if (name.endsWith("/SignatureVerifier$1$1.class")) {
					isFoundSV$1$1 = true;
				}
			}

			if ((isFoundSV) && (isFoundSV$1) && (isFoundSV$1$1))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (zipFile != null)
					zipFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			if (zipFile != null)
				zipFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	public String getProcessInfo() {
		return "Replacing [SignatureVerifier].";
	}
}