/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class App {
  public String getGreeting() {
    return "Hello World!";
  }

  static int counter = 0;

  public static void main(String[] args) throws IOException {
    var br = new BufferedReader(new FileReader("geo-reviews-dataset-2023.tskv"));

    var out_file = new File("result.csv");
    var writer = new FileWriter(out_file);

    String line = null;

    while ((line = br.readLine()) != null) {
      try {
        writer.append(convertLine(line));
      } catch (IOException e) {
        System.out.println("факап");
      }
    }
    writer.close();
    br.close();
  }

  static String convertLine(String tskv) {

    System.out.println(counter++);

    var parts = tskv.split("\t");

    Map<String, String> map = new HashMap<>();

    for (var p : parts) {
      var splitted = p.split("=");
      var key = splitted[0];
      var value = splitted[1];
      map.put(key, value);
    }

    var v1 = map.get("rating");
    var v2 = map.get("text");

    if (v1 != null & v2 != null)
      return String.format("%s\t%s\n", v1.replace(".", ""), v2);
    else
      throw new IllegalStateException();

    // address=Екатеринбург, ул. Московская / ул. Волгоградская / ул. Печатников
    // name_ru=Московский квартал
    // rating=3.
    // rubrics=Жилой комплекс
    // text=Московский квартал 2.\nШумно : летом по ночам дикие гонки. Грязно :
    // кругом стройки, невозможно открыть окна (16 этаж! ), вечно по району летает
    // мусор. Детские площадки убогие, на большой площади однотипные конструкции.
    // Очень дорогая коммуналка. Часто срабатывает пожарная сигнализация. Жильцы уже
    // не реагируют. В это время, обычно около часа, не работают лифты. Из плюсов -
    // отличная планировка квартир ( Московская 194 ), на мой взгляд. Ремонт от
    // застройщика на 3-. Окна вообще жуть - вместо вентиляции. По соотношению
    // цена/качество - 3.
  }

}