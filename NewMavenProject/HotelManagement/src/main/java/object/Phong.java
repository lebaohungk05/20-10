package object;

/**
 * Class Phong: Quản lý thông tin phòng khách sạn.
 */
public class Phong {
    private String maPhong;
    private String loaiPhong;
    private double gia;
    private String trangThai;
    private int soNguoiToiDa;
    private String tienNghi;
    private double danhGia;

    /**
     * Constructor khởi tạo phòng.
     * @param maPhong Mã phòng duy nhất.
     * @param loaiPhong Loại phòng (VIP, Thường, Suite,...).
     * @param gia Giá thuê phòng theo đêm.
     * @param trangThai Trạng thái phòng (Trống, Đã đặt, Đang dọn dẹp,...).
     * @param soNguoiToiDa Số người tối đa có thể ở trong phòng.
     * @param tienNghi Danh sách tiện nghi của phòng.
     * @param danhGia Điểm đánh giá trung bình của khách hàng.
     */
    public Phong(String maPhong, String loaiPhong, double gia, String trangThai, int soNguoiToiDa, String tienNghi, double danhGia) {
        this.maPhong = maPhong;
        this.loaiPhong = loaiPhong;
        setGia(gia);
        this.trangThai = trangThai;
        setSoNguoiToiDa(soNguoiToiDa);
        this.tienNghi = tienNghi;
        setDanhGia(danhGia);
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(String loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    public double getGia() {
        return gia;
    }

    /**
     * Đặt giá phòng, không cho phép giá trị âm.
     * @param gia Giá phòng theo đêm.
     * @throws IllegalArgumentException nếu giá trị âm.
     */
    public void setGia(double gia) {
        if (gia >= 0) {
            this.gia = gia;
        } else {
            throw new IllegalArgumentException("Giá phòng không thể âm!");
        }
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getSoNguoiToiDa() {
        return soNguoiToiDa;
    }

    /**
     * @param soNguoiToiDa Số người tối đa.
     * @throws IllegalArgumentException nếu nhỏ hơn hoặc bằng 0.
     */
    public void setSoNguoiToiDa(int soNguoiToiDa) {
        if (soNguoiToiDa > 0) {
            this.soNguoiToiDa = soNguoiToiDa;
        } else {
            throw new IllegalArgumentException("Số người tối đa phải lớn hơn 0!");
        }
    }

    public String getTienNghi() {
        return tienNghi;
    }

    public void setTienNghi(String tienNghi) {
        this.tienNghi = tienNghi;
    }

    public double getDanhGia() {
        return danhGia;
    }

    /**

     * @param danhGia Điểm đánh giá từ khách hàng.
     * @throws IllegalArgumentException nếu ngoài khoảng 0-5.
     */
    public void setDanhGia(double danhGia) {
        if (danhGia >= 0 && danhGia <= 5) {
            this.danhGia = danhGia;
        } else {
            throw new IllegalArgumentException("Điểm đánh giá phải từ 0 đến 5!");
        }
    }

    @Override
    public String toString() {
        return "Phong{" + "maPhong='" + maPhong + '\'' + ", loaiPhong='" + loaiPhong + '\'' + ", gia=" + gia +
                ", trangThai='" + trangThai + '\'' + ", soNguoiToiDa=" + soNguoiToiDa + ", tienNghi='" + tienNghi + '\'' +
                ", danhGia=" + danhGia + '}';
    }
}
