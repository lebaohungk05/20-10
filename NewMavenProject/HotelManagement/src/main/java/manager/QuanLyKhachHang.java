package manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import object.KhachHang;

/**
 * Class QuanLyKhachHang: Quản lý danh sách khách hàng trong khách sạn.
 */
public class QuanLyKhachHang {
    private final List<KhachHang> danhSachKhachHang;

    /**
     * Constructor khởi tạo danh sách khách hàng.
     */
    public QuanLyKhachHang() {
        danhSachKhachHang = new ArrayList<>();
    }

    /**
     * Thêm khách hàng mới vào danh sách.
     * @param khachHang Khách hàng cần thêm.
     */
    public void themKhachHang(KhachHang khachHang) {
        danhSachKhachHang.add(khachHang);
        System.out.println("Đã thêm khách hàng: " + khachHang.getHoTen());
    }

    /**
     * Sửa thông tin khách hàng theo mã khách hàng.
     * @param maKH Mã khách hàng cần sửa.
     * @param khachHangMoi Thông tin khách hàng mới.
     * @return true nếu cập nhật thành công, false nếu không tìm thấy khách hàng.
     */
    public boolean suaKhachHang(String maKH, KhachHang khachHangMoi) {
        for (KhachHang khachHang : danhSachKhachHang) {
            if (khachHang.getMaKH().equals(maKH)) {
                khachHang.setHoTen(khachHangMoi.getHoTen());
                khachHang.setSoCMTND(khachHangMoi.getSoCMTND());
                khachHang.setSoDienThoai(khachHangMoi.getSoDienThoai());
                khachHang.setEmail(khachHangMoi.getEmail());
                khachHang.setDiaChi(khachHangMoi.getDiaChi());
                khachHang.setDiemTichLuy(khachHangMoi.getDiemTichLuy());
                System.out.println("Cập nhật khách hàng " + maKH + " thành công.");
                return true;
            }
        }
        System.out.println("Không tìm thấy khách hàng có mã: " + maKH);
        return false;
    }

    /**
     * Xóa khách hàng khỏi danh sách.
     * @param maKH Mã khách hàng cần xóa.
     * @return true nếu xóa thành công, false nếu không tìm thấy khách hàng.
     */
    public boolean xoaKhachHang(String maKH) {
        boolean removed = danhSachKhachHang.removeIf(khachHang -> khachHang.getMaKH().equals(maKH));
        if (removed) {
            System.out.println("Đã xóa khách hàng: " + maKH);
        } else {
            System.out.println("Không tìm thấy khách hàng có mã: " + maKH);
        }
        return removed;
    }

    /**
     * Tìm kiếm khách hàng theo mã khách hàng.
     * @param maKH Mã khách hàng cần tìm.
     * @return Optional chứa khách hàng nếu tìm thấy, ngược lại rỗng.
     */
    public Optional<KhachHang> timKhachHang(String maKH) {
        return danhSachKhachHang.stream()
                .filter(khachHang -> khachHang.getMaKH().equals(maKH))
                .findFirst();
    }

    /**
     * Hiển thị danh sách tất cả khách hàng.
     */
    public void hienThiDanhSachKhachHang() {
        if (danhSachKhachHang.isEmpty()) {
            System.out.println("Danh sách khách hàng trống.");
            return;
        }
        System.out.println("Danh sách khách hàng:");
        for (KhachHang khachHang : danhSachKhachHang) {
            System.out.println("Mã KH: " + khachHang.getMaKH() +
                    " | Họ tên: " + khachHang.getHoTen() +
                    " | Số CMTND: " + khachHang.getSoCMTND() +
                    " | SĐT: " + khachHang.getSoDienThoai() +
                    " | Email: " + khachHang.getEmail() +
                    " | Địa chỉ: " + khachHang.getDiaChi() +
                    " | Điểm tích lũy: " + khachHang.getDiemTichLuy());
        }
    }
}
