package expectations.xml;

import expectations.string.failures.MalformedXmlFailure;
import expectations.xml.failures.NoXmlElementFailure;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.filter.ElementFilter;
import org.jdom.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class XMLExpectation {
    private Element xml;
    public XMLExpectation(InputStream xml) throws MalformedXmlFailure {
        try {
            SAXBuilder builder = new SAXBuilder();
            this.xml = builder.build(xml).getRootElement();
        } catch (JDOMException e) {
            throw new MalformedXmlFailure();
        } catch (IOException ignored) {}
    }

    public FieldsExpectation withField(String fieldName) throws NoXmlElementFailure {
        ElementFilter filter = new ElementFilter(fieldName);
        Iterator elementIterator = xml.getDescendants(filter);
        if(!elementIterator.hasNext()){
            throw new NoXmlElementFailure(fieldName);
        }
        return new FieldsExpectation(fieldName,elementIterator);
    }
}