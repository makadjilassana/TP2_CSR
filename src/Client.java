public class Client  extends  Thread  {
   private Site siteDepart ;
  private Site siteArrive ;

    public Client(Site siteDepart,Site siteArrive){
        this.siteDepart = siteDepart;
        this.siteArrive = siteArrive;

    }
    public void empruntVelo(){
        siteDepart.destockerVelo(1);
        System.out.println("Un client a emprunté un vélo sur le site:"+siteDepart.getNumeroSite());
    }
    public void restitueVelo() {
        siteArrive.stockerVelo(1);
        System.out.println("Un client a restitué un vélo sur le site:"+siteArrive.getNumeroSite());
    }
    public void seDeplacer() {
        int duree = (siteArrive.getNumeroSite()-siteDepart.getNumeroSite())*100 ;
        duree = Math.abs(duree);

        try {  this.sleep(duree); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    public  void run(){
        empruntVelo();

        seDeplacer();

        restitueVelo();
    }




}
