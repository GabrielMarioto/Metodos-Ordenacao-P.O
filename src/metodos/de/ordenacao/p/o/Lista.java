/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos.de.ordenacao.p.o;

/**
 *
 * @author Gabriel
 */
public class Lista
{

    private int tl;
    private No inicio, fim;
    private int comp, mov;

    public Lista()
    {
        this.tl = 0;
        this.inicio = null;
        this.fim = null;
    }

    public int getTl()
    {
        return tl;
    }

    public void setTl(int tl)
    {
        this.tl = tl;
    }

    public No getInicio()
    {
        return inicio;
    }

    public void setInicio(No inicio)
    {
        this.inicio = inicio;
    }

    public int getComp()
    {
        return comp;
    }

    public void setComp(int comp)
    {
        this.comp = comp;
    }

    public void setMov(int mov)
    {
        this.mov = mov;
    }

    public int getMov()
    {
        return mov;
    }

    public No getFim()
    {
        return fim;
    }

    public void setFim(No fim)
    {
        this.fim = fim;
    }

    public No getIndex(int num)
    {
        No aux = inicio;

        for (int i = 0; i < num && aux != null; i++)
        {
            aux = aux.getProx();
        }

        return aux;
    }

    public void insert(int info)
    {
        No aux = inicio;
        No novo = new No(info);

        if (inicio == null)
        {
            tl++;
            inicio = novo;
        } else
        {
            while (aux.getProx() != null)
            {
                aux = aux.getProx();
            }

            novo.setAnt(aux);
            aux.setProx(novo);
            tl++;
        }
        fim = novo;
    }

    public void exibe()
    {
        No aux = inicio;

        while (aux != null)
        {
            System.out.println("Info: " + aux.getInfo());
            aux = aux.getProx();
        }
    }

    public No getPosNode(No inicio, int pos)
    {
        No aux = inicio;
        int i = 0;
        if (i < tl - 1 && aux != null)
        {
            while (i <= pos)
            {
                aux = aux.getProx();
                i++;
            }
        }
        return aux;
    }

    public void setInfoPos(int pos, int info)
    {
        No aux = inicio;
        int i = 0;
        while (i <= pos && aux != null)
        {
            aux = aux.getProx();
            i++;
        }
        aux.setInfo(info);
    }

    public void InsercaoDiretaLista()
    {
        No i = inicio.getProx();
        int aux;
        No pos = null;
        while (i != null)
        {
            pos = i;
            aux = pos.getInfo();
            while (pos != inicio && aux < pos.getAnt().getInfo())
            {
                pos.setInfo(pos.getAnt().getInfo());
                pos = pos.getAnt();
            }
            pos.setInfo(aux);
            i = i.getProx();
        }
    }

    public void selecaoDiretaLista()
    {
        No aux, j, posmenor;

        int i, menor;

        aux = inicio;
        while (aux.getProx() != null)
        {
            menor = aux.getInfo();
            i = aux.getInfo();
            j = aux.getProx();
            posmenor = aux;
            while (j != null)
            {
                if (j.getInfo() < menor)
                {
                    menor = j.getInfo();
                    posmenor = j;
                }
                j = j.getProx();
            }
            aux.setInfo(menor);
            posmenor.setInfo(i);
            aux = aux.getProx();
        }
    }

    public void BubbleSortLista()
    {
        int tl = getTl();
        No aux = inicio;
        No fim = this.fim;
        int temp;
        while (aux != fim)
        {
            while (aux.getProx() != null)
            {
                if (aux.getInfo() > aux.getProx().getInfo())
                {
                    temp = aux.getInfo();
                    aux.setInfo(aux.getProx().getInfo());
                    aux.getProx().setInfo(temp);
                }
                aux = aux.getProx();
            }
            aux = inicio;
            fim = fim.getAnt();
        }
    }

    public void ShakeSortLista()
    {
        No aux = inicio;
        No vai = inicio, vem = fim;
        No ult = fim;
        int temp;

        while (aux != ult)
        {
            while (aux.getProx() != null)
            {
                if (aux.getInfo() > aux.getProx().getInfo())
                {
                    temp = aux.getInfo();
                    aux.setInfo(aux.getProx().getInfo());
                    aux.getProx().setInfo(temp);
                }
                aux = aux.getProx();
            }
            while (ult.getAnt() != null)
            {
                if (ult.getInfo() < ult.getAnt().getInfo())
                {
                    temp = ult.getInfo();
                    ult.setInfo(ult.getAnt().getInfo());
                    ult.getAnt().setInfo(temp);
                }
                ult = ult.getAnt();
            }

            vai = vai.getProx();
            aux = vai;
            vem = vem.getAnt();
            ult = vem;
        }
    }

