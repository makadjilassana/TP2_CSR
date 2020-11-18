import java.util.List;

public class Camion extends Thread {
    //stock du camion
    private int stock;
    private Site[] listSite ;

    public Camion( Site[] listSite){
       this.listSite = listSite;
    }
   private synchronized void equilibrer(Site site){
        int ancienStockDuCamion = stock;
        //si le site est en manque de stock
        if(site.getStock_courant() < site.BORNE_INF){
            //acompleter est la quantite manquante
            int aCompleter= site.STOCK_INIT-site.getStock_courant();
            //si le camion a suffisamant de stock pour stocker la quantite manquante sur le site
           if(aCompleter <= stock){
                 site.stockerVelo(aCompleter);
                 stock -=aCompleter;
           }
           else{
               site.stockerVelo(stock);
               stock =0;
           }

        }
        //si le site a un exces de stock
        else if(site.getStock_courant()>site.BORNE_SUP){

            //si la quantite a retirer du stock
            int aRetirer = site.getStock_courant() - site.STOCK_INIT ;
            site.destockerVelo(aRetirer);
            stock +=aRetirer;
        }
        System.out.println("le camion a visite le site numero"+site.getNumeroSite()+", Infos du Camion: ancienStock = "+ancienStockDuCamion+" , nouveauStock = "+stock);
    }

   private void seDeplacer(int duree) {
       try {  this.sleep(duree);  } catch (InterruptedException e) {e.printStackTrace(); }
   }

    public void run() {
        int duree  = 0;
        int dureeFinale=0;

        for(int i=0;i<listSite.length-1;i++) {
            equilibrer(listSite[i]);

                duree = Math.abs(listSite[i+1].getNumeroSite() - listSite[i].getNumeroSite()) * 100;
                seDeplacer(duree);

        }
        dureeFinale = Math.abs(listSite[listSite.length - 1].getNumeroSite() - listSite[0].getNumeroSite())*100;
        seDeplacer(dureeFinale);
    }

}
