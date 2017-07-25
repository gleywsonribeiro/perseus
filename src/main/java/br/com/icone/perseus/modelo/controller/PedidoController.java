/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.icone.perseus.modelo.controller;

import br.com.icone.martan.modelo.Cliente;
import br.com.icone.martan.modelo.Pagamento;
import br.com.icone.martan.modelo.Endereco;
import br.com.icone.martan.modelo.TipoPagamento;
import br.com.icone.martan.modelo.ItemPedido;
import br.com.icone.martan.modelo.Pedido;
import br.com.icone.martan.modelo.Produto;
import br.com.icone.martan.modelo.StatusPedido;
import br.com.icone.martan.modelo.Usuario;
import br.com.icone.martan.modelo.repositorio.ClienteFacade;
import br.com.icone.martan.modelo.repositorio.PagamentoFacade;
import br.com.icone.martan.modelo.repositorio.PedidoFacade;
import br.com.icone.martan.modelo.repositorio.ProdutoFacade;
import br.com.icone.martan.modelo.repositorio.UsuarioFacade;
import br.com.icone.martan.util.jsf.JsfUtil;
//import br.com.icone.martan.util.mail.Mailer;
//import com.outjected.email.api.MailMessage;
//import com.outjected.email.impl.templating.velocity.VelocityTemplate;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import org.apache.velocity.tools.generic.NumberTool;

/**
 *
 * @author raque
 */
@Named(value = "pedidoController")
@SessionScoped
public class PedidoController implements Serializable {

    @Inject
    private PedidoFacade repositorio;
    @Inject
    private UsuarioFacade usuarioRepositoy;
    @Inject
    private ClienteFacade clienteRepository;
    @Inject
    private ProdutoFacade produtoRepository;
    @Inject
    private PagamentoFacade repositorioCR;

//    @Inject
//    private Mailer mailer; 
    private Pedido pedido;

//    private Produto produtoCorrente;
    private ItemPedido item;

    private boolean usarEnderecoCliente;

    private List<Usuario> vendedores;

    private List<Pedido> pedidos;

    public PedidoController() {
        novo();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Usuario> getVendedores() {
        if (vendedores == null) {
            vendedores = usuarioRepositoy.findAll();
        }
        return vendedores;
    }

    public void novo() {
        this.pedido = new Pedido();
        this.pedido.setEnderecoEntrega(new Endereco());
        this.item = new ItemPedido();
        this.usarEnderecoCliente = false;
    }

    public void salvar() {
        if (pedido.getItens().isEmpty()) {
            JsfUtil.addWarnMessage("Adicione pelo menos um item");
        } else if (pedido.isNovo()) {
            repositorio.create(pedido);
            this.pedidos = null;
            JsfUtil.addMessage("Pedido salvo com sucesso!");
        } else {
            repositorio.edit(pedido);
            JsfUtil.addMessage("Pedido alterado com sucesso com sucesso!");
        }
    }

//    public void enviarPorEmail() {
//        MailMessage message = mailer.novaMensagem();
//        message.to(this.pedido.getCliente().getContato().getEmail())
//                .subject("Pedido " + pedido.getId())
//                .bodyHtml(new VelocityTemplate(getClass().getResourceAsStream("/emails/pedido.template")))
//                .put("pedido", this.pedido)
//                .put("numberTool", new NumberTool())
//                .put("locale", new Locale("pt", "BR"))
//                .send();
//        JsfUtil.addMessage("Email enviado com sucesso!");
//    }
    public List<Pedido> getPedidos() {
        if (pedidos == null) {
            this.pedidos = repositorio.getPedidos();
        }
        return pedidos;
    }

    public TipoPagamento[] getFormasPagamento() {
        return TipoPagamento.values();
    }

    public List<Cliente> completarClientes(String nome) {
        return clienteRepository.getClientesPorNome(nome);
    }

    public boolean isUsarEnderecoCliente() {
        return usarEnderecoCliente;
    }

    public void setUsarEnderecoCliente(boolean usarEnderecoCliente) {
        this.usarEnderecoCliente = usarEnderecoCliente;
    }

    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

//    public Produto getProdutoCorrente() {
//        return produtoCorrente;
//    }
//
//    public void setProdutoCorrente(Produto produtoCorrente) {
//        this.produtoCorrente = produtoCorrente;
//    }
    public ItemPedido getItem() {
        return item;
    }

    public void setItem(ItemPedido item) {
        this.item = item;
    }

    public void adicionar() {
        if (pedido.isJaExiste(item)) {
            //Já adiciona logo pra não ter que percorrer a lista de novo para ajustar a quantidade
        } else {
            this.item.setPedido(pedido);
            this.pedido.getItens().add(item);
        }

        item = new ItemPedido();
        this.pedido.recalcularValorTotal();
    }

    public void recalcularPedido() {
        this.pedido.recalcularValorTotal();
    }

    //Vai ser usado para selecionar o endereço do cliente e usar na entrega
    public void ajustarEndereco() {
        this.pedido.setEnderecoEntrega(new Endereco());
    }

    public void setMesmoEnderecoCliente() {
        pedido.setEnderecoEntrega(pedido.getCliente().getEndereco());
    }

    public void removerItem() {
        pedido.getItens().remove(item);
        this.item = new ItemPedido();
        this.pedido.recalcularValorTotal();
    }

    public List<Produto> buscaProdutoDescricao(String descricao) {
        return produtoRepository.getProdutosPorDescricao(descricao);
    }

    public List<Produto> buscaProdutoCodigo(String cod) {
        return produtoRepository.getProdutosPorCodigo(cod);
    }

    public boolean isTemItem() {
        return this.item.getProduto() != null;
    }

    public boolean isNaoTemItem() {
        return !isTemItem();
    }

    public boolean isAdicionarAtivo() {
        return isNaoTemItem() || pedido.isEmitido();
    }

    public void emitir() {
        if (pedido.getItens().isEmpty()) {
            JsfUtil.addWarnMessage("Adicione pelo menos um item");
        } else {
            boolean possuiTodosItens = true;

            for (ItemPedido itemDoCarrinho : pedido.getItens()) {
                if (itemDoCarrinho.isEstoqueInsuficiente()) {
                    possuiTodosItens = false;
                    break;
                }
            }

            if (possuiTodosItens) {

                for (ItemPedido itemDoCarrinho : pedido.getItens()) {
//                itemDoCarrinho.getProduto().baixar(itemDoCarrinho.getQuantidade());
                    Produto produto = itemDoCarrinho.getProduto();
                    produto.baixar(itemDoCarrinho.getQuantidade());

                    produtoRepository.edit(produto);
                }

                this.pedido.setStatus(StatusPedido.EMITIDO);
                if (pedido.getId() == null) {
                    repositorio.create(pedido);
                } else {
                    repositorio.edit(pedido);
                }
                
                Pagamento conta = new Pagamento();
                conta.setPedido(pedido);
                conta.setValor(pedido.getValorTotal());
                conta.setDesconto(pedido.getValorDesconto());
                
                repositorioCR.create(conta);
                
                JsfUtil.addMessage("Pedido emitido com sucesso!");

            } else {
                JsfUtil.addWarnMessage("Infelizmente não há estoque para todos os itens!");
            }
        }
    }
}
