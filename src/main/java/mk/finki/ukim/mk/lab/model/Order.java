package mk.finki.ukim.mk.lab.model;

import javax.persistence.*;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String balloonColor;
    private String balloonSize;
    private String clientName;
    private String clientAddress;
  
    Long orderId;

    public Order(String balloonColor, String balloonSize, String clientName, String clientAddress) {
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
    }

    String getBalloonColor() {
        return balloonColor;
    }

    String getBalloonSizeb() {
        return balloonSize;
    }

    String getClientName() {
        return clientName;
    }

    String getClientAddres() {
        return clientAddress;
    }

    void setBalloonColor(String ballonColor) {
        this.balloonColor = ballonColor;
    }

    void setBalloonSizeb(String balloonSize) {
        this.balloonSize = balloonSize;
    }

    void setClientName(String clientName) {
        this.clientName = clientName;
    }

    void setClientAddres(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    @Override
    public String toString() {
        return this.balloonColor + " " + this.balloonSize + " " + this.clientAddress + " " + this.clientName;
    }

}
