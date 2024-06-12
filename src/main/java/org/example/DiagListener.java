package org.example;

import com.sun.tools.javac.api.ClientCodeWrapper;
import com.sun.tools.javac.api.DiagnosticFormatter;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.RichDiagnosticFormatter;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaFileObject;

@ClientCodeWrapper.Trusted
public class DiagListener implements DiagnosticListener<JavaFileObject> {
    private final Context context;

    public DiagListener(Context context) {
        this.context = context;
    }

    @Override
    public void report(Diagnostic<? extends JavaFileObject> diagnostic) {
        // Should use `BasicDiagnosticFormatter`, which works in all tested cases
        String basicDiagnoticMessage = diagnostic.toString();

        try {
            // To my knowledge, this will always return `RichDiagnosticFormatter` instance
            DiagnosticFormatter<JCDiagnostic> richDiagnosticFormatter = Log.instance(context).getDiagnosticFormatter();
            // Will never reach this line, as `Log#instance` will throw an exception
            String richDiagnosticMessage = richDiagnosticFormatter.format((JCDiagnostic) diagnostic, null);
        } catch (Exception ex) {
            // BUG: This branch will happen when annotation processors are used
            // If you debug into the line below, and observe `this.context`, all values are null
            // This will trigger the check in `Context#checkState`, resulting in this exception
            System.out.println("Failed to rich format diagnostic message");
        }
    }
}
