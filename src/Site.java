

class Site {

    /* Constantes associees au site */

    static final int STOCK_INIT = 5;
    static final int STOCK_MAX = 10;
    static final int BORNE_SUP = 8;
    static final int BORNE_INF = 2;
    private int stock_courant ;
    private int numeroSite;

    public Site(int numeroSite){
        this.stock_courant = STOCK_INIT;
        this.numeroSite = numeroSite;
    }

    public synchronized int getStock_courant(){
        return  stock_courant;
    }

    public int getNumeroSite(){
        return  numeroSite;
    }

    public synchronized void stockerVelo(int nbreVelo) {
        while(stock_courant+nbreVelo > STOCK_MAX){
            try { this.wait(); } catch (InterruptedException e) { e.printStackTrace();}
        }
        int ancienStockDuSite = stock_courant;
        this.stock_courant += nbreVelo;
        System.out.println("stokage sur le site numero"+numeroSite+". ancien stock: "+ancienStockDuSite+", nouveau stock: "+stock_courant);
        notifyAll();

    }

    public synchronized void destockerVelo(int nbreVelo)  {
        while (stock_courant-nbreVelo<0){
            try {  this.wait();   } catch (InterruptedException e) { e.printStackTrace(); }
        }
        int ancienStockDuSite = stock_courant;
        this.stock_courant -= nbreVelo;
        System.out.println("destokage sur le site numero"+numeroSite+". ancien stock: "+ancienStockDuSite+", nouveau stock: "+stock_courant);
        notifyAll();
    }

}
