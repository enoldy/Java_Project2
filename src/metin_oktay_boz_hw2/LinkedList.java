package metin_oktay_boz_hw2;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MONSTER
 */
public class LinkedList extends DLNode implements HW2Interface{
    
    private DLNode first; //First element
    
    public void LinkedList(){
    first = null;
}

    @Override
    public void Insert(int newElement, int pos) throws Exception {           
  
        if (pos < 0){           
            throw LinkedListException();
        }
        DLNode dummy;
        dummy = first;
        if (pos == 0) // Add to the first location
        {
            DLNode NewNode = new DLNode();
            NewNode.Element = newElement;
            NewNode.right = first;
            first = NewNode;
            if(NewNode.right != null)
            NewNode.right.left = NewNode;
        }
        else
        {
            for (int i = 0; i < pos-1; i++) //Control not exist positions
            {
                  dummy = dummy.right;
                  if (pos > 0 && dummy == null)
                      throw LinkedListException();
            }
            if(dummy.right == null){ //add to last location
            DLNode NewNode = new DLNode();
            NewNode.Element = newElement;
            NewNode.right = null;
            NewNode.left = dummy;
            dummy.right = NewNode;
            
            }
            else{   //add to between last and first locations
            DLNode NewNode = new DLNode();
            NewNode.Element = newElement;
            NewNode.right = dummy.right;
            NewNode.left = dummy;
            dummy.right = NewNode;
            NewNode.right.left = NewNode;
            }
            
        }
        
    }

    @Override
    public int Delete(int pos) throws Exception {
       int DeletedNodeElement;
        if(pos<1){  //Exception for not exist locations
            throw LinkedListException();
        }
        DLNode Dummy; 
        if (pos == 1 && first != null){    // The First Element
            DeletedNodeElement = first.Element;
            first = first.right;
            first.left = null;
        }
        else {
        Dummy = first;
        for (int i = 0; i < pos-2; i++)
        {
              Dummy = Dummy.right;              
        }
        if(Dummy.right.right != null){  // between last and first elements
        DeletedNodeElement = Dummy.right.Element;
        Dummy.right = Dummy.right.right;
        Dummy.right.left = Dummy;
        }
        else{      // Last element
        DeletedNodeElement = Dummy.right.Element;
        Dummy.right = null;
        }
        }
        return DeletedNodeElement;
    }

    @Override
    public void ReverseLink() {
        DLNode Dummy;
        int[] elements = new int[1000];
        Dummy = first;
        int i = 0;
       while (Dummy != null){          // Storing elements in an array
            elements[i] = Dummy.Element;
            i++;
            Dummy = Dummy.right;
            if(Dummy == null){
                break;
            }
        }
       i--;
       Dummy = first;
       while(Dummy!=null){           //Assigning reverse of elements to linked list
           Dummy.Element = elements[i];
           Dummy=Dummy.right;
           i--;
           if(Dummy==null){
               break;
           }
       }
     
    }

    @Override
    public void SquashL() {
        DLNode dummy;
        dummy=first;
        int pos = 1;
        int repeat = 1;
        while(dummy != null){
            if(dummy.right!=null){         //checking last element
            if(dummy.Element == dummy.right.Element){   //checking repeated elements
              repeat++;       //control repeat after processing 
              pos++;         //control position after processing 
              dummy=dummy.right;            
        } else{                           // insertion and deletion elements
                if(repeat>1){            // checking repeat value
                dummy=dummy.right;
                try {
                    Insert(repeat,pos);
                    for(int i=0; i<repeat-1; i++){
                        Delete(pos-i);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(LinkedList.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(repeat == 2){
                    pos++;            //control position after processing 
                }
                if(repeat>2){
                    pos= pos-(repeat-3);              //control position after processing 
                }
                repeat = 1;           //control repeat after processing
               }
                else{     // Repeat element is 1
                    dummy=dummy.right;
                    try {                    
                        Insert(1,pos);
                    } catch (Exception ex) {
                        Logger.getLogger(LinkedList.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    pos+=2;          //control position after processing 
                }
            }
        } 
            else{      //Checking last node's element
                if(repeat>1){
                dummy=dummy.right;
                try {
                    Insert(repeat,pos);
                    for(int i=0; i<repeat-1; i++){
                        Delete(pos-i);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(LinkedList.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(repeat == 2){
                    pos++;                      //control position after processing 
                }
                if(repeat>2){
                    pos= pos-(repeat-3);        //control position after processing 
                }
                repeat = 1;             //control repeat after processing
               }
                else{
                    dummy=dummy.right;
                    try {                    
                        Insert(1,pos);
                    } catch (Exception ex) {
                        Logger.getLogger(LinkedList.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    pos+=2;          //control position after processing 
                }
                
                break;
            }
        }
        
    }

    @Override
    public void OplashL() {
        DLNode dummy;
        dummy=first;
        int pos = 1;
        while(dummy!=null){            
            if(dummy.right.Element==1){    //repeat value is 1
                try {
                    Delete(pos+1);
                } catch (Exception ex) {
                    Logger.getLogger(LinkedList.class.getName()).log(Level.SEVERE, null, ex);
                }
                pos++;            //control position after processing 
                dummy=dummy.right;
            }
             else{                      //repeat value is different from 1
                int del = dummy.right.Element;
                int ins = dummy.Element;
                dummy=dummy.right.right;
                try {
                    for(int i=0; i<del-1; i++){
                    Insert(ins,pos);
                    }
                    Delete(pos+del);
                            } catch (Exception ex) {
                    Logger.getLogger(LinkedList.class.getName()).log(Level.SEVERE, null, ex);
                }
                pos+=del;  //control position after processing             
        }           
            
        }
    }

    @Override
    public void Output() {
        DLNode Dummy;
        Dummy = first;
        System.out.print("The Elements in the list are : ");
        while (Dummy != null){         //printing loop
            System.out.print(Dummy.Element + " " );
            Dummy = Dummy.right;
        }
        System.out.println("");
        
    }

    @Override
    public void ROutput() {
        
        DLNode Dummy;
        Dummy = first;
       while (Dummy != null){      //to reach last node
            Dummy = Dummy.right;
            if(Dummy.right == null){
                break;
            }
        }
        System.out.print("The Reverse Elements in the list are : ");
        while (Dummy != null){         //printing loop
            System.out.print(Dummy.Element + " " );
            Dummy = Dummy.left;
        }
        System.out.println("");
    }

    @Override
    public Exception LinkedListException() {            //exception method
        throw new HW2Exception("Not supported yet."); 
    }
    
     @Override
    public String toString() {             
        DLNode dummy;
        dummy=first;
        String elements ="";
        while(dummy!=null){              //assigning elements as string values
            elements = (elements + dummy.Element + " ");
            dummy=dummy.right;            
        }
        
       return elements;  
    } 
    
   
       
}


