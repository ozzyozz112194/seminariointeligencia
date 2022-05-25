package examples.behaviours;

import java.security.interfaces.EdECKey;
import java.time.Year;
import java.util.Scanner;
import java.util.Vector;

//package examples.behaviours;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class OneShotAgent extends Agent {

  protected void setup() {
    System.out.println("Agent "+getLocalName()+" started.");
    addBehaviour(new MyOneShotBehaviour());

    
  } 

  private class MyOneShotBehaviour extends OneShotBehaviour {
    
    class benneton {
      double year = 9;
      double [] xadver = {23, 26, 30, 34,  43,  48,  52,  57,  58};
      double [] ysales = {651,762,856,1063,1190,1298,1421,1440,1518};
      double b0 = 0 ;
      double b1 = 0 ;


      void calculate () {
    
      double multi     =  0 ;
      double msale     =  0 ;
      double madvert   =  0 ; 
      double multixy   =  0 ;
      double advertsq2 =  0 ;
      double sale      =  ysales[0];
      double advert    =  xadver[0];
      double advertsq  =  xadver[0] * xadver[0] ; 
      
      for (int i = 0 ; i < year; i++){
        multi = ysales[i] * xadver[i] ;
        multixy = multi+multixy;
      }

      for ( int i = 0 ; i < year; i++){
      
        if (i >= year - 1){
          break;
        }
  
        advert = advert + xadver[i+1];
        advertsq =  xadver[i+1] *  xadver[i+1] + advertsq;
        sale = sale +  ysales[i+1];
      }
      
      advertsq2 = advert * advert ; 
    
      double result1 = year * multixy - advert * sale;
      double result2 = year * advertsq - advertsq2;
      b1  = result1/result2;
      msale = sale/year;
      madvert = advert/year;
      b0 = msale - b1 * madvert;

    /*
    System.out.println("E(xy) = " + multixy);
    System.out.println("E(x) = " + advert);
    System.out.println("E(y) = " + sale);
    System.out.println("E(x^2) = " + advertsq);
    System.out.println("E(x)^2 = " + advertsq2);
    System.out.println("R1 = " +result1);
    System.out.println("R2 = " + result2);
    System.out.println("msale = " + msale);
    System.out.println("madver = " + madvert);
    */
    System.out.println("B0 = " + b0);
    System.out.println("B1 = " + b1);
    
    }

    void userInput (){
        
        
        year = year+1;
        
        Scanner myAdv = new Scanner(System.in);
        System.out.println("Enter advertisment amount");
        double amount = myAdv.nextDouble();
        double sales = b0+b1*amount+0.1;
        

        System.out.println(sales);
      }
  }

    public void action() {
       
      benneton bn = new benneton();

      bn.calculate();
      bn.userInput();
      
    } 
    
    public int onEnd() {
      myAgent.doDelete();   
      return super.onEnd();
    } 
  }    // END of inner class ...Behaviour