    public void QuickSemPivo()
    {
        QuickSP(inicio, fim);
    }

    public void QuickSP(No ini, No fim)
    {
        No i = ini, j = fim;
        int aux;

        while (i != j)
        {
            comp++;
            while (i != j && i.getInfo() <= j.getInfo())
            {
                i = i.getProx();
                comp++;
            }
            comp++;
            while (i != j && j.getInfo() >= i.getInfo())
            {
                j = j.getAnt();
                comp++;
            }
            if (i != j)
            {
                aux = j.getInfo();
                j.setInfo(i.getInfo());
                i.setInfo(aux);
                mov += 2;
            }
        }
        if (ini != i)
        {
            QuickSP(ini, i.getAnt());
        }
        if (j != fim)
        {
            QuickSP(j.getProx(), fim);
        }
    }

    public void QuickComPivo()
    {
        QuickCP(0, tl - 1);
    }

    public int getPos(No aux)
    {
        int i = 0;
        No temp = inicio;
        while(temp != null && aux != null && temp.getInfo() != aux.getInfo())
        {
            i++;
            temp = temp.getProx();
        }
        
        return i;
    }
    public void QuickCP(int ini, int fim)
    {
        No i = getIndex(ini);
        No j = getIndex(fim);
        No pivo = getIndex((ini + fim) / 2);
        int aux;

        while (getPos(i) < getPos(j))
        {
            comp++;
            while (i.getInfo() < pivo.getInfo())
            {
                i = i.getProx();
                comp++;
            }
            while (j.getInfo() > pivo.getInfo())
            {                
                j = j.getAnt();
                comp++;
            }

            if (getPos(i) <= getPos(j))
            {
                aux = j.getInfo();
                j.setInfo(i.getInfo());
                i.setInfo(aux);
                mov += 2;
                i = i.getProx();
                j = j.getAnt();
            }
        }
        if(ini < getPos(j))
            QuickCP(ini, getPos(j));
        if(getPos(i) < fim)
            QuickCP(getPos(i), fim);
    }

    public int buscaBinaria(int chave, int tl)
    {
        int inicio = 0, fim = tl - 1, meio = tl / 2;

        No aux = getIndex(meio);
        while (inicio < fim && aux.getInfo() != chave)
        {
            if (aux.getInfo() == chave)
            {
                return meio;
            } else if (chave > aux.getInfo())
            {
                inicio = meio + 1;
            } else
            {
                fim = meio - 1;
            }

            meio = (inicio + fim) / 2;
            aux = getIndex(meio);
        }
        if (chave > aux.getInfo())
        {
            return meio + 1;
        }
        return meio;
    }

    public void insercaoBinariaLista()
    {
        int pos, j = 0;
        No reg1 = inicio.getProx();
        int temp;
        int i = 1;
        while (reg1 != null)
        {
            pos = buscaBinaria(reg1.getInfo(), i); // NUMERO E TAMANHO DO TL
            temp = reg1.getInfo();
            j = i;
            while (j > pos && reg1.getAnt() != null)
            {
                reg1.setInfo(reg1.getAnt().getInfo());
                reg1 = reg1.getAnt();
                j--;
            }
            reg1.setInfo(temp);
            i++;
            reg1 = reg1.getProx();
        }
    }

    public No getNo(int pos)
    {
        No dir = inicio;
        int i = 0;
        while (i < pos)
        {
            dir = dir.getProx();
            i++;
        }
        return dir == null ? null : dir;
    }

