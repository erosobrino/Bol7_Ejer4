/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template f, choose Tools | Templates
 * and open the template in the editor.
 */
package bol7_ejer4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author ero
 */
class Informacion extends JFrame implements ActionListener {

    JLabel info;
    JLabel error;
    JButton abrir;
    JButton crear;
    JLabel lblCantidad;
    JTextField cantidad;
    
    ArrayList<Double> numero1;
    ArrayList<Double> numero2;
    ArrayList<Double> numero3;

    public Informacion() {
        super("Informacion");
        this.setLayout(null);

        abrir = new JButton("Abir");
        abrir.setSize(abrir.getPreferredSize());
        abrir.setLocation(175, 20);
        abrir.addActionListener(this);
        this.add(abrir);

        crear = new JButton("Crear");
        crear.setSize(crear.getPreferredSize());
        crear.setLocation(abrir.getX() + abrir.getWidth() + 5, 20);
        crear.addActionListener(this);
        this.add(crear);

        info = new JLabel();
        info.setSize(info.getPreferredSize());
        info.setLocation(20, abrir.getY() + abrir.getHeight() + 10);
        this.add(info);

        error = new JLabel("");
        error.setSize(error.getPreferredSize());
        error.setLocation(20, info.getY() + info.getHeight() + 10);
        this.add(error);

        lblCantidad = new JLabel("Cantidad lineas");
        lblCantidad.setSize(lblCantidad.getPreferredSize());
        lblCantidad.setLocation(crear.getX() + crear.getWidth() + 5, crear.getY() + 5);
        this.add(lblCantidad);

        cantidad = new JTextField(5);
        cantidad.setSize(cantidad.getPreferredSize());
        cantidad.setLocation(lblCantidad.getX() + lblCantidad.getWidth() + 5, lblCantidad.getY());
        this.add(cantidad);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == abrir) {
            numero1=new ArrayList<>();
            numero2=new ArrayList<>();
            numero3=new ArrayList<>();
            int respuesta;
            JFileChooser fc = new JFileChooser();
            respuesta = fc.showDialog(null, "Aceptar");
            if (respuesta == JFileChooser.APPROVE_OPTION) {
                File archivo = new File(fc.getSelectedFile().getPath());
                String[] numeros;
                try (Scanner sc = new Scanner(archivo)) {
                    String texto = String.format("<html>");
                    while (sc.hasNext()) {
                        numeros = sc.nextLine().split(",");
                        int num1 = Integer.parseInt(numeros[0]);
                        numero1.add((Double.parseDouble(num1+"")));
                        double num2 = Double.parseDouble(numeros[1]);
                        numero2.add(num2);
                        double num3 = Double.parseDouble(numeros[2]);
                        numero3.add(num3);
//                        System.out.println(num1);
//                        System.out.println(num2);
//                        System.out.println(num3);
//                        double media = (num1 + num2 + num3) / 3;
//                        // System.out.println(media);
//                        String mediaString = String.format("%.2f", media);
//                        texto += mediaString + "<br>";
                    }
                    Double suma1=0.0;
                    Double suma2=0.0;
                    Double suma3 = 0.0;
                    for (int i = 0; i < numero1.size(); i++) {
                        suma1+=numero1.get(i);
                        suma2+=numero2.get(i);
                        suma3+=numero3.get(i);
                    }
                    Double media1=suma1/numero1.size();
                    Double media2=suma2/numero1.size();
                    Double media3=suma3/numero1.size();
                    texto += media1+"<br>"+media2+"<br>"+media3+"</html>";
                    //System.out.println(texto);
                    error.setText(texto);
                    error.setSize(error.getPreferredSize());
                } catch (Exception ex) {
                    //System.err.println("Error en la apertura del archivo");
                    error.setText("Error de archivo");
                    error.setSize(error.getPreferredSize());
                }
            }
        }
        if (e.getSource() == crear) {
            String home = System.getProperty("user.home");
            File f = new File(home + "/archivos.csv");
            if (f.exists() == false) {
                try {
                    f.createNewFile();
                } catch (IOException ex) {
                }
            }
            try (PrintWriter p = new PrintWriter(new FileWriter(f))) {
                int numero = Integer.parseInt(cantidad.getText());
                if (numero <= 0) {
                    JOptionPane.showMessageDialog(null, "Introduce un numero de lineas valido");
                } else {
                    for (int i = 0; i < Integer.parseInt(cantidad.getText()); i++) {
                        int num1 = (int) (Math.random() * 90 + 10);
                        double num2 = (double) (Math.random() * 1001 + 0);
                        double num3 = (double) (Math.random() * 10001 + 100);
                        String linea = num1 + "," + num2 + "," + num3 + "\n";
                        p.print(linea);
                    }
                    JOptionPane.showMessageDialog(null, "Se ha creado, con el nombre archivos.csv en el directorio personal");
                }
            } catch (Exception exc) {
            }
        }
    }
}

public class Bol7_Ejer4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Informacion inf = new Informacion();
        inf.setSize(500, 500);
        inf.setLocationRelativeTo(null);
        inf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inf.setVisible(true);
    }

}
