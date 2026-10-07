package org.flashCardManager.view;

import org.flashCardManager.model.dto.meaningDto.MeaningRequestResponse;
import org.flashCardManager.model.entity.Context;

import java.util.List;

public class MeaningView {

    public static void mostrarAcoesMeanings() {
        System.out.println("\n\n+ ------------------------------ +");
        System.out.println("| Acoes meanings                 |");
        System.out.println("----------------------------------");
        System.out.println("| [1] - Criar Meaning            |");
        System.out.println("| [2] - Ver meanings             |");
        System.out.println("| [3] - Acessar um meaning       |");
        System.out.println("| [0] - Voltar                   |");
        System.out.println("+ ------------------------------ +");
    }

    public static void mostrarMeaning(MeaningRequestResponse meaningRequestResponse) {
        System.out.println("\n\n+ ------------------------------ +");
        System.out.println("| Meaning                        |");
        System.out.println("----------------------------------");
        System.out.println(" --> Definition: " + meaningRequestResponse.definition());

        List<String> contextString = meaningRequestResponse.contexts()
                        .stream()
                        .map(Context::toShow)
                        .toList();

        System.out.println(" --> Contexts: " + contextString);
    }

    public static void mostrarAcoesMeaning() {
        System.out.println("\n+ ------------------------------ +");
        System.out.println("| Acoes do Meaning               |");
        System.out.println("----------------------------------");
        System.out.println("| [1] - Acessar examples         |");
        System.out.println("| [0] - Voltar                   |");
        System.out.println("+ ------------------------------ +");
    }

    public static void mostrarMeaningsPorCard(List<MeaningRequestResponse> meaningRequestResponses) {
        String tituloMenu = "Meanings";

        List<String> meaningOptions = meaningRequestResponses.stream()
                .map(meaningRequestResponse -> " [" + meaningRequestResponse.id() + "] - " + meaningRequestResponse.definition().substring(0, 20) + "...")
                .toList();

        int maiorLinhaDefinitionMeaning = tituloMenu.length();

        for(var meaningOption : meaningOptions) {
            maiorLinhaDefinitionMeaning = Math.max(maiorLinhaDefinitionMeaning, meaningOption.length());
        }

        final int maiorLinhaOpMeaning = maiorLinhaDefinitionMeaning;
        int marginLado = 6;

        String linaMenu = "+ " + "-".repeat((maiorLinhaDefinitionMeaning + marginLado) - 3) + " +";

        int paddingTitulo = ((maiorLinhaDefinitionMeaning - tituloMenu.length()) + marginLado) - 2;

        String tituloMenuFormado = "| " + tituloMenu + " ".repeat(paddingTitulo) + "|";

        System.out.println("\n\n" + linaMenu);
        System.out.println(tituloMenuFormado);
        System.out.println(linaMenu);
        meaningOptions.stream()
                .forEach(meaningOp ->
                        System.out.println(
                                "|" +
                                        meaningOp +
                                        " ".repeat((maiorLinhaOpMeaning - meaningOp.length()) + (marginLado - 1)) +
                                        "|"
                        )
                );

        System.out.println(linaMenu);
    }
}
