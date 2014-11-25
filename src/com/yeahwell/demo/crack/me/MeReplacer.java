package com.yeahwell.demo.crack.me;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import com.yeahwell.demo.crack.core.JarReplacer;

public class MeReplacer {

	private List<JarReplacer> replacers = null;

	public MeReplacer() {
		initReplacers();
	}

	public void initReplacers() {
		this.replacers = new ArrayList(5);
		this.replacers.add(new SignatureVerifierReplacer());
		this.replacers.add(new PublicKeyBytesReplacer());
	}

	public void addReplacer(JarReplacer replacer) {
		if (replacer != null)
			this.replacers.add(replacer);
	}

	public boolean replace(File fileFolder) {
		List allFiles = findFiles(fileFolder);
		return replace(allFiles);
	}

	private boolean replace(List<File> allFiles) {
		if ((this.replacers == null) || (allFiles == null)) {
			return false;
		}
		boolean success = true;
		for (JarReplacer rp : this.replacers) {
			System.out.println(rp.getProcessInfo());
			success &= rp.replace(allFiles);
		}
		return success;
	}

	private List<File> findFiles(File file) {
		System.out.println("finding files in [" + file.getAbsolutePath() + "]");
		return findFiles(file, JarReplacer.getFileFilter());
	}

	private List<File> findFiles(File file, FileFilter fileFliter) {
		if (!file.exists()) {
			return null;
		}

		if (file.isFile()) {
			List tmpFiles = new ArrayList(1);
			tmpFiles.add(file);
			return tmpFiles;
		}

		File[] files = file.listFiles(fileFliter);
		if ((files == null) || (files.length == 0)) {
			return null;
		}
		List fff = new ArrayList(100);
		for (File f : files) {
			List tmpFile = findFiles(f, fileFliter);
			if (tmpFile != null) {
				fff.addAll(tmpFile);
			}
		}
		return fff;
	}

}