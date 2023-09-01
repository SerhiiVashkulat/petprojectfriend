package ua.shapoval.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
@ToString(exclude = "id")
@Table(name = "confirmation_token")
@Builder
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id")
    private UUID id;

    @Column(name="verification_token")
    private String verificationToken;

    @CreationTimestamp
    private LocalDateTime createToken;
    @Column(name = "expire")
    private LocalDateTime expireToken;

    @Column(nullable = false)
    private boolean sentToCustomer;

    @OneToOne
    @JoinColumn(name = "customers_id", nullable = false)
    private Customer customer;


    @PrePersist
       private void set(){
        this.expireToken = createToken.plusHours(24);
    }


}
