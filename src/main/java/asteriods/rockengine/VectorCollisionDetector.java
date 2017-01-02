/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteriods.rockengine;

import asteriods.configuration.Properties;
import asteriods.configuration.PropertiesImpl;
import asteriods.elements.Element;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author rafael
 */
public class VectorCollisionDetector implements Detectable {

    private List<Element> elements;
    private Properties properties;
    private int width;
    private int height;

    public VectorCollisionDetector() {
        properties = new PropertiesImpl();
        this.width = properties.getWidth();
        this.height = properties.getHeight();
        elements = new ArrayList<>();
    }

    @Override
    public List<Set<Integer>> getCrashedElements() {
        List<Set<Integer>> indexesOfBoxes = getOverlappedBoxes();
        List<Set<Integer>> indexesOfCrashedElements = new ArrayList<>();
        
        for (int indexNumber = 0; indexNumber<indexesOfBoxes.size(); indexNumber++){
            Set<Integer> crashedElements = new HashSet<>();
            List<Integer> indexesArray = new ArrayList<>(indexesOfBoxes.get(indexNumber));
            for (int i = 0; i < indexesArray.size(); i++) {
                for (int j = 0; j < indexesArray.size(); j++) {
                    addCrashedElement(crashedElements, indexesArray, i, j);
                }
            }
            if (!crashedElements.isEmpty()){
                indexesOfCrashedElements.add(crashedElements);
            }
        }
        return indexesOfCrashedElements;
    }

    @Override
    public void addElement(Element e) {
        if (elements == null) {
            elements = new ArrayList<>();
        }

        if (e.getPoints().isEmpty()) {
            return;
        }
        elements.add(e);
    }

    @Override
    public void clearElements() {
        elements.clear();
    }

    public List<Set<Integer>> getOverlappedBoxes() {
        List<Set<Integer>> indexes = new ArrayList<>();
        List<Box> elementsBoxed = getBoxes();

        for (int i = 0; i < elementsBoxed.size(); i++) {
            for (int j = 0; j < elementsBoxed.size(); j++) {
                addBoxOverlapped(elementsBoxed, indexes, i, j);
            }
        }

        return indexes;
    }

    public boolean areElementsIntersected(Element e1, Element e2) {
        List<LineEq> firstElementLines = e1.toLines();
        List<LineEq> secondElementLines = e2.toLines();

        for (LineEq firstLine : firstElementLines) {
            if (isElementIntersected(firstLine, secondElementLines)) {
                return true;
            }
        }

        return false;
    }

    private void addCrashedElement(Set<Integer> indexes, List<Integer> boxIndexes, int i, int j) {
        if (i == j) {
            return;
        }

        int firstElementIndex = (int) boxIndexes.get(i);
        int secondElementIndex = (int) boxIndexes.get(j);
        Element firsElement = elements.get(firstElementIndex);
        Element secondElement = elements.get(secondElementIndex);

        if (this.areElementsIntersected(firsElement, secondElement)) {
            indexes.add(firstElementIndex);
            indexes.add(secondElementIndex);
        }
    }

    private void addBoxOverlapped(List<Box> boxes, List<Set<Integer>> indexes, int i, int j) {
        if (i == j) {
            return;
        }
        Box firstBox = boxes.get(i);
        Box secondBox = boxes.get(j);
        if (!firstBox.isBoxOverlapped(secondBox)) {
            return;
        }
        int indexesPosition = getPositionInIndexes(i, indexes);
        if (indexesPosition != -1) {
            indexes.get(indexesPosition).add(j);
        } else {
            Set<Integer> index = new HashSet<>();
            index.add(i);
            index.add(j);
            indexes.add(index);
        }
    }

    private int getPositionInIndexes(int number, List<Set<Integer>> indexes) {
        for (int i = 0; i < indexes.size(); i++) {
            Set<Integer> index = indexes.get(i);
            if (index.contains((Integer) number)) {
                return i;
            }
        }
        return -1;
    }

    private List<Box> getBoxes() {
        List<Box> elementsBoxed = new ArrayList<>();
        for (Element element : elements) {
            Box box = new Box(element.getMaxValues());
            elementsBoxed.add(box);
        }
        return elementsBoxed;
    }

    private boolean isElementIntersected(LineEq firstLine, List<LineEq> secondElementLines) {
        for (LineEq secondLine : secondElementLines) {
            if (firstLine.areLinesIntersected(secondLine)) {
                return true;
            }
        }
        return false;
    }
}
