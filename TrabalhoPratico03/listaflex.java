import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;;

class celula {
    public classeFilme elemento;
    public celula prox;

    public celula() {
    }

    public celula(classeFilme elemento) {
        this.elemento = elemento;
        this.prox = null;
    }
}

class listaFlexivel {
    private celula primeiro;
    private celula ultimo;

    public listaFlexivel() {
        primeiro = new celula();
        ultimo = primeiro;
    }

    public void inserirInicio(classeFilme x) {
        celula tmp = new celula(x);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        }
        tmp = null;
    }

    public void inserirFim(classeFilme x) {
        ultimo.prox = new celula(x);
        ultimo = ultimo.prox;
    }

    public classeFilme removerInicio() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        }

        celula tmp = primeiro;
        primeiro = primeiro.prox;
        classeFilme resp = primeiro.elemento;
        tmp.prox = null;
        tmp = null;
        return resp;
    }

    public classeFilme removerFim() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");
        }

        // Caminhar ate a penultima celula:
        celula i;
        for (i = primeiro; i.prox != ultimo; i = i.prox)
            ;

        classeFilme resp = ultimo.elemento;
        ultimo = i;
        i = ultimo.prox = null;

        return resp;
    }

    public void inserir(classeFilme x, int pos) throws Exception {

        int tamanho = tamanho();

        if (pos < 0 || pos > tamanho) {
            throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
        } else if (pos == 0) {
            inserirInicio(x);
        } else if (pos == tamanho) {
            inserirFim(x);
        } else {
            // Caminhar ate a posicao anterior a insercao
            celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox)
                ;

            celula tmp = new celula(x);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;
        }
    }

    public classeFilme remover(int pos) throws Exception {
        classeFilme resp;
        int tamanho = tamanho();

        if (primeiro == ultimo) {
            throw new Exception("Erro ao remover (vazia)!");

        } else if (pos < 0 || pos >= tamanho) {
            throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
        } else if (pos == 0) {
            resp = removerInicio();
        } else if (pos == tamanho - 1) {
            resp = removerFim();
        } else {
            // Caminhar ate a posicao anterior a insercao
            celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.prox)
                ;

            celula tmp = i.prox;
            resp = tmp.elemento;
            i.prox = tmp.prox;
            tmp.prox = null;
            i = tmp = null;
        }

        return resp;
    }

    public void mostrar() {
        int j = 0;
        for (celula i = primeiro.prox; i != null; i = i.prox, j++) {

            MyIO.print("[" + j + "]" + " ");
            i.elemento.imprimir();
        }
    }

    public boolean pesquisar(classeFilme x) {
        boolean resp = false;
        for (celula i = primeiro.prox; i != null; i = i.prox) {
            if (i.elemento == x) {
                resp = true;
                i = ultimo;
            }
        }
        return resp;
    }

    public int tamanho() {
        int tamanho = 0;
        for (celula i = primeiro; i != ultimo; i = i.prox, tamanho++)
            ;
        return tamanho;
    }

}

class classeFilme {
    private static final String pastafilmesString = "/tmp/filmes/";

    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String nome;
    private String tituloOriginal;
    private Date dataLancamento;
    private int Duracao;
    private String genero;
    private String idiomaOriginal;
    private String situacao;
    private String palavrasChave[];

    private float orcamento;
    private ArrayList<String> palavras_chave;

    public classeFilme() {
        nome = "";
        tituloOriginal = "";
        Duracao = 0;
        genero = "";
        idiomaOriginal = "";
        situacao = "";
        orcamento = 0.0f;
        palavras_chave = new ArrayList<String>();

    }

    public classeFilme(String nome, String tituloOriginal, Date dataLancamento, int Duracao, String genero,
            String idiomaOriginal, String situacao, float orcamento, ArrayList<String> palavras_chave) {
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.tituloOriginal = tituloOriginal;
        this.Duracao = Duracao;
        this.genero = genero;
        this.idiomaOriginal = idiomaOriginal;
        this.situacao = situacao;
        this.orcamento = orcamento;
        this.palavras_chave = palavras_chave;

    }

    public String getnome() {
        return nome;
    }

    public void setnome(String nome) {
        this.nome = nome;
    }

    public void setdataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getTituloOString() {
        return tituloOriginal;
    }

    public void settituloOriginal(String tituloString) {
        this.tituloOriginal = tituloString;
    }

    public Date getDataLancamento() {
        if (this.dataLancamento == null) {
            return new Date();
        }
        return this.dataLancamento;
    }

    public int getDuracao() {
        return Duracao;
    }

    public void setDuracao(int Duracao) {
        this.Duracao = Duracao;
    }

    public String getgenero() {
        return genero;
    }

    public void setgenero(String genero) {
        this.genero = genero;
    }

    public String getidiomaOriginal() {
        return idiomaOriginal;
    }

    public void setidiomaOriginal(String idiomaOriginal) {
        this.idiomaOriginal = idiomaOriginal;
    }

