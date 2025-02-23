package object;

import java.util.Date;

/**
 * Class KhachHang: Chứa thông tin khách hàng.
 */
public class KhachHang {
    private String maKH;
    private String hoTen;
    private String soCMTND;
    private String soDienThoai;
    private String email;
    private String diaChi;
    private int diemTichLuy;
    private Date ngayDangKy;

    /**
     * Constructor khởi tạo thông tin khách hàng.
     * @param maKH Mã khách hàng.
     * @param hoTen Họ tên khách hàng.
     * @param soCMTND Số CMTND/CCCD.
     * @param soDienThoai Số điện thoại liên hệ.
     * @param email Địa chỉ email.
     * @param diaChi Địa chỉ khách hàng.
     * @param ngayDangKy Ngày đăng ký tài khoản.
     */
    public KhachHang(String maKH, String hoTen, String soCMTND, String soDienThoai, String email, String diaChi, Date ngayDangKy) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.soCMTND = soCMTND;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.diaChi = diaChi;
        this.diemTichLuy = 0; // Khởi tạo điểm tích lũy bằng 0.
        this.ngayDangKy = ngayDangKy;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoCMTND() {
        return soCMTND;
    }

    public void setSoCMTND(String soCMTND) {
        this.soCMTND = soCMTND;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getDiemTichLuy() {
        return diemTichLuy;
    }

    public void setDiemTichLuy(int diemTichLuy) {
        if (diemTichLuy >= 0) {
            this.diemTichLuy = diemTichLuy;
        } else {
            throw new IllegalArgumentException("Điểm tích lũy không thể âm!");
        }
    }

    public Date getNgayDangKy() {
        return ngayDangKy;
    }

    public void setNgayDangKy(Date ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }

    @Override
    public String toString() {
        return "KhachHang{" + "maKH='" + maKH + '\'' + ", hoTen='" + hoTen + '\'' + ", soCMTND='" + soCMTND + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' + ", email='" + email + '\'' + ", diaChi='" + diaChi + '\'' +
                ", diemTichLuy=" + diemTichLuy + ", ngayDangKy=" + ngayDangKy + '}';
    }
}
