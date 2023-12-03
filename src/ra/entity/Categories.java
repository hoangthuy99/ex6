package ra.entity;

import java.util.Objects;
import java.util.Scanner;

public class Categories {

    private int catalogId;
    private String catalogName, descriptions;
    private String catalogStatus;
    private static int lastCategoryId = 0;

    public Categories() {
        this.catalogId = lastCategoryId++;
    }

    public Categories(int catalogId, String catalogName, String descriptions, String catalogStatus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.descriptions = descriptions;
        this.catalogStatus = catalogStatus;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String isCatalogStatus() {
        return catalogStatus;
    }

    public boolean setCatalogStatus(String catalogStatus) {
        if (Objects.equals(catalogStatus, "true") || Objects.equals(catalogStatus, "false")) {
            this.catalogStatus = catalogStatus;
            return true;
        } else {
            System.out.println("Không hoạt động");
        }

        return false;
    }

    public void inputData(Scanner scanner, Categories[] arrCategories, int index) {


        boolean validCatalogName = false;
        do {
            System.out.print("Nhập tên danh mục (độ dài tối đa 50 ký tự): ");
            String name = scanner.nextLine();

            if (isValidCatalogName(name, arrCategories, index)) {
                this.setCatalogName(name);
                validCatalogName = true;
            } else {
                System.out.println("Tên danh mục không hợp lệ hoặc đã tồn tại. Vui lòng nhập lại.");
            }
        } while (!validCatalogName);
        System.out.println("Nhập mô tả danh mục ");
        this.descriptions = scanner.nextLine();
        System.out.println("Nhập trạng thái danh mục (true/false):");
        while (true) {
            String catalogStatus = scanner.nextLine();
            if (setCatalogStatus(catalogStatus)) {
                break;
            }
            System.out.println("Vui long nhap lai");
        }

    }
    private boolean isValidCatalogName(String name, Categories[] arrCategories, int index) {
        if (name.length() <= 50) {
            for (int i = 0; i < index; i++) {
                if (arrCategories[i] != null && arrCategories[i].getCatalogName().equalsIgnoreCase(name)) {
                    return false; // Tên danh mục đã tồn tại (kiểm tra không phân biệt hoa thường)
                }
            }
            return true; // Tên danh mục hợp lệ và không trùng lặp
        }
        return false; // Tên danh mục không hợp lệ
    }
    public void displayData() {
        System.out.println("Mã danh mục: " + catalogId);
        System.out.println("Tên danh mục: " + catalogName);
        System.out.println("Mô tả danh mục: " + descriptions);
        System.out.println("Trạng thái danh mục: " + catalogStatus);
    }


}