    public String getsituacao() {
        return situacao;
    }

    public void setsituacao(String situacao) {
        this.situacao = situacao;
    }

    public ArrayList<String> getPalavrasChave() { // <> operador diamante
        return palavras_chave;
    }

    public void setPalavrasChave(String[] palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    public float getOrcamento() {
        return this.orcamento;
    }

    public void setorcamento(float orcamento) {
        this.orcamento = orcamento;
    }

    public void imprimir() {

        System.out.println(getnome() + " " + getTituloOString() + " " + sdf.format(getDataLancamento()) + " "
                + getDuracao() + " " +
                getgenero() + " " + getidiomaOriginal() + " " + getsituacao() + " " + getOrcamento() + " "
                + "[" + palavras_chave() + "]");
    }

    public classeFilme clone() {

        classeFilme clone = new classeFilme();

        clone.nome = this.nome;
        clone.tituloOriginal = this.tituloOriginal;
        clone.dataLancamento = this.dataLancamento;
        clone.Duracao = this.Duracao;
        clone.genero = this.genero;
        clone.idiomaOriginal = this.idiomaOriginal;
        clone.situacao = this.situacao;
        clone.orcamento = this.orcamento;
        clone.palavras_chave = this.palavras_chave;

        return clone;
    }

    // chamar metodo vazia
    public String removeTags(String original) {
        String remover = "";
        for (int i = 0; i < original.length(); i++) { // tags de abertuda e fechamento <>

            if (original.charAt(i) == '<') {
                while (original.charAt(i) != '>')
                    i++;
            } else {
                remover += original.charAt(i);
            }
        }
        return remover;
    }

    public String palavras_chave() {
        String vazia = "";

        if (this.palavras_chave.size() == 0) {
            return vazia;
        }

        for (String s : this.palavras_chave) { // s = proprio elemento do array

            vazia += s + ", ";
        }
        vazia = vazia.substring(0, vazia.length() - 2);
        return vazia;
    }

    public String buscaParenteses(String original) {
        String removeParenteses = "";

        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) != '(') {
                removeParenteses += original.charAt(i);
            }
        }

        return removeParenteses;
    }

    public void lerlancamento(String filmes) throws Exception {

        FileReader arq = new FileReader(pastafilmesString + filmes);
        BufferedReader br = new BufferedReader(arq);

        String linha = br.readLine();

        while (!linha.contains("span class=\"release\"")) {
            linha = br.readLine();
        }
        linha = br.readLine().trim();
        linha = linha.substring(0, 10);
        Date result = new SimpleDateFormat("dd/MM/yyyy").parse(linha);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = format.parse(removeTags(buscaParenteses(linha)));
            this.setdataLancamento(date);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        br.close();

    }

    public void lerGenero(String filmes) throws Exception {

        FileReader arq = new FileReader(pastafilmesString + filmes);
        BufferedReader br = new BufferedReader(arq);

        String linha = br.readLine();
        this.genero = "";
        while (!linha.contains("\"genres\"")) {
            linha = br.readLine();
        }
        linha = br.readLine();
        linha = br.readLine();
        for (String genero : removeTags(linha).trim().split("&nbsp;")) {
            this.genero += genero;
        }
        br.close();
    }

    public void lerduracao(String filmes) throws Exception {

        FileReader arq = new FileReader(pastafilmesString + filmes);
        BufferedReader br = new BufferedReader(arq);

        String linha = br.readLine();

        while (!linha.contains("\"runtime\"")) {
            linha = br.readLine();
        }
        linha = br.readLine();
        linha = br.readLine().trim();
        if (linha.contains("h")) {
            if (linha.contains("m"))
                this.Duracao = Integer.parseInt(linha.split(" ")[1].replace("m", ""));
            this.Duracao += Integer.parseInt(linha.split("h")[0]) * 60;
        } else {
            this.Duracao = Integer.parseInt(linha.replace("m", ""));
        }
        br.close();
    }

    public void lerTituloeSituacao(String filmes) throws Exception {

        FileReader arq = new FileReader(pastafilmesString + filmes);
        BufferedReader br = new BufferedReader(arq);

        String linha = br.readLine();

        while (!linha.contains("Título original") || !linha.contains("Situação")) {
            if (linha.contains("Título original")) {
                this.tituloOriginal = removeTags(linha).split("original")[1].trim(); // PRINTAR POSICAO [1] DO TITULO
                                                                                     // ORIGINAL
            } else if (linha.contains("Situação") && linha.contains("strong")) { // se conter
                this.situacao = removeTags(linha).trim().split(" ")[1];

                break;
            }
            linha = br.readLine();
        }
        if (this.tituloOriginal.isEmpty()) {
            this.tituloOriginal = nome;
        }
        br.close();
    }

    public void lerIdioma(String filmes) throws Exception {

        FileReader arq = new FileReader(pastafilmesString + filmes);
        BufferedReader br = new BufferedReader(arq);

        String linha = br.readLine();

        while (!linha.contains("Idioma original")) {
            linha = br.readLine();
        }
        this.idiomaOriginal = removeTags(linha).split("original")[1].trim();
        br.close();
    }

    public void lerPalavras(String filmes) throws Exception {
        // criada para abrir o arquivo tmp, onde contem os filmes
        FileReader arq = new FileReader(pastafilmesString + filmes);
        BufferedReader br = new BufferedReader(arq);

        String linha = br.readLine();
        this.palavras_chave.clear();
        while (!linha.contains("<section class=\"keywords right_column\">")) {
            linha = br.readLine();
        }
        while (!linha.contains("</ul>")) {

            if (linha.contains("<li>"))
                this.palavras_chave.add(removeTags(linha).trim());
            linha = br.readLine();
            if (linha.contains("Nenhuma palavra-chave")) {
                linha = br.readLine();
                break;
            }
        }
        br.close();
    }

    public void lerTudo(String filme) throws Exception {

        this.lerArquivoNome(filme);
        this.lerTituloeSituacao(filme);
        this.lerduracao(filme);
        this.lerIdioma(filme);
        this.lerOrcamento(filme);
        this.lerPalavras(filme);
        this.lerlancamento(filme);
        this.lerIdioma(filme);
        this.lerGenero(filme);
    }

    public void lerOrcamento(String filmes) throws Exception {
        // criada para abrir o arquivo tmp, onde contem os filmes
        FileReader arq = new FileReader(pastafilmesString + filmes);
        BufferedReader br = new BufferedReader(arq);
        String linha = br.readLine();

        while (!linha.contains("Orçamento")) {
            linha = br.readLine();
        }
        if (!linha.contains("-")) {
            String[] orcamentoString = removeTags(linha).trim().split(" ")[1].split("\\.");
            orcamentoString[0] = orcamentoString[0].replace(",", "").replace("$", "");
            this.orcamento = Float.parseFloat(orcamentoString[0]);
            this.orcamento += Float.parseFloat(orcamentoString[1]) * Math.pow(10, -orcamentoString[1].length());
        }
        br.close();
    }

    public void lerArquivoNome(String filmes) throws Exception {

        // criada para abrir o arquivo tmp, onde contem os filmes
        FileReader arq = new FileReader(pastafilmesString + filmes);
        BufferedReader br = new BufferedReader(arq);
        String linha = br.readLine();

        while (!linha.contains("h2 class")) {
            linha = br.readLine();
        }
        linha = br.readLine().trim();
        setnome(removeTags(linha));
        br.close();
    }
}

