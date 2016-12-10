package pl.edu.pw.ee.lzw;

import static java.lang.System.out;

/**
 * Application presents the LZW algorithm to compress and decompress text files.
 *
 * @author deckerw
 * @author bladekp
 */
public class Application {

    /**
     * Sample client, reads command line parameters and decide what user
     * want to do.
     *
     * @param args the command-line arguments
     */
    public static void main(String args[]) {
        if ((args.length == 3 && (args[0].equals("-d") || args[0].equals("-k"))) ||
                args.length == 1 && args[0].equals("-h")) {
            switch (args[0]) {
                case "-d":
                    LZWDecompression.invoke(args[1], args[2]);
                    break;
                case "-k":
                    LZWCompression.invoke(args[1], args[2]);
                    break;
                case "-h":
                    showHelp();
                    break;
                default:
                    showError();
            }
        } else {
            showError();
        }
    }

    /**
     * Shows error if user's command line parameters does not meet
     * requirements, then it call showHelp method.
     */
    private static void showError() {
        out.println("Error: input parameters doesn't meet requirements, read text below, and try again.");
        showHelp();
    }

    /**
     * Shows help how to use program in command line.
     */
    private static void showHelp() {
        out.println("Usage: java -jar lzw.jar [-hdk] [SOURCE] [DESTINATION]");
        out.println("\t -h shows this text.");
        out.println("\t -d LZW decompressing.");
        out.println("\t -k LZW compressing.");
        out.println("Example: java -jar lzw.jar -d compressed_file decompressed_file");
    }


}
