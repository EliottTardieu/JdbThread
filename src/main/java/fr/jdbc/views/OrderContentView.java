package fr.jdbc.views;

public class OrderContentView {
    public OrderContentView() {

    }

    /**
     * Stocke le contenu d'une commande dans une liste, qui est elle-même mise
     * dans la liste concernant le contenu de toutes les commandes.
     *
     * @param content La liste avec le contenu de toutes les commandes que l'on met à jour à chaque appel.
     * @return La liste du contenu de toutes les commandes mise à jour.
     */
    /*
    public ArrayList<ArrayList<Object>> displayContent(ArrayList<ArrayList<Object>> content) {
        for (Product product : this.products) {
            ArrayList<Object> toAdd = new ArrayList<>();
            toAdd.add(this.getId());
            toAdd.add(product.getName());
            toAdd.add(this.getQuantityProduct().get(this.products.indexOf(product)));
            toAdd.add(product.getUnitPrice());
            content.add(toAdd);
        }
        return content;
    }

     */
}
