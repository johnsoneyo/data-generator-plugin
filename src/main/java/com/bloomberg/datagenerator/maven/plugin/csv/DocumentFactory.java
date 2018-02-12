/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloomberg.datagenerator.maven.plugin.csv;

/**
 *
 * @author johnson3yo
 */
public class DocumentFactory {

    public TextDocument getTextDocument(String type) {

        if (type == null) {
            return null;
        }
        if (type.equalsIgnoreCase("CSV")) {
            return new CSV();

        }
        return null;
    }

}
