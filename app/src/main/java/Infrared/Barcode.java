package Infrared;

/**
 * Goods类为实体类，做测试使用
 * @author Administrator
 *
 */
public class Barcode {

    private int barcodeid;
    private String name;	  //商品名
    private String barcode;   //条码
    private int count;		  //数量

    public void setBarcodeid(int barcodeid) {
        this.barcodeid = barcodeid;
    }

    public Barcode(String name, String barcode, int count) {
        super();
        this.name = name;
        this.barcode = barcode;
        this.count = count;
    }

    public Barcode() {
        super();
    }

    public int getBarcodeid() {
        return barcodeid;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "商品名：" + this.name + ",条码： " + this.barcode;
    }
}
