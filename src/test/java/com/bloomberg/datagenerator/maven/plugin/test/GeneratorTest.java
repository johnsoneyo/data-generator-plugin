/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloomberg.datagenerator.maven.plugin.test;

import com.bloomberg.datagenerator.maven.plugin.csv.DocumentFactory;
import com.bloomberg.datagenerator.maven.plugin.csv.TextDocument;
import java.util.Arrays;
import org.junit.Test;

/**
 *
 * @author johnson3yo
 */
public class GeneratorTest {

    @Test
    public void generateRandomRow() {
        DocumentFactory dfactory = new DocumentFactory();
        TextDocument csv = dfactory.getTextDocument("CSV");
        for (int g = 1; g <= 5; g++) {
            String row[] = csv.generateRandomRow(g);
            System.out.println(Arrays.toString(row));
        }

    }

}
