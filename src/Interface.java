import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class Interface extends JFrame {
    private JTextField valorField;
    private JComboBox<String> moedaOrigemCombo;
    private JComboBox<String> moedaDestinoCombo;
    private JLabel resultadoLabel;

    public Interface(){
        setTitle("Conversor de Moedas PRIX");
        setSize(400,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        valorField = new JTextField(10);
        moedaOrigemCombo = new JComboBox<>(new String[]{"USD", "BRL", "EUR"});
        moedaDestinoCombo = new JComboBox<>(new String[]{"USD", "BRL", "EUR"});
        JButton converterButton = new JButton("Converter");
        resultadoLabel = new JLabel("Total: ");

        converterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    double valor = Double.parseDouble(valorField.getText());
                    String moedaOrigem = (String) moedaOrigemCombo.getSelectedItem();
                    String moedaDestino = (String) moedaDestinoCombo.getSelectedItem();

                    Map<String, Double> taxas = APIClient.getExchangeRates();

                    double resultado = Conversor.converter(valor,moedaOrigem, moedaDestino, taxas);
                    resultadoLabel.setText("Total: " + resultado);
                } catch (Exception ex) {
                    resultadoLabel.setText("Erro ao converter");
                }
            }
        });

        add(new JLabel("Valor:"));
        add(valorField);
        add(new JLabel("Moeda de Origem:"));
        add(moedaOrigemCombo);
        add(new JLabel("Moeda de Destino"));
        add(moedaDestinoCombo);
        add(converterButton);
        add(resultadoLabel);

        setVisible(true);
    }

    public static void main(String[] args){
        new Interface();
    }
}

//Aqui criamos a Interface Grafica, confesso, que para esta etapa, foi necessario pesquisar e tutorias para realizar, ja que minha
//Experiencia com Swing nao e muito boa!