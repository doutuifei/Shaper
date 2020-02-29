package com.ekwing.shaper.ui;

import com.ekwing.shaper.listener.OnOkClickListener;

import javax.swing.*;
import java.awt.event.*;

public class PrefixDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JEditorPane prefixEt;
    private OnOkClickListener listener;

    public void setOnClickListener(OnOkClickListener listener) {
        this.listener = listener;
    }

    public void setDefaultPrefix(String text) {
        prefixEt.setText(text);
    }

    public PrefixDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });


        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        String text = prefixEt.getText();
        if (listener != null) {
            listener.onOkClick(text);
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        PrefixDialog dialog = new PrefixDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

}