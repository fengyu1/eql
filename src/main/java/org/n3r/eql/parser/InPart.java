package org.n3r.eql.parser;

import org.n3r.eql.map.EqlRun;
import org.n3r.eql.util.EqlUtils;

import java.util.Collection;
import java.util.Iterator;

public class InPart implements EqlPart {
    private final String inParamsContainer;

    public InPart(String inParamsContainer) {
        this.inParamsContainer = inParamsContainer;
    }

    @Override
    public String evalSql(EqlRun eqlRun) {
        Iterable<?> items = EqlUtils.evalCollection(inParamsContainer, eqlRun);
        if (items == null ) return "";
        Iterator<?> iterator = items.iterator();

        StringBuilder questions = new StringBuilder();
        for (int i  = 0; iterator.hasNext(); ++i, iterator.next()) {
            if (i > 0) questions.append(',');

            questions.append('#').append(inParamsContainer)
                    .append('[').append(i).append(']').append('#');
        }

        return questions.toString();
    }
}
