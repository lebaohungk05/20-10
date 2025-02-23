package ui;

import java.sql.Date;

import database.DatabaseConnection;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import object.DatPhong;
import object.KhachHang;
import object.Phong;

public class HotelManagementApp extends Application {

    private ObservableList<Phong> phongData;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Quản Lý Khách Sạn");

        phongData = FXCollections.observableArrayList(DatabaseConnection.layDanhSachPhongTrong());

        TabPane tabPane = new TabPane();

        Tab tabRoomList = new Tab("Danh sách phòng");
        tabRoomList.setContent(createRoomListPane());
        tabRoomList.setClosable(false);

        Tab tabBooking = new Tab("Đặt phòng");
        tabBooking.setContent(createBookingPane());
        tabBooking.setClosable(false);

        Tab tabBookingList = new Tab("Danh sách đặt phòng");
        tabBookingList.setContent(createBookingListPane());
        tabBookingList.setClosable(false);

        Tab tabCustomerSearch = new Tab("Tìm kiếm khách hàng");
        tabCustomerSearch.setContent(createCustomerSearchPane());
        tabCustomerSearch.setClosable(false);

        Tab tabReport = new Tab("Báo cáo");
        tabReport.setContent(createReportPane());
        tabReport.setClosable(false);

        tabPane.getTabs().addAll(tabRoomList, tabBooking, tabBookingList, tabCustomerSearch, tabReport);

