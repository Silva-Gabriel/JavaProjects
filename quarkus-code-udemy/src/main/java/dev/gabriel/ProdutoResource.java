package dev.gabriel;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {
    @GET
    public List<Produto> buscarTodos(){
        return Produto.listAll();
    }

    @POST
    @Transactional
    public void buscarProdutos(CadastrarProdutoDTO produtoDTO){
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setValor(produtoDTO.getValor());
        produto.persist();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public void atualizarProdutos(@PathParam("id") Long id, CadastrarProdutoDTO produtoDTO) {
        Optional<Produto> produtoOp = Produto.findByIdOptional(id);
        if(produtoOp.isPresent()){
            Produto produto = produtoOp.get();
            produto.setNome(produtoDTO.getNome());
            produto.setValor(produtoDTO.getValor());
            produto.persist();
        }else{
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void deletarProduto(@PathParam("id") Long id){
        Optional<Produto> produtoOp = Produto.findByIdOptional(id);

        produtoOp.ifPresentOrElse(Produto::delete, () -> {throw new NotFoundException();});

    }

}
