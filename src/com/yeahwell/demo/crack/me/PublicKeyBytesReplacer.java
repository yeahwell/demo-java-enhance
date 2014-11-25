package com.yeahwell.demo.crack.me;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.yeahwell.demo.crack.core.JarReplacer;

public class PublicKeyBytesReplacer extends JarReplacer
{

  private String defaultFileName = "publicKey.bytes";

  public PublicKeyBytesReplacer() {
  }

  public boolean replace(File f) {
    if (f == null) {
      return false;
    }
    System.out.println("\treplacing file [" + f.getAbsolutePath() + "]");

    File bakFile = createBakFile(f);
    if (bakFile != null) {
      JarOutputStream out = null;
      try {
        if (bakFile != null) {
          String defaultPackage = "";
          JarFile bakJar = new JarFile(bakFile);
          out = new JarOutputStream(new FileOutputStream(f));
          Enumeration jes = bakJar.entries();
          while (jes.hasMoreElements()) {
            JarEntry j = (JarEntry)jes.nextElement();
            String name = j.getName();
            if (name.endsWith(this.defaultFileName)) {
              defaultPackage = name.substring(0, name.lastIndexOf('/'));
            }
            else {
              out.putNextEntry(j);
              InputStream in = bakJar.getInputStream(j);
              writeJarEntry(in, out);
            }
          }
          addJarFile(
            defaultPackage + "/" + this.defaultFileName, 
            new FileInputStream(this.defaultFileName), 
            out);
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
    }
    return false;
  }

  public boolean isFileLegal(File f)
  {
    if (f == null) {
      return false;
    }

    String fn = f.getName();
    if (fn.startsWith("com.genuitec.eclipse.core_")) {
      ZipFile zipFile = null;
      try {
        zipFile = new ZipFile(f);
        Enumeration zes = zipFile.entries();
        while (zes.hasMoreElements()) {
          ZipEntry ze = (ZipEntry)zes.nextElement();
          String name = ze.getName();
          if (name.endsWith(this.defaultFileName))
            return true;
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
        return false;
      } finally {
        try {
          if (zipFile != null)
            zipFile.close();
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }
      try
      {
        if (zipFile != null)
          zipFile.close();
      }
      catch (IOException e) {
        e.printStackTrace();
      }

    }

    return false;
  }

  public String getProcessInfo()
  {
    return "Replacing [publicKey.bytes].";
  }
}