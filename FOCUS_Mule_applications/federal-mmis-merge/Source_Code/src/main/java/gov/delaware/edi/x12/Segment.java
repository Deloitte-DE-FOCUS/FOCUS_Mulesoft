package gov.delaware.edi.x12;

import java.util.regex.Pattern;

import org.slf4j.LoggerFactory;

import gov.delaware.edi.x12.HealthCareClaim837.Reader;
import gov.delaware.edi.x12.HealthCareClaim837.Writer;
import gov.delaware.edi.x12.annotation.Declaration;

public class Segment {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    private boolean parseError;
    protected int fieldSize;
    protected String name, content;
    public String delimiter = null;
    protected String[] elements;

    public Segment() {
    	this("");
        
    }
    public Segment(String c ) {
        parseError = false;
        content = c;
        delimiter = Reader.getElementTerminator();

        Class obj = this.getClass();
        if (obj.isAnnotationPresent(Declaration.class)) {
            Declaration declaration = (Declaration) obj.getAnnotation(Declaration.class);
            fieldSize = declaration.fieldSize();
            name = declaration.name();
        }


        if (c.length() > 0 && !c.startsWith(name)) {
            logger.error(String.format("Invalid segment constructor %s for message %s", name, c));
        }
        else parse();
    }

    private void parse() {
    	elements = new String[fieldSize+1];
    	elements[0] = name;
        for (int i = 1; i < elements.length; i++) elements[i] = "";

        if (content.length() > 0){

                String[] pCollection = content.split(Pattern.quote(delimiter));
                int min = Math.min(elements.length, pCollection.length);
                for (int i = 0; i < min; i++) {
                    try {
                    	elements[i] = pCollection[i];
                    }
                    catch (Exception ex) {
                    	elements[i] = "";
                        logger.error(ex.getMessage(), content);
                    }
                }
/* Warnings disabled for now*/
/*                if (pCollection.length >( elements.length)) {
                    parseError = true;
                    logger.info(name+" parse()");
                    logger.warn(name+" field length should be less than or equal to "+fieldSize, content);
                }*/
            }       
    }

    public int size() {
        return fieldSize;
    }
	public  String getDelimiter() {
		return delimiter;
	}
	public  void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}
    
	public void updateTransactionSegmentCount()
	{
		Writer.setTransactionSegmentCount(Writer.transactionSegmentCount + 1);
	}
}