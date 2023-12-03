package ra.run;

import ra.entity.Categories;
import ra.entity.Product;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ShopManagement {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Categories[] arrCategories = new Categories[100];
        Product[] arrProducts = new Product[100];

        int categoryIndex = 0;
        int productIndex = 0;

        int choice;
        do {
            System.out.println("******************SHOP MENU*******************");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.println("Vui lòng nhập lựa chọn của bạn");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    categoryManagementMenu(scanner, arrCategories, categoryIndex);
                    break;
                case 2:
                    productManagementMenu(scanner, arrProducts, productIndex, arrCategories);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
            }
        } while (true);
    }

    private static void categoryManagementMenu(Scanner scanner, Categories[] arrCategories, int categoryIndex) {
        int choice;
        boolean isExit = true;

        do {
            System.out.println("********************CATEGORIES MENU***********");
            System.out.println("1. Nhập thông tin các danh mục");
            System.out.println("2. Hiển thị thông tin các danh mục");
            System.out.println("3. Cập nhật thông tin danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Cập nhật trạng thái danh mục");
            System.out.println("6. Thoát");
            System.out.println("Vui lòng nhập lựa chọn của bạn");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Nhập vào số lượng danh mục cần thêm:");
                    int numberOfCategories = Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < numberOfCategories; i++) {
                        arrCategories[categoryIndex] = new Categories();
                        arrCategories[categoryIndex].inputData(scanner, arrCategories, categoryIndex);
                        categoryIndex++;
                    }
                    break;
                case 2:
                    // Hiển thị thông tin các danh mục
                    for (int i = 0; i < categoryIndex; i++) {
                        arrCategories[i].displayData();
                        System.out.println("---------------------------");
                    }
                    break;
                case 3:
                    // Cập nhật thông tin danh mục
                    System.out.print("Nhập mã danh mục cần cập nhật thông tin: ");
                    int updateCategoryId = scanner.nextInt();
                    scanner.nextLine();
                    boolean categoryFound = false;
                    for (int i = 0; i < categoryIndex; i++) {
                        if (arrCategories[i].getCatalogId() == updateCategoryId) {
                            arrCategories[i].inputData(scanner, arrCategories, i);
                            categoryFound = true;
                            break;
                        }
                    }
                    if (!categoryFound) {
                        System.out.println("Không tìm thấy danh mục có mã " + updateCategoryId);
                    }
                    break;
                case 4:
                    // Xóa danh mục
                    System.out.print("Nhập mã danh mục cần xóa: ");
                    int deleteCategoryId = scanner.nextInt();
                    scanner.nextLine();
                    categoryFound = false;
                    for (int i = 0; i < categoryIndex; i++) {
                        if (arrCategories[i].getCatalogId() == deleteCategoryId) {
                            for (int j = i; j < categoryIndex - 1; j++) {
                                arrCategories[j] = arrCategories[j + 1];
                            }
                            categoryIndex--;
                            categoryFound = true;
                            System.out.println("Đã xóa danh mục có mã " + deleteCategoryId);
                            break;
                        }
                    }
                    if (!categoryFound) {
                        System.out.println("Không tìm thấy danh mục có mã " + deleteCategoryId);
                    }
                    break;
                case 5:
                    // Cập nhật trạng thái danh mục
                    System.out.print("Nhập mã danh mục cần cập nhật trạng thái: ");
                    int updateStatusCategoryId = scanner.nextInt();
                    scanner.nextLine();
                    categoryFound = false;
                    for (int i = 0; i < categoryIndex; i++) {
                        if (arrCategories[i].getCatalogId() == updateStatusCategoryId) {
                            System.out.println("Chọn trạng thái mới (true - hoạt động, false - không hoạt động):");
                            String newStatus = scanner.nextLine();
                            arrCategories[i].setCatalogStatus(newStatus);
                            categoryFound = true;
                            System.out.println("Đã cập nhật trạng thái cho danh mục có mã " + updateStatusCategoryId);
                            break;
                        }
                    }
                    if (!categoryFound) {
                        System.out.println("Không tìm thấy danh mục có mã " + updateStatusCategoryId);
                    }
                    break;
                case 6:
                    isExit = false;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                    break;
            }
        } while (isExit);
    }


    private static void productManagementMenu(Scanner scanner, Product[] arrProducts, int productIndex, Categories[] arrCategories) {
        int choice;
        boolean isExit = true;

        do {
            System.out.println("*******************PRODUCT MANAGEMENT*****************");
            System.out.println("1. Nhập thông tin các sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Sắp xếp các sản phẩm theo giá");
            System.out.println("4. Cập nhật thông tin sản phẩm theo mã sản phẩm");
            System.out.println("5. Xóa sản phẩm theo mã sản phẩm");
            System.out.println("6. Tìm kiếm các sản phẩm theo tên sản phẩm");
            System.out.println("7. Tìm kiếm sản phẩm trong khoảng giá a – b");
            System.out.println("8. Thoát");
            System.out.println("Vui lòng nhập lựa chọn của bạn! ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nhập số lượng sản phẩm cần thêm: ");
                    int numberOfProducts = scanner.nextInt();
                    scanner.nextLine();
                    for (int i = 0; i < numberOfProducts; i++) {
                        arrProducts[productIndex] = new Product();
                        arrProducts[productIndex].inputData(scanner, arrProducts, productIndex, arrCategories);
                        productIndex++;
                    }
                    break;
                case 2:
                    // Hiển thị thông tin các sản phẩm
                    for (int i = 0; i < productIndex; i++) {
                        arrProducts[i].displayData();
                        System.out.println("---------------------------");
                    }
                    break;
                case 3:
                    // Sắp xếp sản phẩm theo giá
                    Arrays.sort(arrProducts, 0, productIndex, Comparator.comparing(Product::getPrice));
                    System.out.println("Đã sắp xếp sản phẩm theo giá.");
                    break;
                case 4:
                    // Cập nhật sản phẩm theo giá
                    System.out.print("Nhập mã sản phẩm cần cập nhật trạng thái: ");
                    int updateProductPrice = scanner.nextInt();
                    scanner.nextLine();
                    boolean productFound = false;
                    for (int i = 0; i < productIndex; i++) {
                        if (arrCategories[i].getCatalogId() == updateProductPrice) {
                            System.out.println("Chọn trạng thái mới (true - hoạt động, false - không hoạt động):");
                            String newStatus = scanner.nextLine();
                            arrCategories[i].setCatalogStatus(newStatus);
                            productFound = true;
                            System.out.println("Đã cập nhật trạng thái cho danh mục có mã " + updateProductPrice);
                            break;
                        }
                    }
                    if (!productFound) {
                        System.out.println("Không tìm thấy danh mục có mã " + updateProductPrice);
                    }
                    break;

                case 5:
                    // Xóa danh mục
                    System.out.print("Nhập mã danh mục cần xóa: ");
                    int deleteCategoryId = scanner.nextInt();
                    scanner.nextLine();
                    boolean productFound2 = false;
                    for (int i = 0; i < productIndex; i++) {
                        if (arrCategories[i].getCatalogId() == deleteCategoryId) {
                            for (int j = i; j < productIndex - 1; j++) {
                                arrCategories[j] = arrCategories[j + 1];
                            }
                            productIndex--;
                            productFound2 = true;
                            System.out.println("Đã xóa danh mục có mã " + deleteCategoryId);
                            break;
                        }
                    }
                    if (!productFound2) {
                        System.out.println("Không tìm thấy danh mục có mã " + deleteCategoryId);
                    }
                    break;
                case 6:
                    // Tìm kiếm sản phẩm theo tên sản phẩm
                    System.out.print("Nhập tên sản phẩm cần tìm kiếm: ");
                    String searchProductName = scanner.nextLine();
                    boolean productFoundByName = false;
                    for (int i = 0; i < productIndex; i++) {
                        if (arrProducts[i].getProductName().toLowerCase().contains(searchProductName.toLowerCase())) {
                            arrProducts[i].displayData();
                            System.out.println("---------------------------");
                            productFoundByName = true;
                        }
                    }
                    if (!productFoundByName) {
                        System.out.println("Không tìm thấy sản phẩm có tên chứa '" + searchProductName + "'");
                    }
                    break;
                case 7:
                    // Tìm kiếm sản phẩm trong khoảng giá a - b
                    System.out.print("Nhập giá a: ");
                    float priceA = scanner.nextFloat();
                    System.out.print("Nhập giá b: ");
                    float priceB = scanner.nextFloat();
                    boolean productFoundByPriceRange = false;
                    for (int i = 0; i < productIndex; i++) {
                        if (arrProducts[i].getPrice() >= priceA && arrProducts[i].getPrice() <= priceB) {
                            arrProducts[i].displayData();
                            System.out.println("---------------------------");
                            productFoundByPriceRange = true;
                        }
                    }
                    if (!productFoundByPriceRange) {
                        System.out.println("Không tìm thấy sản phẩm trong khoảng giá từ " + priceA + " đến " + priceB);
                    }
                    break;
                case 8:
                    isExit = false;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
            }
        } while (isExit);

    }
}
