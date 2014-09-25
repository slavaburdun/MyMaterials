package controllers.chainofresponsibility;

import controllers.chainofresponsibility.interfaces.ChainElement;
import java.util.LinkedList;

/**
 * Chain of responsibility itself.Just a list of ChainElement childs.
 *
 * @author kelebra
 * @see controllers.chainelements.interfaces.ChainElement
 */
public class ChainOfResponsibility {

    LinkedList<ChainElement> chain;

    public ChainOfResponsibility() {
        chain = new LinkedList<ChainElement>();
    }

    public void addElement(ChainElement element) {
        chain.add(element);
    }

    public LinkedList<ChainElement> getChain() {
        return chain;
    }
}
