import java.util.Map;

public class Conversor {
    public static double converter(double valor, String moedaOrigem, String moedaDestino, Map<String, Double> taxas){
        double taxaOrigem = taxas.get(moedaOrigem);
        double taxaDestino = taxas.get(moedaDestino);
        return valor * (taxaDestino / taxaOrigem);
    }

}


//Esse converser funciona da seguinte maneira, obtemos por meio da base da api os valores de conversao das
//Moedas, um exemplo nao real, o dolar esta a R$5.89, ou seja, o valor que gostariamos de converter
//Seria 100 reais em dolares, sendo assim teremos o retorno 100 * (5.89/1) ou seja U$589