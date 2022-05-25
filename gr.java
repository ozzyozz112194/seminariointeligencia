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
    
    class RegresionLinealGradiente {
    private double [] x = {23,26,30,34,43,48,52,57,58};
		private double [] y = {651,762,856,1063,1190,1298,1421,1440,1518};
		
		private double B0 =0;
		private double B1 =0;
		private double alfha =0.0005;
		private double presicion =0.001;
		private double error =0.0;
		
	
		
		
		public void setX(double[] x)
		{
			this.x = x;
			calculate();	
		} 
		
		public void setY(double[] y)
		{
			this.y = y;
			calculate();	
		}
		
		public double getB0()
		{
			return B0;
		}		

		public double getB1()
		{
			return B1;
		}	
		
		public double calculateError()
		{
			double E = 0;
			for(int i=0; i<x.length; i++)
			{
				E = E + (y[i] - (B0 + (B1 * x[i])));
				error=E;
			}
			return (E*E) * 1.0/9;
		}
		
		public double calculateB0()
		{
			double DB0 = 0;
			for(int i=0; i<x.length; i++)
			{
				DB0 += (y[i] - (B0 + (B1 * x[i])));
			}
			return (-2.0/9) * DB0;
		}
		
		public double calculateB1()
		{
			double DB1 = 0;
			for(int i=0; i<x.length; i++)
			{
				DB1 += x[i] * (y[i] - (B0 + (B1 * x[i])));
			}
			return (-2.0/9) * DB1;
		}
		
		public void calculate()
		{
			int count = 0;
			while(calculateError() > presicion)
			{
				B0 -= (calculateB0() * alfha);
				B1 -= (calculateB1() * alfha);
				
				System.out.println("Error: "+error);
				
				count++;
			}
			System.out.println("Beta 0: "+B0);
			System.out.println("Beta 1: "+B1);
			System.out.println("Numero de Iteraciones: "+count);
		}
  }

    public void action() {
       
      
      RegresionLinealGradiente rl = new RegresionLinealGradiente();

      rl.calculate();
      
    } 
    
    public int onEnd() {
      myAgent.doDelete();   
      return super.onEnd();
    } 
  }    // END of inner class ...Behaviour
}
