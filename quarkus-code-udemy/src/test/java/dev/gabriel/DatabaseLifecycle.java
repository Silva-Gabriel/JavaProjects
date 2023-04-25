package dev.gabriel;

import io.quarkus.test.common.QuarkusTestResourceConfigurableLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

public class DatabaseLifecycle implements QuarkusTestResourceConfigurableLifecycleManager {
    private static PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres");
    @Override
    public Map<String, String> start() {
        POSTGRES.start();
        Map<String, String> propriedades = new HashMap<>();
        propriedades.put("quarkus.datasource.db-kind", POSTGRES.getJdbcUrl());
        propriedades.put("quarkus.datasource.username", "postgres");
        propriedades.put("quarkus.datasource.password", "123");
        return propriedades;
    }

    @Override
    public void stop() {
        if(POSTGRES != null){
            POSTGRES.stop();
        }
    }

    @Override
    public void setContext(Context context) {
        QuarkusTestResourceConfigurableLifecycleManager.super.setContext(context);
    }

    @Override
    public void init(Map<String, String> initArgs) {
        QuarkusTestResourceConfigurableLifecycleManager.super.init(initArgs);
    }

    @Override
    public void inject(Object testInstance) {
        QuarkusTestResourceConfigurableLifecycleManager.super.inject(testInstance);
    }

    @Override
    public void inject(TestInjector testInjector) {
        QuarkusTestResourceConfigurableLifecycleManager.super.inject(testInjector);
    }

    @Override
    public int order() {
        return QuarkusTestResourceConfigurableLifecycleManager.super.order();
    }

    @Override
    public void init(Annotation annotation) {
        QuarkusTestResourceConfigurableLifecycleManager.super.init(annotation);
    }
}
