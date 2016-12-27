package com.yeahwell.demo.dbschema;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Keygen
{
  public static void main(String[] args)
  {
    new Keygen();
  }

  public Keygen()
  {
    JFrame guiFrame = new JFrame("* DbSchema Keygen *");

    guiFrame.setDefaultCloseOperation(3);
    guiFrame.setSize(400, 235);
    guiFrame.setAlwaysOnTop(true);
    guiFrame.setResizable(false);

    guiFrame.setLocationRelativeTo(null);

    JPanel imagePanel = new JPanel();
    JLabel logo = new JLabel();
    logo.setIcon(new ImageIcon(getClass().getResource("/image/logo.png")));
    imagePanel.add(logo);

    JPanel serialPanel = new JPanel();

    JLabel lblName = new JLabel("Name:");
    final JTextField txtName = new JTextField(28);
    serialPanel.add(lblName);
    serialPanel.add(txtName);

    JLabel lblSerial = new JLabel("Serial:");
    final JTextField txtSerial = new JTextField(28);
    txtSerial.setEditable(false);
    txtSerial.setHorizontalAlignment(0);
    serialPanel.add(lblSerial);
    serialPanel.add(txtSerial);

    JButton btnCopy = new JButton("Copy");
    serialPanel.add(btnCopy);
    JButton btnExit = new JButton("Exit");
    serialPanel.add(btnExit);

    JPanel labelPanel = new JPanel();
    JLabel lblAbout = new JLabel("DrAww AliEn © 2015 | Protection: Basic");
    lblAbout.setEnabled(false);
    labelPanel.add(lblAbout);

    btnCopy.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent event) {
        StringSelection stringSelection = new StringSelection(txtSerial.getText());
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);
      }
    });
    btnExit.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent event) {
        System.exit(0);
      }
    });
    txtName.getDocument().addDocumentListener(new DocumentListener()
    {
      public void insertUpdate(DocumentEvent e) {
        GenKey();
      }

      public void removeUpdate(DocumentEvent e)
      {
        GenKey();
      }

      public void changedUpdate(DocumentEvent e)
      {
        GenKey();
      }

      public void GenKey()
      {
        if (txtName.getText().length() > 5) {
          String strHash = Keygen.this.hashMD5("ax5" + txtName.getText() + "b52w" + "99999" + "vb3");
          txtSerial.setText(strHash.substring(0, 4) + "99999" + strHash.substring(4));
        } else {
          txtSerial.setText("Enter a longer name");
        }
      }
    });
    txtName.addFocusListener(new FocusListener()
    {
      public void focusGained(FocusEvent e) {
        txtName.selectAll();
      }

      public void focusLost(FocusEvent e)
      {
      }
    });
    txtName.setText("DrAww AliEn");
    txtName.requestFocus();

    guiFrame.add(imagePanel, "North");
    guiFrame.add(serialPanel, "Center");
    guiFrame.add(labelPanel, "South");

    guiFrame.setVisible(true);
  }

  public String hashMD5(String md5) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] array = md.digest(md5.getBytes());
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < array.length; i++) {
        sb.append(Integer.toHexString(array[i] & 0xFF | 0x100).substring(1, 3));
      }
      return sb.toString();
    } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
    }
    return null;
  }
}
