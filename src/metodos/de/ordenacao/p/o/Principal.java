/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos.de.ordenacao.p.o;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class Principal
{

    Arquivo arqOrd, arqRev, arqRand, auxRev, auxRand;
    private int tini, tfim, movO, compO, movR, compR, movRev, compRev, ttotalO, ttotalRev, ttotalRand, compRand, movRand;

    FileWriter fw;
    PrintWriter pw;

    public Principal()
    {
        try
        {
            arqOrd = new Arquivo("Ordenado.dat");
            arqRev = new Arquivo("Reverso.dat");
            arqRand = new Arquivo("Random.dat");
            auxRev = new Arquivo("auxRev.dat");
            auxRand = new Arquivo("auxRand.dat");

            fw = new FileWriter("tabela.txt");
            pw = new PrintWriter(fw);
            pw.println("+-----------------------+---------------+---------------+---------------+---------------+---------------+");
            pw.print("|   Metodos Ordenação\t|");
            pw.println("   Comp. Prog\t|   Comp. Equa\t|   Mov. Prog\t|    Mov. Equa\t|    Tempo\t|"); // INICIO DA TABELA
            pw.println("+-----------------------+---------------+---------------+---------------+---------------+---------------+");
        } catch (IOException e)
        {
        }
    }

    //comparacao programa, comparacao esperada, movimentacao programa, movimentacao esperada, tempo total
    public void gravaLinha(int compP, int compE, int movP, int movE, int tempo) throws IOException
    {
        pw.println("\t|    " + compP + "\t\t|    " + compE + "\t\t|    " + movP + "\t\t|    " + movE + "\t\t|   " + tempo + "\t\t|");
    }

    public void insercaoDireta() throws IOException // CERTO
    {
        //Arquivo Ordenado       
        pw.println("|   Inserção Direta\t|\t\t\t\t\t\t\t\t\t\t|"); // NOME METODO        
        pw.println("+-----------------------+---------------+---------------+---------------+---------------+---------------+");
        arqOrd.initComp();
        arqOrd.initMov();
        tini = (int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        arqOrd.InsercaoDiretaArq();
        tfim = (int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        compO = arqOrd.getComp();
        movO = arqOrd.getMov();
        ttotalO = tfim - tini;

        pw.print("|   Arquivo Ordenado");
        gravaLinha(compO, (int) arqOrd.filesize() - 1, movO, (int) (3 * (arqOrd.filesize() - 1)), ttotalO);//tempo execução no Arquivo Ordenado já convertido para segundos

        //Arquivo Reverso
        arqRev.seekArq(0);
        auxRev.copiaArquivo(arqRev.getFile()); //faz uma cópia do arquivo de arqRev para preservar o original
        auxRev.initComp();
        auxRev.initMov();
        tini = (int) System.currentTimeMillis();
        auxRev.InsercaoDiretaArq();
        tfim = (int) System.currentTimeMillis();
        ttotalRev = tfim - tini;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();

        pw.print("|   Arquivo Reverso");
        gravaLinha(compRev, (int) ((Math.pow((int) auxRev.filesize(), 2) + (int) auxRev.filesize()) - 4) / 4, movRev, (int) ((Math.pow((int) auxRev.filesize(), 2) + 3 * auxRev.filesize() - 4) / 2), ttotalRev);//tempo execução no Arquivo Reverso já convertido para segundos

        //Arquivo Randomico
        arqRand.seekArq(0);
        auxRand.copiaArquivo(arqRand.getFile()); //faz uma cópia do arquivo de arqRand para preservar o original
        auxRand.initComp();
        auxRand.initMov();
        tini = (int) System.currentTimeMillis();
        auxRand.InsercaoDiretaArq();
        tfim = (int) System.currentTimeMillis();
        ttotalRand = tfim - tini;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        pw.print("|   Arquivo Randomico");
        gravaLinha(compRand, (int) ((Math.pow((int) auxRand.filesize(), 2) + (int) auxRand.filesize() - 2) / 4), movRand, (int) ((Math.pow((int) auxRand.filesize(), 2) + 9 * auxRand.filesize() - 10) / 4), ttotalRand);//tempo execução no Arquivo Randomico já convertido para segundos   
        pw.print("+-----------------------+---------------+---------------+---------------+---------------+---------------+");
    }

    public void InsercaoBinaria() throws IOException // CERTO
    {
        pw.println("\n|   Inserção Binaria\t|\t\t\t\t\t\t\t\t\t\t|"); // NOME METODO
        pw.println("+-----------------------+---------------+---------------+---------------+---------------+---------------+");
        arqOrd.initComp();
        arqOrd.initMov();
        tini = (int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        arqOrd.InsercaoBinariaArq();
        tfim = (int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        compO = arqOrd.getComp();
        movO = arqOrd.getMov();
        ttotalO = tfim - tini;

        pw.print("|   Arquivo Ordenado");
        gravaLinha(compO, (int) (arqOrd.filesize() * (Math.log10(arqOrd.filesize()) - Math.log10(Math.E) + 0.5)), movO, (int) (3 * (arqOrd.filesize() - 1)), ttotalO);//tempo execução no Arquivo Ordenado já convertido para segundos

        //Arquivo Reverso
        arqRev.seekArq(0);
        auxRev.copiaArquivo(arqRev.getFile()); //faz uma cópia do arquivo de arqRev para preservar o original
        auxRev.initComp();
        auxRev.initMov();
        tini = (int) System.currentTimeMillis();
        auxRev.InsercaoBinariaArq();
        tfim = (int) System.currentTimeMillis();
        ttotalRev = tfim - tini;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();

        pw.print("|   Arquivo Reverso");
        gravaLinha(compRev, (int) (auxRev.filesize() * (Math.log10(auxRev.filesize()) - Math.log10(Math.E) + 0.5)), movRev, (int) ((Math.pow(auxRev.filesize(), 2) + 9 * auxRev.filesize() - 10) / 4), ttotalRev);//tempo execução no Arquivo Reverso já convertido para segundos

        //Arquivo Randomico
        arqRand.seekArq(0);
        auxRand.copiaArquivo(arqRand.getFile()); //faz uma cópia do arquivo de arqRand para preservar o original
        auxRand.initComp();
        auxRand.initMov();
        tini = (int) System.currentTimeMillis();
        auxRand.InsercaoBinariaArq();
        tfim = (int) System.currentTimeMillis();
        ttotalRand = tfim - tini;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        pw.print("|   Arquivo Randomico");
        gravaLinha(compRand, (int) (auxRand.filesize() * (Math.log10(auxRand.filesize()) - Math.log10(Math.E) + 0.5)), movRand, (int) ((Math.pow((int) auxRand.filesize(), 2) + 3 * auxRand.filesize() - 4) / 2), ttotalRand);//tempo execução no Arquivo Randomico já convertido para segundos   
        pw.print("+-----------------------+---------------+---------------+---------------+---------------+---------------+");
    }

    public void BubbleSort() throws IOException // CERTO
    {
        pw.println("\n|   Bubble Sort\t\t|\t\t\t\t\t\t\t\t\t\t|"); // NOME METODO
        pw.println("+-----------------------+---------------+---------------+---------------+---------------+---------------+");
        arqOrd.initComp();
        arqOrd.initMov();
        tini = (int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        arqOrd.bubbleSortArq();
        tfim = (int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        compO = arqOrd.getComp();
        movO = arqOrd.getMov();
        ttotalO = tfim - tini;

        pw.print("|   Arquivo Ordenado");
        gravaLinha(compO, (int) (Math.pow(arqOrd.filesize(), 2) - arqOrd.filesize()) / 2, movO, 0, ttotalO);//tempo execução no Arquivo Ordenado já convertido para segundos

        //Arquivo Reverso
        arqRev.seekArq(0);
        auxRev.copiaArquivo(arqRev.getFile()); //faz uma cópia do arquivo de arqRev para preservar o original
        auxRev.initComp();
        auxRev.initMov();
        tini = (int) System.currentTimeMillis();
        auxRev.bubbleSortArq();
        tfim = (int) System.currentTimeMillis();
        ttotalRev = tfim - tini;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();

        pw.print("|   Arquivo Reverso");
        gravaLinha(compRev, (int) (Math.pow(auxRev.filesize(), 2) - auxRev.filesize()) / 2, movRev, (int) (3 * (Math.pow((int) auxRev.filesize(), 2) - auxRev.filesize())) / 4, ttotalRev);//tempo execução no Arquivo Reverso já convertido para segundos

        //Arquivo Randomico
        arqRand.seekArq(0);
        auxRand.copiaArquivo(arqRand.getFile()); //faz uma cópia do arquivo de arqRand para preservar o original
        auxRand.initComp();
        auxRand.initMov();
        tini = (int) System.currentTimeMillis();
        auxRand.bubbleSortArq();
        tfim = (int) System.currentTimeMillis();
        ttotalRand = tfim - tini;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        pw.print("|   Arquivo Randomico");
        gravaLinha(compRand, (int) ((Math.pow(auxRand.filesize(), 2) - auxRand.filesize()) / 2), movRand, (int) (3 * (Math.pow((int) auxRand.filesize(), 2) - auxRand.filesize())) / 2, ttotalRand);//tempo execução no Arquivo Randomico já convertido para segundos   
        pw.print("+-----------------------+---------------+---------------+---------------+---------------+---------------+");
    }

    public void SelecaoDireta() throws IOException // CERTO
    {
        pw.println("\n|   Seleção Direta\t|\t\t\t\t\t\t\t\t\t\t|"); // NOME METODO
        pw.println("+-----------------------+---------------+---------------+---------------+---------------+---------------+");
        arqOrd.initComp();
        arqOrd.initMov();
        tini = (int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        arqOrd.SelecaoDiretaArq();
        tfim = (int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        compO = arqOrd.getComp();
        movO = arqOrd.getMov();
        ttotalO = tfim - tini;

        pw.print("|   Arquivo Ordenado");
        gravaLinha(compO, (int) ((Math.pow(arqOrd.filesize(), 2) - arqOrd.filesize()) / 2), movO, (int) (3 * (arqOrd.filesize() - 1)), ttotalO);//tempo execução no Arquivo Ordenado já convertido para segundos

        //Arquivo Reverso
        arqRev.seekArq(0);
        auxRev.copiaArquivo(arqRev.getFile()); //faz uma cópia do arquivo de arqRev para preservar o original
        auxRev.initComp();
        auxRev.initMov();
        tini = (int) System.currentTimeMillis();
        auxRev.SelecaoDiretaArq();
        tfim = (int) System.currentTimeMillis();
        ttotalRev = tfim - tini;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();

        pw.print("|   Arquivo Reverso");
        gravaLinha(compRev, (int) ((Math.pow(auxRev.filesize(), 2) - auxRev.filesize()) / 2), movRev, (int) (Math.pow((int) auxRev.filesize(), 2) / 4 + 3 * (auxRev.filesize() - 1)), ttotalRev);//tempo execução no Arquivo Reverso já convertido para segundos

        //Arquivo Randomico
        arqRand.seekArq(0);
        auxRand.copiaArquivo(arqRand.getFile()); //faz uma cópia do arquivo de arqRand para preservar o original
        auxRand.initComp();
        auxRand.initMov();
        tini = (int) System.currentTimeMillis();
        auxRand.SelecaoDiretaArq();
        tfim = (int) System.currentTimeMillis();
        ttotalRand = tfim - tini;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        pw.print("|   Arquivo Randomico");
        gravaLinha(compRand, (int) ((Math.pow(arqRand.filesize(), 2) - auxRand.filesize()) / 2), movRand, (int) (auxRand.filesize() * Math.log10(auxRand.filesize()) + 0.577216), ttotalRand);//tempo execução no Arquivo Randomico já convertido para segundos          
        pw.println("+-----------------------+---------------+---------------+---------------+---------------+---------------+");
    }

    public void Heap() throws IOException
    {
        pw.print("\n|Heap|\t\t");
        arqOrd.initComp();
        arqOrd.initMov();
        tini = (int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        arqOrd.heapArq();
        tfim = (int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        compO = arqOrd.getComp();
        movO = arqOrd.getMov();
        ttotalO = tfim - tini;

//        System.out.println("Arquivo Ordenado");
//        gravaLinha(compO, (int) arqOrd.filesize() - 1, movO, (int) (3 * (arqOrd.filesize() - 1)), ttotalO);//tempo execução no Arquivo Ordenado já convertido para segundos
        //Arquivo Reverso
        arqRev.seekArq(0);
        auxRev.copiaArquivo(arqRev.getFile()); //faz uma cópia do arquivo de arqRev para preservar o original
        auxRev.initComp();
        auxRev.initMov();
        tini = (int) System.currentTimeMillis();
        auxRev.heapArq();
        tfim = (int) System.currentTimeMillis();
        ttotalRev = tfim - tini;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();

//        System.out.println("Arquivo Reverso");
//        gravaLinha(compRev, (int) ((Math.pow((int) auxRev.filesize(), 2) + (int) auxRev.filesize()) - 4) / 2, movRev, (int) ((Math.pow((int) auxRev.filesize(), 2) + 3 * auxRev.filesize() - 4) / 2), ttotalRev);//tempo execução no Arquivo Reverso já convertido para segundos
        //Arquivo Randomico
        arqRand.seekArq(0);
        auxRand.copiaArquivo(arqRand.getFile()); //faz uma cópia do arquivo de arqRand para preservar o original
        auxRand.initComp();
        auxRand.initMov();
        tini = (int) System.currentTimeMillis();
        auxRand.heapArq();
        tfim = (int) System.currentTimeMillis();
        ttotalRand = tfim - tini;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
//        System.out.println("Arquivo Randomico");
//        gravaLinha(compRand, (int) ((Math.pow((int) auxRand.filesize(), 2) + (int) auxRand.filesize() - 2) / 4), movRand, (int) ((Math.pow((int) auxRev.filesize(), 2) + 9 * auxRev.filesize() - 10) / 4), ttotalRand);//tempo execução no Arquivo Randomico já convertido para segundos   

    }

    public void Shell() throws IOException
    {
        pw.print("\n|Inserção direta\t\t");
        arqOrd.initComp();
        arqOrd.initMov();
        tini = (int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        arqOrd.ShellArq();
        tfim = (int) System.currentTimeMillis(); //método para pegar a hora atual em milisegundos
        compO = arqOrd.getComp();
        movO = arqOrd.getMov();
        ttotalO = tfim - tini;

//        System.out.println("Arquivo Ordenado");
//        gravaLinha(compO, (int) arqOrd.filesize() - 1, movO, (int) (3 * (arqOrd.filesize() - 1)), ttotalO);//tempo execução no Arquivo Ordenado já convertido para segundos
        //Arquivo Reverso
        arqRev.seekArq(0);
        auxRev.copiaArquivo(arqRev.getFile()); //faz uma cópia do arquivo de arqRev para preservar o original
        auxRev.initComp();
        auxRev.initMov();
        tini = (int) System.currentTimeMillis();
        auxRev.ShellArq();
        tfim = (int) System.currentTimeMillis();
        ttotalRev = tfim - tini;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();

//        System.out.println("Arquivo Reverso");
//        gravaLinha(compRev, (int) ((Math.pow((int) auxRev.filesize(), 2) + (int) auxRev.filesize()) - 4) / 2, movRev, (int) ((Math.pow((int) auxRev.filesize(), 2) + 3 * auxRev.filesize() - 4) / 2), ttotalRev);//tempo execução no Arquivo Reverso já convertido para segundos
        //Arquivo Randomico
        arqRand.seekArq(0);
        auxRand.copiaArquivo(arqRand.getFile()); //faz uma cópia do arquivo de arqRand para preservar o original
        auxRand.initComp();
        auxRand.initMov();
        tini = (int) System.currentTimeMillis();
        auxRand.ShellArq();
        tfim = (int) System.currentTimeMillis();
        ttotalRand = tfim - tini;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();

//        System.out.println("Arquivo Randomico");
//        gravaLinha(compRand, (int) ((Math.pow((int) auxRand.filesize(), 2) + (int) auxRand.filesize() - 2) / 4), movRand, (int) ((Math.pow((int) auxRev.filesize(), 2) + 9 * auxRev.filesize() - 10) / 4), ttotalRand);//tempo execução no Arquivo Randomico já convertido para segundos   
    }

    public void geraTabela() throws IOException
    {
        arqOrd.geraArquivoOrdenado();
        arqRev.geraArquivoReverso();
        arqRand.geraArquivoRandomico();
        insercaoDireta();
        InsercaoBinaria();

        BubbleSort();
        SelecaoDireta();
//        Heap();
//        Shell();





        pw.println("|\t\t\t\t\t\t\t\t\t\t\tGabriel Marioto\t|");
        pw.println("+-----------------------+---------------+---------------+---------------+---------------+---------------+");
        fw.close();
        //e assim continua para os outros métodos de ordenacao!!!
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {
        // TODO code application logic here
        Principal p = new Principal();
        p.geraTabela();
    }

}
