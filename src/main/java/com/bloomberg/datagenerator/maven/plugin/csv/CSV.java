/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloomberg.datagenerator.maven.plugin.csv;

import java.util.Random;

/**
 *
 * @author johnson3yo
 */
public class CSV implements TextDocument {

    @Override
    public String[] generateRandomRow(int pk) {
        String[] row = new String[5];
        String[] codes = Currencies.isoCodes;
        int codeSize = codes.length;

        for (int c = 0; c < row.length; c++) {
            if (c == 0) {
                row[c] = String.valueOf(pk);
            } else if (c == 1 | c == 2) {
                row[c] = codes[new Random().nextInt(codeSize)];
                //retry if 
                if (row[1].equals(row[2])) {
                    row[c] = codes[new Random().nextInt(codeSize)];
                }
            } else if (c == 3) {
                row[c] = "";
            } else {
                row[c] = String.valueOf(new Random().nextInt((10000 - 2000) + 1) + 2000);
            }

        }

        return row;
    }

}
