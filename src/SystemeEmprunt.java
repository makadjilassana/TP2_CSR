import java.util.Random;

class SystemeEmprunt {

	/* Constantes */

	static final int NB_SITES = 5;
	static final int MAX_CLIENTS = 10;

	/* Attributs */

	private Site[] sites = new Site[NB_SITES];
	private Client[] clients = new Client[MAX_CLIENTS];
	private Camion camion = null;

	private int nbClients = 0;

	/* Constructeur du systeme d'emprunt */
	SystemeEmprunt() {

		/* Instanciation des sites */
		for(int i = 0; i < NB_SITES; i++)
			sites[i] = new Site(i);

		Random r = new Random();
		/* Instanciation des clients */
		for(int i = 0; i < MAX_CLIENTS; i++) {
			int siteDepId = r.nextInt(NB_SITES);
			int siteArrId = r.nextInt(NB_SITES);
			clients[i] = new Client(sites[siteDepId], sites[siteArrId]);
		}

		/* Instanciation du camion */
		camion = new Camion(sites);

	}

	public void fonctionner() throws InterruptedException {
		camion.start();
		for (Client client : clients) {
			client.start();
		}

		camion.join();
		for (Client client : clients) {
			client.join();
		}

	}

	/*public void fonctionnerTest() throws InterruptedException {
		camion.start();

		int siteDepId1 =1;
		int siteArrId1= 2;


		sites[0]= new Site(siteDepId1);
		sites[1]= new Site(siteArrId1);

		Client client1= new Client(sites[0],sites[1]);
		Client client2= new Client(sites[0],sites[1]);

		clients[0]=client1;
		clients[1]=client2;

		clients[0].start();
		clients[1].start();

		camion.join();
		clients[0].join();
		clients[1].join();


	} */

	/* Point d'entree du programme */

	public static void main(String[] args) throws InterruptedException {
		 new SystemeEmprunt().fonctionner();
		//new SystemeEmprunt().fonctionnerTest();
	}

} // class SystemeEmprunt
