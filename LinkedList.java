package Extensions;

/*
Kalyan Khatiwada
February 26, 2023
Professor Bender
CS231 A
Project 03

This class is used to create a linked list of agents for this project.
*/

import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Iterator;

public class LinkedList<T> implements Iterable<T>{

    private static class Node<T>{
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            next = null;
        }

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public T getData(){
            return data;
        }

        public void setNext(Node<T> newNext){
            next = newNext;
        }

        public Node<T> getNext(){
            return next;
        }
    }
    // The number of items in this linked list 
    private int size;

    //The first node in this list
    private Node<T> head;

    public LinkedList(){
        size = 0;
        head= null;
    }

    public LinkedList(String string, String string2, String string3, String string4, String string5, String string6,
            String string7, String string8) {
    }

    public void add(T item){
        Node<T> newNode = new Node<T>(item, head);
        size++;
        head = newNode;
    }

    public T get(int index){
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        Node<T> walker = head;
        for (int i = 0; i<index; i++){
            walker = walker.getNext();
        }
        return walker.getData();
    // walker is sitting at the given index after the end of the loop
    }

    public int size(){
        Node<T> walker = head;
        int size = 0;
        while (walker != null){
            walker = walker.getNext();
            size++;
        }
        return size;
    }

    public boolean isEmpty(){
        if (size == 0){
            return true;
        } else{
            return false;
        }
    }

    public void clear(){
        size =0;
        head = null;
    }

    public void add(int index, T item){
        if (index == 0){
            add(item);
            return;
        }
        Node<T> walker = head;
        for (int i = 0; i<index - 1; i++){
            walker = walker.getNext();
        }
        Node<T> newNode = new Node<T>(item, walker.getNext());
        walker.setNext(newNode);
    }

    public T remove(){
            if (size == 0) {
                throw new NoSuchElementException("List is empty");
            }
            Node<T> walker = head;
            head = walker.getNext();
            size--;
            return walker.getData();
    }

    public T remove(int index){
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        Node<T> walker = head;
        for (int i =0; i <index - 1; i++){
            walker = walker.getNext();
        }
        Node<T> removedItem = walker.getNext();
        walker.setNext(removedItem.getNext());
        size--;
        return removedItem.getData();
    }

    public boolean contains(Object o){
        Node<T> walker = head;
        while (walker !=null){
            if (o.equals(walker.getData())){
                return true;
            }
            walker = walker.getNext();
        }
        return false;
    }
    
    public boolean equals(Object o){
        if (!(o instanceof LinkedList)){
            return false;
        }
        LinkedList oAsALinkedList = (LinkedList) o;
        // Now I have a reference to something Java knows is a LinkedList!
        Node<T> walker1 = head;
        Node<T> walker2 = oAsALinkedList.head;
        while (walker1 != null && walker2 != null){
            if (!walker1.getData().equals(walker2.getData())){
                return false;
            }
            walker1 = walker1.getNext();
            walker2 = walker2.getNext();
        }
        return true;
    }  

    private class LLIterator implements Iterator<T>{
        private Node<T> current;
        public LLIterator(Node<T> head){
            current = head;  
        }

        public boolean hasNext(){
            return !(current == null);
        }

        public T next(){
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            T data= current.getData();
            current = current.getNext();
            return data;
        }

        public void remove(){
            return;
        }
    }
    public Iterator<T> iterator() {
            return new LLIterator( this.head );
    }
    public ArrayList<T> toArrayList(){
        ArrayList<T> arrayList = new ArrayList<T>(size);
        Node<T> current = head;
        while (current != null){
            arrayList.add(current.getData());
            current = current.getNext();
        }
        return arrayList;
    }

}