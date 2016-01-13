JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $*.java

CLASSES = \
        Node.java \
        AVLNode.java \
        MySet.java \
        MyLinkedList.java \
        PageEntry.java \
        PageIndex.java \
        Position.java \
        InvertedPageIndex.java \
        MyException.java \
        WordEntry.java \
        MyHashTable.java \
        SearchEngine.java \
        SearchResult.java \
        MySort.java \
        AVLTree.java \
        checker.java 

default: classes
classes: $(CLASSES:.java=.class)
clean:
	rm -f *.class