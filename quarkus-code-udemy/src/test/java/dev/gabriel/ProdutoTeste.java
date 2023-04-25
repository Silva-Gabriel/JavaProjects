package dev.gabriel;


import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

@QuarkusTest
@QuarkusTestResource(DatabaseLifecycle.class)
public class ProdutoTeste {

    @Test
    public void test1(){
        Assert.assertEquals(3, Produto.count());
    }

}