public class listaflex {
    public static void main(String[] args) throws Exception {

        String entrada[] = new String[1000];
        int numEntrada = 0;
        MyIO.setCharset("UTF-8");

        listaFlexivel filme = new listaFlexivel();

        while (true) {

            entrada[numEntrada] = MyIO.readLine();

            if (entrada[numEntrada].equals("FIM")) {
                break;
            }
            classeFilme teste = new classeFilme();
            teste.lerTudo(entrada[numEntrada]);
            filme.inserirFim(teste);

        }
        int x;
        x = MyIO.readInt(); // fazendo um for com valor de 25

        for (int i = 0; i < x; i++) {

            entrada[numEntrada] = MyIO.readLine();
            String recebe = entrada[numEntrada].substring(0, 2); // II e IF POREM DEPENDE
            String reserva = ""; // recebe nome novo
            int posIR = 0;

            if (recebe.compareTo("I*") == 0) {

                posIR = Integer.parseInt(entrada[numEntrada].substring(3, 5)); // pegando IF e a primeira letra do nome
                reserva = entrada[numEntrada].substring(6); // atribuindo a entrada na pos [6] para a nova
            } else if (recebe.compareTo("R*") == 0) {

                posIR = Integer.parseInt(entrada[numEntrada].substring(3));
            } else if (recebe.compareTo("II") == 0 || recebe.compareTo("IF") == 0) {

                reserva = entrada[numEntrada].substring(3);
            }

            if (recebe.compareTo("I*") == 0) {
                classeFilme film = new classeFilme();

                // ler atributos dos filmes
                film.lerTudo(reserva);
                filme.inserir(film, posIR);

            } else if (recebe.compareTo("II") == 0) {

                classeFilme film = new classeFilme();
                film.lerTudo(reserva);

                filme.inserirInicio(film);

            } else if (recebe.compareTo("IF") == 0) {

                classeFilme film = new classeFilme();
                film.lerTudo(reserva);

                filme.inserirFim(film);
            } else if (recebe.compareTo("R*") == 0) {

                MyIO.println("(R)" + " " + filme.remover(posIR).getnome());
            } else if (recebe.compareTo("RI") == 0) {

                MyIO.println("(R)" + " " + filme.removerInicio().getnome());
            } else if (recebe.compareTo("RF") == 0) {

                MyIO.println("(R)" + " " + filme.removerFim().getnome());
            }

        }

        filme.mostrar();

    }

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' &&
                s.charAt(2) == 'M');
    }

}
