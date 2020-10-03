package com.mboutaleb.pdfconverter;


import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import com.google.common.io.Files;

import java.io.*;

import static java.lang.System.exit;

/**
 * FilesConverter to convert file from pdf to docx and viceversa
 *
 * @author Mohamed Boutaleb <mohamed.boutaleb@student.supsi.ch>
 * @version 1.0.0 2020-08-19
 * @copyright Copyright (c) 2020 by Mohamed Boutaleb
 * @license This code is licensed under MIT license (see LICENSE.txt for details)
 * @run to run this program simply run the command : $ java -jar file-converter.jar <input-file> <output-file>
 * then the output file will be showed in the current directory
 */

public class WordToPdfConverter {
    private static final String ERROR_ARGS = "Error: you have not specified the args\nSpecify the input and the output file" +
            "\ne.g.: java -jar <input-file> <output-file>";
    private static final String ERROR_INPUT_FILE="Error: you must specify a docx file as input";
    private static final String DOCX_EXTENSION = "docx";
    private static final String PDF_EXTENSION = "pdf";

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println(ERROR_ARGS);
            exit(1);
        }

        final File inputFile = new File(args[0]);
        final File outputFile = new File(args[1]);

        if (Files.getFileExtension(args[0]).equalsIgnoreCase(DOCX_EXTENSION))
            try {
                InputStream docxInputStream = new FileInputStream(inputFile);
                OutputStream outputStream = new FileOutputStream(outputFile);
                IConverter converter = LocalConverter.builder().build();
                converter.convert(docxInputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).execute();
                outputStream.close();
                System.out.println("success");
            } catch (Exception e) {
                System.err.println(ERROR_INPUT_FILE);
                exit(2);
            }

        System.out.println("FILE CONVERTED SUCCESSFULLY");
        exit(0);
    }
}
