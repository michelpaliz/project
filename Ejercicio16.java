import java.util.Scanner;
import java.util.Random;
public class Ejercicio16 {
   public static Scanner lector = new Scanner(System.in);
   public static void main(String[] args) throws InterruptedException {
      final int DELAY = 500;     
      boolean plantado = false;
      int tiradaPlayer;
      int puntosPlayer = 0;
      int porrasPlayer = 0;
      int tiradaCPU;
      int puntosCPU = 0;
      int porrasCPU = 0;
      int opcion;
      char plantarse;
      final int MIN = 1;
      final int MAX = 6;

      do {
         opcion = mostrarMenu();
         if(opcion == 1) {
            porrasCPU = 0;
            porrasPlayer = 0;
            puntosCPU = 0;
            puntosPlayer = 0;
            do {      
               System.out.println("Jugador lanza el dado al aire ...");
               Thread.sleep(DELAY);
               tiradaPlayer = random(MIN, MAX);
               System.out.println("\n*****");               
               System.out.println("* " + tiradaPlayer + " *");               
               System.out.println("*****");               
               puntosPlayer += tiradaPlayer;               
               System.out.println("\nTu puntuación actual es " + puntosPlayer);
               if(puntosPlayer < 11) {
                  System.out.print("¿Deseas plantarte con " + puntosPlayer +" puntos? (s/n): ");
                  plantarse = lector.nextLine().charAt(0); 
                  plantado =  plantarse == 's' || plantarse == 'S';
                  if(plantado) {
                     plantado = false;
                     //Ahora la CPU intenta igualar o mejorar el resultado del jugador
                     while(puntosCPU < puntosPlayer) {
                        System.out.println("CPU lanza el dado al aire ...");
                        Thread.sleep(DELAY);                        
                        tiradaCPU = random(MIN, MAX);
                        System.out.println("\n?????");               
                        System.out.println("? " + tiradaCPU + " ?");               
                        System.out.println("?????\n");                        
                        puntosCPU += tiradaCPU;
                        System.out.println("La puntuación actual de la CPU es " +puntosCPU);
                     }
                     if(puntosCPU > 11) {
                        System.out.println("CPU se ha pasado. Porra para Player!!");
                        porrasPlayer++;
                        puntosCPU = 0;
                        puntosPlayer = 0;
                     } else if(puntosCPU > puntosPlayer) { // Gana CPU
                        if(puntosCPU == 11) {
                           doblePorra();
                           System.out.println("\nDoble porra para CPU\n");
                           porrasCPU += 2;
                        } else {
                           System.out.println("CPU ha mejorado tu puntuación. Porra para CPU!!");
                           porrasCPU++;   
                        }
                        puntosCPU = 0;
                        puntosPlayer = 0;
                     } else { // Empate
                        System.out.println("Empate!! Porra para los dos");
                        puntosCPU = 0;
                        puntosPlayer = 0;
                        porrasCPU++;
                        porrasPlayer++;                          
                     }
                     
                     System.out.println("\n**********************");
                     System.out.println("* " + porrasPlayer + " PLAYER  -  CPU " +porrasCPU + " *");
                     System.out.println("**********************");
                     System.out.print("\nPulsa intro para continuar...");
                     lector.nextLine();
                  }
               } else if (puntosPlayer == 11) {                  
                  //Al obtener 11 el jugador suma automáticamente 2 porras
                  porrasPlayer += 2;
                  puntosCPU = 0;
                  puntosPlayer = 0;
                  doblePorra();
                  System.out.println("\n¡Bonificación! Doble porra");
                  
                  System.out.println("\n**********************");
                  System.out.println("* " + porrasPlayer + " PLAYER  -  CPU " +porrasCPU + " *");
                  System.out.println("**********************");
                  System.out.print("\nPulsa intro para continuar...");
                  lector.nextLine();
               } else {
                  //El jugador se ha pasado
                  System.out.println("Ohh!! Te has pasado");
                  System.out.println("Porra para la CPU");                  
                  porrasCPU += 1;
                  puntosCPU = 0;
                  puntosPlayer = 0;
                  System.out.println("\n**********************");
                  System.out.println("* " + porrasPlayer + " PLAYER  -  CPU " +porrasCPU + " *");
                  System.out.println("**********************");
                  System.out.print("\nPulsa intro para continuar...");
                  lector.nextLine();
               }
               
            } while(porrasPlayer < 5 && porrasCPU < 5);
            if(porrasCPU >= 5) {
               System.out.println("\nCPU gana la partida!!! por " + porrasCPU + " porras a "+ porrasPlayer +"\n");
            } else if(porrasPlayer >= 5) {
               System.out.println("\nPlayer gana la partida!!! por " + porrasPlayer + " porras a " + porrasCPU +"\n");
            }
         }
      } while(opcion != 0);
      System.out.println("Hasta pronto!!");
   }

   public static int mostrarMenu(){

      System.out.println("***************************");
      System.out.println("*** JUEGO DE DADOS ONCE ***");
      System.out.println("***************************");
      System.out.println("1. Nueva partida ...");
      System.out.println("0. Salir");
      System.out.print("\nElige una opción: ");
      return Integer.parseInt(lector.nextLine());

   }

   public static void doblePorra(){
      System.out.println("\n$$$$$$$$$$$$$$$$");
      System.out.println("$$$$ ¡ONCE! $$$$");
      System.out.println("$$$$$$$$$$$$$$$$");
   }

   public static int random(int min, int max) {
      Random r = new Random();
      return r.nextInt(max - min + 1) + min;
  }



}