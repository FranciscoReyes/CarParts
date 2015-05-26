/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LengthDocument;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Francisco A. Reyes
 */
public class MaxLengthDocument extends PlainDocument {
    
    private int maxLength;
    
    public MaxLengthDocument(int maxLength) {
        this.maxLength = maxLength;
    }

    public MaxLengthDocument() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        
        if (getLength() + str.length() <= maxLength) {
        super.insertString(offs, str, a); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
}