        Scene scene = new Scene(tabPane, 1000, 800); // Increased scene size
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Tạo pane cho danh sách phòng
    private VBox createRoomListPane() {
        ObservableList<Phong> phongData = FXCollections.observableArrayList(DatabaseConnection.layDanhSachPhongTrong());
        TableView<Phong> tableView = new TableView<>();

        TableColumn<Phong, String> colMaPhong = new TableColumn<>("Mã phòng");
        colMaPhong.setCellValueFactory(new PropertyValueFactory<>("maPhong"));

        TableColumn<Phong, String> colLoaiPhong = new TableColumn<>("Loại phòng");
        colLoaiPhong.setCellValueFactory(new PropertyValueFactory<>("loaiPhong"));

        TableColumn<Phong, Number> colGia = new TableColumn<>("Giá");
        colGia.setCellValueFactory(new PropertyValueFactory<>("gia"));

        TableColumn<Phong, String> colTrangThai = new TableColumn<>("Trạng thái");
        colTrangThai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));

        TableColumn<Phong, Number> colSoNguoi = new TableColumn<>("Số người tối đa");
        colSoNguoi.setCellValueFactory(new PropertyValueFactory<>("soNguoiToiDa"));

        TableColumn<Phong, String> colTienNghi = new TableColumn<>("Tiện nghi");
        colTienNghi.setCellValueFactory(new PropertyValueFactory<>("tienNghi"));

        TableColumn<Phong, Number> colDanhGia = new TableColumn<>("Đánh giá");
        colDanhGia.setCellValueFactory(new PropertyValueFactory<>("danhGia"));

        tableView.getColumns().addAll(colMaPhong, colLoaiPhong, colGia, colTrangThai, colSoNguoi, colTienNghi, colDanhGia);
        tableView.setItems(phongData);

        VBox vbox = new VBox(10, tableView);
        vbox.setPadding(new Insets(10));
        return vbox;
    }

    // Tạo pane cho đặt phòng
    private VBox createBookingPane() {
        Label lblKhachHang = new Label("Chọn Khách Hàng:");
        ComboBox<KhachHang> cbKhachHang = new ComboBox<>();
        cbKhachHang.setItems(FXCollections.observableArrayList(DatabaseConnection.layDanhSachKhachHang()));

        Label lblPhong = new Label("Chọn Phòng:");
        ComboBox<Phong> cbPhong = new ComboBox<>();
        cbPhong.setItems(FXCollections.observableArrayList(DatabaseConnection.layDanhSachPhongTrong()));

        Label lblNgayNhan = new Label("Ngày Nhận (YYYY-MM-DD):");
        TextField txtNgayNhan = new TextField();

        Label lblNgayTra = new Label("Ngày Trả (YYYY-MM-DD):");
        TextField txtNgayTra = new TextField();

        Label lblTongTien = new Label("Tổng Tiền:");
        TextField txtTongTien = new TextField();

        Button btnDatPhong = new Button("Đặt Phòng");
        Label lblStatus = new Label();

        btnDatPhong.setOnAction(e -> {
            KhachHang kh = cbKhachHang.getValue();
            Phong phong = cbPhong.getValue();
            String ngayNhan = txtNgayNhan.getText();
            String ngayTra = txtNgayTra.getText();

            if (kh == null || phong == null) {
                lblStatus.setText("Vui lòng chọn khách hàng và phòng!");
                return;
            }

            try {
                int tongTien = Integer.parseInt(txtTongTien.getText());
                boolean success = DatabaseConnection.datPhong(kh.getMaKH(), phong.getMaPhong(), ngayNhan, ngayTra, tongTien);
                if (success) {
                    lblStatus.setText("Đặt phòng thành công!");
                    cbPhong.setItems(FXCollections.observableArrayList(DatabaseConnection.layDanhSachPhongTrong()));
                    cbKhachHang.setItems(FXCollections.observableArrayList(DatabaseConnection.layDanhSachKhachHang()));
                } else {
                    lblStatus.setText("Đặt phòng thất bại!");
                }
            } catch (NumberFormatException ex) {
                lblStatus.setText("Vui lòng nhập số hợp lệ cho tổng tiền!");
            }
        });

        VBox vbox = new VBox(10, lblKhachHang, cbKhachHang, lblPhong, cbPhong, lblNgayNhan, txtNgayNhan, lblNgayTra, txtNgayTra, lblTongTien, txtTongTien, btnDatPhong, lblStatus);
        vbox.setPadding(new Insets(10));
        return vbox;
    }

    // Tạo pane cho danh sách đặt phòng
    private VBox createBookingListPane() {
        TableView<DatPhong> tableView = new TableView<>();

        TableColumn<DatPhong, String> colMaDatPhong = new TableColumn<>("Mã Đặt Phòng");
        colMaDatPhong.setCellValueFactory(new PropertyValueFactory<>("maDatPhong"));

        TableColumn<DatPhong, String> colMaKH = new TableColumn<>("Mã Khách Hàng");
        colMaKH.setCellValueFactory(new PropertyValueFactory<>("maKhachHang"));

        TableColumn<DatPhong, String> colMaPhong = new TableColumn<>("Mã Phòng");
        colMaPhong.setCellValueFactory(new PropertyValueFactory<>("maPhong"));

        TableColumn<DatPhong, Date> colNgayNhan = new TableColumn<>("Ngày Nhận");
        colNgayNhan.setCellValueFactory(new PropertyValueFactory<>("ngayNhanPhong"));

        TableColumn<DatPhong, Date> colNgayTra = new TableColumn<>("Ngày Trả");
        colNgayTra.setCellValueFactory(new PropertyValueFactory<>("ngayTraPhong"));

        TableColumn<DatPhong, Double> colTongTien = new TableColumn<>("Tổng Tiền");
        colTongTien.setCellValueFactory(new PropertyValueFactory<>("tongTien"));

        tableView.getColumns().addAll(colMaDatPhong, colMaKH, colMaPhong, colNgayNhan, colNgayTra, colTongTien);
        tableView.setItems(DatabaseConnection.layDanhSachDatPhong());

        VBox vbox = new VBox(10, tableView);
        vbox.setPadding(new Insets(10));
        return vbox;
    }

    // Tạo pane cho tìm kiếm khách hàng
    private VBox createCustomerSearchPane() {
        Label lblSearchName = new Label("Tìm theo tên:");
        TextField txtSearchName = new TextField();
        Button btnSearchName = new Button("Tìm");

        Label lblSearchId = new Label("Tìm theo mã KH:");
        TextField txtSearchId = new TextField();
        Button btnSearchId = new Button("Tìm");

        TableView<KhachHang> tableView = new TableView<>();
        TableColumn<KhachHang, String> colMaKH = new TableColumn<>("Mã KH");
        colMaKH.setCellValueFactory(new PropertyValueFactory<>("maKH"));
        TableColumn<KhachHang, String> colHoTen = new TableColumn<>("Họ Tên");  // Use correct field name
        colHoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen")); // Use correct field name
        TableColumn<KhachHang, String> colSoCMTND = new TableColumn<>("Số CMTND"); //Use correct field name
        colSoCMTND.setCellValueFactory(new PropertyValueFactory<>("soCMTND"));
        TableColumn<KhachHang, String> colSoDienThoai = new TableColumn<>("Số Điện Thoại"); //Use correct field name
        colSoDienThoai.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
        TableColumn<KhachHang, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<KhachHang, String> colDiaChi = new TableColumn<>("Địa Chỉ");
        colDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        TableColumn<KhachHang, Date> colNgayDangKy = new TableColumn<>("Ngày Đăng Ký");
        colNgayDangKy.setCellValueFactory(new PropertyValueFactory<>("ngayDangKy"));

        tableView.getColumns().addAll(colMaKH, colHoTen, colSoCMTND, colSoDienThoai, colEmail, colDiaChi, colNgayDangKy);

        btnSearchName.setOnAction(e -> {
            String name = txtSearchName.getText();
            ObservableList<KhachHang> searchResult = FXCollections.observableArrayList(DatabaseConnection.timKhachHangTheoTen(name));
            tableView.setItems(searchResult);
        });

        btnSearchId.setOnAction(e -> {
            String id = txtSearchId.getText();
            ObservableList<KhachHang> searchResult = FXCollections.observableArrayList(DatabaseConnection.timKhachHangTheoMa(id));
            tableView.setItems(searchResult);
        });

        VBox vbox = new VBox(10,
                new HBox(10, lblSearchName, txtSearchName, btnSearchName),
                new HBox(10, lblSearchId, txtSearchId, btnSearchId),
                tableView);
        vbox.setPadding(new Insets(10));
        return vbox;
    }

    // Tạo pane cho báo cáo
    private VBox createReportPane() {
        Label lblReport = new Label("Chọn loại báo cáo:");
        ComboBox<String> cbReportType = new ComboBox<>();
        cbReportType.getItems().addAll("Doanh thu theo tháng", "Số lượng khách hàng mới");

        Button btnGenerateReport = new Button("Xuất Báo Cáo");
        Label lblReportStatus = new Label();

        // Tạo TableView để hiển thị dữ liệu báo cáo

        TableView<DatabaseConnection.ThongKeDoanhThu> reportTable = new TableView<>();
        final TableView<DatabaseConnection.ThongKeDoanhThu> finalReportTable = reportTable; // Make it effectively final


        btnGenerateReport.setOnAction(e -> {
            String reportType = cbReportType.getValue();
            if (reportType == null) {
                lblReportStatus.setText("Vui lòng chọn loại báo cáo!");
                return;
            }

            // Clear the previous report data
            finalReportTable.getColumns().clear();
            finalReportTable.getItems().clear();

            ObservableList<?> reportData = null;

            // Gọi phương thức tương ứng trong DatabaseConnection dựa trên loại báo cáo
            switch (reportType) {
                case "Doanh thu theo tháng":
                    reportData = DatabaseConnection.generateMonthlyRevenueReport();

                    // Định nghĩa các cột cho TableView
                    TableColumn<DatabaseConnection.ThongKeDoanhThu, String> monthCol = new TableColumn<>("Tháng");
                    monthCol.setCellValueFactory(new PropertyValueFactory<>("thang"));

                    TableColumn<DatabaseConnection.ThongKeDoanhThu, Double> revenueCol = new TableColumn<>("Tổng Doanh Thu");
                    revenueCol.setCellValueFactory(new PropertyValueFactory<>("tongDoanhThu"));

                    finalReportTable.getColumns().addAll(monthCol, revenueCol);
                    finalReportTable.setItems((ObservableList<DatabaseConnection.ThongKeDoanhThu>) reportData); // Ép kiểu về kiểu dữ liệu thích hợp
                    break;
                case "Số lượng khách hàng mới":
                     // Tạo TableView mới cho KhachHang
                    final TableView<KhachHang> reportTableKhachHang = new TableView<>();

                    // Định nghĩa các cột cho TableView KhachHang (sử dụng đúng tên field trong class KhachHang)
                    TableColumn<KhachHang, String> colMaKH = new TableColumn<>("Mã KH");
                    colMaKH.setCellValueFactory(new PropertyValueFactory<>("maKH"));
                    TableColumn<KhachHang, String> colHoTen = new TableColumn<>("Họ Tên");
                    colHoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
                    TableColumn<KhachHang, String> colSoCMTND = new TableColumn<>("Số CMTND");
                    colSoCMTND.setCellValueFactory(new PropertyValueFactory<>("soCMTND"));
                    TableColumn<KhachHang, String> colSoDienThoai = new TableColumn<>("Số Điện Thoại");
                    colSoDienThoai.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
                    TableColumn<KhachHang, String> colEmail = new TableColumn<>("Email");
                    colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
                    TableColumn<KhachHang, String> colDiaChi = new TableColumn<>("Địa Chỉ");
                    colDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
                    TableColumn<KhachHang, Date> colNgayDangKy = new TableColumn<>("Ngày Đăng Ký");
                    colNgayDangKy.setCellValueFactory(new PropertyValueFactory<>("ngayDangKy"));

                    reportTableKhachHang.getColumns().addAll(colMaKH, colHoTen, colSoCMTND, colSoDienThoai, colEmail, colDiaChi, colNgayDangKy);

                    reportData = DatabaseConnection.generateNewCustomerReport();
                    reportTableKhachHang.setItems((ObservableList<KhachHang>) reportData);

                    // Thay thế reportTable cũ bằng reportTableKhachHang trong VBox
                    VBox parent = (VBox) finalReportTable.getParent();
                    int index = parent.getChildren().indexOf(finalReportTable);
                    parent.getChildren().remove(index);
                    parent.getChildren().add(index, reportTableKhachHang);
                    break;
                default:
                    lblReportStatus.setText("Loại báo cáo không hợp lệ!");
            }
            if (reportData != null) {
                lblReportStatus.setText("Báo cáo đã được tạo!");
            }
        });

        VBox vbox = new VBox(10, lblReport, cbReportType, btnGenerateReport, lblReportStatus, finalReportTable);
        vbox.setPadding(new Insets(10));
        return vbox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}