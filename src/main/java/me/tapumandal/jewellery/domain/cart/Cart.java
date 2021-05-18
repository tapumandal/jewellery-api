package me.tapumandal.jewellery.domain.cart;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @Column(name = "user_id")
    protected int userId;

    @Column(name = "name")
    protected String name;

    @Column(name = "mobile_number")
    protected String mobileNo;

    @Column(name = "area")
    protected String area;

    @Column(name = "address")
    protected String address;


    @Column(name = "delivery_charge")
    protected int deliveryCharge;

    @Column(name = "default_discount_btn")
    protected String defaultDiscountBtn; // radioOnProduct/radioSpecialOffer

    @Column(name = "selected_discount_name")
    protected String selectedDiscountName; //On Product/Special Discount/Mobile Payment/Card Payment;

    @Column(name = "selected_discount_type")
    protected String selectedDiscountType; // TotalPercentage/OverallAmount

    @Column(name = "selected_discount_details", length = 500)
    protected String selectedDiscountDetails;

    @Column(name = "total_product_discount")
    protected int totalProductDiscount;

    @Column(name = "total_product_quantity")
    protected int totalProductQuantity;

    @Column(name = "total_product_price")
    protected int totalProductPrice ;

    @Column(name = "total_discount")
    protected int totalDiscount;

    @Column(name = "total_payable")
    protected int totalPayable;


    @Column(name = "is_active", columnDefinition = "boolean default 1")
    private boolean isActive = true;

    @Column(name = "is_deleted", columnDefinition = "boolean default 0")
    private boolean isDeleted = false;

    @Column(name = "created_at", updatable=false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    protected List<CartProduct> productList;


    @Column(name = "status")
    protected String status = "Pending";

    @Column(name = "invoice_no")
    protected String invoiceNo = "";


    @Column(name = "transaction_id")
    protected String transactionId = "";

    @Column(name = "payment_method")
    protected String paymentMethod = "";



//    @OneToMany(mappedBy = "cart")
//    protected List<CartProduct> cartProducts;

    public Cart() {
    }

    public Cart(CartDto cartDto){

        this.id = cartDto.getId();
        this.userId = cartDto.getUserId();
        this.name = cartDto.getName();
        this.mobileNo = cartDto.getMobileNo();
        this.area = cartDto.getArea();
        this.address = cartDto.getAddress();
        this.deliveryCharge = cartDto.getDeliveryCharge();
        this.defaultDiscountBtn = cartDto.getDefaultDiscountBtn();
        this.selectedDiscountName = cartDto.getSelectedDiscountName();
        this.selectedDiscountType = cartDto.getSelectedDiscountType();
        this.selectedDiscountDetails = cartDto.getSelectedDiscountDetails();
        this.totalProductDiscount = cartDto.getTotalProductDiscount();
        this.totalProductQuantity = cartDto.getTotalProductQuantity();
        this.totalProductPrice = cartDto.getTotalProductPrice();
        this.totalDiscount = cartDto.getTotalDiscount();
        this.totalPayable = cartDto.getTotalPayable();
        this.transactionId = cartDto.getTransactionId();
        this.paymentMethod = cartDto.getPaymentMethod();

        this.productList = new ArrayList<CartProduct>();
        if(cartDto.getProductList() != null) {
            for (CartProductDto cartDtoTmp : cartDto.getProductList()) {
                CartProduct cartPro = new CartProduct(cartDtoTmp);
                this.productList.add(cartPro);
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo == null ? "" : mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getArea() {
        return area == null ? "" : area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address == null ? "" : address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(int deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public String getDefaultDiscountBtn() {
        return defaultDiscountBtn == null ? "" : defaultDiscountBtn;
    }

    public void setDefaultDiscountBtn(String defaultDiscountBtn) {
        this.defaultDiscountBtn = defaultDiscountBtn;
    }

    public String getSelectedDiscountName() {
        return selectedDiscountName == null ? "" : selectedDiscountName;
    }

    public void setSelectedDiscountName(String selectedDiscountName) {
        this.selectedDiscountName = selectedDiscountName;
    }

    public String getSelectedDiscountType() {
        return selectedDiscountType == null ? "" : selectedDiscountType;
    }

    public void setSelectedDiscountType(String selectedDiscountType) {
        this.selectedDiscountType = selectedDiscountType;
    }

    public String getSelectedDiscountDetails() {
        return selectedDiscountDetails == null ? "" : selectedDiscountDetails;
    }

    public void setSelectedDiscountDetails(String selectedDiscountDetails) {
        this.selectedDiscountDetails = selectedDiscountDetails;
    }

    public int getTotalProductDiscount() {
        return totalProductDiscount;
    }

    public void setTotalProductDiscount(int totalProductDiscount) {
        this.totalProductDiscount = totalProductDiscount;
    }

    public int getTotalProductQuantity() {
        return totalProductQuantity;
    }

    public void setTotalProductQuantity(int totalProductQuantity) {
        this.totalProductQuantity = totalProductQuantity;
    }

    public int getTotalProductPrice() {
        return totalProductPrice;
    }

    public void setTotalProductPrice(int totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
    }

    public int getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(int totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public int getTotalPayable() {
        return totalPayable;
    }

    public void setTotalPayable(int totalPayable) {
        this.totalPayable = totalPayable;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<CartProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<CartProduct> productList) {
        this.productList = productList;
    }

    public String getStatus() {
        return status == null ? "" : status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInvoiceNo() {
        return invoiceNo == null ? "" : invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getTransactionId() {
        return transactionId == null ? "" : transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPaymentMethod() {
        return paymentMethod == null ? "" : paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
