/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloomberg.datagenerator.maven.plugin;

import com.bloomberg.datagenerator.maven.plugin.csv.DocumentFactory;
import com.bloomberg.datagenerator.maven.plugin.csv.TextDocument;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.FileUtils;

/**
 *
 * @author johnson3yo
 */
@Mojo(name = "generate")
public class GeneratorMojo extends AbstractMojo {

    @Parameter(defaultValue = "~/csv")
    private String csvGenerationPath;
    @Parameter(defaultValue = "100000")
    private int rows;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        File f = new File(csvGenerationPath);
        if (!f.exists()) {
            FileUtils.mkdir(csvGenerationPath);
        } else {
            try {
                FileUtils.cleanDirectory(f);
            } catch (IOException ex) {
                Logger.getLogger(GeneratorMojo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        DocumentFactory dfactory = new DocumentFactory();
        TextDocument csv = dfactory.getTextDocument("CSV");
        String cv = csvGenerationPath.concat("csv_gen_").
                concat(String.valueOf(System.currentTimeMillis())).
                concat(".csv");
        getLog().info(">>>>>>>>>>>>-------Generating CSV >>>>>>>>> process will return in background ---");

        try (
                Writer writer = Files.newBufferedWriter(Paths.get(cv));
                CSVWriter write = new CSVWriter(writer,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);) {

            for (int g = 1; g <= rows; g++) {
                String row[] = csv.generateRandomRow(g);
                write.writeNext(row);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(GeneratorMojo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
