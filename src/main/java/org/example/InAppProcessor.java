package org.example;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * No-op processor
 */
@SupportedAnnotationTypes("org.example.InApp")
@SupportedSourceVersion(javax.lang.model.SourceVersion.RELEASE_8)
public class InAppProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return false;
    }

}