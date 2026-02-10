package Tileproject.model;

import jakarta.persistence.*;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false, unique=true)
    private User user;

    @OneToMany(
        mappedBy = "cart",
        cascade = CascadeType.ALL,
        fetch = FetchType.EAGER,
        orphanRemoval = true
    )
    @JsonManagedReference
    private List<CartItem> items = new ArrayList<>();

    // ✅ THIS is where address goes
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address selectedAddress;

    public Address getSelectedAddress() {
        return selectedAddress;
    }
    public void setSelectedAddress(Address selectedAddress) {
        this.selectedAddress = selectedAddress;
    }



    public Integer getCartId() { return cartId; }
    public void setCartId(Integer cartId) { this.cartId = cartId; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<CartItem> getItems() { return items; }
    public void setItems(List<CartItem> items) { this.items = items; }
}
