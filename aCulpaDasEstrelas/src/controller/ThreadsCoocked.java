package controller;
import java.util.concurrent.Semaphore;
public class ThreadsCoocked extends Thread{
    private int prato;
    private double tempoDeCozimento;
    private Semaphore semaphore;
    private Semaphore jogador;
    public ThreadsCoocked(Semaphore semaphore, Semaphore semaphore1){
        this.prato = (int) getId();
        this.tempoDeCozimento = 0;
        this.semaphore= semaphore;
        this.jogador = semaphore1;
    }
    public void run() {
        if(validaPrato()){
            sopa();
        } else{
            lasanha();
        }
    }
    private void sopa() {
        double tempoDePreparo =((Math.random()*(0.8-0.5)+0.5));
        do{
            try {
                System.out.println("Sopa de cebola:"+prato+" está em "+percentual(tempoDePreparo)+"%");
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            tempoDeCozimento +=0.1;
        }while (tempoDeCozimento<tempoDePreparo);
        pratoPronto();
    }
    private void lasanha() {
        double tempoDePreparo =((Math.random()*(1.2-0.6)+0.6));
        do{
            try {
                System.out.println("Lasanha a bolonhesa:"+prato+" está em "+percentual(tempoDePreparo)+"%");
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            tempoDeCozimento +=0.1;
        }while (tempoDeCozimento<tempoDePreparo);
        pratoPronto();
    }
    private void pratoPronto() {
        try {
            semaphore.acquire();
            if(validaPrato()){
                System.out.println("Sopa de cebola: "+prato+" está pronto!!");
            }else {
                System.out.println("Lasanha bolonhesa: "+prato+" está pronta!!");
            }
            entregarPrato();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            semaphore.release();
        }
    }
    private void entregarPrato() {
        try{
            jogador.acquire();
            if(validaPrato()){
                System.out.println("Sopa de cebola: "+prato+" está sendo entregue!!");
            }else {
                System.out.println("Lasanha bolonhesa: "+prato+" está sendo entregue!!");
            }
            sleep(500);
        }catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(prato+"# Foi entregue!!");
            jogador.release();
        }
    }
    private boolean validaPrato() { return (prato %2 == 0);}
    private int percentual(double tempoDePreparo){
        return (int) ((tempoDeCozimento*100)/tempoDePreparo);
    }
}
