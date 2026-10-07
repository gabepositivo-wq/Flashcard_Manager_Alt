package org.flashCardManager.view;

import org.flashCardManager.controller.common.ErrorType;

public class ErrorDetailsView {

    private ErrorDetailsView() {}

    public static void show(ErrorType errorType, String errorMessage) {
        switch (errorType) {
            case VALIDATION -> System.out.println("\n⚠️ Dado invalido: " + errorMessage);
            case NOT_FOUND -> System.out.println("\n⚠️ Dado nao encontrado: " + errorMessage);
            case PERSISTENCE -> System.out.println("\n⚠️ Erro na persistencia de dados: " + errorMessage);
            case AUTHENTICATION -> System.out.println("\n⚠️ Erro na autenticacao: " + errorMessage);
            case UNEXPECTED -> System.out.println("\n⚠️ " + errorMessage);
        }
    }
}
