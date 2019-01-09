package android.sisgo.model;

public class InsertStockItem {
    private String barcode;
    private String addStock;

    public InsertStockItem(String barcode, String addStock) {
        this.barcode = barcode;
        this.addStock = addStock;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getAddStock() {
        return addStock;
    }
}
