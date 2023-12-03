package ra.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Product {
    private static final String[] VALID_PRODUCT_TYPES = {"C", "S", "A"};
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private static final String[] PRODUCT_STATUSES = {"Đang bán", "Hết hàng", "Không bán"};
    private static int maxProductId = 0;

    private String productId;
    private String productName;
    private float price;
    private String description;
    int day, month, year;
    private int catalogId;
    private int productStatus;
    private Date created;

    public Product() {
        this.productId = generateProductId();
    }

    private String generateProductId() {
        return String.format("%04d", ++maxProductId);
    }

    public Product(String productId, String productName, float price, String description, Date created, int catalogId, int productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.created = created;
        this.catalogId = catalogId;
        this.productStatus = productStatus;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {

        this.catalogId = catalogId;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public void inputData(Scanner scanner, Product[] arrProduct, int index, Categories[] arrCategories) {
        boolean validProductId = false;
        do {
            System.out.print("Nhập mã sản phẩm (4 ký tự, bắt đầu bằng C, S hoặc A): ");
            this.setProductId(scanner.next());

            // Kiểm tra xem mã sản phẩm có hợp lệ không
            if (isValidProductId(this.getProductId(), arrProduct, index)) {
                validProductId = true;
            } else {
                System.out.println("Mã sản phẩm không hợp lệ hoặc đã tồn tại. Vui lòng nhập lại.");
            }
        } while (!validProductId);
        this.setProductName(scanner.nextLine());

        // Kiểm tra tính hợp lệ của tên sản phẩm
        while (!isValidProductName(this.getProductName(), arrProduct, index)) {
            System.out.println("Nhập tên sản phẩm (từ 10-50 ký tự): ");
            this.setProductName(scanner.nextLine());
        }
        System.out.println("Nhập giá sản phẩm:");
        this.price = Float.parseFloat(scanner.nextLine());
        System.out.println("Nhập mô tả sản phẩm:");
        this.description = scanner.nextLine();
        System.out.println("Nhập ngày tạo sản phẩm (dd/MM/yyyy):");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.created = sdf.parse(scanner.nextLine());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        boolean validCatalogId = false;
        do {
            System.out.print("Chọn mã danh mục sản phẩm từ danh sách sau:\n");
            for (int i = 0; i < arrCategories.length; i++) {
                if(arrCategories[i] !=null){
                    arrCategories[i].displayData();
                }
            }
            System.out.print("Nhập mã danh mục sản phẩm: ");
            int categoryId = scanner.nextInt();

            // Kiểm tra xem mã danh mục có tồn tại trong danh sách không
            boolean categoryExists = false;
            for (Categories category : arrCategories) {
                if (category != null && category.getCatalogId() == categoryId) {
                    categoryExists = true;
                    break;
                }
            }

            if (categoryExists) {
                this.setCatalogId(categoryId);
                validCatalogId = true;
            } else {
                System.out.println("Mã danh mục không tồn tại. Vui lòng nhập lại.");
            }
        } while (!validCatalogId);

        System.out.println("Chọn trạng thái danh mục (0: Đang bán, 1: Hết hàng, 2: Không bán):");
        setProductStatus(scanner.nextInt());
    }

    public void displayData() {
        System.out.println("ID sản phẩm: " + productId);
        System.out.println("Tên sản phẩm: " + productName);
        System.out.println("Giá: " + price);
        System.out.println("Mô tả sản phẩm: " + description);
        System.out.println("Ngày tạo: " + DATE_FORMAT.format(created));
        System.out.println("ID danh mục: " + catalogId);
        System.out.println("Trạng thái sản phảm: " + PRODUCT_STATUSES[productStatus]);
    }

    private boolean isValidProductId(String productId, Product[] arrProduct, int index) {
        if (productId.length() == 4) {
            char categoryCode = productId.charAt(0);
            if (categoryCode == 'C' || categoryCode == 'S' || categoryCode == 'A') {
                for (int i = 0; i < index; i++) {
                    if (arrProduct[i] != null && arrProduct[i].getProductId().equals(productId)) {
                        return false; // Mã sản phẩm đã tồn tại
                    }
                }

                return true; // Mã sản phẩm hợp lệ và không trùng lặp
            }
        }else {
            System.out.println("Tên sản phẩm không hợp lệ hoặc đã tồn tại. Vui lòng nhập lại.");
        }

        return false;
    }

    private boolean isValidProductName(String productName, Product[] arrProduct, int index) {
        if (productName.length() >= 10 && productName.length() <= 50) {
            for (int i = 0; i < index; i++) {
                if (arrProduct[i] != null && arrProduct[i].getProductName().equals(productName)) {
                    return false; // Tên sản phẩm đã tồn tại
                }
            }
            return true; // Tên sản phẩm hợp lệ và không trùng lặp
        }
        return false; // Tên sản phẩm không hợp lệ
    }

}
