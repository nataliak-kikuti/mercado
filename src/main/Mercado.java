package main;

import model.Produto;
import utils.Utils;

import java.util.*;

public class Mercado {
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Produto> produtos;
    private static Map<Produto, Integer> carrinho;


    public static void main(String[] args) {
        produtos = new ArrayList<>();
        carrinho = new HashMap<>();
        menu();
    }

    private static void menu() {
        System.out.println("------------------------------------------------");
        System.out.println("--------------Bem Vindo Ao Mercado--------------");
        System.out.println("------------------------------------------------");
        System.out.println("*** Selecione a operacao que deseja realizar ****");
        System.out.println("------------------------------------------------");
        System.out.println("|    Opcao 1 - Cadastrar      |");
        System.out.println("|    Opcao 2 - Listar         |");
        System.out.println("|    Opcao 3 - Comprar        |");
        System.out.println("|    Opcao 4 - Carrinho       |");
        System.out.println("|    Opcao 5 - Sair           |");

        int opcao = input.nextInt();

        switch (opcao) {
            case 1:
                cadastrarProdutos();
                break;
            case 2:
                listarProdutos();
                break;
            case 3:
                comprarProdutos();
                break;
            case 4:
                verCarrinho();
                break;
            case 5:
                System.out.println("Volte Sempre");
                System.exit(0);
            default:
                System.out.println("Opcao Invalida");
                menu();
                break;
        }
    }

    private static void cadastrarProdutos() {
        System.out.println("Nome do Produto");
        String nome = input.next();

        System.out.println("Preco do Produto");
        Double preco = input.nextDouble();

        Produto produto = new Produto(nome, preco);
        produtos.add(produto);

        System.out.println(produto.getNome() + "cadastrado com sucesso");
        menu();
    }

    private static void listarProdutos() {
        if (produtos.size() > 0) {
            System.out.println("Lista de Produtos!\n");
            for (Produto p : produtos) {
                System.out.println(p);
            }
        } else {
            System.out.println("Nenhum produto cadastrado");
        }
        menu();
    }

    private static void comprarProdutos() {
        if (produtos.size() > 0) {
            System.out.println("Codigo do Produtos!\n");

            System.out.println("-------------------Produtos disponiveir------------");

            for (Produto p : produtos) {
                System.out.println(p + "\n");
            }
            int id = Integer.parseInt(input.next());
            boolean isPresente = false;

            for (Produto p : produtos) {
                if (p.getId() == id) {
                    int qtd = 0;
                    try {
                        qtd = carrinho.get(p);
                        //checa se o prouto esta no carrinho, incrementa a quantidade
                        carrinho.put(p, qtd+1);
                    } catch (NullPointerException e) {
                        //se o produto for o primeiro no carrinho
                        carrinho.put(p, 1);
                    }
                    System.out.println(p.getNome() + " adicionado ao carrinho");
                    isPresente = true;

                    if (isPresente) {
                        System.out.println("Deseja adicionar outro produto ao carrinho? ");
                        System.out.println("Digite 1 para Sim, 0 para finalizar a compra");

                        int option = Integer.parseInt(input.next());
                        if (option == 1) {
                            comprarProdutos();
                        } else {
                            finalizarCompra();
                        }
                    }
                } else {
                    System.out.println("Produto nao encontrado");
                    menu();
                }
            }
        } else {
            System.out.println("Nao exite produto nao Cadastrado");
            menu();
        }
    }

    private static void verCarrinho() {
        System.out.println("----------Produtos no seu carrinho!---------");
        if (carrinho.size() > 0) {
            for (Produto p : carrinho.keySet()) {
                System.out.println("Produto: " + p + "\nQunatidade: " + carrinho.get(p));
            }
        } else {
            System.out.println("Carrinho vazio!");
        }
        menu();
    }


    private static void finalizarCompra() {
        Double valorDaCompra = 0.0;
        System.out.println("Seus produtos!");

        for (Produto p : carrinho.keySet()) {
            int qtd = carrinho.get(p);
            valorDaCompra += p.getPreco() * qtd;
            System.out.println(p);
            System.out.println("Quantidade: " + qtd);
            System.out.println("-------------------");
        }
        System.out.println("O valor da sua compra e: " + Utils.doubleToString(valorDaCompra));
        carrinho.clear();
        System.out.println("Obrigado pela Preferencia");
        menu();
    }

}

