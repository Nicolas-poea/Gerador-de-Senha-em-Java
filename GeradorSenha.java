import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.StringSelection;
import java.util.Random;

public class GeradorSenha {

    public static void main(String[] args) {

        JFrame janela = new JFrame("Gerador de Senhas");
        janela.setSize(420,330);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(null);

        JLabel titulo = new JLabel("Gerador de Senhas");
        titulo.setBounds(130,20,200,30);

        JLabel senha = new JLabel("");
        senha.setBounds(80,140,120,30);

        JLabel tamanhoLabel = new JLabel("Tamanho:");
        tamanhoLabel.setBounds(30,60,80,30);

        JSpinner tamanho = new JSpinner(new SpinnerNumberModel(12,4,32,1));
        tamanho.setBounds(110,60,60,30);

        JCheckBox letras = new JCheckBox("Letras");
        letras.setBounds(30,100,100,30);
        letras.setSelected(true);

        JCheckBox numeros = new JCheckBox("Números");
        numeros.setBounds(130,100,100,30);
        numeros.setSelected(true);

        JCheckBox simbolos = new JCheckBox("Símbolos");
        simbolos.setBounds(240,100,100,30);

        JButton gerar = new JButton("Gerar");
        gerar.setBounds(80,180,100,30);

        JButton copiar = new JButton("Copiar");
        copiar.setBounds(220,180,100,30);

        JLabel msg = new JLabel("");
        msg.setBounds(170,210,200,30);

        JProgressBar forca = new JProgressBar(0,100);
        forca.setBounds(80,240,220,25);
        forca.setStringPainted(true);

        gerar.addActionListener(e -> {

            String letrasStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String numerosStr = "0123456789";
            String simbolosStr = "!@#$%&";

            String caracteres = "";

            if(letras.isSelected()) caracteres += letrasStr;
            if(numeros.isSelected()) caracteres += numerosStr;
            if(simbolos.isSelected()) caracteres += simbolosStr;

            if(caracteres.equals("")){
                msg.setText("Selecione um tipo");
                return;
            }

            int tam = (int) tamanho.getValue();

            Random random = new Random();
            String senhaGerada = "";

            for(int i=0;i<tam;i++){
                int index = random.nextInt(caracteres.length());
                senhaGerada += caracteres.charAt(index);
            }

            senha.setText(senhaGerada);

            int score = 0;

            if(tam >= 8) score += 25;
            if(tam >= 12) score += 25;
            if(letras.isSelected()) score += 15;
            if(numeros.isSelected()) score += 15;
            if(simbolos.isSelected()) score += 20;

            if(score > 100) score = 100;

            forca.setValue(score);

            if(score < 40) forca.setString("Fraca");
            else if(score < 70) forca.setString("Média");
            else forca.setString("Forte");

        });

        copiar.addActionListener(e -> {

            StringSelection selection =
                    new StringSelection(senha.getText());

            Toolkit.getDefaultToolkit()
                    .getSystemClipboard()
                    .setContents(selection,null);

            msg.setText("Copiado!");

            Timer timer = new Timer(2000,new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    msg.setText("");
                }
            });

            timer.setRepeats(false);
            timer.start();
        });

        janela.add(titulo);
        janela.add(senha);
        janela.add(tamanhoLabel);
        janela.add(tamanho);
        janela.add(letras);
        janela.add(numeros);
        janela.add(simbolos);
        janela.add(gerar);
        janela.add(copiar);
        janela.add(msg);
        janela.add(forca);

        janela.setVisible(true);
    }
}