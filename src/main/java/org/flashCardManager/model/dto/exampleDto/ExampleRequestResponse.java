package org.flashCardManager.model.dto.exampleDto;

import org.flashCardManager.model.entity.VerbTense;

public class ExampleRequestResponse {

    private String id;
    private String text;
    private String targetToBeHidden;
    private VerbTense verbTense;

    private ExampleRequestResponse(Builder builder) {
        this(builder.id, builder.text, builder.targetToBeHidden, builder.verbTense);
    }

    private ExampleRequestResponse(String id, String text, String targetToBeHidden, VerbTense verbTense) {
        setId(id);
        setText(text);
        setTargetToBeHidden(targetToBeHidden);
        setVerbTense(verbTense);
    }

    //Getters
    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getTargetToBeHidden() {
        return targetToBeHidden;
    }

    public VerbTense getVerbTense() {
        return verbTense;
    }

    //Setters
    private void setId(String id) {
        this.id = id;
    }

    private void setText(String text) {
        this.text = text;
    }

    private void setTargetToBeHidden(String targetToBeHidden) {
        this.targetToBeHidden = targetToBeHidden;
    }

    private void setVerbTense(VerbTense verbTense) {
        this.verbTense = verbTense;
    }

    //Builder
    public static class Builder {
        private String id;
        private String text;
        private String targetToBeHidden;
        private VerbTense verbTense;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder targetToBeHidden(String targetToBeHidden) {
            this.targetToBeHidden = targetToBeHidden;
            return this;
        }

        public Builder verbTense(VerbTense verbTense) {
            this.verbTense = verbTense;
            return this;
        }

        public ExampleRequestResponse build() {
            return new ExampleRequestResponse(this);
        }
    }
}