    public void ShellLista()
    {
        int dist = 4, j;
        No aux;
        No auxD;
        No auxK;
        int temp;
        int cont, k;

        while (dist > 0)
        {
            for (int i = 0; i < dist; i++)
            {
                j = i;
                while (j + dist < tl)
                {
                    aux = getNo(j);
                    auxD = getNo(j + dist);
                    if (aux.getInfo() < auxD.getInfo())
                    {
                        temp = auxD.getInfo();
                        auxD.setInfo(aux.getInfo());
                        aux.setInfo(temp);
                        k = j;
                        auxK = getNo(k - dist);

                        while (k - dist >= i && auxD.getInfo() > auxK.getInfo())
                        {
                            temp = auxD.getInfo();
                            auxD.setInfo(auxK.getInfo());
                            auxK.setInfo(temp);
                            k = k - dist;
                            auxK = getNo(k - dist);
                        }
                    }
                    j = j + dist;
                }
            }
            dist = dist / 2;
        }
    }
    public void setInfoPos(int pos, int num, No l)
    {
        int i = 0;
        while(l != null && i != pos)
        {
            l = l.getProx();
            i++;
        }
        l.setInfo(num);
    }

    public int getInfoPos(int num)
    {
        int i = 0;
        No aux = inicio;
        while( aux != null && i != num)
        {
            aux = aux.getProx();
            i++;
        }
        return aux.getInfo();
    }
    public void fusaoMerge(Lista l1, Lista l2, int seq)
    {
        int i = 0, j = 0, k = 0, auxSeq = seq;
        No lista1 = l1.getInicio();
        No lista2 = l2.getInicio();
        No aux = inicio;
        
        while(k < tl)
        {
            while(aux != null && i < seq && j < seq)
            {
                if(lista1.getInfo() < lista2.getInfo())
                {
                    aux.setInfo(lista1.getInfo());
                    lista1 = lista1.getProx();
                    i++;
                }
                else
                {
                    aux.setInfo(lista2.getInfo());
                    lista2 = lista2.getProx();
                    j++;
                }
                aux = aux.getProx();
                k++;
            }
            while(aux != null && i < seq)
            {
                aux.setInfo(lista1.getInfo());
                lista1 = lista1.getProx();
                aux = aux.getProx();
                i++;                
                k++;
            }
            while(aux != null && j < seq)
            {
                aux.setInfo(lista2.getInfo());
                lista2 = lista2.getProx();
                aux = aux.getProx();
                j++;                
                k++;
            }
            seq += auxSeq;
        }
    }
    public void particaoLista(Lista l1, Lista l2)
    {
        No principal = inicio;
        for (int i = 0, j = tl / 2; i < tl /2 ; i++, j++)
        {
            l1.insert(principal.getInfo());
            l2.insert(getInfoPos(j));
            principal = principal.getProx();
        }
    }
    public void mergeListaI()
    {
        int seq = 1;
        Lista l1 = new Lista();
        Lista l2 = new Lista();
        
        while(seq <= tl / 2)
        {
            particaoLista(l1, l2);
            fusaoMerge(l1, l2, seq);
            seq *= 2;
        }
    }
    public void mergeSortListaII()
    {
        Lista list = new Lista();
        mergeListaRecursivo(list, 0, tl-1);        
    }
    public void mergeListaRecursivo(Lista l1, int esq, int dir)
    {
        int meio;
        if(esq < dir)
        {
            meio = (esq + dir) / 2;
            mergeListaRecursivo(l1, esq, meio);
            mergeListaRecursivo(l1, meio+1, dir);
            fusaoMergeLista(l1, esq, meio, meio+1, dir);
        }
    }
    public void fusaoMergeLista(Lista l1, int ini1, int fim1, int ini2, int fim2)
    {
        int i = ini1, j = ini2, k = 0;
        No aux1 = getNo(i);
        No aux2 = getNo(j);
        No principal = inicio;
        No lista;
        while(i <= fim1 && j <=fim2)
        {
            if(aux1.getInfo() < aux2.getInfo())
            {
                l1.insert(aux1.getInfo());
                i++;
                mov++;
                aux1 = aux1.getProx();
            }
            else
            {
                l1.insert(aux2.getInfo());
                j++;
                mov++;
                aux2 = aux2.getProx();
            }
            k++;
        }
        while(i <= fim1)
        {
            l1.insert(aux1.getInfo());
            i++;
            aux1 = aux1.getProx();
            mov++;
        }
        while(j <= fim2)
        {
            l1.insert(aux2.getInfo());
            j++;
            aux2 = aux2.getProx();
            mov++;
        }
        lista = l1.getInicio();
        for ( i = 0; i < k; i++)
        {
            setInfoPos(i+ini1, lista.getInfo(), principal);
            lista = lista.getProx();
        }
    }
} // FIM DA CLASSE LISTA
